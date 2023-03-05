package com.ondeviceresearch.vendingmachineapi.drinks.model;

import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;
import lombok.Data;

import java.util.Map;

@Data
public class DrinkApiResponse implements ApiResponse {


    private Map<Drink, Boolean> drinksInStock;

    private int statusCode;

    private String message;


    public DrinkApiResponse(Map<Drink, Boolean> drinkInfromation, int statusCode, String message) {
        this.drinksInStock = drinkInfromation;
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
