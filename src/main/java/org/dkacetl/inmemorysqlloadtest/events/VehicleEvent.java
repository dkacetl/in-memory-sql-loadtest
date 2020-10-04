package org.dkacetl.inmemorysqlloadtest.events;

import java.util.StringJoiner;

public class VehicleEvent {

    private Long vehicleId;

    private String licencePlate;

    private float latitude;

    private float longitude;

    public VehicleEvent() {
    }

    public VehicleEvent(Long vehicleId, String licencePlate, float latitude, float longitude) {
        this.vehicleId = vehicleId;
        this.licencePlate = licencePlate;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", VehicleEvent.class.getSimpleName() + "[", "]")
                .add("vehicleId=" + vehicleId)
                .add("licencePlate='" + licencePlate + "'")
                .add("latitude=" + latitude)
                .add("longitude=" + longitude)
                .toString();
    }
}
