package com.ondeviceresearch.vendingmachineapi.drinks.model;

import com.ondeviceresearch.vendingmachineapi.coins.model.CoinList;
import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;
import lombok.Data;

@Data
public class DrinkPurchaseResponse  implements ApiResponse {


    private Drink drink;

    private CoinList coinList;
    private int statusCode;

    private String message;


    public DrinkPurchaseResponse(Drink drink, CoinList coinList, int statusCode, String message) {
        this.drink = drink;
        this.coinList = coinList;
        this.statusCode = statusCode;
        this.message = message;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
