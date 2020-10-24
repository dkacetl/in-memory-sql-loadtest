package org.dkacetl.inmemorysqlloadtest.simulator;

import org.dkacetl.inmemorysqlloadtest.calculation.VehiclesEventProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

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

    @Bean
    public VehiclesSimulatorProducer vehicleSimulatorProducer() {
        return new VehiclesSimulatorProducer();
    }

    /**
     * Start producing when app is ready and connected to resources (db etc.)
     */
    @EventListener(ApplicationReadyEvent.class)
    public void connectSimulatorToProcessor() {
        vehicleSimulatorProducer().vehicleEventStream().
                forEach((ve) -> {
                    LOGGER.info("Event from vehicle achieved: " + ve.toString());
                    vehiclesEventProcessor.process(ve).subscribe(
                            (e) -> LOGGER.info("Entity processed " + e)
                    );

                } );
    }
}
