package org.dkacetl.inmemorysqlloadtest.simulator;

import org.dkacetl.inmemorysqlloadtest.events.VehicleEvent;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

public class VehiclesSimulatorProducer {

    // Status of simulator - last vehicles events
    // last events are source for new events.
    private Map<Long, VehicleEvent> nextVehiclesEventMap;

    // source of randomize
    private final Random random = new Random();

    @Autowired
    private VehiclesSimulatorProperties vehiclesSimulatorProperties;

    public Stream<VehicleEvent> vehicleEventStream() {
        nextVehiclesEventMap = new HashMap<>(vehiclesSimulatorProperties.getVehiclesCount());

        Stream<VehicleEvent> vehicleEventStream = Stream.generate(
                () -> {
                    long randomVehicleId = (random.nextInt(vehiclesSimulatorProperties.getVehiclesCount()));

                    VehicleEvent currentVehicleEvent =
                            nextVehiclesEventMap.getOrDefault(randomVehicleId, generateFirstVehicleEvent(randomVehicleId));

                    // prepare new event for next iteration
                    nextVehiclesEventMap.put(randomVehicleId, generateNextVehicleEvent(currentVehicleEvent));

                    try {
                        Thread.sleep(vehiclesSimulatorProperties.getEmittingRate());
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return currentVehicleEvent;
                });
        if (vehiclesSimulatorProperties.getEventLimit()>0) {
            return vehicleEventStream.limit(vehiclesSimulatorProperties.getEventLimit()); // limit of emitted messages
        } else {
            return vehicleEventStream;
        }
    }

    private VehicleEvent generateNextVehicleEvent(VehicleEvent lastVehicleEvent) {
        VehicleEvent vehicleEvent = new VehicleEvent();
        vehicleEvent.setVehicleId(lastVehicleEvent.getVehicleId());
        vehicleEvent.setLatitude(lastVehicleEvent.getLatitude() + (0.0001F * (random.nextFloat()-0.5f)));
        vehicleEvent.setLongitude(lastVehicleEvent.getLongitude() + (0.0001F * (random.nextFloat()-0.5f))); //((random.nextFloat()/Float.MAX_VALUE)/100.0F)
        vehicleEvent.setEngineOn((random.nextInt()%100)==1);
        vehicleEvent.setTimestamp(Instant.now());
        return vehicleEvent;
    }

    private VehicleEvent generateFirstVehicleEvent(long vehicleId) {
        VehicleEvent vehicleEvent = new VehicleEvent();
        vehicleEvent.setVehicleId(vehicleId);
        vehicleEvent.setLatitude(random.nextFloat());
        vehicleEvent.setLongitude(random.nextFloat());
        vehicleEvent.setEngineOn(false);
        vehicleEvent.setTimestamp(Instant.now());
        return vehicleEvent;
    }

//    This is just experiments, to do create Flux based stream
//
//    public Flux<VehicleEvent> vehicleEventFlux() {
//        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
//        Flux<VehicleEvent> generator = Flux.generate( sink -> {
//            VehicleEvent vehicleEvent = generateVehicleEvent();
//            sink.next(vehicleEvent);
//        });
//        return Flux.zip(interval, generator).map(Tuple2::getT2);
//    }
}
