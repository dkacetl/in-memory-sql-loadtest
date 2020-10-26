package org.dkacetl.inmemorysqlloadtest.calculation;

import org.dkacetl.inmemorysqlloadtest.db.model.TripEntity;
import org.dkacetl.inmemorysqlloadtest.db.model.VehicleEntity;
import org.dkacetl.inmemorysqlloadtest.db.repository.TripRepository;
import org.dkacetl.inmemorysqlloadtest.events.VehicleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TripProcessor {

    @Autowired
    private TripRepository tripRepository;

    /**
     *
     * @return
     */
    public Mono<TripEntity> handleTrip(VehicleEntity vehicle, VehicleEvent event) {
        Mono<TripEntity> tripEntityMono = tripRepository.findTripForEvent(vehicle.getId(), event.getTimestamp());

        return tripEntityMono
                .switchIfEmpty(
                        // no trip found, create new open trip
                        openNewTrip(vehicle, event))
                .flatMap( tripEntity -> {
                    if (event.isEngineOn()) {
                        // engine started - close told trip, open a new one
                        closeCurrentTrip(tripEntity, event);
                        // new unfinished trip
                        return openNewTrip(vehicle, event);
                    } else {
                        // just current trip
                        return Mono.just(tripEntity);
                    }
                }
        );
    }

    private Mono<TripEntity> closeCurrentTrip(TripEntity currentTrip, VehicleEvent event) {
        currentTrip.setStopTs(event.getTimestamp());
        return tripRepository.save(currentTrip)
                .doOnError(Throwable::printStackTrace);
    }

    private Mono<TripEntity> openNewTrip(VehicleEntity vehicle, VehicleEvent event) {
        TripEntity tripEntity = new TripEntity();
        tripEntity.setStartTs(event.getTimestamp());
        tripEntity.setStopTs(null); // open trip
        tripEntity.setVehicleId(vehicle.getId());
        return tripRepository.save(tripEntity.setAsNew())
                .doOnError(Throwable::printStackTrace);
    }
}
