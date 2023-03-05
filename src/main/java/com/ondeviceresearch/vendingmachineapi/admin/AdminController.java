package com.ondeviceresearch.vendingmachineapi.admin;

import com.ondeviceresearch.vendingmachineapi.datastore.VendingMachineDataStore;
import com.ondeviceresearch.vendingmachineapi.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private VendingMachineDataStore dataStore;

    public AdminController(VendingMachineDataStore dataStore) {
        this.dataStore = dataStore;
    }

    @GetMapping("/reset")
    public ResponseEntity<ApiResponse> reset() {
        dataStore.reset();
        return ResponseEntity.ok(new AdminResetResponse(200, "Drink Stock / Coins reset to initial values"));
    }
}
