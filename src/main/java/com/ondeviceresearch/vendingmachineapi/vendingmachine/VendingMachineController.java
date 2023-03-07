package com.ondeviceresearch.vendingmachineapi.vendingmachine;

import com.ondeviceresearch.vendingmachineapi.coins.CoinService;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinList;
import com.ondeviceresearch.vendingmachineapi.datastore.VendingMachineDataStore;
import com.ondeviceresearch.vendingmachineapi.drinks.DrinksService;
import com.ondeviceresearch.vendingmachineapi.drinks.model.Drink;
import com.ondeviceresearch.vendingmachineapi.drinks.model.DrinkPurchaseResponse;
import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;
import com.ondeviceresearch.vendingmachineapi.model.ChangeUnavailableException;
import com.ondeviceresearch.vendingmachineapi.model.InsufficientBalanceException;
import com.ondeviceresearch.vendingmachineapi.model.OutOfStockException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@AllArgsConstructor
@Controller
public class VendingMachineController {


    private VendingMachineDataStore dataStore;

    private DrinksService drinksService;

    private CoinService coinService;

    @CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
    @PostMapping("/admin/reset")
    public ResponseEntity<ApiResponse> reset() {
        dataStore.reset();
        return ResponseEntity.ok(new ResetResponse("Drink Stock / Coins reset to initial values"));
    }

    @CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
    @PostMapping("/purchase")
    public ResponseEntity<ApiResponse> makePurchase(@RequestBody Drink drink) {
        var  coinsInserted = coinService.getInsertedCoins();
        try {
            CoinList change = drinksService.purchaseDrink(drink, coinsInserted);
            var response = new DrinkPurchaseResponse(drink, change, drink.name() + " Purchased");
            coinService.clearInsertedCoins();
            return ResponseEntity.ok().body(response);
        } catch (InsufficientBalanceException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new DrinkPurchaseResponse(null, coinsInserted,
                    e.getMessage()));
        } catch (OutOfStockException | ChangeUnavailableException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new DrinkPurchaseResponse(null, coinsInserted,
                    e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(new DrinkPurchaseResponse(null, coinsInserted, "An Internal Error Occurred"));
        }
    }
}
