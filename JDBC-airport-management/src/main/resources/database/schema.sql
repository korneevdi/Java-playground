-- Basic reference tables (nothing depends on anything) --

-- 1
CREATE TABLE sexes
(
    sex_id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    sex_name VARCHAR (20) UNIQUE NOT NULL
);

-- 2
CREATE TABLE types
(
    type_id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    type_name VARCHAR (30) UNIQUE NOT NULL
);

-- 3
CREATE TABLE statuses
(
    status_id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    status_name VARCHAR (40) UNIQUE NOT NULL
);

-- 4
CREATE TABLE crew_roles
(
    role_id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_name VARCHAR (50) UNIQUE NOT NULL
);

-- 5
CREATE TABLE control_types
(
    type_id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    type_name VARCHAR (40) UNIQUE NOT NULL
);

-- 6
CREATE TABLE check_in_counters
(
    counter_id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    counter_name VARCHAR (5) UNIQUE NOT NULL
);

-- 7
CREATE TABLE baggage_claims
(
    claim_id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    claim_name VARCHAR (3) UNIQUE NOT NULL
);

-- 8
CREATE TABLE gates
(
    gate_id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    gate_number VARCHAR (5) UNIQUE NOT NULL
);

-- 9
CREATE TABLE terminals
(
    terminal_id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    terminal_number VARCHAR (3) UNIQUE NOT NULL
);

-- 10
CREATE TABLE flight_runways
(
    runway_id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    runway_number VARCHAR (4) UNIQUE NOT NULL
);

-- 11
CREATE TABLE airport_employee_roles
(
    role_id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    role_name VARCHAR (100) UNIQUE NOT NULL
);


-- Contact tables --

-- 12
CREATE TABLE airline_contacts
(
    contact_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    contact_name VARCHAR (100) NOT NULL,
    contact_email VARCHAR (100) UNIQUE NOT NULL,
    contact_phone VARCHAR (30) NOT NULL,
    city VARCHAR (25) NOT NULL,
    notes TEXT
);

-- 13
CREATE TABLE customer_contacts
(
    contact_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    contact_email VARCHAR (100) UNIQUE NOT NULL,
    contact_phone VARCHAR (30) NOT NULL,
    city VARCHAR (25),
    address VARCHAR (200),
    notes TEXT
);

-- 14
CREATE TABLE airport_employee_contacts
(
    contact_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    contact_email VARCHAR (100) UNIQUE NOT NULL,
    contact_phone VARCHAR (30) NOT NULL,
    city VARCHAR (25) NOT NULL,
    address VARCHAR (200) NOT NULL,
    notes TEXT
);

-- 15
CREATE TABLE emergency_contacts
(
    contact_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    contact_name VARCHAR (100) NOT NULL,
    relation VARCHAR (30),
    contact_phone VARCHAR (30) NOT NULL
);


-- Basic entity tables --

-- 16
CREATE TABLE airports
(
    airport_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    iata VARCHAR (3) UNIQUE NOT NULL,
    icao VARCHAR (4) UNIQUE NOT NULL,
    name VARCHAR (100) UNIQUE NOT NULL,
    city VARCHAR (25) NOT NULL,
    country VARCHAR (25) NOT NULL,
    timezone VARCHAR (40) NOT NULL
);

-- 17
CREATE TABLE airlines
(
    airline_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    iata VARCHAR (2) UNIQUE NOT NULL,
    icao VARCHAR (3) UNIQUE NOT NULL,
    name VARCHAR (60) UNIQUE NOT NULL,
    contact INT NOT NULL,

    CONSTRAINT airlines_contact_fk FOREIGN KEY (contact)
        REFERENCES airline_contacts(contact_id)
);

-- 18
CREATE TABLE customers
(
    customer_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR (50) NOT NULL,
    last_name VARCHAR (50) NOT NULL,
    passport_country VARCHAR (20) NOT NULL,
    passport_number VARCHAR (20) NOT NULL,
    contact INT NOT NULL,

    CONSTRAINT customers_contact_fk FOREIGN KEY (contact)
        REFERENCES customer_contacts(contact_id),
    CONSTRAINT customers_unique_passport UNIQUE (passport_country, passport_number)
);

-- 19
CREATE TABLE flight_crews
(
    flight_crew_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pilot_license_number VARCHAR (20),
    first_name VARCHAR (50) NOT NULL,
    last_name VARCHAR (50) NOT NULL,
    sex SMALLINT NOT NULL,
    birth_date DATE NOT NULL,
    passport_country VARCHAR (20) NOT NULL,
    passport_number VARCHAR (20) NOT NULL,

    CONSTRAINT flight_crews_sex_fk FOREIGN KEY (sex)
        REFERENCES sexes(sex_id),
    CONSTRAINT flight_crews_unique_passport UNIQUE (passport_country, passport_number)
);

-- 20
CREATE TABLE airport_employees
(
    airport_employee_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR (50) NOT NULL,
    last_name VARCHAR (50) NOT NULL,
    role INT NOT NULL,
    sex SMALLINT NOT NULL,
    birth_date DATE NOT NULL,
    passport_country VARCHAR (20) NOT NULL,
    passport_number VARCHAR (20) NOT NULL,
    contact SMALLINT NOT NULL,
    emergency_contact BIGINT NOT NULL,

    CONSTRAINT airport_employees_role_fk FOREIGN KEY (role)
        REFERENCES airport_employee_roles(role_id),
    CONSTRAINT airport_employees_sex_fk FOREIGN KEY (sex)
        REFERENCES sexes(sex_id),
    CONSTRAINT airport_employees_contact_fk FOREIGN KEY (contact)
        REFERENCES airport_employee_contacts(contact_id),
    CONSTRAINT airport_employees_emergency_contact_fk FOREIGN KEY (emergency_contact)
        REFERENCES emergency_contacts(contact_id),
    CONSTRAINT airport_employees_unique_passport UNIQUE (passport_country, passport_number)
);

-- 21
CREATE TABLE passengers
(
    passenger_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR (50) NOT NULL,
    last_name VARCHAR (50) NOT NULL,
    sex SMALLINT NOT NULL,
    age SMALLINT NOT NULL,
    passport_country VARCHAR (20) NOT NULL,
    passport_number VARCHAR (20) NOT NULL,

    CONSTRAINT passengers_sex_fk FOREIGN KEY (sex)
        REFERENCES sexes(sex_id),
    CONSTRAINT passengers_unique_passport UNIQUE (passport_country, passport_number)
);

-- 22
CREATE TABLE airplanes
(
    airplane_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    airline INT,
    registration_number VARCHAR (20) UNIQUE NOT NULL,
    model VARCHAR (30) NOT NULL,
    total_capacity SMALLINT NOT NULL,
    type SMALLINT NOT NULL,

    CONSTRAINT airplanes_airline_fk FOREIGN KEY (airline)
        REFERENCES airlines(airline_id),
    CONSTRAINT airplanes_type_fk FOREIGN KEY (type)
        REFERENCES types(type_id)
);


-- Central table --

-- 23
CREATE TABLE flights
(
    flight_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    flight_number VARCHAR (10) NOT NULL,
    service_date DATE NOT NULL,
    airline INT NOT NULL,
    departure_airport INT NOT NULL,
    arrival_airport INT NOT NULL,
    scheduled_departure_time TIMESTAMPTZ NOT NULL,
    actual_departure_time TIMESTAMPTZ,
    scheduled_arrival_time TIMESTAMPTZ NOT NULL,
    actual_arrival_time TIMESTAMPTZ,
    airplane BIGINT NOT NULL,
    responsible_dispatcher BIGINT NOT NULL,
    type SMALLINT NOT NULL,
    customer BIGINT,
    status SMALLINT NOT NULL,
    passport_control SMALLINT NOT NULL,
    departure_gate SMALLINT,
    arrival_gate SMALLINT,
    departure_terminal SMALLINT,
    arrival_terminal SMALLINT,
    runway SMALLINT NOT NULL,

    CONSTRAINT flights_airline_fk FOREIGN KEY (airline)
        REFERENCES airlines(airline_id),
    CONSTRAINT flights_departure_airport_fk FOREIGN KEY (departure_airport)
        REFERENCES airports(airport_id),
    CONSTRAINT flights_arrival_airport_fk FOREIGN KEY (arrival_airport)
        REFERENCES airports(airport_id),
    CONSTRAINT flights_airplane_fk FOREIGN KEY (airplane)
        REFERENCES airplanes(airplane_id),
    CONSTRAINT flights_responsible_dispatcher_fk FOREIGN KEY (responsible_dispatcher)
        REFERENCES airport_employees(airport_employee_id),
    CONSTRAINT flights_type_fk FOREIGN KEY (type)
        REFERENCES types(type_id),
    CONSTRAINT flights_customer_fk FOREIGN KEY (customer)
        REFERENCES customers(customer_id),
    CONSTRAINT flights_status_fk FOREIGN KEY (status)
        REFERENCES statuses(status_id),
    CONSTRAINT flights_passport_control_fk FOREIGN KEY (passport_control)
        REFERENCES control_types(type_id),
    CONSTRAINT flights_departure_gate_fk FOREIGN KEY (departure_gate)
        REFERENCES gates(gate_id),
    CONSTRAINT flights_arrival_gate_fk FOREIGN KEY (arrival_gate)
        REFERENCES gates(gate_id),
    CONSTRAINT flights_departure_terminal_fk FOREIGN KEY (departure_terminal)
        REFERENCES terminals(terminal_id),
    CONSTRAINT flights_arrival_terminal_fk FOREIGN KEY (arrival_terminal)
        REFERENCES terminals(terminal_id),
    CONSTRAINT flights_runway_fk FOREIGN KEY (runway)
        REFERENCES flight_runways(runway_id),
    CONSTRAINT unique_flight UNIQUE (flight_number, service_date)
);


-- Linking tables (many-to-many) --

-- 24
CREATE TABLE flight_check_in_link
(
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    flight_id BIGINT NOT NULL,
    counter_id SMALLINT NOT NULL,

    CONSTRAINT flight_fk FOREIGN KEY (flight_id)
        REFERENCES flights(flight_id),
    CONSTRAINT counter_fk FOREIGN KEY (counter_id)
        REFERENCES check_in_counters(counter_id),
    CONSTRAINT unique_flight_counter UNIQUE (flight_id, counter_id)
);

-- 25
CREATE TABLE flight_bag_claims_link
(
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    flight_id BIGINT NOT NULL,
    claim_id SMALLINT NOT NULL,

    CONSTRAINT flight_fk FOREIGN KEY (flight_id)
        REFERENCES flights(flight_id),
    CONSTRAINT claim_fk FOREIGN KEY (claim_id)
        REFERENCES baggage_claims(claim_id),
    CONSTRAINT unique_flight_claim UNIQUE (flight_id, claim_id)
);

-- 26
CREATE TABLE flight_crew_link
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    flight_id BIGINT NOT NULL,
    crew_id BIGINT NOT NULL,
    role SMALLINT NOT NULL,

    CONSTRAINT flight_fk FOREIGN KEY (flight_id)
        REFERENCES flights(flight_id),
    CONSTRAINT crew_fk FOREIGN KEY (crew_id)
        REFERENCES flight_crews(flight_crew_id),
    CONSTRAINT crew_role_fk FOREIGN KEY (role)
        REFERENCES crew_roles(role_id),
    CONSTRAINT unique_flight_crew_role UNIQUE (flight_id, crew_id, role)
);

-- 27
CREATE TABLE flight_passenger_link
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    flight_id BIGINT NOT NULL,
    passenger_id BIGINT NOT NULL,
    checked_in BOOLEAN NOT NULL,
    passed_control BOOLEAN NOT NULL,
    boarded BOOLEAN NOT NULL,
    boarding_group SMALLINT,
    boarding_time TIMESTAMPTZ,
    seat_number VARCHAR (5),

    CONSTRAINT flight_fk FOREIGN KEY (flight_id)
        REFERENCES flights(flight_id),
    CONSTRAINT passenger_fk FOREIGN KEY (passenger_id)
        REFERENCES passengers(passenger_id),
    CONSTRAINT unique_flight_passenger UNIQUE (flight_id, passenger_id)
);
