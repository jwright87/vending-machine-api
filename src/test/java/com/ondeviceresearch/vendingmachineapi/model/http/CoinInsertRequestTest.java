package com.ondeviceresearch.vendingmachineapi.model.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ondeviceresearch.vendingmachineapi.TestValues;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class CoinInsertRequestTest {


    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldMapToJson() {
        var coin = TestValues.TWENTY_PENCE;
        var coinInsertRequest = new CoinInsertRequest(coin);
        String json = null;
        try {
            json = mapper.writeValueAsString(coinInsertRequest);
        } catch (JsonProcessingException e) {
            fail(e);
        }

        assertThat(json).isEqualTo("""
                {"coin":{"name":"TWENTY_PENCE","valueInPence":20}}""");

    }

    @Test
    void shouldMapFromJson() {
        var json = """
                {"coin":{"name":"ONE_POUND","valueInPence":100}}""";

        CoinInsertRequest coinInsertRequest = null;
        try {
            coinInsertRequest = mapper.readValue(json, CoinInsertRequest.class);
        } catch (JsonProcessingException e) {
            fail(e);
        }
        assertThat(coinInsertRequest.coin()).isEqualTo(TestValues.ONE_POUND);
    }

}