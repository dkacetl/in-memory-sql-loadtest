package org.dkacetl.trips.service;

import org.dkacetl.trips.simulator.VehiclesSimulatorConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(VehiclesSimulatorConfiguration.class)
public class TripsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripsServiceApplication.class, args);
	}

}
