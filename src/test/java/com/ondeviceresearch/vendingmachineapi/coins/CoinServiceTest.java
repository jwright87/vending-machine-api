package com.ondeviceresearch.vendingmachineapi.coins;

import com.ondeviceresearch.vendingmachineapi.TestUtils;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinList;
import com.ondeviceresearch.vendingmachineapi.datastore.VendingMachineDataStore;
import com.ondeviceresearch.vendingmachineapi.model.ChangeUnavailableException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoinServiceTest {

    private VendingMachineDataStore dataStore = TestUtils.vendingMachineDataStore();
    private CoinService coinService = new CoinService(
            dataStore,new CoinList(),TestUtils.config());

    @Test
    void shouldInsertCoinAndReturnCorrectBalance() {
        coinService.insertCoin(TestUtils.FIFTY_PENCE);
        assertThat(coinService.insertedCoinsBalanceInPence()).isEqualTo(50);
    }

    @Test
    void shouldInsertMultipleCoinsAndReturnCorrectBalance() {
        coinService.insertCoin(TestUtils.FIFTY_PENCE);
        coinService.insertCoin(TestUtils.TEN_PENCE);
        coinService.insertCoin(TestUtils.TEN_PENCE);
        assertThat(coinService.insertedCoinsBalanceInPence()).isEqualTo(70);
    }

    @Test
    void  shouldRefundCoins() {
        coinService.insertCoin(TestUtils.FIVE_PENCE);
        coinService.insertCoin(TestUtils.ONE_POUND);
        assertThat(coinService.insertedCoinsBalanceInPence()).isEqualTo(105);
        var refundedCoins = coinService.refundCoins();
        assertThat(refundedCoins).hasSize(2);
        assertThat(refundedCoins).contains(
                TestUtils.FIVE_PENCE,
                TestUtils.ONE_POUND
        );
        assertThat(coinService.insertedCoinsBalanceInPence()).isEqualTo(0);
    }
    @Test
    void shouldRetrieveChangeInCoins() {
        var change = coinService.retrieveChangeInCoins(25);
        assertThat(change).hasSize(2);
        assertThat(change).contains(TestUtils.TWENTY_PENCE, TestUtils.FIVE_PENCE);
    }

    @Test
    void shouldRetrieveChangeInWithMultipleOfSameCoin() {
        var change = coinService.retrieveChangeInCoins(40);
        assertThat(change).hasSize(2);
        assertThat(change).contains(TestUtils.TWENTY_PENCE, TestUtils.TWENTY_PENCE);
    }


    @Test
    void shouldOnlyUseCoinsInDataStoreWhenRetrievingChange() {
        dataStore.fetchCoins(TestUtils.TWENTY_PENCE, 4);
        dataStore.fetchCoins(TestUtils.TEN_PENCE, 4);

        var change = coinService.retrieveChangeInCoins(40);
        assertThat(change).hasSize(4);
        assertThat(change).contains(TestUtils.TWENTY_PENCE, TestUtils.TEN_PENCE, TestUtils.FIVE_PENCE, TestUtils.FIVE_PENCE);
    }

    @Test
    void shouldFailToRetrieveChangeAsExactChangeImpossible() {
       dataStore.fetchCoins(TestUtils.FIVE_PENCE,5);

        assertThrows(ChangeUnavailableException.class,() -> coinService.retrieveChangeInCoins(25));

    }
}