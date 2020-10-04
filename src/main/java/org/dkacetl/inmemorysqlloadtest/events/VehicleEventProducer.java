package org.dkacetl.inmemorysqlloadtest.events;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

@Component
public class VehicleEventProducer {



    public Flux<VehicleEvent> vehicleEventFlux() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<VehicleEvent> generator = Flux.generate( sink -> {
            VehicleEvent vehicleEvent = generateVehicleEvent();
            sink.next(vehicleEvent);
        });

       return Flux.zip(interval, generator).map(Tuple2::getT2);
    }

    public Stream<VehicleEvent> vehicleEventStream() {
        Stream<VehicleEvent> vehicleEventStream = Stream.generate(
                () -> {
                    VehicleEvent vehicleEvent = generateVehicleEvent();

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return vehicleEvent;
                });

        return vehicleEventStream;
    }

    private VehicleEvent generateVehicleEvent() {
        VehicleEvent vehicleEvent = new VehicleEvent();
        vehicleEvent.setLicencePlate("ABC-" + new Random().nextInt(3));
        vehicleEvent.setLatitude(new Random().nextFloat());
        vehicleEvent.setLongitude(new Random().nextFloat());
        return vehicleEvent;
    }
}
