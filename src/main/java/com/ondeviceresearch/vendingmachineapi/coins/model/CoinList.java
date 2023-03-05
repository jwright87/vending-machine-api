package com.ondeviceresearch.vendingmachineapi.coins.model;

import java.util.ArrayList;

public class CoinList extends ArrayList<Coin> {

        public int sum() {
            return stream().mapToInt(Coin::valueInPence).sum();
        }

    }
