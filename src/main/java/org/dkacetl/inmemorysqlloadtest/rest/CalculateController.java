package org.dkacetl.inmemorysqlloadtest.rest;

import org.dkacetl.inmemorysqlloadtest.db.model.TripPointEntity;
import org.dkacetl.inmemorysqlloadtest.db.repository.TripPointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;

@RestController
@RequestMapping("/calculate")
public class CalculateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculateController.class);

    @Autowired
    private TripPointRepository tripPointRepository;

    @Autowired
    private DatabaseClient databaseClient;

    @RequestMapping("/distance")
    public Mono<Float> distance() {
        return databaseClient.execute(
                 "SELECT sum(distance) FROM ( " +
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
                        "AND tp.timestamp <= :stopTime " +
                 ")"
        )
                .bind("vehicleId",1L)
                .bind("startTime", Instant.now().minusSeconds(1000L))
                .bind("stopTime",Instant.now())
                .as(Float.class)
                .fetch()
                .first()
                .doOnError( (throwable) -> {
                    LOGGER.error("error ", throwable);
                    throwable.printStackTrace();
                });
    }
}
