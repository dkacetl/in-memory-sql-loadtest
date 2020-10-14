package org.dkacetl.inmemorysqlloadtest.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.StringJoiner;

@Table("trip_point")
public class TripPointEntity {

    @Id
    private Long id;

    @Column("trip_id")
    private Long tripId;

    private Float latitude;

    private Float longitude;

    private Instant timestamp;

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

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", TripPointEntity.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("tripId=" + tripId)
                .add("latitude=" + latitude)
                .add("longitude=" + longitude)
                .add("timestamp=" + timestamp)
                .toString();
    }
}
