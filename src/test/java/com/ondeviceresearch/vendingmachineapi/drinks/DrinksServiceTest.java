package com.ondeviceresearch.vendingmachineapi.drinks;

import com.ondeviceresearch.vendingmachineapi.TestUtils;
import com.ondeviceresearch.vendingmachineapi.coins.CoinService;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinList;
import com.ondeviceresearch.vendingmachineapi.drinks.model.Drink;
import com.ondeviceresearch.vendingmachineapi.model.InsufficientBalanceException;
import com.ondeviceresearch.vendingmachineapi.model.OutOfStockException;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DrinksServiceTest {


    private CoinService coinService = TestUtils.coinService();
    private DrinksService drinksService = TestUtils.drinksService(coinService);


    @Test
    void shouldReturnDrinkInformation() {
        var drinkInfo = drinksService.drinksInStock();
        assertThat(drinkInfo).hasSize(3);
        assertThat(drinkInfo).containsExactly(
                TestUtils.COKE,
                TestUtils.PEPSI,
                TestUtils.LEMONADE
        );
    }


    @Test
    void shouldReturnDrinkInformationWithOutOfStock() {
        buyDrinks(TestUtils.COKE, 5);
        buyDrinks(TestUtils.LEMONADE, 5);
        var drinkInfo = drinksService.drinksInStock();
        assertThat(drinkInfo).hasSize(1);
        assertThat(drinkInfo).containsExactly(
                TestUtils.PEPSI
        );
    }

    @Test
    void shouldPurchaseDrink() {

        var coins = new CoinList();
        coins.add(TestUtils.ONE_POUND);
        assertThat(coinService.insertedCoinsBalanceInPence()).isZero();
        var change = drinksService.purchaseDrink(TestUtils.COKE, coins);
        assertThat(change.sum()).isEqualTo(35);
        assertThat(drinksService.drinkStockCount(TestUtils.COKE)).isEqualTo(4);

        assertThat(coinService.insertedCoinsBalanceInPence()).isZero();
    }

    @Test
    void ShouldPurchaseMultipleDrinks() {

        var coins = new CoinList();
        coins.add(TestUtils.FIFTY_PENCE);
        coins.add(TestUtils.FIVE_PENCE);
        coins.add(TestUtils.TWENTY_PENCE);
        coins.add(TestUtils.TWENTY_PENCE);
        coins.add(TestUtils.TEN_PENCE);
        coins.add(TestUtils.FIVE_PENCE);
        var change = drinksService.purchaseDrink(TestUtils.LEMONADE, coins);
        change = drinksService.purchaseDrink(TestUtils.LEMONADE, change);
        assertThat(change).isEmpty();
        assertThat(change.sum()).isEqualTo(0);
        assertThat(drinksService.drinkStockCount(TestUtils.LEMONADE)).isEqualTo(3);
    }

    @Test
    void shouldNotBeAbleToBuyDrinkThatIsOutOfStock() {
        buyDrinks(TestUtils.PEPSI, 5);
        assertThrows(OutOfStockException.class, () -> drinksService.purchaseDrink(TestUtils.PEPSI,
                preFIlledCoinlist()));

    }

    @Test
    void shouldNotBeAbleToBuyDrinkWithoutEnoughCoinsInserted() {
        var insertedCoins = new CoinList();
        insertedCoins.add(TestUtils.FIFTY_PENCE);
        assertThrows(InsufficientBalanceException.class, () -> drinksService.purchaseDrink(TestUtils.LEMONADE,
                insertedCoins));
    }

    private CoinList preFIlledCoinlist() {
        var coins = new CoinList();
        coins.add(TestUtils.FIFTY_PENCE);
        coins.add(TestUtils.TEN_PENCE);
        coins.add(TestUtils.FIVE_PENCE);
        return coins;
    }

    private void buyDrinks(Drink drink, int amount) {
        IntStream.range(0, amount).forEach(i -> {
            var coins = preFIlledCoinlist();
            drinksService.purchaseDrink(drink, coins);
        });
    }
}