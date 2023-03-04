package com.ondeviceresearch.vendingmachineapi.model.http;

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
