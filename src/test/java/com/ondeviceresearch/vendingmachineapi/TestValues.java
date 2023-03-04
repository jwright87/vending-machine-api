package com.ondeviceresearch.vendingmachineapi;

import com.ondeviceresearch.vendingmachineapi.model.basic.Coin;
import com.ondeviceresearch.vendingmachineapi.model.basic.Drink;

/**
 * Convenience static value for testing
 */
public class TestValues {

    public static final Coin FIVE_PENCE = new Coin("FIVE_PENCE", 5);
    public static final Coin TEN_PENCE = new Coin("TEN_PENCE", 10);
    public static final Coin TWENTY_PENCE = new Coin("TWENTY_PENCE", 20);
    public static final Coin FIFTY_PENCE = new Coin("FIFTY_PENCE", 50);
    public static final Coin ONE_POUND = new Coin("ONE_POUND", 100);

    public static final Drink COKE = new Drink("Coke", 65);
    public static final Drink PEPSI = new Drink("Pepsi", 60);
    public static final Drink LEMONADE = new Drink("Lemonade", 55);
}
