package org.dkacetl.inmemorysqlloadtest.db.repository;

import org.dkacetl.inmemorysqlloadtest.db.model.TripEntity;
import org.dkacetl.inmemorysqlloadtest.db.model.VehicleEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface TripRepository extends ReactiveCrudRepository<TripEntity, Long> {

    @Query("SELECT t.* FROM vehicle v, trip t " +
            "WHERE v.id = :vehicleId " +
            "and v.id = t.vehicle_id " +
            "and (t.start_ts <= :timestamp) " +
            "and (t.stop_ts is null or t.stop_ts > :timestamp)")
    Mono<TripEntity> findTripForEvent(Long vehicleId, Instant timestamp);
}
