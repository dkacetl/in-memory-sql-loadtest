package org.dkacetl.inmemorysqlloadtest.events;

import java.time.Instant;
import java.util.StringJoiner;

public class VehicleEvent {

    private Long vehicleId;

    private boolean engineOn;

    private float latitude;

    private float longitude;

    private Instant timestamp;

    public VehicleEvent() {
    }

    public VehicleEvent(Long vehicleId, boolean engineOn, float latitude, float longitude, Instant timestamp) {
        this.vehicleId = vehicleId;
        this.engineOn = engineOn;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", VehicleEvent.class.getSimpleName() + "[", "]")
                .add("vehicleId=" + vehicleId)
                .add("engineOn=" + engineOn)
                .add("latitude=" + latitude)
                .add("longitude=" + longitude)
                .add("timestamp=" + timestamp)
                .toString();
    }
}
