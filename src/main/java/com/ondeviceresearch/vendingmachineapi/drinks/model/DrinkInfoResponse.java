package com.ondeviceresearch.vendingmachineapi.drinks.model;

import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;
import lombok.Data;

import java.util.List;

@Data
public class DrinkInfoResponse implements ApiResponse {


    private List<DrinkStockData> drinkInformation;

    private String message;


    public DrinkInfoResponse(List<DrinkStockData> drinkInformation, String message) {
        this.drinkInformation = drinkInformation;
        this.message = message;
    }


    @Override
    public String getMessage() {
        return message;
    }

}
