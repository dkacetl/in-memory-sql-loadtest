package org.dkacetl.inmemorysqlloadtest.db.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class TripPointEntity {

    @Id
    private Long id;

    private Long tripId;

    private Float latitude;

    private Float longitude;

    private LocalDateTime ts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getTs() {
        return ts;
    }

    public void setTs(LocalDateTime ts) {
        this.ts = ts;
    }
}
