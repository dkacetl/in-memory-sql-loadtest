package org.dkacetl.inmemorysqlloadtest.rest;

import org.dkacetl.inmemorysqlloadtest.db.model.TripPointEntity;
import org.dkacetl.inmemorysqlloadtest.db.repository.TripPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/calculate")
public class CalculateSumController {

    @Autowired
    private TripPointRepository tripPointRepository;

    @RequestMapping("/trip-points")
    public Flux<TripPointEntity> allTripPoints() {
        return tripPointRepository.findAll();
    }

}
