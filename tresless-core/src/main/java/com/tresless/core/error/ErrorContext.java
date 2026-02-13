package com.tresless.core.error;

public class ErrorContext {

    private final String service;
    private final String operation;
    private final String exceptionType;
    private final String message;

    public ErrorContext(String service, String operation, Throwable ex) {
        this.service = service;
        this.operation = operation;
        this.exceptionType = ex.getClass().getSimpleName();
        this.message = ex.getMessage();
    }

    public String getService() {
        return service;
    }

    public String getOperation() {
        return operation;
    }

    public String getExceptionType() {
        return exceptionType;
    }

    public String getMessage() {
        return message;
    }
}