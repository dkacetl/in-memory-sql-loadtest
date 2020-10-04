package org.dkacetl.inmemorysqlloadtest.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("trip")
public class TripEntity {
    @Id
    private Long id;

    @Column("vehicle_id")
    private Long vehicleId;

    @Column("start_ts")
    private LocalDateTime startTs;

    @Column("stop_ts")
    private LocalDateTime stopTs;

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

    public LocalDateTime getStartTs() {
        return startTs;
    }

    public void setStartTs(LocalDateTime startTs) {
        this.startTs = startTs;
    }

    public LocalDateTime getStopTs() {
        return stopTs;
    }

    public void setStopTs(LocalDateTime stopTs) {
        this.stopTs = stopTs;
    }
}
