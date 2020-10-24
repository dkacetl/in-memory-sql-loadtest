package org.dkacetl.inmemorysqlloadtest.calculation;

import org.dkacetl.inmemorysqlloadtest.db.model.TripPointEntity;
import org.dkacetl.inmemorysqlloadtest.db.model.VehicleEntity;
import org.dkacetl.inmemorysqlloadtest.db.repository.VehicleRepository;
import org.dkacetl.inmemorysqlloadtest.events.VehicleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class VehiclesEventProcessor {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private TripProcessor tripProcessor;

    @Autowired
    private TripPointProcessor tripPointProcessor;

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
        return vehicleRepository.save(newVehicleEntity.setAsNew());
    }

}
