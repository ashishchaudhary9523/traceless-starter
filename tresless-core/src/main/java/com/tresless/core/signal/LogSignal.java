package com.tresless.core.signal;

import java.time.Instant;

public record LogSignal(String name, Instant timestamp, String message, String level) implements Signal {
    @Override
    public String getName() {
        return name;
    }


}
