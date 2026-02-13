package com.tresless.core.timeline;

import java.time.Instant;

public record TimelineEvent(Instant timestamp, String description, String source) {
}
