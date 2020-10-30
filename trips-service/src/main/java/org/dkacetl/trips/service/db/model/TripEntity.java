package org.dkacetl.trips.service.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("trip")
public class TripEntity implements Persistable<Long> {
    @Id
    private Long id;

    @Column("vehicle_id")
    private Long vehicleId;

    @Column("start_ts")
    private Instant startTs;

    @Column("stop_ts")
    private Instant stopTs;

    @Column("trip_points_count")
    private Long tripPointsCount;

    @Transient
    private boolean newProduct = false;

    @Override
    public boolean isNew() {
        return id==null || newProduct;
    }

    public TripEntity setAsNew() {
        this.newProduct = true;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Instant getStartTs() {
        return startTs;
    }

    public void setStartTs(Instant startTs) {
        this.startTs = startTs;
    }

    public Instant getStopTs() {
        return stopTs;
    }

    public void setStopTs(Instant stopTs) {
        this.stopTs = stopTs;
    }

    public Long getTripPointsCount() {
        return tripPointsCount;
    }

    public void setTripPointsCount(Long tripPointsCount) {
        this.tripPointsCount = tripPointsCount;
    }
}
