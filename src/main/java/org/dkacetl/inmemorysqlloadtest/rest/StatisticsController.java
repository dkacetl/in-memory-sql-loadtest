package org.dkacetl.inmemorysqlloadtest.rest;

import org.dkacetl.inmemorysqlloadtest.db.model.TripEntity;
import org.dkacetl.inmemorysqlloadtest.db.repository.TripPointRepository;
import org.dkacetl.inmemorysqlloadtest.db.repository.TripRepository;
import org.dkacetl.inmemorysqlloadtest.db.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripPointRepository tripPointRepository;

    @RequestMapping("/trips/count")
    public Mono<Long> getTripsCount() {
        return tripRepository.count();
    }

    @RequestMapping("/trippoints/count")
    public Mono<Long> getTripPointsCount() {
        return tripPointRepository.count();
    }
}
