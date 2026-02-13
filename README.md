# 🚀 Tresless Spring Boot Starter

> AI-Powered Exception Handling for Spring Boot  
> Turn stack traces into intelligent, human-readable solutions.

---

## 📌 Overview

📌 Overview

>Tresless is a custom Spring Boot starter that enhances traditional exception handling by integrating AI (Llama 3.2 via Ollama).

Instead of returning raw stack traces or generic error responses, Tresless:

>Captures exceptions globally  
>Analyzes the root cause  
>Generates a short explanation  
>Suggests a possible fix  
>Returns a clean, structured response  
>All automatically.  

Future Enhancements:

>In the future, we will allow users to simply provide the API key of any AI service. This will eliminate the need to run Ollama locally.

Current Setup:

>For now, users need to download a ZIP file, load it into Maven, run mvn clean install
 to install the package in their local Maven repository. After installation, you can start using Tresless by adding the GlobalExceptionHandler Java class (see example below), and you'll be ready to go. Once set up, you won’t need to handle runtime exceptions on your own, making development much faster and more efficient.

---

## ❓ Why Tresless?

In a typical Spring Boot application:

- 404 errors show generic messages
- 500 errors expose confusing stack traces
- Logs are long and difficult to interpret
- Debugging consumes unnecessary time

Traditional error handling tells you **what happened**,  
Tresless tells you:

- ✅ What happened
- ✅ Why it happened
- ✅ How to fix it

---

## 🧠 How It Works

```
Client Request
      ↓
Controller
      ↓
Exception Thrown
      ↓
GlobalExceptionHandler
      ↓
Tresless AI Service
      ↓
Ollama (Llama 3.2)
      ↓
AI Generated Explanation
      ↓
Structured JSON Response
```

---

## ✨ Example

### ❌ Default Spring Boot Response

```
org.springframework.web.HttpRequestMethodNotSupportedException:
Request method 'POST' not supported
```

### ✅ Tresless Response

```json

{
  "message": "No endpoint GET /demo-traceless.",
  "error": "NoHandlerFoundException",
  "AIExplanation": "SUMMARY: No handler found for GET /demo-traceless request.\nCATEGORY: UNKNOWN\nROOT_CAUSE: No matching controller or handler method found for the GET /demo-traceless request.\nFIX: Ensure that a suitable controller or handler method exists for the GET /demo-traceless endpoint. Verify that it is correctly annotated and implemented.\nCONFIDENCE: 0.8"
}
```
### ✅ Tresless Console Log

```
026-02-13T15:14:09.883+05:30  WARN 3126 --- [ghost-share] [nio-8080-exec-3] .m.m.a.ExceptionHandlerExceptionResolver : Resolved [org.springframework.web.servlet.NoHandlerFoundException: No endpoint GET /demo-traceless.]
2026-02-13T15:14:09.915+05:30  WARN 3126 --- [ghost-share] [nio-8080-exec-4] o.s.web.servlet.PageNotFound             : No mapping for GET /favicon.ico
2026-02-13T15:14:14.180+05:30 ERROR 3126 --- [ghost-share] [nio-8080-exec-4] com.tresless.ai.AiErrorExplainer         : 

=====================================================
                TRESLESS ERROR REPORT
-----------------------------------------------------
ERROR_ID        : TRX-1770975849917
EXCEPTION       : NoHandlerFoundException
MESSAGE         : No endpoint GET /favicon.ico.
AI_RESPONSE     :
SUMMARY: Spring Boot application unable to handle favicon requests.
CATEGORY: UNKNOWN
ROOT_CAUSE: The application is not configured to handle favicon requests, as the default favicon handler (e.g., ResourceServer) is not enabled by default.
FIX: Enable favicon handling by adding `@EnableWebMvc` and configuring the favicon location in the application configuration. For example, add `setFaviconRoot("/");` to the `WebMvcConfigurer`.
CONFIDENCE: 0.8
AI_TIME_MS      : 4263
=====================================================



2026-02-13T15:14:14.181+05:30 ERROR 3126 --- [ghost-share] [nio-8080-exec-4] o.b.g.handler.GlobalExceptionHandler     : 
Error occurred: No endpoint GET /favicon.ico.
```

Clean. Simple. Actionable.

---

## 🏗️ Features

- 🔥 Global Exception Handling
- 🤖 AI-Powered Error Explanation
- 🧩 Plug-and-Play Spring Boot Starter
- 📝 Structured JSON Error Responses
- 💡 Suggested Fix Recommendations
- ⚡ Lightweight & Local (Runs with Ollama)

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot 3
- Custom Starter Architecture
- RESTTemplate / WebClient
- Ollama (Local LLM)
- Llama 3.2

---

## ⚙️ Installation

If installed locally:

#### Add this to pom.xml
```xml
<dependency>
    <groupId>com.tresless</groupId>
    <artifactId>tresless-spring-boot-starter</artifactId>
    <version>1.0.0-SNAPSHOT</version>
</dependency>
```

#### Add this to main class
```
@SpringBootApplication(
exclude = {JmxAutoConfiguration.class}
)
```
#### Add this to application.properties
```
tresless.enabled=true
server.port=8080
spring.jmx.enabled=false
spring.mvc.throw-exception-if-no-handler-found=true
spring.web.resources.add-mappings=false
```
#### Create a new class
```
package online.backend.ghostshare.handler;

import com.tresless.ai.AiErrorExplainer;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final AiErrorExplainer aiErrorExplainer;
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    public GlobalExceptionHandler(AiErrorExplainer aiErrorExplainer) {
        this.aiErrorExplainer = aiErrorExplainer;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception , HttpServletRequest request) {
        String message = exception.getMessage();

        String response = aiErrorExplainer.explainError(exception);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "error", exception.getClass().getSimpleName(),
                        "message", exception.getMessage(),
                        "AIExplanation", response
                ));

    }
    
}
```

---

### 2️⃣ Make Sure Ollama is Running

```bash
ollama run llama3.2
```

---

### 3️⃣ Run Your Application

Tresless automatically activates through auto-configuration.



No additional setup required.

---

## 📦 Project Structure

```
tresless-starter/
 ├── config/
 ├── exception/
 ├── service/
 │     └── AiExplanationService
 └── auto-configuration
```

---

## 🎯 Use Cases

- Backend APIs
- Developer Tools
- Learning Platforms
- Internal Debugging Systems
- Production Error Standardization

---

## 🚀 Future Roadmap

- Smart log summarization
- Root cause pattern detection
- Performance anomaly explanation
- AI-based monitoring integration
- Observability layer evolution

---

## 👨‍💻 Author

**Ashish Kumar**  
Java & Spring Boot Developer  
Building AI-powered backend systems.

---

## ⭐ Final Pitch

Tresless transforms Spring Boot errors into intelligent, human-readable solutions using AI — making debugging faster, cleaner, and smarter.