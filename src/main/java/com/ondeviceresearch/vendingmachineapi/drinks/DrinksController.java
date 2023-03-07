package com.ondeviceresearch.vendingmachineapi.drinks;

import com.ondeviceresearch.vendingmachineapi.coins.CoinService;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinList;
import com.ondeviceresearch.vendingmachineapi.config.VendingMachineConfig;
import com.ondeviceresearch.vendingmachineapi.drinks.model.DrinksInStockResponse;
import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@Controller
public class DrinksController {

    private DrinksService drinksService;

    private CoinList coinsInserted;

    private CoinService coinService;

    private VendingMachineConfig config;

    public DrinksController(DrinksService drinksService, CoinList coinsInserted, CoinService coinService, VendingMachineConfig config) {
        this.drinksService = drinksService;
        this.coinsInserted = coinsInserted;
        this.coinService = coinService;
        this.config = config;
    }

    @CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
    @GetMapping("/drinks/choices")
    public ResponseEntity<ApiResponse> drinkChoices() {
        var drinksInStock = drinksService.drinksInStock();
        var response = new DrinksInStockResponse(drinksInStock, drinksInStock.size() + " Drinks Available:");
        return ResponseEntity.ok(response);
    }


}
