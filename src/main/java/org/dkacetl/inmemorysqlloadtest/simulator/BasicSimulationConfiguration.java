package org.dkacetl.inmemorysqlloadtest.simulator;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "simulation", havingValue = "basic", matchIfMissing = false)
public class BasicSimulationConfiguration {

    @Bean
    public SimulatorCore simulatorCore() {
        return new SimulatorCore();
    }

    @Bean
    public VehicleSimulatorProducer vehicleSimulatorProducer() {
        return new VehicleSimulatorProducer();
    }
}
