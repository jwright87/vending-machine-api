package com.ondeviceresearch.vendingmachineapi.datastore;

import com.ondeviceresearch.vendingmachineapi.TestUtils;
import com.ondeviceresearch.vendingmachineapi.config.VendingMachineConfig;
import com.ondeviceresearch.vendingmachineapi.model.ChangeUnavailableException;
import com.ondeviceresearch.vendingmachineapi.model.OutOfStockException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class VendingMachineDataStoreTest {


    private VendingMachineDataStore dataStore;

    @BeforeEach
    void setup() {
        dataStore = new VendingMachineDataStore(VendingMachineConfig.createFromConfigFile());
        dataStore.getDrinkStore().put(TestUtils.COKE,1);
        dataStore.getDrinkStore().put(TestUtils.PEPSI,0);
        dataStore.getDrinkStore().put(TestUtils.LEMONADE,4);

        dataStore.getCoinStore().put(TestUtils.FIVE_PENCE,0);
        dataStore.getCoinStore().put(TestUtils.TEN_PENCE,1);
        dataStore.getCoinStore().put(TestUtils.TWENTY_PENCE,7);
        dataStore.getCoinStore().put(TestUtils.FIFTY_PENCE,3);
        dataStore.getCoinStore().put(TestUtils.ONE_POUND,8);
    }


    @Test
    void shouldResetDrinks() {
        dataStore.reset();
        assertThat(dataStore.getDrinkStore().get(TestUtils.COKE)).isEqualTo(5);
        assertThat(dataStore.getDrinkStore().get(TestUtils.PEPSI)).isEqualTo(5);
        assertThat(dataStore.getDrinkStore().get(TestUtils.LEMONADE)).isEqualTo(5);
    }

    @Test
    void shouldResetCoins() {
        dataStore.reset();
        assertThat(dataStore.getCoinStore().get(TestUtils.FIVE_PENCE)).isEqualTo(5);
        assertThat(dataStore.getCoinStore().get(TestUtils.TEN_PENCE)).isEqualTo(5);
        assertThat(dataStore.getCoinStore().get(TestUtils.TWENTY_PENCE)).isEqualTo(5);
        assertThat(dataStore.getCoinStore().get(TestUtils.FIFTY_PENCE)).isEqualTo(5);
        assertThat(dataStore.getCoinStore().get(TestUtils.ONE_POUND)).isEqualTo(5);
    }

    @Test
    void drinkShouldBeInStock() {
        assertThat(dataStore.isInStock(TestUtils.COKE)).isTrue();
    }

    @Test
    void drinkNotShouldBeInStock() {
        assertThat(dataStore.isInStock(TestUtils.PEPSI)).isFalse();
    }

    @Test
    void shouldHaveCoinsAvailableForChange() {
        assertThat(dataStore.coinsAvailable(TestUtils.TWENTY_PENCE)).isEqualTo(7);
    }
    @Test
    void shouldHaveNoCoinsAvailableForChange() {
        assertThat(dataStore.coinsAvailable(TestUtils.FIVE_PENCE)).isEqualTo(0);
    }

    @Test
    void shouldRemoveDrinkFromInventory() {
        assertThat(dataStore.removeDrink(TestUtils.LEMONADE)).isEqualTo(3);
    }

    @Test
    void shouldFailToRemoveDrinkAsNoneInInventory() {
        assertThrows(OutOfStockException.class,() -> dataStore.removeDrink(TestUtils.PEPSI));
    }

    @Test
    void shouldRemoveCoin() {
        assertThat(dataStore.coinsAvailable(TestUtils.ONE_POUND)).isEqualTo(8);
        dataStore.removeCoin(TestUtils.ONE_POUND);
        assertThat(dataStore.coinsAvailable(TestUtils.ONE_POUND)).isEqualTo(7);
    }

    @Test
    void shouldFailToRemoveCoin() {
        assertThrows(ChangeUnavailableException.class,() -> dataStore.removeCoin(TestUtils.FIVE_PENCE));
    }


}