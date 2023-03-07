package com.ondeviceresearch.vendingmachineapi.coins;

import com.ondeviceresearch.vendingmachineapi.coins.model.Coin;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinInsertResponse;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinListResponse;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinRefundResponse;
import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
@Controller
public class CoinsController {

    private static final Logger log = LoggerFactory.getLogger(CoinsController.class);

    private CoinService coinService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Test OK");
    }

    @PostMapping("/coins/insert")
    public ResponseEntity<ApiResponse> insertCoin(@RequestBody Coin coin) {
        coin = coinService.insertCoin(coin);
        return ResponseEntity.ok(new CoinInsertResponse(200, "%d pence added to balance"
                .formatted(coin.valueInPence())));
    }

    @GetMapping("/coins/balance")
    public ResponseEntity<ApiResponse> coinBalance() {
        var balance = coinService.insertedCoinsBalanceInPence();
        return ResponseEntity.ok(new CoinInsertResponse(balance, "Balance is %d pence"
                .formatted(balance)));
    }

    @PutMapping("/coins/refund")
    public ResponseEntity<ApiResponse> refundCoins() {
        var refundedCoins = coinService.refundCoins();
        var response = new CoinRefundResponse(refundedCoins, "Coins Refunded");
        return ResponseEntity.ok(response);
    }


    @GetMapping("/coins/list")
    public ResponseEntity<ApiResponse> acceptedCoins() {
        var coins = coinService.acceptedCoins();
        return ResponseEntity.ok(new CoinListResponse(coins, "Coins Accepted By The Vending Machine"));
    }
}
