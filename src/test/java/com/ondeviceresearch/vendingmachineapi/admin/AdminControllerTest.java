package com.ondeviceresearch.vendingmachineapi.admin;

import com.ondeviceresearch.vendingmachineapi.TestUtils;
import com.ondeviceresearch.vendingmachineapi.datastore.VendingMachineDataStore;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AdminControllerTest {

    VendingMachineDataStore dataStore = TestUtils.vendingMachineDataStore();
    private AdminController controller = new AdminController(dataStore);

    @Test
    void shouldReset() {
        var response = controller.reset();
        assertThat(response.getStatusCode().value()).isEqualTo(200);

    }

}