package org.dkacetl.trips.service;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

public class ReactorTest {

    @Test
    public void experiment() {
        Flux.create( (fluxSink -> {
            fluxSink.currentContext().put("test","test");
            fluxSink.next("test");
            fluxSink.complete();
        }));

        Flux.fromArray(new String[] {"a", "b", "c"})
                .subscribe(

                );
    }
}
