package com.roc.admin.backend.exception;

public class TominException extends RuntimeException {

    public TominException() {
        super();
    }

    public TominException(String message, Throwable cause) {
        super(message, cause);
    }

    public TominException(String message) {
        super(message);
    }

    public TominException(Throwable cause) {
        super(cause);
    }
}