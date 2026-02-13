package com.tresless.core.reasoning;

import java.util.List;

public record ReasoningResult(List<Hypothesis> hypotheses, String rootCause) {
}
