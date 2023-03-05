package com.ondeviceresearch.vendingmachineapi.drinks;

import com.ondeviceresearch.vendingmachineapi.drinks.model.Drink;
import com.ondeviceresearch.vendingmachineapi.drinks.model.DrinkApiResponse;
import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DrinksController {

    private DrinksService drinksService;

    public DrinksController(DrinksService drinksService) {
        this.drinksService = drinksService;
    }

    @GetMapping("/drinks/choices")
    public ResponseEntity<ApiResponse> drinkChoices() {
        var response = new DrinkApiResponse(drinksService.drinksInStock(), 200, "Drinks Available:");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/drinks/purchase")
    public ResponseEntity<ApiResponse> purchaseDrink(Drink drink) {
        var response = drinksService.purchaseDrink(drink);
        return null;//TODO
    }
}
