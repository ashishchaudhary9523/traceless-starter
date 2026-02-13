package com.tresless.core.signal;

import java.time.Instant;

public record MetricSignal(String name, Instant timestamp, double value) implements Signal {
    @Override
    public String getName() {
        return name;
    }


}
