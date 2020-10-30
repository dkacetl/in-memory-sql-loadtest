package org.dkacetl.trips.service;

import org.dkacetl.trips.api.simulator.VehicleEventsProducer;
import org.dkacetl.trips.service.calculation.VehicleEventsProcessor;
import org.dkacetl.trips.service.monitor.StdoutTripPointMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import reactor.core.publisher.Flux;

/**
 * Vehicles-simulator configuration
 *
 * connect infinity simulator of vehicles to VehiclesEventProcessor
 */
@Configuration
public class VehicleEventsProcessingConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleEventsProcessingConfiguration.class);

    @Autowired
    private VehicleEventsProcessor vehicleEventsProcessor;

    @Autowired
    private VehicleEventsProducer vehicleEventsProducer;

    @Autowired
    private StdoutTripPointMonitor stdoutTripPointMonitor;

    /**
     * Start producing when app is ready and connected to resources (db etc.)
     */
    @EventListener(ApplicationReadyEvent.class)
    public void startProcessing() {
        Flux.fromStream(vehicleEventsProducer.vehicleEventsStream())
            .flatMap((vehicleEvent -> vehicleEventsProcessor.process(vehicleEvent)))
            .subscribe(stdoutTripPointMonitor);
    }
}
