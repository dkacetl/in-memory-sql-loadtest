package org.dkacetl.inmemorysqlloadtest.simulator;

import org.dkacetl.inmemorysqlloadtest.events.VehicleEvent;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

public class VehicleSimulatorProducer {

    public Flux<VehicleEvent> vehicleEventFlux() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<VehicleEvent> generator = Flux.generate( sink -> {
            VehicleEvent vehicleEvent = generateVehicleEvent();
            sink.next(vehicleEvent);
        });
       return Flux.zip(interval, generator).map(Tuple2::getT2);
    }

    public Stream<VehicleEvent> vehicleEventStream() {
        return Stream.generate(
                () -> {
                    VehicleEvent vehicleEvent = generateVehicleEvent();

                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return vehicleEvent;
                });
    }

    private VehicleEvent generateVehicleEvent() {
        VehicleEvent vehicleEvent = new VehicleEvent();
        //vehicleEvent.setVehicleId(new Random().nextLong()%10);
        vehicleEvent.setVehicleId(1L);
        vehicleEvent.setLatitude(new Random().nextFloat());
        vehicleEvent.setLongitude(new Random().nextFloat());
        vehicleEvent.setEngineOn(false);
        return vehicleEvent;
    }
}
