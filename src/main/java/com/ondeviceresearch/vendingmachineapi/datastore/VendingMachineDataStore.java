package com.ondeviceresearch.vendingmachineapi.datastore;

import com.ondeviceresearch.vendingmachineapi.VisibleForTesting;
import com.ondeviceresearch.vendingmachineapi.config.VendingMachineConfig;
import com.ondeviceresearch.vendingmachineapi.coins.model.Coin;
import com.ondeviceresearch.vendingmachineapi.drinks.model.Drink;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinList;
import com.ondeviceresearch.vendingmachineapi.model.ChangeUnavailableException;
import com.ondeviceresearch.vendingmachineapi.model.OutOfStockException;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Getter
public class VendingMachineDataStore {
    private HashMap<Drink, Integer> drinkStore = new HashMap<>();

    private HashMap<Coin, Integer> coinStore = new HashMap<>();
    private VendingMachineConfig vendingMachineConfig;

    private CoinList insertedCoins;


    public VendingMachineDataStore(VendingMachineConfig vendingMachineConfig, CoinList insertedCoins) {
        this.vendingMachineConfig = vendingMachineConfig;
        this.insertedCoins = insertedCoins;
        reset();
    }


    /**
     * Resets the data store to original requirement limits;
     */
    public void reset() {
        drinkStore.clear();
        vendingMachineConfig.getDrinks().forEach(drink -> drinkStore.put(drink, 5));

        coinStore.clear();
        vendingMachineConfig.getCoins().forEach(coin -> coinStore.put(coin, 5));

        insertedCoins.clear();
    }

    @VisibleForTesting
    int coinsAvailable(Coin coin) {
        return coinStore.get(coin);
    }


    public int drinkStockCount(Drink drink) {
        return drinkStore.get(drink);
    }


    public boolean isInStock(Drink drink) {
        return drinkStockCount(drink) > 0;
    }


    /**
     * @param drink
     * @return The stock count after the drink has been removed
     */
    public int removeDrink(Drink drink) {
        if (!isInStock(drink)) {
            throw new OutOfStockException(drink.name() + " is out of stock");
        }
        decreaseCount(drink);
        return drinkStore.get(drink);
    }


    public void addCoins(CoinList coins) {
        coins.forEach(coin -> addCoin(coin));
    }

    private void addCoin(Coin coin) {
        coinStore.put(coin, coinStore.get(coin) + 1);
    }

    public Coin fetchCoin(Coin coin) {
        fetchCoins(coin, 1);
        return coin;
    }

    public void fetchCoins(Coin coin, int number) {
        if (coinsAvailable(coin) < number) {
            throw new ChangeUnavailableException("Cannot remove %d %s  coins as only %d in store"
                    .formatted(number, coin.name(), coinStore.get(coin)));
        }
        decreaseCount(coin, number);
    }


    private void decreaseCount(Coin coin, int number) {
        coinStore.put(coin, coinStore.get(coin) - number);
    }

    private void decreaseCount(Drink drink) {
        drinkStore.put(drink, drinkStore.get(drink) - 1);
    }

}
