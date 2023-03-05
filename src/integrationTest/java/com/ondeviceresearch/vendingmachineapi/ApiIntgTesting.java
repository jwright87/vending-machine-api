package com.ondeviceresearch.vendingmachineapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ondeviceresearch.vendingmachineapi.coins.model.Coin;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinInsertRequest;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.junit.jupiter.api.Test;
import uk.intenso.hwan.http.HwanTtp;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ApiIntgTesting {


    private static final String BASE_URL = "http://localhost:8080";


    private ObjectMapper mapper = new ObjectMapper();

    /***
     * Scenerio: User wants t buy a Coke with a £1 coin
     * @throws IOException
     */
    @Test
    void firstScenario() throws IOException {

     insertCoin(TestUtils.FIFTY_PENCE);
     insertCoin(TestUtils.TEN_PENCE);
    }


    private String responseToString(HttpResponse response) {
        try {
            return IOUtils.toString(response.getEntity().getContent());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void insertCoin(Coin coin) throws IOException {
        var body = new CoinInsertRequest(coin);
        var response = HwanTtp.createPost()
                .url(BASE_URL + "/coins/insert")
                .jsonContentType()
                .body(mapper.writeValueAsString(body))
                .build()
                .execute();

        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        System.out.println(responseToString(response));
    }
}
