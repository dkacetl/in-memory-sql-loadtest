package org.dkacetl.trips.simulator;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Vehicles-simulator configuration
 *
 * connect infinity simulator of vehicles to VehiclesEventProcessor
 */
@Configuration
@EnableConfigurationProperties(VehiclesSimulatorProperties.class)
@Import(VehiclesSimulatorProducer.class)
public class VehiclesSimulatorConfiguration {
}
