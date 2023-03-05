package com.ondeviceresearch.vendingmachineapi.drinks;

import com.ondeviceresearch.vendingmachineapi.config.VendingMachineConfig;
import com.ondeviceresearch.vendingmachineapi.model.OutOfStockException;
import com.ondeviceresearch.vendingmachineapi.datastore.VendingMachineDataStore;
import com.ondeviceresearch.vendingmachineapi.drinks.model.Drink;
import com.ondeviceresearch.vendingmachineapi.model.NotEnoughCashException;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinList;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DrinksService {


    private VendingMachineDataStore dataStore;

    private VendingMachineConfig vendingMachineConfig;


    private CoinList balance;


    public DrinksService(VendingMachineDataStore dataStore, VendingMachineConfig vendingMachineConfig,
                         CoinList balance) {
        this.dataStore = dataStore;
        this.vendingMachineConfig = vendingMachineConfig;
        this.balance=balance;
    }

    public Map<Drink, Boolean> drinksInStock() {
        var drinksInStockMap = new HashMap<Drink, Boolean>();
        vendingMachineConfig.getDrinks().forEach(drink -> {
            drinksInStockMap.put(drink, dataStore.isInStock(drink));
        });
        return drinksInStockMap;
    }


    public boolean purchaseDrink(Drink drink) {
        if (drink.cost() > balance.sum()) {
            throw new NotEnoughCashException("curent blaance is less then the cost of the drimk, please insert more coins");
        } else if (!dataStore.isInStock(drink)) {
            throw new OutOfStockException();
        }
        dataStore.removeDrink(drink);
        dataStore.addCoins(balance);
        return true;
    }
}
