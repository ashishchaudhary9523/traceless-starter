package com.tresless.core.signal;

import java.time.Instant;

public record ExceptionSignal(String name, Throwable exception) implements Signal {
    @Override
    public String getName() {
        return name;
    }


}
