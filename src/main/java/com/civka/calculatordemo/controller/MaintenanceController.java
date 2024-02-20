package com.civka.calculatordemo.controller;

import com.civka.calculatordemo.maintenance.MaintenanceModeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    private final MaintenanceModeManager maintenanceModeManager;

    @Autowired
    public MaintenanceController(MaintenanceModeManager maintenanceModeManager) {
        this.maintenanceModeManager = maintenanceModeManager;
    }

    @GetMapping
    public String getMaintenancePage() {
        return "maintenance";
    }

    @PostMapping("/enable")
    public String enableMaintenanceMode() {
        maintenanceModeManager.setMaintenanceModeEnabled(true);
        return "maintenance-status-on";
    }

    @PostMapping("/disable")
    public String disableMaintenanceMode() {
        maintenanceModeManager.setMaintenanceModeEnabled(false);
        return "maintenance-status-off";
    }
}