package org.dkacetl.inmemorysqlloadtest.db.model;

import java.time.Instant;

public class DistanceEntity {

    private Float last_latitude;

    private Float last_longitude;

    private Float latitude;

    private Float longitude;

    private Instant timestamp;

    public DistanceEntity(Float last_latitude, Float last_longitude, Float latitude, Float longitude, Instant timestamp) {
        this.last_latitude = last_latitude;
        this.last_longitude = last_longitude;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timestamp = timestamp;
    }

    public DistanceEntity() {
    }

    public Float getLast_latitude() {
        return last_latitude;
    }

    public void setLast_latitude(Float last_latitude) {
        this.last_latitude = last_latitude;
    }

    public Float getLast_longitude() {
        return last_longitude;
    }

    public void setLast_longitude(Float last_longitude) {
        this.last_longitude = last_longitude;
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

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
