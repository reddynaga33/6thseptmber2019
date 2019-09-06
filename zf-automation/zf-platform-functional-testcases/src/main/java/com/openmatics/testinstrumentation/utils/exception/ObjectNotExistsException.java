package com.openmatics.testinstrumentation.utils.exception;

public class ObjectNotExistsException extends Exception {

    public ObjectNotExistsException(String message){
        super(message);
    }

    public ObjectNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
