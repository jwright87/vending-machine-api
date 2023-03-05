package com.ondeviceresearch.vendingmachineapi.coins;

import com.ondeviceresearch.vendingmachineapi.TestUtils;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinInsertRequest;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinList;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CoinsControllerTest {

    CoinList balance = new CoinList();

    CoinService coinService = TestUtils.coinService();
    CoinsController coinsController = new CoinsController(coinService);

    @Test
    void shouldReturnSuccessfulResponseWhenCoinInsertMessageSent() {
        var response = coinsController.insertCoin(new CoinInsertRequest(TestUtils.TEN_PENCE));
        assertThat(response.getStatusCode().value()).isEqualTo(response.getBody().getStatusCode());
        assertThat(response.getBody().getStatusCode()).isEqualTo(200);
        assertThat(response.getBody().getMessage()).isEqualTo("10 pence added to balance");
    }


    @Test
    void shouldReturnBalance() {
        coinService.insertCoin(TestUtils.FIFTY_PENCE);
        coinService.insertCoin(TestUtils.TEN_PENCE);
        coinService.insertCoin(TestUtils.FIVE_PENCE);


        var response = coinsController.coinBalance();
        assertThat(response.getBody().getMessage()).isEqualTo("Balance is 65 pence");
    }

    @Test
    void shouldRefundBalance() {
        coinService.insertCoin(TestUtils.ONE_POUND);
        coinService.insertCoin(TestUtils.TWENTY_PENCE);
        coinService.insertCoin(TestUtils.FIVE_PENCE);
        var response = coinsController.refundCoins();
        assertThat(response.getBody().getMessage()).isEqualTo("Coins Refunded");
        assertThat(balance).isEmpty();
    }

}