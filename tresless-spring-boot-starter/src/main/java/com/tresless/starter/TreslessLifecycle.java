package com.tresless.starter;

import com.tresless.core.event.ExceptionEvent;
import com.tresless.core.signal.ExceptionSignal;
import com.tresless.core.store.SignalStore;

public class TreslessLifecycle {

    private final SignalStore signalStore;

    public TreslessLifecycle( SignalStore signalStore) {
        this.signalStore = signalStore;

//        eventBus.subscribe(ExceptionEvent.class, this::onException);
    }

    private void onException(ExceptionEvent event) {
        Exception ex = event.exception();

        ExceptionSignal signal = new ExceptionSignal(
                ex.getClass().getName(),
                ex
        );

        signalStore.save(signal);
    }
}
