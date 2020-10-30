package org.dkacetl.trips.api.simulator;


import org.dkacetl.trips.api.events.VehicleEvent;

import java.util.stream.Stream;

public interface VehicleEventsProducer {

    Stream<VehicleEvent> vehicleEventsStream();
}
