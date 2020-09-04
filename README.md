# Load Test for In-memory-sql

The aim for this project is to test performance and stability of pure 
in-memory sql database (H2 in this case) during high data changes (inserts, updates) and computations (selects with joins etc.)

## Telematics solution example

Inspiration comes from automotive/telematics domain. Let's imagine big amount of simple data stream 
with simple structure and data:

* Vehicle ID
* GPS position 
* Velocity
* Acceleration
* ...   

These data are collected into sophisticated queue (Azure Event Hub, Apache Kafka...) with 
retention/persistence for 14 days, for example.

All these data can be processed and transformed into better structure (into sql) which allows to do some 
calculations and reporting, but with time-window limitations. 
In this case there will be collected and structured history last few days.

# Trips/Trip points use-case

Example collects all points of multiple vehicles and organize them into "Trips" i.e. 
groups of paths when engine was working and vehicle was moving. 

* Trip : single vehicle path (trip) including distance, starting/stopping time, avg. speed etc.  
* Trip Point : detail of point with geo-locations

# Outage

Important condition : to have retention storage of raw data in queue. When service crashed, must be able to 
reload all the data from queue which causes CPU resource consumption. For these cases a snapshoting 
of in-memory h2 state can be used.



