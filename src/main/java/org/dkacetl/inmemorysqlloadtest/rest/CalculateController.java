package org.dkacetl.inmemorysqlloadtest.rest;

import org.dkacetl.inmemorysqlloadtest.db.repository.TripPointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/calculate")
public class CalculateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalculateController.class);

    @Autowired
    private TripPointRepository tripPointRepository;

    @RequestMapping("/distance")
    public Mono<Float> distance(@RequestParam long vehicleId, @RequestParam String from, @RequestParam String to) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;//ofPattern("yyyy-MM-ddTHH:mm:ss");

        LocalDateTime fromLocalDateTime = LocalDateTime.from(dateTimeFormatter.parse(from));
        LocalDateTime toLocalDateTime = LocalDateTime.from(dateTimeFormatter.parse(to));

        Instant fromInstant = fromLocalDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Instant toInstant = toLocalDateTime.atZone(ZoneId.systemDefault()).toInstant();

        return tripPointRepository
                .calculateDistance(vehicleId, fromInstant, toInstant)
                .doOnError( (throwable) -> {
                    LOGGER.error("error ", throwable);
                    throwable.printStackTrace();
                });
    }
}
