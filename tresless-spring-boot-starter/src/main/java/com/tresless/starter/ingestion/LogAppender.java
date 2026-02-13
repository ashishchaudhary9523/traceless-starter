package com.tresless.starter.ingestion;

import com.tresless.core.event.EventBus;
import com.tresless.core.event.EventType;
import com.tresless.core.event.SystemEvent;
import com.tresless.core.signal.LogSignal;

import java.time.Instant;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class LogAppender extends Handler {
    private final EventBus eventBus;

    public LogAppender(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void publish(LogRecord record) {
        LogSignal signal = new LogSignal(record.getLoggerName(), Instant.ofEpochMilli(record.getMillis()), record.getMessage(), record.getLevel().getName());
        eventBus.publish(new SystemEvent(EventType.SIGNAL_RECEIVED, Instant.now(), signal));
    }

    @Override
    public void flush() {}

    @Override
    public void close() throws SecurityException {}
}
