package com.ondeviceresearch.vendingmachineapi.datastore;

public class ChangeUnavailableException extends RuntimeException {


    public ChangeUnavailableException() {
    }

    public ChangeUnavailableException(String message) {
        super(message);
    }

    public ChangeUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
