package com.ondeviceresearch.vendingmachineapi.datastore;

import com.ondeviceresearch.vendingmachineapi.config.ConfigData;
import com.ondeviceresearch.vendingmachineapi.model.basic.Coin;
import com.ondeviceresearch.vendingmachineapi.model.basic.Drink;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Getter
class VendingMachineDataStore {
    private HashMap<Drink, Integer> drinks = new HashMap<>();

    private HashMap<Coin, Integer> coins = new HashMap<>();
    private ConfigData configData;


    public VendingMachineDataStore(ConfigData configData) {
        this.configData = configData;
    }


    /**
     * Resets the data store to original requirement limits;
     */
    public void reset() {
        drinks.clear();
        configData.getDrinks().forEach(drink -> drinks.put(drink, 5));

        coins.clear();
        configData.getCoins().forEach(coin -> coins.put(coin, 5));

    }

    int coinsAvailable(Coin coin) {
        return coins.get(coin);
    }


    int drinksAvailable(Drink drink) {
        return drinks.get(drink);
    }


    public boolean isInStock(Drink drink) {
        return drinksAvailable(drink) > 0;
    }


    /**
     * @param drink
     * @return The stock count after the drink has been removed
     */
    public int removeDrink(Drink drink) {
        if (!isInStock(drink)) {
            throw new OutOfStockException(drink.name() + " is out of stock");
        }
        decreeaseDrinkCount(drink);
        return drinks.get(drink);
    }

    void removeCoin(Coin coin) {
        removeCoins(coin, 1);
    }

    void removeCoins(Coin coin, int number) {
        if (coinsAvailable(coin) < number) {
            throw new ChangeUnavailableException();
        }
        decreaseCoinCount(coin, number);
    }

    private void decreaseCoinCount(Coin coin, int number) {
        coins.put(coin, coins.get(coin) - number);
    }

    private void decreeaseDrinkCount(Drink drink) {
        drinks.put(drink, drinks.get(drink) - 1);
    }

}
