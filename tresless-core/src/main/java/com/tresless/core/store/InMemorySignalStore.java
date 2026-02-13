package com.tresless.core.store;

import com.tresless.core.signal.ExceptionSignal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class InMemorySignalStore implements SignalStore {
    private final List<ExceptionSignal> signals = new CopyOnWriteArrayList<>();

    @Override
    public void save(ExceptionSignal signal) {
        signals.add(signal);
    }

    @Override
    public List<ExceptionSignal> findAll() {
        return List.copyOf(signals);
    }
}
