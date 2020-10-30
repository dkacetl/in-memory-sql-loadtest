package org.dkacetl.trips.service;

import org.dkacetl.trips.api.simulator.VehicleEventsProducer;
import org.dkacetl.trips.service.calculation.VehiclesEventProcessor;
import org.dkacetl.trips.service.db.model.TripPointEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

import java.time.Instant;
import java.util.stream.Stream;

/**
 * Vehicles-simulator configuration
 *
 * connect infinity simulator of vehicles to VehiclesEventProcessor
 */
@Configuration
public class VehicleEventsProcessingConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleEventsProcessingConfiguration.class);

    @Autowired
    private VehiclesEventProcessor vehiclesEventProcessor;

    @Autowired
    private VehicleEventsProducer vehicleEventsProducer;

    private TripPointEntity lastTripPointEntity;

    private long eventCount;

    /**
     * Start producing when app is ready and connected to resources (db etc.)
     */
    @EventListener(ApplicationReadyEvent.class)
    public void startProcessing() {

        // TODO: monitor of events
        new Thread(() -> {
            Stream.generate(() -> {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return Instant.now();
            }).forEach((instant) -> LOGGER.info(eventCount + " point: " + lastTripPointEntity));
        }).start();

        // TODO to flux for nonblocking forEach call
        vehicleEventsProducer.vehicleEventStream().
                forEach((ve) -> {
                    eventCount++;
                    vehiclesEventProcessor.process(ve).subscribe(
                            (tpe) -> lastTripPointEntity = tpe
                    );
                });
    }
}
