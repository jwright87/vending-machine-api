package com.ondeviceresearch.vendingmachineapi.admin;

import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;

public record AdminResetResponse(String message) implements ApiResponse {

    @Override
    public String getMessage() {
        return message;
    }
}
