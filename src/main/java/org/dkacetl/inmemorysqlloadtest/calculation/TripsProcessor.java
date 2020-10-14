package org.dkacetl.inmemorysqlloadtest.calculation;

import org.dkacetl.inmemorysqlloadtest.db.model.TripEntity;
import org.dkacetl.inmemorysqlloadtest.db.model.TripPointEntity;
import org.dkacetl.inmemorysqlloadtest.db.model.VehicleEntity;
import org.dkacetl.inmemorysqlloadtest.db.repository.TripPointRepository;
import org.dkacetl.inmemorysqlloadtest.db.repository.TripRepository;
import org.dkacetl.inmemorysqlloadtest.db.repository.VehicleRepository;
import org.dkacetl.inmemorysqlloadtest.events.VehicleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TripsProcessor  {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripPointRepository tripPointRepository;

    public Mono<TripPointEntity> process(VehicleEvent event) {

        Mono<TripPointEntity> vehicleEntityMono = vehicleRepository
                .findById(event.getVehicleId())
                .switchIfEmpty(createNewVehicleEntity(event))
                .flatMap( (vehicle) -> handleCurrentTrip(vehicle, event))
                .flatMap( (tripEntity) -> createNewTripPoint(tripEntity, event));

        return vehicleEntityMono;
    }

    /**
     *
     * @return
     */
    private Mono<TripEntity> handleCurrentTrip(VehicleEntity vehicle, VehicleEvent event) {
        Mono<TripEntity> tripEntityMono = tripRepository.findTripForEvent(vehicle.getId(), event.getTimestamp());

        return tripEntityMono.flatMap( tripEntity -> {
                    if (event.isEngineOn()) {
                        // engine started - close told trip, open a new one
                        closeCurrentTrip(tripEntity, event);
                        // new unfinished trip
                        return createNewTripEntity(vehicle, event);
                    } else {
                        // just current trip
                        return Mono.just(tripEntity);
                    }
                }
        ).switchIfEmpty(
                // no trip found, create new open trip
                createNewTripEntity(vehicle, event)
        );
    }

    private Mono<TripPointEntity> createNewTripPoint(TripEntity trip, VehicleEvent event) {
        TripPointEntity tripPointEntity = new TripPointEntity();
        tripPointEntity.setTripId(trip.getId());
        tripPointEntity.setTimestamp(event.getTimestamp());
        return tripPointRepository.save(tripPointEntity);
    }

    private Mono<VehicleEntity> createNewVehicleEntity(VehicleEvent vehicleEvent) {
        VehicleEntity newVehicleEntity =new VehicleEntity();
        newVehicleEntity.setLicensePlate(vehicleEvent.getVehicleId().toString());
        return vehicleRepository.save(newVehicleEntity);
    }

    private Mono<TripEntity> closeCurrentTrip(TripEntity currentTrip, VehicleEvent event) {
        currentTrip.setStopTs(event.getTimestamp());
        return tripRepository.save(currentTrip);
    }

    private Mono<TripEntity> createNewTripEntity(VehicleEntity vehicle, VehicleEvent event) {
        TripEntity tripEntity = new TripEntity();
        tripEntity.setStartTs(event.getTimestamp());
        tripEntity.setStopTs(null); // open trip
        tripEntity.setVehicleId(vehicle.getId());
        return tripRepository.save(tripEntity);
    }



}
