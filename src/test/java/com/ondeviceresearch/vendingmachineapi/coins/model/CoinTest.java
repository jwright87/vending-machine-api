package com.ondeviceresearch.vendingmachineapi.coins.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ondeviceresearch.vendingmachineapi.TestUtils;
import org.junit.jupiter.api.Test;

class CoinTest {

    @Test
    void tmp() throws JsonProcessingException {
        System.out.println(new ObjectMapper().writeValueAsString(TestUtils.COKE));
    }

}