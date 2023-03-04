package com.ondeviceresearch.vendingmachineapi.coins;

import com.ondeviceresearch.vendingmachineapi.TestValues;
import com.ondeviceresearch.vendingmachineapi.model.http.CoinInsertRequest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
class CoinsControllerTest {


    CoinsController coinsController = new CoinsController();

    @Test
    void shouldReturnSuccessfulResponseWhenCoinInsertMessageSent() {

        var response = coinsController.insertCoin(new CoinInsertRequest(TestValues.TEN_PENCE));
        assertThat(response.getStatusCode().value()).isEqualTo(response.getBody().getStatusCode());
        assertThat(response.getBody().getStatusCode()).isEqualTo(200);
        assertThat(response.getBody().getMessage()).isEqualTo("Coin added to balance");
    };


}