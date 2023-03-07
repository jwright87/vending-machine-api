package com.ondeviceresearch.vendingmachineapi;

import com.ondeviceresearch.vendingmachineapi.coins.CoinService;
import com.ondeviceresearch.vendingmachineapi.coins.model.Coin;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinList;
import com.ondeviceresearch.vendingmachineapi.config.VendingMachineConfig;
import com.ondeviceresearch.vendingmachineapi.datastore.VendingMachineDataStore;
import com.ondeviceresearch.vendingmachineapi.drinks.DrinksService;
import com.ondeviceresearch.vendingmachineapi.drinks.model.Drink;
import com.ondeviceresearch.vendingmachineapi.vendingmachine.VendingMachineController;

/**
 * Convenience static Methods/Values
 */
public class TestUtils {

    public static final Coin FIVE_PENCE = new Coin("FIVE_PENCE", 5);
    public static final Coin TEN_PENCE = new Coin("TEN_PENCE", 10);
    public static final Coin TWENTY_PENCE = new Coin("TWENTY_PENCE", 20);
    public static final Coin FIFTY_PENCE = new Coin("FIFTY_PENCE", 50);
    public static final Coin ONE_POUND = new Coin("ONE_POUND", 100);

    public static final Drink COKE = new Drink("Coke", 65);
    public static final Drink PEPSI = new Drink("Pepsi", 60);
    public static final Drink LEMONADE = new Drink("Lemonade", 55);


    public static VendingMachineConfig config() {
        return VendingMachineConfig.createFromConfigFile();
    }

    public static VendingMachineController vendingMachineController() {
        return new VendingMachineController(vendingMachineDataStore(),
                drinksService(), coinService());
    }

    public static VendingMachineDataStore vendingMachineDataStore() {
        return new VendingMachineDataStore(config(),new CoinList());
    }

    public static CoinService coinService() {
        return new CoinService(vendingMachineDataStore(), new CoinList(), config());
    }

    public static DrinksService drinksService() {
        return new DrinksService(vendingMachineDataStore(), config(), coinService());

    }

    public static DrinksService drinksService(CoinService coinService) {
        return new DrinksService(vendingMachineDataStore(), config(), coinService);

    }
}
