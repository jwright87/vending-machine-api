package com.ondeviceresearch.vendingmachineapi.model;

public class InsufficientBalanceException extends ClientException {


    public InsufficientBalanceException(String message) {
        super(message);
    }
}
