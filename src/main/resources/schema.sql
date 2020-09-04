CREATE TABLE trip (id IDENTITY PRIMARY KEY, start_date TIMESTAMP, stop_date TIMESTAMP);
CREATE TABLE trip_data (id IDENTITY PRIMARY KEY, trip_id BIGINT, datetime TIMESTAMP, lat Decimal(9,6), lon Decimal(9,6));
alter table trip_data add constraint trip_trip_data_ref foreign key (trip_id) references trip;