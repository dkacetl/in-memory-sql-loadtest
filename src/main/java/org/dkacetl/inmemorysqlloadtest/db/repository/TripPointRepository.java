//package org.dkacetl.inmemorysqlloadtest.db.repository;
//
//import org.dkacetl.inmemorysqlloadtest.db.model.TripPointEntity;
//import org.dkacetl.inmemorysqlloadtest.db.model.VehicleEntity;
//import org.springframework.data.r2dbc.repository.Query;
//import org.springframework.data.repository.reactive.ReactiveCrudRepository;
//import reactor.core.publisher.Flux;
//
//public interface TripPointRepository extends ReactiveCrudRepository<TripPointEntity, Long> {
//
//    @Query("SELECT id, licence_plate FROM vehicle v WHERE c.licence_plate = :licencePlate")
//    Flux<TripPointEntity> findByLicencePlate(String licencePlate);
//}
