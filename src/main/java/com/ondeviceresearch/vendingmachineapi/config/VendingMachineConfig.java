package com.ondeviceresearch.vendingmachineapi.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ondeviceresearch.vendingmachineapi.coins.model.Coin;
import com.ondeviceresearch.vendingmachineapi.drinks.model.Drink;
import lombok.Data;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.List;


@Data
public class VendingMachineConfig {


    private List<Coin> coins;
    private List<Drink> drinks;

    public Coin enrich(Coin coin) {
        return new Coin(coin.name(), coins.stream().filter(p -> p.name().equals(coin.name())).findFirst()
                .orElseThrow().valueInPence());
    }

    public Drink enrich(Drink drink) {
        return new Drink(drink.name(), drinks.stream().filter(p -> p.name().equals(drink.name()))
                .findFirst().orElseThrow().cost());
    }

    VendingMachineConfig() {
    }


    public static VendingMachineConfig createFromConfigFile() {
        try {
            var classpathFileInputStream = VendingMachineConfig.class.getClassLoader().getResourceAsStream("static/config.json");
            var jsonData = IOUtils.toString(classpathFileInputStream);
            return new ObjectMapper().readValue(jsonData, VendingMachineConfig.class);
        } catch (JsonProcessingException jpe) {
            throw new RuntimeException("Problem with config json", jpe);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read data config file", e);
        }
    }


}
