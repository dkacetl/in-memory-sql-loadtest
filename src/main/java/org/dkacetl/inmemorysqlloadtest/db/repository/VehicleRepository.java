package org.dkacetl.inmemorysqlloadtest.db.repository;

import org.dkacetl.inmemorysqlloadtest.db.model.VehicleEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface VehicleRepository extends ReactiveCrudRepository<VehicleEntity, Long> {

    @Query("SELECT v FROM vehicle v WHERE v.license_plate = :licencePlate")
    Mono<VehicleEntity> findByLicensePlate(String licencePlate);
}
