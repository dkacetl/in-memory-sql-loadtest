package org.dkacetl.inmemorysqlloadtest.db.repository;

import org.dkacetl.inmemorysqlloadtest.db.model.TripPointEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.time.Instant;

public interface TripPointRepository extends ReactiveCrudRepository<TripPointEntity, Long> {

//    @Query("SELECT tp. FROM vehicle v, trip t, trip_point tp " +
//            "WHERE v.id = :vehicleId " +
//            "and v.id = t.vehicle_id " +
//            "and (t.start_ts > :startTime) " +
//            "and (t.stop_ts < :stopTime)")
//    Mono<TripPointEntity> findTripForEvent(Long vehicleId, Instant startTime, Instant stopTime);
}
