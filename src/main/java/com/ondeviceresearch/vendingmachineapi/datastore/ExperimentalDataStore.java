package com.ondeviceresearch.vendingmachineapi.datastore;

import com.ondeviceresearch.vendingmachineapi.config.ConfigData;

public class ExperimentalDataStore extends VendingMachineDataStore {
    public ExperimentalDataStore(ConfigData configData) {
        super(configData);
    }


    /**
     * Throws expections if fails currently - TODO refactor
     *
     * @param coinsInput
     * @param requestedDrink
     */
//    void attemptPurchase(CoinList coinsInput, Drink requestedDrink) {
//        notEnoughBalanceToPayForDrink(coinsInput, requestedDrink);
//        outOfStock(requestedDrink);
//
//        inputUsersCoinsIntoStore(coinsInput);
//        removeDrinkFromStore(requestedDrink);
//        if (userRequiresChange(coinsInput, requestedDrink)) {
//            tryToAddChangeToUserCoinList();
//        }
//    }
//
//    void outOfStock(Drink requestedDrink) {
//        if (drinks().get(requestedDrink) == 0) {
//            throw new RuntimeException(requestedDrink.getName() + " is out of stock");
//        }
//    }
//
//
//    void notEnoughBalanceToPayForDrink(CoinList coinsInput, Drink requestedDrink) {
//        if (coinsInput.sum() < requestedDrink.getCost()) {
//            throw new RuntimeException("Not enough balance - please insert more coins");
//        }
//    }
//
//    void tryToAddChangeToUserCoinList() {
//        throw new RuntimeException("Not Done Yet..");
//    }
//
//    boolean userRequiresChange(CoinList coinsInput, Drink requestedDrink) {
//        var change = coinsInput.sum() - requestedDrink.getCost();
//        if (change > 0) {
//            return true;
//        }
//        return false;
//    }
//
//    void removeDrinkFromStore(Drink requestedDrink) {
//        drinks().put(requestedDrink, drinks().get(requestedDrink) - 1);
//    }
//
//    void inputUsersCoinsIntoStore(CoinList coinsInput) {
//        coinsInput.forEach(coin -> {
//            currency().put(coin, currency().get(coin) + 1);
//            coinsInput.remove(coin);
//        });
//    }
}
