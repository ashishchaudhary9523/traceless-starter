package com.tresless.core.reasoning;

import com.tresless.core.detection.Incident;

public interface ReasoningAgent {
    ReasoningResult reason(Incident incident);
}
