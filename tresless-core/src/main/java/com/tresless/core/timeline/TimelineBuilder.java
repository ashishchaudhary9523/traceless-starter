package com.tresless.core.timeline;

import java.util.ArrayList;
import java.util.List;

public class TimelineBuilder {
    private final List<TimelineEvent> events = new ArrayList<>();

    public TimelineBuilder addEvent(TimelineEvent event) {
        this.events.add(event);
        return this;
    }

    public Timeline build() {
        return new Timeline(new ArrayList<>(events));
    }
}
