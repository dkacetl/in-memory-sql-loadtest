package org.dkacetl.trips.service.calculation;

import org.dkacetl.trips.api.events.VehicleEvent;
import org.dkacetl.trips.service.db.model.TripPointEntity;
import org.dkacetl.trips.service.db.model.VehicleEntity;
import org.dkacetl.trips.service.db.repository.VehicleRepository;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class VehicleEventsProcessor implements Subscriber<VehicleEvent> {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TripProcessor tripProcessor;

    @Autowired
    private TripPointProcessor tripPointProcessor;

    @Override
    public void onSubscribe(Subscription subscription) {

    }

    @Override
    public void onNext(VehicleEvent vehicleEvent) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }

    public Mono<TripPointEntity> process(VehicleEvent event) {

        Mono<TripPointEntity> vehicleEntityMono = vehicleRepository
                .findById(event.getVehicleId())
                .switchIfEmpty(saveNewVehicle(event))
                .flatMap( (vehicle) -> tripProcessor.handleTrip(vehicle, event))
                .flatMap( (tripEntity) -> tripPointProcessor.saveNewTripPoint(tripEntity, event));

        return vehicleEntityMono;
    }

    private Mono<VehicleEntity> saveNewVehicle(VehicleEvent vehicleEvent) {
        VehicleEntity newVehicleEntity =new VehicleEntity();
        newVehicleEntity.setLicensePlate(vehicleEvent.getVehicleId().toString());
        newVehicleEntity.setId(vehicleEvent.getVehicleId());
        return vehicleRepository.save(newVehicleEntity.setAsNew())
                .doOnError(Throwable::printStackTrace);
    }

}
