package com.ondeviceresearch.vendingmachineapi.model;

public class NotEnoughCashException extends RuntimeException {


    public NotEnoughCashException(String message) {
        super(message);
    }
}
