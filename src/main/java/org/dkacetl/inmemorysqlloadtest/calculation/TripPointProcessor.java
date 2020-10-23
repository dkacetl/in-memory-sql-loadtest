package org.dkacetl.inmemorysqlloadtest.calculation;

import org.dkacetl.inmemorysqlloadtest.db.model.TripEntity;
import org.dkacetl.inmemorysqlloadtest.db.model.TripPointEntity;
import org.dkacetl.inmemorysqlloadtest.db.repository.TripPointRepository;
import org.dkacetl.inmemorysqlloadtest.events.VehicleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TripPointProcessor {

    @Autowired
    private TripPointRepository tripPointRepository;

    public Mono<TripPointEntity> saveNewTripPoint(TripEntity trip, VehicleEvent event) {
        TripPointEntity tripPointEntity = new TripPointEntity();
        tripPointEntity.setTripId(trip.getId());
        tripPointEntity.setTimestamp(event.getTimestamp());

        return tripPointRepository.save(tripPointEntity);
    }
}
