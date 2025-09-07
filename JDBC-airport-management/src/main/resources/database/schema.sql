-- Basic reference tables (nothing depends on anything) --

-- 1
CREATE TABLE person_sex
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
    control_type_id SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    control_type VARCHAR (40) UNIQUE NOT NULL
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


-- Contact tables --

-- 11
CREATE TABLE airline_contacts
(
    contact_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    contact_name VARCHAR (100) NOT NULL,
    email VARCHAR (100) UNIQUE NOT NULL,
    phone VARCHAR (30) NOT NULL,
    headquarter_city VARCHAR (25) NOT NULL,
    notes TEXT
);

-- 12
CREATE TABLE customer_contacts
(
    contact_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email VARCHAR (100) UNIQUE NOT NULL,
    phone VARCHAR (30) NOT NULL,
    city VARCHAR (25),
    address VARCHAR (200),
    notes TEXT
);

-- 13
CREATE TABLE employee_contacts
(
    contact_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email VARCHAR (100) UNIQUE NOT NULL,
    phone VARCHAR (30) NOT NULL,
    city VARCHAR (25) NOT NULL,
    address VARCHAR (200) NOT NULL,
    notes TEXT
);

-- 14
CREATE TABLE emergency_contacts
(
    contact_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    contact_name VARCHAR (100) NOT NULL,
    relation VARCHAR (30),
    phone VARCHAR (20) NOT NULL
);


-- Basic entity tables --

-- 15
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

-- 16
CREATE TABLE airlines
(
    airline_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    iata VARCHAR (2) UNIQUE NOT NULL,
    icao VARCHAR (3) UNIQUE NOT NULL,
    name VARCHAR (60) NOT NULL,
    contact INT NOT NULL,

    CONSTRAINT airline_contacts_fk FOREIGN KEY (contact)
        REFERENCES airline_contacts(contact_id)
);

-- 17
CREATE TABLE customers
(
    customer_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR (50) NOT NULL,
    last_name VARCHAR (50) NOT NULL,
    passport_country VARCHAR (20) NOT NULL,
    passport_number VARCHAR (20) NOT NULL,
    contact INT NOT NULL,

    CONSTRAINT customer_contacts_fk FOREIGN KEY (contact)
        REFERENCES customer_contacts(contact_id),
    CONSTRAINT unique_passport_customers UNIQUE (passport_country, passport_number)
);

-- 18
CREATE TABLE crews
(
    employee_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pilot_license_number VARCHAR (20),
    first_name VARCHAR (50) NOT NULL,
    last_name VARCHAR (50) NOT NULL,
    sex SMALLINT NOT NULL,
    birth_date DATE NOT NULL,
    passport_country VARCHAR (20) NOT NULL,
    passport_number VARCHAR (20) NOT NULL,

    CONSTRAINT sex_fk FOREIGN KEY (sex)
        REFERENCES person_sex(sex_id),
    CONSTRAINT unique_passport_crews UNIQUE (passport_country, passport_number)
);

-- 19
CREATE TABLE airport_employees
(
    employee_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR (50) NOT NULL,
    last_name VARCHAR (50) NOT NULL,
    sex SMALLINT NOT NULL,
    birth_date DATE NOT NULL,
    passport_country VARCHAR (20) NOT NULL,
    passport_number VARCHAR (20) NOT NULL,
    contact INT NOT NULL,
    emergency_contact BIGINT NOT NULL,

    CONSTRAINT sex_fk FOREIGN KEY (sex)
        REFERENCES person_sex(sex_id),
    CONSTRAINT employee_contacts_fk FOREIGN KEY (contact)
        REFERENCES employee_contacts(contact_id),
    CONSTRAINT employee_emergency_contacts_fk FOREIGN KEY (emergency_contact)
        REFERENCES emergency_contacts(contact_id),
    CONSTRAINT unique_passport_airport_employees UNIQUE (passport_country, passport_number)
);

-- 20
CREATE TABLE passengers
(
    passenger_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR (50) NOT NULL,
    last_name VARCHAR (50) NOT NULL,
    sex SMALLINT NOT NULL,
    age SMALLINT NOT NULL,
    passport_country VARCHAR (20) NOT NULL,
    passport_number VARCHAR (20) NOT NULL,

    CONSTRAINT sex_fk FOREIGN KEY (sex)
        REFERENCES person_sex(sex_id),
    CONSTRAINT unique_passport_passengers UNIQUE (passport_country, passport_number)
);

-- 21
CREATE TABLE airplanes
(
    airplane_id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    airline INT,
    registration_number VARCHAR (20) UNIQUE NOT NULL,
    model VARCHAR (30) NOT NULL,
    total_capacity SMALLINT NOT NULL,
    type SMALLINT NOT NULL,

    CONSTRAINT airline_fk FOREIGN KEY (airline)
        REFERENCES airlines(airline_id),
    CONSTRAINT airplines_type_fk FOREIGN KEY (type)
        REFERENCES types(type_id)
);


-- Central table --

-- 22
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

    CONSTRAINT airline_fk FOREIGN KEY (airline)
        REFERENCES airlines(airline_id),
    CONSTRAINT departure_airport_fk FOREIGN KEY (departure_airport)
        REFERENCES airports(airport_id),
    CONSTRAINT arrival_airport_fk FOREIGN KEY (arrival_airport)
        REFERENCES airports(airport_id),
    CONSTRAINT airplane_fk FOREIGN KEY (airplane)
        REFERENCES airplanes(airplane_id),
    CONSTRAINT responsible_dispatcher_fk FOREIGN KEY (responsible_dispatcher)
        REFERENCES airport_employees(employee_id),
    CONSTRAINT flights_type_fk FOREIGN KEY (type)
        REFERENCES types(type_id),
    CONSTRAINT customer_fk FOREIGN KEY (customer)
        REFERENCES customers(customer_id),
    CONSTRAINT status_fk FOREIGN KEY (status)
        REFERENCES statuses(status_id),
    CONSTRAINT passport_control_fk FOREIGN KEY (passport_control)
        REFERENCES control_types(control_type_id),
    CONSTRAINT departure_gate_fk FOREIGN KEY (departure_gate)
        REFERENCES gates(gate_id),
    CONSTRAINT arrival_gate_fk FOREIGN KEY (arrival_gate)
        REFERENCES gates(gate_id),
    CONSTRAINT departure_terminal_fk FOREIGN KEY (departure_terminal)
        REFERENCES terminals(terminal_id),
    CONSTRAINT arrival_terminal_fk FOREIGN KEY (arrival_terminal)
        REFERENCES terminals(terminal_id),
    CONSTRAINT runway_fk FOREIGN KEY (runway)
        REFERENCES flight_runways(runway_id),
    CONSTRAINT unique_flight UNIQUE (flight_number, service_date)
);


-- Linking tables (many-to-many) --

-- 23
CREATE TABLE flight_check_in
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

-- 24
CREATE TABLE flight_bag_claims
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

-- 25
CREATE TABLE flight_crew
(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    flight_id BIGINT NOT NULL,
    crew_id BIGINT NOT NULL,
    role SMALLINT NOT NULL,

    CONSTRAINT flight_fk FOREIGN KEY (flight_id)
        REFERENCES flights(flight_id),
    CONSTRAINT crew_fk FOREIGN KEY (crew_id)
        REFERENCES crews(employee_id),
    CONSTRAINT role_fk FOREIGN KEY (role)
        REFERENCES crew_roles(role_id),
    CONSTRAINT unique_flight_crew_role UNIQUE (flight_id, crew_id, role)
);

-- 26
CREATE TABLE flight_passenger
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
