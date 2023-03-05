package com.ondeviceresearch.vendingmachineapi.coins.model;

import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;

public record CoinInsertResponse(int statusCode, String message) implements ApiResponse {
    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
