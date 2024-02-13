package com.civka.calculatordemo.maintenance;

import org.springframework.stereotype.Component;

@Component
public class MaintenanceModeManager {

    private boolean maintenanceModeEnabled = false;

    public boolean isMaintenanceModeEnabled() {
        return maintenanceModeEnabled;
    }

    public void setMaintenanceModeEnabled(boolean maintenanceModeEnabled) {
        this.maintenanceModeEnabled = maintenanceModeEnabled;
    }
}