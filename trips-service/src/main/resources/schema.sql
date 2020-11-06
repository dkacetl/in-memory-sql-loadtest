
CREATE TABLE vehicle (id IDENTITY PRIMARY KEY, license_plate varchar(255));
CREATE TABLE trip (id IDENTITY PRIMARY KEY, vehicle_id BIGINT not null, start_ts TIMESTAMP, stop_ts TIMESTAMP, trip_points_count BIGINT);
CREATE TABLE trip_point (id IDENTITY PRIMARY KEY, trip_id BIGINT not null, timestamp TIMESTAMP, latitude Decimal(9,6), longitude Decimal(9,6), velocity number);

alter table trip add constraint vehicle_trip_ref foreign key (vehicle_id) references vehicle;
alter table trip_point add constraint trip_trip_point_ref foreign key (trip_id) references trip;

CREATE UNIQUE INDEX vehicle_license_plate_idx ON vehicle (license_plate);
CREATE INDEX trip_start_ts_idx ON trip (start_ts);
CREATE INDEX trip_stop_ts_idx ON trip (stop_ts);