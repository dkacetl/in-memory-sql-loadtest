package org.dkacetl.trips.service.calculation;

import org.dkacetl.trips.service.db.model.TripEntity;
import org.dkacetl.trips.service.db.model.VehicleEntity;
import org.dkacetl.trips.service.db.repository.TripPointRepository;
import org.dkacetl.trips.service.db.repository.TripRepository;
import org.dkacetl.trips.api.events.VehicleEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TripProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripProcessor.class);

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripPointRepository tripPointRepository;

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
                        return closeCurrentTrip(tripEntity, event)
                                .then(openNewTrip(vehicle, event)); // new unfinished trip
                    } else {
                        // just current trip
                        return Mono.just(tripEntity);
                    }
                }
        );
    }

    private Mono<TripEntity> closeCurrentTrip(TripEntity currentTrip, VehicleEvent event) {
        return tripPointRepository
                .getTripPointsCount(currentTrip.getId())
                .flatMap((count)-> {
                    currentTrip.setStopTs(event.getTimestamp());
                    currentTrip.setTripPointsCount(count);
                    return tripRepository.save(currentTrip);
                }).doOnError( (exception) ->
                    {
                        LOGGER.error("Error "+currentTrip,  exception);
                    }
                );
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
