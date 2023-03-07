package com.ondeviceresearch.vendingmachineapi.vendingmachine;

import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;

public record ResetResponse(String message) implements ApiResponse {

    @Override
    public String getMessage() {
        return message;
    }
}
