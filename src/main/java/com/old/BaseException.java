package com.old;

/**
 **/
public class BaseException extends RuntimeException{

    public String defaultMessage;

    public BaseException(String message) {
        super(message);
        this.defaultMessage = message;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
