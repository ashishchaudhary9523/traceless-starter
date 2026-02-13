package com.tresless.core.event;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class EventBus {
    private final List<Consumer<SystemEvent>> subscribers = new ArrayList<>();

    public void subscribe(Consumer<SystemEvent> subscriber) {
        subscribers.add(subscriber);
    }

    public void publish(SystemEvent event) {
        subscribers.forEach(s -> s.accept(event));
    }
}
