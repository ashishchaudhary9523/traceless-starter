package com.tresless.core.detection;

import java.time.Instant;
import java.util.List;

public record Incident(String id, Failure initialFailure, List<Failure> relatedFailures, Instant createdAt) {
}
