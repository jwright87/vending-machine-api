package com.ondeviceresearch.vendingmachineapi.drinks.model;

import com.ondeviceresearch.vendingmachineapi.coins.model.CoinList;
import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;
import lombok.Data;

@Data
public class DrinkPurchaseResponse implements ApiResponse {


    private Drink drink;

    private CoinList coinList;


    private String message;


    public DrinkPurchaseResponse(Drink drink, CoinList coinList, String message) {
        this.drink = drink;
        this.coinList = coinList;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
