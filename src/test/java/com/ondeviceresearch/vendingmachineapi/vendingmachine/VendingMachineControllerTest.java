package com.ondeviceresearch.vendingmachineapi.vendingmachine;

import com.ondeviceresearch.vendingmachineapi.TestUtils;
import com.ondeviceresearch.vendingmachineapi.datastore.VendingMachineDataStore;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class VendingMachineControllerTest {

    VendingMachineDataStore dataStore = TestUtils.vendingMachineDataStore();
    private VendingMachineController controller = TestUtils.vendingMachineController();

    @Test
    void shouldReset() {
        var response = controller.reset();
        assertThat(response.getStatusCode().value()).isEqualTo(200);

    }

}