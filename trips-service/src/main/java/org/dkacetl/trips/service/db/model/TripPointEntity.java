package org.dkacetl.trips.service.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("trip_point")
public class TripPointEntity implements Persistable<Long> {

    @Id
    private Long id;

    @Column("trip_id")
    private Long tripId;

    @Column("latitude")
    private Float latitude;

    @Column("longitude")
    private Float longitude;

    @Column("timestamp")
    private Instant timestamp;

    @Column("velocity")
    private Integer velocity;

    @Transient
    private boolean newProduct = false;

    @Override
    public boolean isNew() {
        return id==null || newProduct;
    }

    public TripPointEntity setAsNew() {
        this.newProduct = true;
        return this;
    }

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

    public Integer getVelocity() {
        return velocity;
    }

    public void setVelocity(Integer velocity) {
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return "TripPointEntity{" +
                "id=" + id +
                ", tripId=" + tripId +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timestamp=" + timestamp +
                ", velocity=" + velocity +
                ", newProduct=" + newProduct +
                '}';
    }
}
