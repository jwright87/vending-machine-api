package com.ondeviceresearch.vendingmachineapi.coins.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
class CoinListTest {


    @Test
    void shouldSumToZeroWhenEmpty() {
        var sum = new CoinList().sum();
        assertNotNull(sum);
        assertThat(sum).isZero();
    }

}