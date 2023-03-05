package com.ondeviceresearch.vendingmachineapi.other;

import com.ondeviceresearch.vendingmachineapi.model.basic.Coin;

import java.util.ArrayList;

public class CoinList extends ArrayList<Coin> {

        public int sum() {
            return stream().mapToInt(Coin::valueInPence).sum();
        }

    }
