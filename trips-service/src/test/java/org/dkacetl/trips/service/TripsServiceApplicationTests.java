package org.dkacetl.trips.service;

import org.dkacetl.trips.api.simulator.VehicleEventsProducer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Stream;

@SpringBootTest
class TripsServiceApplicationTests {

	@MockBean
	public VehicleEventsProducer vehicleEventsProducer;


	@Test
	void contextLoads() {
		Mockito.when(vehicleEventsProducer.vehicleEventStream()).thenReturn(Stream.empty());
	}

}
