package com.ondeviceresearch.vendingmachineapi.model;

public class OutOfStockException extends RuntimeException {


    public OutOfStockException() {
    }

    public OutOfStockException(String message) {
        super(message);
    }

    public OutOfStockException(String message, Throwable cause) {
        super(message, cause);
    }
}
