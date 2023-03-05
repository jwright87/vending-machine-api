package com.ondeviceresearch.vendingmachineapi.admin;

import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;

public record AdminResetResponse(int statusCode,String message) implements ApiResponse {
    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
