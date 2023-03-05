package com.ondeviceresearch.vendingmachineapi.coins.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ondeviceresearch.vendingmachineapi.TestUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoinInsertRequestTest {



    @Test
    void testJson() throws JsonProcessingException {
        var json = new ObjectMapper().writeValueAsString(new CoinInsertRequest(TestUtils.FIFTY_PENCE));
        System.out.println(json);
    }

}