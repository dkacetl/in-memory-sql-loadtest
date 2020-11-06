package org.dkacetl.trips.api.events;

import java.time.Instant;

public class VehicleEvent {

    private Long vehicleId;

    private boolean engineOn;

    private float latitude;

    private float longitude;

    private Instant timestamp;

    private int velocity;

    public VehicleEvent() {
    }

    public VehicleEvent(Long vehicleId, boolean engineOn, float latitude, float longitude, Instant timestamp, int velocity) {
        this.vehicleId = vehicleId;
        this.engineOn = engineOn;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
        this.velocity = velocity;
    }

    public boolean isEngineOn() {
        return engineOn;
    }

    public void setEngineOn(boolean engineOn) {
        this.engineOn = engineOn;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return "VehicleEvent{" +
                "vehicleId=" + vehicleId +
                ", engineOn=" + engineOn +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                ", velocity=" + velocity +
                '}';
    }
}
