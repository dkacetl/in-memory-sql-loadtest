package org.dkacetl.inmemorysqlloadtest.db.repository;

import org.dkacetl.inmemorysqlloadtest.db.model.TripPointEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TripPointRepository extends ReactiveCrudRepository<TripPointEntity, Long> {
}
