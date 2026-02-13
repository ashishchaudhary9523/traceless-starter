package com.tresless.ai;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AiErrorExplainer {

    private final RestTemplate restTemplate = new RestTemplate();

    private String buildPrompt(Throwable ex, String errorId) {

        return """
You are a senior Spring Boot production debugging assistant.

Analyze the error and respond STRICTLY in this format:

SUMMARY: (max 2 lines simple explanation)
CATEGORY: (one of: SECURITY, DATABASE, CONFIGURATION, BEAN_WIRING, NULL_POINTER, NETWORK, VALIDATION, UNKNOWN)
ROOT_CAUSE: (actual technical root cause in 1 line)
FIX: (clear actionable fix in 1–2 lines)
CONFIDENCE: (0.0 to 1.0)

Error ID: %s
Exception Type: %s
Message: %s
""".formatted(
                errorId,
                ex.getClass().getSimpleName(),
                ex.getMessage()
        );
    }


    public String explainError(Throwable throwable) {
        try {
            String url = "http://localhost:11434/api/generate";

            String errorId = generateErrorId();

            long start = System.currentTimeMillis();

            Map<String, Object> request = new HashMap<>();
            request.put("model", "llama3.2");
            request.put("prompt", buildPrompt(throwable, errorId));
            request.put("stream", false);

            Map response = restTemplate.postForObject(url, request, Map.class);

            assert response != null;
            String result = response.get("response").toString();

            long end = System.currentTimeMillis();

            logStructuredError(throwable, errorId, result, (end - start));

            return result;

        } catch (Exception e) {
            return "AI unavailable";
        }
    }

    private static final Logger log = LoggerFactory.getLogger(AiErrorExplainer.class);

    private void logStructuredError(Throwable ex,
                                    String errorId,
                                    String aiResponse,
                                    long processingTime) {

        log.error("\n\n" + """
=====================================================
                TRESLESS ERROR REPORT
-----------------------------------------------------
ERROR_ID        : {}
EXCEPTION       : {}
MESSAGE         : {}
AI_RESPONSE     :
{}
AI_TIME_MS      : {}
=====================================================
""" + "\n\n",
                errorId,
                ex.getClass().getSimpleName(),
                ex.getMessage(),
                aiResponse,
                processingTime
        );

        // Optional: print full stack trace AFTER summary
//        log.error("STACKTRACE:", ex);
    }

    private String generateErrorId() {
        return "TRX-" + System.currentTimeMillis();
    }

    //    public String explainError(Throwable ex, String errorId) {
//
//        String prompt = buildPrompt(ex, errorId);
//
//        long start = System.currentTimeMillis();
//
//        String aiResponse = this.generate(prompt); // your llama call
//
//        long end = System.currentTimeMillis();
//
//        logStructuredError(ex, errorId, aiResponse, (end - start));
//
//        return aiResponse;
//    }


//    private String generatePrompt(String errorMessage,
//                                String stackTrace,
//                                String httpMethod,
//                                String endpoint) {
//
//        String prompt = """
//You are a senior Java Spring Boot backend engineer.
//
//Your task is to analyze the following exception and provide a concise developer-friendly response.
//
//Rules:
//- Keep the explanation short (maximum 3 sentences).
//- Be technically accurate.
//- Do not add unnecessary text.
//- Do not apologize.
//- Do not repeat the full stack trace.
//- Focus only on the most likely root cause.
//
//Respond strictly in this format:
//
//Explanation:
//<clear and simple explanation of what caused the error>
//
//Possible Fix:
//- <one practical fix suggestion>
//
//Error Details:
//""" + errorMessage + """
//
//Stack Trace:
//""" + stackTrace + """
//
//HTTP Method:
//""" + httpMethod + """
//
//Endpoint:
//""" + endpoint;
//
//        return prompt; // or whatever your AI call method is
//    }
//
//
//    public String explainError(String errorMessage ,String stackTrace,
//                               String httpMethod,
//                               String endpoint ) {
//        try {
//            String url = "http://localhost:11434/api/generate";
//
//            Map<String, Object> request = new HashMap<>();
//            request.put("model", "llama3.2");
//            request.put("prompt", generatePrompt(errorMessage, "", "", url));
//            request.put("stream", false);
//
//            Map response = restTemplate.postForObject(url, request, Map.class);
//
//            return response != null ? response.get("response").toString() : "No AI response";
//
//        } catch (Exception e) {
//            return "AI unavailable";
//        }
//    }
}