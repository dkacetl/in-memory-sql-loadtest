package org.dkacetl.inmemorysqlloadtest.db.repository;

import org.dkacetl.inmemorysqlloadtest.db.model.TripEntity;
import org.dkacetl.inmemorysqlloadtest.db.model.VehicleEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface TripRepository extends ReactiveCrudRepository<TripEntity, Long> {

    @Query("SELECT t FROM vehicle v, trip t " +
            "WHERE v.license_plate = :licencePlate " +
            "and v.id = t.vehicle_id " +
            "and (t.start_ts <= :ts) " +
            "and (t.stop_ts is null or t.stop_ts > :ts)")
    Mono<TripEntity> findTripForEvent(Long vehicleId);
}
