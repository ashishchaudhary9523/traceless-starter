package com.tresless.core.explanation;

import com.tresless.core.detection.Incident;
import com.tresless.core.reasoning.ReasoningResult;
import com.tresless.core.timeline.Timeline;

public record IncidentExplanation(Incident incident, Timeline timeline, ReasoningResult reasoning) {
}
