package com.openmatics.testinstrumentation.utils.azure.exception;

/**
 * @author marek.rasocha@inventi.cz
 *         date: 27.02.2019.
 */
public class DestinationHandlerException extends Exception {
    private static final long serialVersionUID = 3620156513007610797L;

    public DestinationHandlerException() {
    }

    public DestinationHandlerException(String message) {
        super(message);
    }

    public DestinationHandlerException(String message, Throwable cause) {
        super(message, cause);
    }

    public DestinationHandlerException(Throwable cause) {
        super(cause);
    }

    public DestinationHandlerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
