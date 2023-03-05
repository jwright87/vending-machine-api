package com.ondeviceresearch.vendingmachineapi.drinks;

import com.ondeviceresearch.vendingmachineapi.coins.CoinService;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinList;
import com.ondeviceresearch.vendingmachineapi.config.VendingMachineConfig;
import com.ondeviceresearch.vendingmachineapi.datastore.VendingMachineDataStore;
import com.ondeviceresearch.vendingmachineapi.drinks.model.Drink;
import com.ondeviceresearch.vendingmachineapi.drinks.model.DrinkStockData;
import com.ondeviceresearch.vendingmachineapi.model.InsufficientBalanceException;
import com.ondeviceresearch.vendingmachineapi.model.OutOfStockException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrinksService {


    private VendingMachineDataStore dataStore;

    private VendingMachineConfig vendingMachineConfig;

    private CoinService coinService;


    public DrinksService(VendingMachineDataStore dataStore, VendingMachineConfig vendingMachineConfig,
                         CoinService coinService) {
        this.dataStore = dataStore;
        this.vendingMachineConfig = vendingMachineConfig;
        this.coinService = coinService;
    }

    public List<DrinkStockData> drinkInformation() {
        return vendingMachineConfig.getDrinks().stream()
                .map(drink -> new DrinkStockData(drink, dataStore.isInStock(drink)))
                .toList();
    }

    public CoinList purchaseDrink(Drink drink, CoinList coinsInserted) {
        if (drink.cost() > coinsInserted.sum()) {
            throw new InsufficientBalanceException("current balance is less then the cost of the drink, please insert more coins");
        } else if (!dataStore.isInStock(drink)) {
            throw new OutOfStockException();
        }
        dataStore.removeDrink(drink);
        dataStore.addCoins(coinsInserted);
        return coinService.retrieveChangeInCoins(calculateChange(drink, coinsInserted));
    }

    int drinkStockCount(Drink drink) {
        return dataStore.drinkStockCount(drink);
    }

    private int calculateChange(Drink drink, CoinList coinsInserted) {
        return coinsInserted.sum() - drink.cost();
    }
}
