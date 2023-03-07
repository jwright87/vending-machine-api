package com.ondeviceresearch.vendingmachineapi.config;

import com.ondeviceresearch.vendingmachineapi.coins.model.Coin;
import com.ondeviceresearch.vendingmachineapi.drinks.model.Drink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VendingMachineConfigTest {


    private VendingMachineConfig dataModel;


    @BeforeEach
    void setup() {
        dataModel = VendingMachineConfig.createFromConfigFile();
    }

    @Test
    void shouldParseAcceptedCoins() {
        assertThat(dataModel.getCoins()).hasSize(5);
        assertThat(dataModel.getCoins()).contains(
                new Coin("FIVE_PENCE", 5),
                new Coin("TEN_PENCE", 10),
                new Coin("TWENTY_PENCE", 20),
                new Coin("FIFTY_PENCE", 50),
                new Coin("ONE_POUND", 100)
        );
    }

    @Test
    void shouldParseAvailableDrinks() {
        assertThat(dataModel.getDrinks()).hasSize(3);
        assertThat(dataModel.getDrinks()).contains(
                new Drink("Coke", 65),
                new Drink("Pepsi", 60),
                new Drink("Lemonade", 55)
        );
    }

    @Test
    void shouldEnrichCoin() {
        var coin = new Coin("FIVE_PENCE",55);
        coin = dataModel.enrich(coin);
        assertThat(coin.valueInPence()).isEqualTo(5);
    }

    @Test
    void shouldEnrichDrink() {
        var drink = new Drink("Coke",110);
        drink = dataModel.enrich(drink);
        assertThat(drink.cost()).isEqualTo(65);
    }
}