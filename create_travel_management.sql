
DROP DATABASE IF EXISTS travel_management;
CREATE DATABASE travel_management;

use travel_management;

CREATE TABLE agencies (
    username VARCHAR(30),
    name VARCHAR(30),
    password VARCHAR(30)
);

CREATE TABLE travel_package (
    username VARCHAR(30),
    package_name VARCHAR(30)
);



CREATE TABLE destination (
	username VARCHAR(30),
    package_name VARCHAR(30),
    destination_name VARCHAR(30)
);


CREATE TABLE activity (
    activity_name VARCHAR(30),
    activity_desc VARCHAR(30),
    cost INT,
    capacity INT,
    destination_name VARCHAR(30)
);

create table passenger (
	passenger_name varchar(30),
    passenger_number int not null,
    category int not null,
    balance int not null
);

CREATE TABLE passenger_activity (
	username varchar(30),
    passenger_name varchar(30),
    passenger_number INT NOT NULL,
    activity_name VARCHAR(30),
    destination_name VARCHAR(30)
);

INSERT INTO agencies (username, name, password)
VALUES
    ('agency1', 'Agency A', 'pass123'),
    ('agency2', 'Agency B', 'pass456');
    
INSERT INTO travel_package (username, package_name)
VALUES
    ('agency1', 'Package Goa'),
    ('agency1', 'Package Delhi'),
    ('agency1', 'Package America'),
    ('agency1', 'Package Thailand'),
    ('agency2', 'Package Europe'),
    ('agency2', 'Package Goa');

INSERT INTO destination (username, package_name, destination_name)
VALUES
    ('agency1', 'Package Goa', 'Goa destination 1'),
    ('agency1', 'Package Goa', 'Goa destination 2'),
    ('agency1', 'Package Goa', 'Goa destination 3'),
    ('agency1', 'Package Delhi', 'Delhi destination 1'),
    ('agency1', 'Package Delhi', 'Delhi destination 2'),
    ('agency1', 'Package America', 'America destination 1'),
    ('agency1', 'Package America', 'America destination 2'),
    ('agency1', 'Package Thailand', 'Thailand destination 1'),
    ('agency1', 'Package Thailand', 'Thailand destination 2'),
    ('agency2', 'Package Europe', 'Europe destination 1'),
    ('agency2', 'Package Europe', 'Europe destination 2'),
    ('agency2', 'Package Goa', 'Goa destination 1'),
    ('agency2', 'Package Goa', 'Goa destination 4'),
    ('agency2', 'Package Goa', 'Goa destination 5');

INSERT INTO activity (activity_name, activity_desc, cost, capacity, destination_name)
VALUES
    ('Activity 1', 'Description 1', 50, 10, 'Goa destination 1'),
    ('Activity 11', 'Description 11', 50, 11, 'Goa destination 1'),
    ('Activity 111', 'Description 111', 50, 12, 'Goa destination 1'),
    ('Activity 2', 'Description 2', 50, 13, 'Goa destination 2'),
    ('Activity 22', 'Description 22', 50, 14, 'Goa destination 2'),
    ('Activity 3', 'Description 3', 50, 15,  'Goa destination 3'),
    ('Activity 4', 'Description 3', 50, 16,  'Goa destination 3'),
    ('Activity 5', 'Description 3', 50, 17, 'Goa destination 4'),
    ('Activity 6', 'Description 3', 50, 18, 'Goa destination 5'),
    ('Activity B', 'Description B', 40, 19, 'Delhi destination 1'),
    ('Activity B1', 'Description B1', 40, 20, 'Delhi destination 2'),
    ('Activity C', 'Description B', 40, 21, 'America destination 1'),
    ('Activity C1', 'Description B1', 40, 22, 'America destination 2'),
    ('Activity D', 'Description B', 40, 23, 'Thailand destination 1'),
    ('Activity D1', 'Description B1', 40, 24, 'Thailand destination 2'),
    ('Activity E', 'Description B', 40, 25, 'Europe destination 1'),
    ('Activity EE', 'Description B', 40, 26, 'Europe destination 1'),
    ('Activity E1', 'Description B1', 40, 27, 'Europe destination 2');

INSERT INTO passenger (passenger_name, passenger_number, category, balance)
VALUES
    ('Passenger 1', 101, 0, 200),
    ('Passenger 2', 102, 1, 150),
    ('Passenger 3', 103, 2, 180),
    ('Passenger 4', 104, 1, 220),
    ('Passenger 5', 105, 1, 250);
    
INSERT INTO passenger_activity (username, passenger_name, passenger_number, activity_name, destination_name)
VALUES
    ('agency1', 'Passenger 1', 101, 'Activity 1', 'Goa destination 1'),
    ('agency1', 'Passenger 1', 101, 'Activity 11', 'Goa destination 1'),
    ('agency1', 'Passenger 1', 101, 'Activity 2', 'Goa destination 2'),
    ('agency2', 'Passenger 1', 101, 'Activity C', 'Goa destination 1'),
    ('agency1', 'Passenger 2', 102, 'Activity 1', 'Goa destination 1'),
    ('agency2', 'Passenger 2', 102, 'Activity E', 'Europe destination 1'),
    ('agency2', 'Passenger 2', 102, 'Activity EE', 'Europe destination 1');