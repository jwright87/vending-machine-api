package com.ondeviceresearch.vendingmachineapi.datastore;

import com.ondeviceresearch.vendingmachineapi.TestValues;
import com.ondeviceresearch.vendingmachineapi.config.ConfigData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VendingMachineDataStoreTest {


    private VendingMachineDataStore dataStore;

    @BeforeEach
    void setup() {
        dataStore = new VendingMachineDataStore(ConfigData.createFromConfigFile());
        dataStore.getDrinkStore().put(TestValues.COKE,1);
        dataStore.getDrinkStore().put(TestValues.PEPSI,0);
        dataStore.getDrinkStore().put(TestValues.LEMONADE,4);

        dataStore.getCoinStore().put(TestValues.FIVE_PENCE,0);
        dataStore.getCoinStore().put(TestValues.TEN_PENCE,1);
        dataStore.getCoinStore().put(TestValues.TWENTY_PENCE,7);
        dataStore.getCoinStore().put(TestValues.FIFTY_PENCE,3);
        dataStore.getCoinStore().put(TestValues.ONE_POUND,8);
    }


    @Test
    void shouldResetDrinks() {
        dataStore.reset();
        assertThat(dataStore.getDrinkStore().get(TestValues.COKE)).isEqualTo(5);
        assertThat(dataStore.getDrinkStore().get(TestValues.PEPSI)).isEqualTo(5);
        assertThat(dataStore.getDrinkStore().get(TestValues.LEMONADE)).isEqualTo(5);
    }

    @Test
    void shouldResetCoins() {
        dataStore.reset();
        assertThat(dataStore.getCoinStore().get(TestValues.FIVE_PENCE)).isEqualTo(5);
        assertThat(dataStore.getCoinStore().get(TestValues.TEN_PENCE)).isEqualTo(5);
        assertThat(dataStore.getCoinStore().get(TestValues.TWENTY_PENCE)).isEqualTo(5);
        assertThat(dataStore.getCoinStore().get(TestValues.FIFTY_PENCE)).isEqualTo(5);
        assertThat(dataStore.getCoinStore().get(TestValues.ONE_POUND)).isEqualTo(5);
    }

    @Test
    void drinkShouldBeInStock() {
        assertThat(dataStore.isInStock(TestValues.COKE)).isTrue();
    }

    @Test
    void drinkNotShouldBeInStock() {
        assertThat(dataStore.isInStock(TestValues.PEPSI)).isFalse();
    }

    @Test
    void shouldHaveCoinsAvailableForChange() {
        assertThat(dataStore.coinsAvailable(TestValues.TWENTY_PENCE)).isEqualTo(7);
    }
    @Test
    void shouldHaveNoCoinsAvailableForChange() {
        assertThat(dataStore.coinsAvailable(TestValues.FIVE_PENCE)).isEqualTo(0);
    }

    @Test
    void shouldRemoveDrinkFromInventory() {
        assertThat(dataStore.removeDrink(TestValues.LEMONADE)).isEqualTo(3);
    }

    @Test
    void shouldFailToRemoveDrinkAsNoneInInventory() {
        assertThrows(OutOfStockException.class,() -> dataStore.removeDrink(TestValues.PEPSI));
    }

    @Test
    void shouldRemoveCoin() {
        assertThat(dataStore.coinsAvailable(TestValues.ONE_POUND)).isEqualTo(8);
        dataStore.removeCoin(TestValues.ONE_POUND);
        assertThat(dataStore.coinsAvailable(TestValues.ONE_POUND)).isEqualTo(7);
    }

    @Test
    void shouldFailToRemoveCoin() {
        assertThrows(ChangeUnavailableException.class,() -> dataStore.removeCoin(TestValues.FIVE_PENCE));
    }


}