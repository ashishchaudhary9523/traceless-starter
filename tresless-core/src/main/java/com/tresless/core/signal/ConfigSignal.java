package com.tresless.core.signal;

import java.time.Instant;
import java.util.Map;

public record ConfigSignal(String name, Instant timestamp, Map<String, String> config) implements Signal {
    @Override
    public String getName() {
        return name;
    }


}
