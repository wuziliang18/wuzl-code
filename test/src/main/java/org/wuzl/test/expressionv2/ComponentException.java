package org.wuzl.test.expressionv2;

public class ComponentException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final int code;
    private final Exception exception;

    public ComponentException(int code, String message) {
        this(code, message, null);
    }

    public ComponentException(int code, String message, Exception exception) {
        super(message);
        this.code = code;
        this.exception = exception;
    }

    public int getCode() {
        return code;
    }

    public Exception getException() {
        return exception;
    }
}
