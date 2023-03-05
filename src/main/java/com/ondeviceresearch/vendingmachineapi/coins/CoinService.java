package com.ondeviceresearch.vendingmachineapi.coins;

import com.ondeviceresearch.vendingmachineapi.coins.model.Coin;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinList;
import com.ondeviceresearch.vendingmachineapi.datastore.VendingMachineDataStore;
import com.ondeviceresearch.vendingmachineapi.model.ChangeUnavailableException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class CoinService {

    private static final Logger log = LoggerFactory.getLogger(CoinService.class);

    private VendingMachineDataStore dataStore;

    private CoinList insertedCoins;

    public CoinService(VendingMachineDataStore dataStore, CoinList insertedCoins) {
        this.dataStore = dataStore;
        this.insertedCoins = insertedCoins;
    }

    public void insertCoin(Coin coin) {
        insertedCoins.add(coin);
    }


    public int insertedCoinsBalanceInPence() {
        return insertedCoins.sum();
    }

    public CoinList refundCoins() {
        var refundedCoins = new CoinList();
        refundedCoins.addAll(insertedCoins);
        insertedCoins.clear();
        return refundedCoins;
    }

    public CoinList retrieveChangeInCoins(int changeValueInPence) {

        var change = new CoinList();
        List<Map.Entry<Coin, Integer>> availableCoinTypes = sortedAndAvailableCoins();


        for (Map.Entry<Coin, Integer> availableCoinType : availableCoinTypes) {
            int i = 0;
            while (i < availableCoinType.getValue()) {

                if (changeStillNeeded(changeValueInPence, change) >= coinValueInPence(availableCoinType)) {
                    change.add(dataStore.fetchCoin(availableCoinType.getKey()));
                }

                if (changeFound(changeValueInPence, change)) {
                    return change;
                }

                i++;
            }

        }

        if (changeStillNeeded(changeValueInPence, change) != 0) {
            throw new ChangeUnavailableException();
        }
        // Should never get here - change should be returned in loop
        return change;
    }

    private List<Map.Entry<Coin, Integer>> sortedAndAvailableCoins() {
        return dataStore.getCoinStore().entrySet()
                .stream()
                .sorted(sortByCoinValueDescending())
                .filter(p -> p.getValue() != 0)
                .toList();
    }

    private static int coinValueInPence(Map.Entry<Coin, Integer> coinAvailability) {
        return coinAvailability.getKey().valueInPence();
    }

    private static int changeStillNeeded(int amountInPence, CoinList change) {
        return amountInPence - change.sum();
    }

    private boolean changeFound(int amountInPence, CoinList change) {
        return change.sum() == amountInPence;
    }

    private Comparator<Map.Entry<Coin, Integer>> sortByCoinValueDescending() {
        return (a, b) -> a.getKey().valueInPence() < b.getKey().valueInPence() ? 1 : -1;
    }
}
