package org.dkacetl.inmemorysqlloadtest.core;

import org.dkacetl.inmemorysqlloadtest.calculation.TripsProcessor;
import org.dkacetl.inmemorysqlloadtest.events.VehicleEventProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SimulatorCore {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimulatorCore.class);

    @Autowired
    private VehicleEventProducer vehicleEventProducer;

    @Autowired
    private TripsProcessor tripsProcessor;

    @PostConstruct
    public void combine() {
        vehicleEventProducer.vehicleEventStream().
                forEach((ve) -> {
                    LOGGER.info("Event from vehicle achieved: "+ve.toString());
                    tripsProcessor.process(ve);
                } );
    }
}
