package org.dkacetl.inmemorysqlloadtest.simulator;

import org.dkacetl.inmemorysqlloadtest.calculation.EventProcessor;
import org.dkacetl.inmemorysqlloadtest.db.model.TripPointEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

public class SimulatorCore {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulatorCore.class);

    @Autowired
    private VehicleSimulatorProducer vehicleSimulatorProducer;

    @Autowired
    private EventProcessor eventProcessor;

    @EventListener(ApplicationReadyEvent.class)
    public void combine() {
        vehicleSimulatorProducer.vehicleEventStream().
                forEach((ve) -> {
                    LOGGER.info("Event from vehicle achieved: " + ve.toString());
                    eventProcessor.process(ve).subscribe(
                            (e) -> { LOGGER.info("Entity stored " + e); }
                    );

                } );
    }
}
