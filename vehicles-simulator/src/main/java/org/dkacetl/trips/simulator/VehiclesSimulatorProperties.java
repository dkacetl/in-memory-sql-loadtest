package org.dkacetl.trips.simulator;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "vehicles-simulator")
public class VehiclesSimulatorProperties {

    private boolean enabled = false; // in case of false, no simulator will be enabled

    private String type = "default"; //

    private int vehiclesCount = 10;  // count of simulated vehicles

    private long emittingRate = 10L; // how often will be event emitted, milisec (common for all vehicles)

    private int eventLimit = -1; // no limit, unlimited emitting

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getVehiclesCount() {
        return vehiclesCount;
    }

    public void setVehiclesCount(int vehiclesCount) {
        this.vehiclesCount = vehiclesCount;
    }

    public long getEmittingRate() {
        return emittingRate;
    }

    public void setEmittingRate(long emittingRate) {
        this.emittingRate = emittingRate;
    }

    public int getEventLimit() {
        return eventLimit;
    }

    public void setEventLimit(int eventLimit) {
        this.eventLimit = eventLimit;
    }
}
