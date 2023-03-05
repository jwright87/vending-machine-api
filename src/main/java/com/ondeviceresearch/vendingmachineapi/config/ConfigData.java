package com.ondeviceresearch.vendingmachineapi.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ondeviceresearch.vendingmachineapi.model.basic.Coin;
import com.ondeviceresearch.vendingmachineapi.model.basic.Drink;
import lombok.Data;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.List;


@Data
public class ConfigData {


    private List<Coin> coins;
    private List<Drink> drinks;

    private ConfigData() {
    }


    public static ConfigData createFromConfigFile() {
        try {
            var classpathFileInputStream = ConfigData.class.getClassLoader().getResourceAsStream("static/config.json");
            var jsonData = IOUtils.toString(classpathFileInputStream);
            return new ObjectMapper().readValue(jsonData, ConfigData.class);
        } catch (JsonProcessingException jpe) {
            throw new RuntimeException("Problem with config json", jpe);
        } catch (IOException e) {
            throw new RuntimeException("Unable to read data config file", e);
        }
    }

}
