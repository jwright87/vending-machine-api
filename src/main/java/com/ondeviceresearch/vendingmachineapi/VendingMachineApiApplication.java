package com.ondeviceresearch.vendingmachineapi;

import com.ondeviceresearch.vendingmachineapi.config.ConfigData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendingMachineApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VendingMachineApiApplication.class, args);
    }



    @Bean
    public ConfigData configData() {
        return ConfigData.createFromConfigFile();
    }

}
