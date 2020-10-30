package org.dkacetl.trips.service.db.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("vehicle")
public class VehicleEntity implements Persistable<Long> {

    @Id
    private Long id;

    @Column("license_plate")
    private String licensePlate;

    @Transient
    private boolean newProduct = false;

    @Override
    public boolean isNew() {
        return id==null || newProduct;
    }

    public VehicleEntity setAsNew() {
        this.newProduct = true;
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
}
