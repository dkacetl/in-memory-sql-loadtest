package org.dkacetl.trips.service.calculation;

import org.dkacetl.trips.service.db.model.TripEntity;
import org.dkacetl.trips.service.db.model.TripPointEntity;
import org.dkacetl.trips.service.db.repository.TripPointRepository;
import org.dkacetl.trips.api.events.VehicleEvent;
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
        tripPointEntity.setLatitude(event.getLatitude());
        tripPointEntity.setLongitude(event.getLongitude());
        tripPointEntity.setTimestamp(event.getTimestamp());
        tripPointEntity.setVelocity(event.getVelocity());
        return tripPointRepository.save(tripPointEntity.setAsNew())
                .doOnError(Throwable::printStackTrace);
    }
}
