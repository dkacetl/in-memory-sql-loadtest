package org.dkacetl.inmemorysqlloadtest.calculation;

import org.dkacetl.inmemorysqlloadtest.db.model.TripEntity;
import org.dkacetl.inmemorysqlloadtest.db.model.TripPointEntity;
import org.dkacetl.inmemorysqlloadtest.db.model.VehicleEntity;
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

    public Mono<TripPointEntity> process(VehicleEvent vehicleEvent) {

        Mono<VehicleEntity> vehicleEntityMono = vehicleRepository
                .findById(vehicleEvent.getVehicleId())
                .onErrorStop()
                .switchIfEmpty(vehicleRepository.save(createNewVehicleEntity(vehicleEvent)));

        VehicleEntity ve = vehicleEntityMono.block();

        Mono<TripEntity> tripEntityMono = tripRepository
                .findTripForEvent(vehicleEvent.getVehicleId());

        return Mono.empty();
    }

    private VehicleEntity createNewVehicleEntity(VehicleEvent vehicleEvent) {
        VehicleEntity newVehicleEntity =new VehicleEntity();
        newVehicleEntity.setLicensePlate(vehicleEvent.getVehicleId().toString());
        return newVehicleEntity;
    }


}
