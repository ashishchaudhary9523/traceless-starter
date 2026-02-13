package com.tresless.starter.ingestion;


import com.tresless.core.event.EventBus;
import com.tresless.core.event.EventType;
import com.tresless.core.event.ExceptionEvent;
import com.tresless.core.event.SystemEvent;
import com.tresless.core.signal.ExceptionSignal;
import com.tresless.starter.TreslessLifecycle;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import java.time.Instant;


@Aspect
public class ExceptionCaptureAspect {

    private final EventBus eventBus;

    public ExceptionCaptureAspect(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @AfterThrowing(
            pointcut = "within(com..*) && !within(com.tresless..*)",
            throwing = "ex"
    )
    public void capture(Exception ex) {
        ExceptionSignal signal = new ExceptionSignal(
                ex.getClass().getName() ,
                ex
        );
        SystemEvent systemEvent = new SystemEvent(
                EventType.SIGNAL_RECEIVED,
                Instant.now(),
                ex
        );
        eventBus.publish(systemEvent);
    }
}