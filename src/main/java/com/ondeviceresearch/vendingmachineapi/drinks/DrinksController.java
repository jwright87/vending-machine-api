package com.ondeviceresearch.vendingmachineapi.drinks;

import com.ondeviceresearch.vendingmachineapi.coins.model.CoinList;
import com.ondeviceresearch.vendingmachineapi.drinks.model.Drink;
import com.ondeviceresearch.vendingmachineapi.drinks.model.DrinkInfoResponse;
import com.ondeviceresearch.vendingmachineapi.drinks.model.DrinkPurchaseResponse;
import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;
import com.ondeviceresearch.vendingmachineapi.model.ChangeUnavailableException;
import com.ondeviceresearch.vendingmachineapi.model.InsufficientBalanceException;
import com.ondeviceresearch.vendingmachineapi.model.OutOfStockException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class DrinksController {

    private DrinksService drinksService;

    private CoinList coinsInserted;

    public DrinksController(DrinksService drinksService, CoinList coinsInserted) {
        this.drinksService = drinksService;
        this.coinsInserted = coinsInserted;
    }

    @GetMapping("/drinks/choices")
    public ResponseEntity<ApiResponse> drinkChoices() {
        var response = new DrinkInfoResponse(drinksService.drinkInformation(), "Drinks Available:");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/drinks/purchase")
    public ResponseEntity<ApiResponse> purchaseDrink(@RequestBody Drink drink) {
        try {
            CoinList change = drinksService.purchaseDrink(drink, coinsInserted);
            var response = new DrinkPurchaseResponse(drink, change, drink.name() + " Purchased");
            return ResponseEntity.ok().body(response);
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.status(400).body(new DrinkPurchaseResponse(null, coinsInserted,
                    e.getMessage()));
        } catch (OutOfStockException | ChangeUnavailableException e) {
            return ResponseEntity.status(422).body(new DrinkPurchaseResponse(null, coinsInserted,
                    e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new DrinkPurchaseResponse(null, coinsInserted, "An Internal Error Occurred"));
        }
    }
}
