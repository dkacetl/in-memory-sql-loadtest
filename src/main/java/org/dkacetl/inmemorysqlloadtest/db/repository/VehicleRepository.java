package org.dkacetl.inmemorysqlloadtest.db.repository;

import org.dkacetl.inmemorysqlloadtest.db.model.VehicleEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface VehicleRepository extends ReactiveCrudRepository<VehicleEntity, Long> {

    @Query("SELECT id, licence_plate FROM vehicle v WHERE c.licence_plate = :licencePlate")
    Mono<VehicleEntity> findByLicencePlate(String licencePlate);
}
