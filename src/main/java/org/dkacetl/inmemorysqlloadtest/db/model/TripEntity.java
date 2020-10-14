package org.dkacetl.inmemorysqlloadtest.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.sql.In;

import java.time.Instant;
import java.time.LocalDateTime;

@Table("trip")
public class TripEntity {
    @Id
    private Long id;

    @Column("vehicle_id")
    private Long vehicleId;

    @Column("start_ts")
    private Instant startTs;

    @Column("stop_ts")
    private Instant stopTs;

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
}
