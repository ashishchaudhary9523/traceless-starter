package com.tresless.core.detection;

import java.time.Instant;

public record Failure(String id, String component, String reason, Instant timestamp) {
}
