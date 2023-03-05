package com.ondeviceresearch.vendingmachineapi.coins;

import com.ondeviceresearch.vendingmachineapi.coins.model.CoinInsertRequest;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinInsertResponse;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinRefundResponse;
import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class CoinsController {


    private CoinService coinService;

    public CoinsController(CoinService coinService) {
        this.coinService = coinService;
    }

    @PostMapping("/coins/insert")
    public ResponseEntity<ApiResponse> insertCoin(CoinInsertRequest request) {
        coinService.insertCoin(request.coin());
        return ResponseEntity.ok(new CoinInsertResponse(200, "%d pence added to balance"
                .formatted(request.coin().valueInPence())));
    }

    @GetMapping("/coins/balance")
    public ResponseEntity<ApiResponse> coinBalance() {
        return ResponseEntity.ok(new CoinInsertResponse(200, "Balance is %d pence"
                .formatted(coinService.insertedCoinsBalanceInPence())));
    }

    @PutMapping("/coins/refund")
    public ResponseEntity<ApiResponse> refundCoins() {
        var refundedCoins = coinService.refundCoins();
        var response = new CoinRefundResponse(refundedCoins, 200, "Coins Refunded");
        return ResponseEntity.ok(response);
    }
}
