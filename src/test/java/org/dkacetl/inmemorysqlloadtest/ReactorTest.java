package org.dkacetl.inmemorysqlloadtest;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

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
