package org.dkacetl.inmemorysqlloadtest.db.repository;

import org.dkacetl.inmemorysqlloadtest.db.model.DistanceEntity;
import org.dkacetl.inmemorysqlloadtest.db.model.TripPointEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.time.Instant;

public interface TripPointRepository extends ReactiveCrudRepository<TripPointEntity, Long> {

    @Query("SELECT tp.* " +
            "FROM vehicle v, trip t, trip_point tp " +
            "WHERE v.id = :vehicleId " +
            "and v.id = t.vehicle_id " +
            "and t.id = tp.trip_id " +
            "and tp.timestamp >= :startTime " +
            "and tp.timestamp <= :stopTime " +
            "ORDER BY tp.timestamp")
    Flux<TripPointEntity> findTripPoints(Long vehicleId, Instant startTime, Instant stopTime);

//    @Query("SELECT LAG(tp.latitude,1,tp.latitude) OVER (ORDER BY tp.timestamp) last_latitude, " +
//            "      LAG(tp.longitude,1,tp.longitude) OVER (ORDER BY tp.timestamp) last_longitude," +
    @Query("SELECT LAG(tp.latitude,1,tp.latitude) OVER (ORDER BY tp.timestamp) last_latitude, " +
            "      LAG(tp.longitude,1,tp.longitude) OVER (ORDER BY tp.timestamp) last_longitude," +
            "      tp.latitude latitude, " +
            "      tp.longitude longitude," +
            "      tp.timestamp timestamp " +
            "FROM vehicle v, trip t, trip_point tp " +
            "WHERE v.id = :vehicleId " +
            "AND v.id = t.vehicle_id " +
            "AND t.id = tp.trip_id " +
            "AND tp.timestamp >= :startTime " +
            "AND tp.timestamp <= :stopTime " +
            "ORDER BY tp.timestamp")
    Flux<DistanceEntity> calculateDistance(Long vehicleId, Instant startTime, Instant stopTime);

}
