package com.tresless.core.store;

import com.tresless.core.signal.ExceptionSignal;

import java.util.List;


public interface SignalStore {
    void save(ExceptionSignal signal);
    List<ExceptionSignal> findAll();
}
