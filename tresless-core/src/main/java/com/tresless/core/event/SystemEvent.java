package com.tresless.core.event;

import java.time.Instant;

public record SystemEvent(EventType type, Instant timestamp, Object payload) {
}
