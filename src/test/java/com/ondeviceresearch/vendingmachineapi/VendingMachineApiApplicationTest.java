package com.ondeviceresearch.vendingmachineapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.support.GenericWebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VendingMachineApiApplicationTest {

    @Autowired
    private GenericWebApplicationContext appContext;

    @BeforeEach()
    void setup() {
        appContext.stop();
        try {
            appContext.registerBean(VendingMachineApiApplicationTest.class, problemBean());
        } catch (Exception e) {
        }
    }

    @Test
    void springBootContextShouldLoad() {
        System.out.println("is AppContext Already Running? " + appContext.isRunning() + " Active? " + appContext.isActive());
        appContext.start();
    }

    @Test
    void springBootContextShouldNotLoad() {
        assertThat(appContext).isNotNull();
    }

    private String problemBean() {
        if (1 == 1) {
            throw new RuntimeException("Test Issue Creating Bean");
        }
        return "OK";
    }



}
