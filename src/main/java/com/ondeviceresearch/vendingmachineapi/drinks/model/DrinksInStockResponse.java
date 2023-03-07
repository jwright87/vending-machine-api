package com.ondeviceresearch.vendingmachineapi.drinks.model;

import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;
import lombok.Data;

import java.util.List;

@Data
public class DrinksInStockResponse implements ApiResponse {


    private List<Drink> drinksInStock;
    private String message;


    public DrinksInStockResponse(List<Drink> drinksInStock, String message) {
        this.drinksInStock = drinksInStock;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
