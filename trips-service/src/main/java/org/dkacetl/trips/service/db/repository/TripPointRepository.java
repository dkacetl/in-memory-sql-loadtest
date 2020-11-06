package org.dkacetl.trips.service.db.repository;

import org.dkacetl.trips.service.db.model.TripPointEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    Flux<TripPointEntity> findTripPoints(long vehicleId, Instant startTime, Instant stopTime);

    @Query("SELECT sum(distance) FROM ( " +
            "SELECT "+
            "    sqrt((tp.latitude - (LAG(tp.latitude,1,tp.latitude) OVER (ORDER BY tp.timestamp)))" +
            "     *(tp.latitude - (LAG(tp.latitude,1,tp.latitude) OVER (ORDER BY tp.timestamp))) + " +
            "    (tp.longitude - (LAG(tp.longitude,1,tp.longitude) OVER (ORDER BY tp.timestamp)))" +
            "     *(tp.longitude - (LAG(tp.longitude,1,tp.longitude) OVER (ORDER BY tp.timestamp)))) distance " +
            "FROM vehicle v, trip t, trip_point tp " +
            "WHERE v.id = :vehicleId " +
            "AND v.id = t.vehicle_id " +
            "AND t.id = tp.trip_id " +
            "AND tp.timestamp >= :startTime " +
            "AND tp.timestamp <= :stopTime) ")
    Mono<Float> calculateDistance(long vehicleId, Instant startTime, Instant stopTime);


    @Query("SELECT count(*) " +
            "FROM  trip_point tp " +
            "WHERE tp.trip_id = :tripId")
    Mono<Long> getTripPointsCount(long tripId);

    @Query("SELECT tpp.* FROM " +
                "(SELECT v.id vehicleId, max(tp.id) id " +
                 "FROM vehicle v, trip t, trip_point tp " +
                 "WHERE v.id = :vehicleId " +
                 "and v.id = t.vehicle_id " +
                 "and t.id = tp.trip_id " +
                "GROUP BY v.id) last_tp, " +
            " trip_point tpp  " +
            " WHERE last_tp.id = tpp.id ")
    Flux<TripPointEntity> getLastTripPoint(long vehicleId);

}

