package org.dkacetl.trips.simulator;

import org.dkacetl.trips.api.simulator.VehicleEventsProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public VehicleEventsProducer vehicleEventsProducer() {
        return new VehiclesSimulatorProducer();
    }
}
