package com.ondeviceresearch.vendingmachineapi.coins.model;

import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;

import java.util.List;

public record CoinListResponse(List<Coin> coins, String message) implements ApiResponse {
    @Override
    public String getMessage() {
        return message;
    }
}
