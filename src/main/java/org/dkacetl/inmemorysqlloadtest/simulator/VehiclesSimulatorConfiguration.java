package org.dkacetl.inmemorysqlloadtest.simulator;

import org.dkacetl.inmemorysqlloadtest.calculation.VehiclesEventProcessor;
import org.dkacetl.inmemorysqlloadtest.db.model.TripPointEntity;
import org.dkacetl.inmemorysqlloadtest.db.repository.TripPointRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

/**
 * Vehicles-simulator configuration
 *
 * connect infinity simulator of vehicles to VehiclesEventProcessor
 */
@Configuration
@EnableConfigurationProperties(VehiclesSimulatorProperties.class)
@ConditionalOnProperty(prefix = "vehicles-simulator", value = "enabled", havingValue = "true")
public class VehiclesSimulatorConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehiclesSimulatorConfiguration.class);

    @Autowired
    private VehiclesEventProcessor vehiclesEventProcessor;

    private TripPointEntity lastTripPointEntity;

    private long eventCount;

    @Bean
    public VehiclesSimulatorProducer vehicleSimulatorProducer() {
        return new VehiclesSimulatorProducer();
    }

    /**
     * Start producing when app is ready and connected to resources (db etc.)
     */
    @EventListener(ApplicationReadyEvent.class)
    public void connectSimulatorToProcessor() {
        // TODO:
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
        vehicleSimulatorProducer().vehicleEventStream().
                forEach((ve) -> {
                    eventCount++;
                    vehiclesEventProcessor.process(ve).subscribe(
                            (tpe) -> lastTripPointEntity = tpe
                    );
                });
    }
}
