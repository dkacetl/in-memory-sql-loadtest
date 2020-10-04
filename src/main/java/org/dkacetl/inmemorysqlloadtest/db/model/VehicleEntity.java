package org.dkacetl.inmemorysqlloadtest.db.model;

import org.springframework.data.annotation.Id;

public class VehicleEntity {

    @Id
    private Long id;

    private String licencePlate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }
}
