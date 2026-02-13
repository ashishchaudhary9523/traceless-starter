package com.tresless.core.timeline;

import java.util.Collections;
import java.util.List;

public record Timeline(List<TimelineEvent> events) {
    public Timeline {
        events = Collections.unmodifiableList(events);
    }
}
