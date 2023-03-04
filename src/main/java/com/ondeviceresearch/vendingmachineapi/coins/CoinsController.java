package com.ondeviceresearch.vendingmachineapi.coins;

import com.ondeviceresearch.vendingmachineapi.model.http.ApiResponse;
import com.ondeviceresearch.vendingmachineapi.model.http.CoinInsertRequest;
import com.ondeviceresearch.vendingmachineapi.model.http.CoinInsertResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CoinsController {

    @PostMapping("/coin/insert")
    public ResponseEntity<ApiResponse> insertCoin(CoinInsertRequest request) {
        //TODO handle incoming request.
        ///TODO move message to constant value;
        return ResponseEntity.ok(new CoinInsertResponse(200,"Coin added to balance"));
    }
}
