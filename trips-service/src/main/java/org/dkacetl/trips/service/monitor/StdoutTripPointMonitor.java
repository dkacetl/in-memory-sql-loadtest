package org.dkacetl.trips.service.monitor;

import org.dkacetl.trips.service.db.model.TripPointEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.function.Consumer;

@Component
public class StdoutTripPointMonitor implements Consumer<TripPointEntity> {

    private static final Logger LOGGER = LoggerFactory.getLogger(StdoutTripPointMonitor.class);

    private TripPointEntity lastTripPointEntity;

    private long eventCount;

    private Thread monitorThread;

    @Override
    public void accept(TripPointEntity tripPointEntity) {
        lastTripPointEntity = tripPointEntity;
        eventCount++;
    }

    @PostConstruct
    public void init() {
        monitorThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    return;
                }
                LOGGER.info(eventCount + " point: " + lastTripPointEntity);
            }
        });
        monitorThread.start();
    }

    @PreDestroy
    public void done() {
        monitorThread.interrupt();
    }

}
