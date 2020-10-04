package org.dkacetl.inmemorysqlloadtest.calculation;

import org.dkacetl.inmemorysqlloadtest.db.model.VehicleEntity;
import org.dkacetl.inmemorysqlloadtest.db.repository.VehicleRepository;
import org.dkacetl.inmemorysqlloadtest.events.VehicleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TripsProcessor  {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Mono<VehicleEntity> process(VehicleEvent vehicleEvent) {

        VehicleEntity newVehicleEntity =new VehicleEntity();
        newVehicleEntity.setLicensePlate(vehicleEvent.getLicencePlate());

        return
                vehicleRepository
                        .findByLicensePlate(vehicleEvent.getLicencePlate())
                        .switchIfEmpty(vehicleRepository.save(newVehicleEntity));
    }

}
