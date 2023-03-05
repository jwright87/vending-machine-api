package com.ondeviceresearch.vendingmachineapi.drinks.model;

import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;
import lombok.Data;

import java.util.List;

@Data
public class DrinkInfoResponse implements ApiResponse {


    private List<DrinkStockData> drinkInformation;

    private int statusCode;

    private String message;


    public DrinkInfoResponse(List<DrinkStockData> drinkInformation, int statusCode, String message) {
        this.drinkInformation = drinkInformation;
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
