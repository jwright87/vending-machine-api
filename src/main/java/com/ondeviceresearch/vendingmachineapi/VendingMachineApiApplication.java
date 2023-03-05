package com.ondeviceresearch.vendingmachineapi;

import com.ondeviceresearch.vendingmachineapi.config.VendingMachineConfig;
import com.ondeviceresearch.vendingmachineapi.coins.model.CoinList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendingMachineApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendingMachineApiApplication.class, args);
    }



    @Bean
    public VendingMachineConfig configData() {
        return VendingMachineConfig.createFromConfigFile();
    }

    @Bean
    public CoinList coinsInserted() {
        return new CoinList();
    }
}
