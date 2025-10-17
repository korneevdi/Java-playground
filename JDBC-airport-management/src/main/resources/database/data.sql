-- Basic reference tables (nothing depends on anything) --

-- 1. Sexes
INSERT INTO sexes (sex_name) VALUES
('male'),
('female'),
('diverse');

-- 2. Types of flights & airplanes
INSERT INTO types (type_name) VALUES
('passenger'),
('cargo'),
('private');

-- 3. Flight statuses
INSERT INTO statuses (status_name) VALUES
('scheduled'),
('boarding'),
('boarding_complete'),
('in_air'),
('landed'),
('completed'), -- landed and ready for disembarkation of passengers
('delayed'),
('cancelled');

-- 4. Crew roles in flights
INSERT INTO crew_roles (role_name) VALUES
('pilot'),              -- captain
('first officer'),      -- co-pilot
('flight engineer'),
('purser'),             -- lead flight attendant
('flight attendant'),
('relief pilot');       -- for long flights

-- 5. Control types for passengers
INSERT INTO control_types (type_name) VALUES
('none'),
('standard'),
('EU / Schengen control'),
('custom control');

-- 6. Check-in counters for departure flights
INSERT INTO check_in_counters (counter_number) VALUES
('A1'), ('A2'), ('A3'), ('A4'), ('A5'),         -- zone A
('A6'), ('A7'), ('A8'), ('A9'), ('A10'),
('A11'), ('A12'), ('A13'), ('A14'), ('A15'),
('A16'), ('A17'), ('A18'), ('A19'), ('A20'),
('A21'), ('A22'), ('A23'), ('A24'), ('A25'),
('A26'), ('A27'), ('A28'), ('A29'), ('A30'),
('B1'), ('B2'), ('B3'), ('B4'), ('B5'),         -- zone B
('B6'), ('B7'), ('B8'), ('B9'), ('B10'),
('B11'), ('B12'), ('B13'), ('B14'), ('B15'),
('B16'), ('B17'), ('B18'), ('B19'), ('B20');

-- 7. Baggage claims for arrival flights
INSERT INTO baggage_claims (claim_number) VALUES
('1'), ('2'), ('3'), ('4'), ('5'),
('6'), ('7'), ('8'), ('9'), ('10'),
('11'), ('12'), ('13'), ('14'), ('15'),
('16'), ('17'), ('18'), ('19'), ('20');

-- 8. Gates
INSERT INTO gates (gate_number) VALUES
('A1'), ('A2'), ('A3'), ('A4'), ('A5'),     -- terminal A
('A6'), ('A7'), ('A8'), ('A9'), ('A10'),
('B1'), ('B2'), ('B3'), ('B4'), ('B5'),     -- terminal B
('B6'), ('B7'), ('B8'), ('B9'), ('B10'),
('C1'), ('C2'), ('C3'), ('C4'), ('C5'),     -- terminal C
('C6'), ('C7'), ('C8'), ('C9'), ('C10'),
('D1'), ('D2'), ('D3'), ('D4'), ('D5'),     -- terminal D
('D6'), ('D7'), ('D8'), ('D9'), ('D10');

-- 9. Terminals
INSERT INTO terminals (terminal_number) VALUES
('A'), ('B'), ('C'), ('D');

-- 10. Runways
INSERT INTO flight_runways (runway_number) VALUES
('09L'), ('09R'), ('18L'), ('18R'), ('27L'), ('27R');

-- 11. Airport employee roles
INSERT INTO airport_employee_roles (role_name) VALUES
('Administrator'),      -- full access to the system (technical admin)
('Director'),           -- strategic management, can manage roles and personnel
('Dispatcher'),         -- flight dispatcher
('Check-in Agent'),     -- check-in counter employee
('Security Officer'),
('Customs Officer'),    -- for long flights
('Baggage Handler'),
('Technician'),         -- technical personnel (aircraft maintenance)
('Manager');            -- middle manager (e.g. flight or ground operations manager)

-- 12. Airline contacts
INSERT INTO airline_contacts (contact_name, contact_email, contact_phone, city, notes) VALUES
('American Airlines Contact', 'contact@aa.com', '+1-800-433-7300', 'Fort Worth', 'Major US carrier'),
('Delta Airlines Contact', 'contact@delta.com', '+1-800-221-1212', 'Atlanta', 'Major US carrier'),
('United Airlines Contact', 'contact@united.com', '+1-800-864-8331', 'Chicago', 'Major US carrier'),
('Southwest Airlines Contact', 'contact@southwest.com', '+1-800-435-9792', 'Dallas', 'Domestic US flights'),
('Alaska Airlines Contact', 'contact@alaskaair.com', '+1-800-252-7522', 'Seattle', 'US West Coast regional carrier'),
('JetBlue Contact', 'contact@jetblue.com', '+1-800-538-2583', 'New York', 'Domestic US and Caribbean'),
('Spirit Airlines Contact', 'contact@spirit.com', '+1-855-728-3555', 'Miramar', 'Low-cost US airline'),
('Hawaiian Airlines Contact', 'contact@hawaiianair.com', '+1-800-367-5320', 'Honolulu', 'Hawaii based airline'),
('Qantas Contact', 'contact@qantas.com', '+61-2-9691-3636', 'Sydney', 'Flag carrier of Australia'),
('Air New Zealand Contact', 'contact@airnz.com', '+64-9-336-5656', 'Auckland', 'Flag carrier of New Zealand'),
('Fiji Airways Contact', 'contact@fijiairways.com.fj', '+679-672-0888', 'Nadi', 'Flag carrier of Fiji'),
('Virgin Australia Contact', 'contact@virginaustralia.com', '+61-2-8222-2222', 'Brisbane', 'Australian carrier'),
('Emirates Contact', 'contact@emirates.com', '+971-600-555555', 'Dubai', 'Middle East international carrier'),
('Singapore Airlines Contact', 'contact@singaporeair.com', '+65-6223-8888', 'Singapore', 'Asian international carrier'),
('Cathay Pacific Contact', 'contact@cathaypacific.com', '+852-2747-3333', 'Hong Kong', 'Asian international carrier'),
('British Airways Contact', 'contact@ba.com', '+44-844-493-0787', 'London', 'European international carrier'),
('Air France Contact', 'contact@airfrance.com', '+33-9-69-39-36-54', 'Paris', 'European international carrier'),
('Lufthansa Contact', 'contact@lufthansa.com', '+49-69-86-799-799', 'Frankfurt', 'European international carrier'),
('Korean Air Contact', 'contact@koreanair.com', '+82-2-2656-2001', 'Seoul', 'Asian international carrier'),
('Japan Airlines Contact', 'contact@jal.com', '+81-3-5460-0511', 'Tokyo', 'Asian international carrier');

-- 13. Customer contacts
INSERT INTO customer_contacts (contact_email, contact_phone, city, address, notes) VALUES
('john.doe@example.com', '+1-310-555-0101', 'Los Angeles', '123 Sunset Blvd', 'Private jet client'),
('jane.smith@example.com', '+1-310-555-0102', 'Los Angeles', '456 Hollywood Blvd', 'Frequent flyer, private'),
('richard.roe@example.com', '+1-310-555-0103', 'Beverly Hills', '789 Rodeo Dr', 'VIP cargo customer'),
('emily.white@example.com', '+1-310-555-0104', 'Santa Monica', '321 Ocean Ave', 'Private charter'),
('oliver.brown@example.com', '+1-310-555-0105', 'Los Angeles', '654 Vine St', 'Cargo logistics'),
('mia.johnson@example.com', '+1-310-555-0106', 'Pasadena', '987 Colorado Blvd', 'Private flights'),
('liam.davis@example.com', '+1-310-555-0107', 'Los Angeles', '111 Wilshire Blvd', 'Corporate client'),
('sophia.miller@example.com', '+1-310-555-0108', 'Long Beach', '222 Pine Ave', 'VIP charter'),
('noah.wilson@example.com', '+1-310-555-0109', 'Los Angeles', '333 Maple St', 'Cargo logistics'),
('ava.moore@example.com', '+1-310-555-0110', 'Burbank', '444 Elm St', 'Private flights');

-- 14. Employee contacts
INSERT INTO airport_employee_contacts (contact_email, contact_phone, city, address, notes) VALUES
('alice.turner@airport.com', '+1-310-555-1001', 'Los Angeles', '100 Airport Rd', 'Dispatcher'),
('bob.harris@airport.com', '+1-310-555-1002', 'Los Angeles', '101 Airport Rd', 'Dispatcher'),
('carol.lee@airport.com', '+1-310-555-1003', 'Los Angeles', '102 Airport Rd', 'Ground staff'),
('david.king@airport.com', '+1-310-555-1004', 'Los Angeles', '103 Airport Rd', 'Ground staff'),
('emma.clark@airport.com', '+1-310-555-1005', 'Los Angeles', '104 Airport Rd', 'Security'),
('frank.martin@airport.com', '+1-310-555-1006', 'Los Angeles', '105 Airport Rd', 'Security'),
('grace.evans@airport.com', '+1-310-555-1007', 'Los Angeles', '106 Airport Rd', 'Operations'),
('harry.adams@airport.com', '+1-310-555-1008', 'Los Angeles', '107 Airport Rd', 'Operations'),
('isabel.brown@airport.com', '+1-310-555-1009', 'Los Angeles', '108 Airport Rd', 'Maintenance'),
('jack.wilson@airport.com', '+1-310-555-1010', 'Los Angeles', '109 Airport Rd', 'Maintenance'),
('karen.moore@airport.com', '+1-310-555-1011', 'Los Angeles', '110 Airport Rd', 'Customer Service'),
('leo.miller@airport.com', '+1-310-555-1012', 'Los Angeles', '111 Airport Rd', 'Customer Service'),
('mia.davis@airport.com', '+1-310-555-1013', 'Los Angeles', '112 Airport Rd', 'Administration'),
('noah.smith@airport.com', '+1-310-555-1014', 'Los Angeles', '113 Airport Rd', 'Administration'),
('olivia.johnson@airport.com', '+1-310-555-1015', 'Los Angeles', '114 Airport Rd', 'Administration');

-- 15. Emergency contacts
INSERT INTO emergency_contacts (contact_name, contact_relation, contact_phone) VALUES
('Mike Turner', 'Husband', '+1-310-555-2002'),
('Laura Harris', 'Wife', '+1-310-555-2001'),
('Peter Lee', 'Brother', '+1-310-555-2003'),
('Nina King', 'Sister', '+1-310-555-2004'),
('Sam Clark', 'Husband', '+1-310-555-2005'),
('Linda Martin', 'Wife', '+1-310-555-2006'),
('Ethan Evans', 'Brother', '+1-310-555-2007'),
('Olga Adams', 'Sister', '+1-310-555-2008'),
('Henry Brown', 'Father', '+1-310-555-2009'),
('Jane Wilson', 'Mother', '+1-310-555-2010'),
('Tom Moore', 'Husband', '+1-310-555-2011'),
('Anna Miller', 'Wife', '+1-310-555-2012'),
('Chris Davis', 'Brother', '+1-310-555-2013'),
('Sophia Smith', 'Mother', '+1-310-555-2014'),
('Paul Johnson', 'Father', '+1-310-555-2015');

-- 16. Airports
INSERT INTO airports (iata, icao, name, city, country, timezone) VALUES
('LAX', 'KLAX', 'Los Angeles International Airport', 'Los Angeles', 'USA', 'America/Los_Angeles'),
('JFK', 'KJFK', 'John F. Kennedy International Airport', 'New York', 'USA', 'America/New_York'),
('SFO', 'KSFO', 'San Francisco International Airport', 'San Francisco', 'USA', 'America/Los_Angeles'),
('ORD', 'KORD', 'O''Hare International Airport', 'Chicago', 'USA', 'America/Chicago'),
('ATL', 'KATL', 'Hartsfield–Jackson Atlanta International Airport', 'Atlanta', 'USA', 'America/New_York'),
('SEA', 'KSEA', 'Seattle–Tacoma International Airport', 'Seattle', 'USA', 'America/Los_Angeles'),
('MEL', 'YMML', 'Melbourne Airport', 'Melbourne', 'Australia', 'Australia/Melbourne'),
('SYD', 'YSSY', 'Sydney Kingsford Smith Airport', 'Sydney', 'Australia', 'Australia/Sydney'),
('AKL', 'NZAA', 'Auckland Airport', 'Auckland', 'New Zealand', 'Pacific/Auckland'),
('CHC', 'NZCH', 'Christchurch Airport', 'Christchurch', 'New Zealand', 'Pacific/Auckland'),
('HNL', 'PHNL', 'Daniel K. Inouye International Airport', 'Honolulu', 'USA', 'Pacific/Honolulu'),
('NAN', 'NFFN', 'Nadi International Airport', 'Nadi', 'Fiji', 'Pacific/Fiji'),
('PPT', 'NTAA', 'Faa''a International Airport', 'Papeete', 'French Polynesia', 'Pacific/Tahiti'),
('HKG', 'VHHH', 'Hong Kong International Airport', 'Hong Kong', 'China', 'Asia/Hong_Kong'),
('NRT', 'RJAA', 'Narita International Airport', 'Tokyo', 'Japan', 'Asia/Tokyo'),
('ICN', 'RKSI', 'Incheon International Airport', 'Seoul', 'South Korea', 'Asia/Seoul'),
('DXB', 'OMDB', 'Dubai International Airport', 'Dubai', 'UAE', 'Asia/Dubai'),
('LHR', 'EGLL', 'Heathrow Airport', 'London', 'UK', 'Europe/London'),
('CDG', 'LFPG', 'Charles de Gaulle Airport', 'Paris', 'France', 'Europe/Paris'),
('FRA', 'EDDF', 'Frankfurt Airport', 'Frankfurt', 'Germany', 'Europe/Berlin'),
('AMS', 'EHAM', 'Amsterdam Airport Schiphol', 'Amsterdam', 'Netherlands', 'Europe/Amsterdam'),
('GRU', 'SBGR', 'São Paulo–Guarulhos International Airport', 'São Paulo', 'Brazil', 'America/Sao_Paulo'),
('EZE', 'SAEZ', 'Ministro Pistarini International Airport', 'Buenos Aires', 'Argentina', 'America/Argentina/Buenos_Aires'),
('JNB', 'FAOR', 'O. R. Tambo International Airport', 'Johannesburg', 'South Africa', 'Africa/Johannesburg'),
('CPT', 'FACT', 'Cape Town International Airport', 'Cape Town', 'South Africa', 'Africa/Johannesburg'),
('SIN', 'WSSS', 'Singapore Changi Airport', 'Singapore', 'Singapore', 'Asia/Singapore'),
('BKK', 'VTBS', 'Suvarnabhumi Airport', 'Bangkok', 'Thailand', 'Asia/Bangkok'),
('KUL', 'WMKK', 'Kuala Lumpur International Airport', 'Kuala Lumpur', 'Malaysia', 'Asia/Kuala_Lumpur'),
('MCO', 'KMCO', 'Orlando International Airport', 'Orlando', 'USA', 'America/New_York'),
('LAS', 'KLAS', 'McCarran International Airport', 'Las Vegas', 'USA', 'America/Los_Angeles');

-- 17. Airlines
INSERT INTO airlines (iata, icao, name, contact) VALUES
('AA', 'AAL', 'American Airlines', 1),
('DL', 'DAL', 'Delta Airlines', 2),
('UA', 'UAL', 'United Airlines', 3),
('WN', 'SWA', 'Southwest Airlines', 4),
('AS', 'ASA', 'Alaska Airlines', 5),
('B6', 'JBU', 'JetBlue', 6),
('NK', 'NKS', 'Spirit Airlines', 7),
('HA', 'HAL', 'Hawaiian Airlines', 8),
('QF', 'QFA', 'Qantas', 9),
('NZ', 'ANZ', 'Air New Zealand', 10),
('FJ', 'FJI', 'Fiji Airways', 11),
('VA', 'VOZ', 'Virgin Australia', 12),
('EK', 'UAE', 'Emirates', 13),
('SQ', 'SIA', 'Singapore Airlines', 14),
('CX', 'CPA', 'Cathay Pacific', 15),
('BA', 'BAW', 'British Airways', 16),
('AF', 'AFR', 'Air France', 17),
('LH', 'DLH', 'Lufthansa', 18),
('KE', 'KAL', 'Korean Air', 19),
('JL', 'JAL', 'Japan Airlines', 20);

-- 18. Customers (for cargo & private flights)
INSERT INTO customers (first_name, last_name, passport_country, passport_number, contact) VALUES
('John', 'Doe', 'US', 'X1234567', 1),
('Jane', 'Smith', 'US', 'X2345678', 2),
('Richard', 'Roe', 'US', 'X3456789', 3),
('Emily', 'White', 'US', 'X4567890', 4),
('Oliver', 'Brown', 'US', 'X5678901', 5),
('Mia', 'Johnson', 'US', 'X6789012', 6),
('Liam', 'Davis', 'US', 'X7890123', 7),
('Sophia', 'Miller', 'US', 'X8901234', 8),
('Noah', 'Wilson', 'US', 'X9012345', 9),
('Ava', 'Moore', 'US', 'X0123456', 10);

-- 19. Crews
INSERT INTO flight_crews (pilot_license_number, first_name, last_name, sex, birth_date, passport_country, passport_number) VALUES
('PILOT001', 'James', 'Smith', 1, '1978-04-15', 'USA', 'A12345678'),     -- pilots
('PILOT002', 'Emily', 'Johnson', 2, '1982-09-10', 'USA', 'B23456789'),
('PILOT003', 'Liam', 'Brown', 1, '1985-06-20', 'USA', 'C34567890'),
('PILOT004', 'Olivia', 'Davis', 2, '1990-01-30', 'USA', 'D45678901'),
('PILOT005', 'Noah', 'Miller', 1, '1975-12-12', 'USA', 'E56789012'),
(NULL, 'Sophia', 'Wilson', 2, '1993-03-05', 'USA', 'F67890123'),         -- cabin crew
(NULL, 'Mason', 'Moore', 1, '1988-07-18', 'USA', 'G78901234'),
(NULL, 'Isabella', 'Taylor', 2, '1991-05-22', 'USA', 'H89012345'),
(NULL, 'Ethan', 'Anderson', 1, '1980-11-11', 'USA', 'I90123456'),
(NULL, 'Mia', 'Thomas', 2, '1986-08-08', 'USA', 'J01234567'),
(NULL, 'Alexander', 'Jackson', 1, '1979-02-28', 'USA', 'K12345678'),
(NULL, 'Charlotte', 'White', 2, '1992-10-15', 'USA', 'L23456789'),
(NULL, 'Benjamin', 'Harris', 1, '1983-09-09', 'USA', 'M34567890'),
(NULL, 'Amelia', 'Martin', 2, '1987-12-02', 'USA', 'N45678901'),
(NULL, 'William', 'Thompson', 1, '1976-06-06', 'USA', 'O56789012'),
(NULL, 'Evelyn', 'Garcia', 2, '1994-04-18', 'USA', 'P67890123'),
(NULL, 'Daniel', 'Martinez', 1, '1981-01-25', 'USA', 'Q78901234'),
(NULL, 'Harper', 'Robinson', 2, '1989-03-12', 'USA', 'R89012345'),
(NULL, 'Jacob', 'Clark', 1, '1984-07-07', 'USA', 'S90123456'),
(NULL, 'Abigail', 'Rodriguez', 2, '1990-09-30', 'USA', 'T01234567');

-- 20. Airport employees
INSERT INTO airport_employees (first_name, last_name, role, sex, birth_date, passport_country, passport_number, contact, emergency_contact) VALUES
('Alice', 'Turner', 1, 2, '1980-05-15', 'US', 'E1234567', 1, 1),
('Bob', 'Harris', 2, 1, '1978-08-22', 'US', 'E2345678', 2, 2),
('Carol', 'Lee', 3, 2, '1985-11-02', 'US', 'E3456789', 3, 3),
('David', 'King', 3, 1, '1982-03-18', 'US', 'E4567890', 4, 4),
('Emma', 'Clark', 3, 2, '1990-07-12', 'US', 'E5678901', 5, 5),
('Frank', 'Martin', 3, 1, '1979-01-25', 'US', 'E6789012', 6, 6),
('Grace', 'Evans', 4, 2, '1983-09-30', 'US', 'E7890123', 7, 7),
('Harry', 'Adams', 5, 1, '1981-12-05', 'US', 'E8901234', 8, 8),
('Isabel', 'Brown', 4, 2, '1986-04-17', 'US', 'E9012345', 9, 9),
('Jack', 'Wilson', 6, 1, '1984-06-20', 'US', 'E0123456', 10, 10),
('Karen', 'Moore', 7, 2, '1991-02-28', 'US', 'E1123456', 11, 11),
('Leo', 'Miller', 8, 1, '1987-10-10', 'US', 'E2123456', 12, 12),
('Mia', 'Davis', 9, 2, '1989-08-08', 'US', 'E3123456', 13, 13),
('Noah', 'Smith', 8, 1, '1980-11-11', 'US', 'E4123456', 14, 14),
('Olivia', 'Johnson', 7, 2, '1985-03-03', 'US', 'E5123456', 15, 15);

-- 21. Passengers
INSERT INTO passengers (first_name, last_name, sex, age, passport_country, passport_number) VALUES
('James', 'Anderson', 1, 34, 'USA', 'P0012345'),
('Emily', 'Brown', 2, 28, 'UK', 'P0012346'),
('Liam', 'Clark', 1, 42, 'Canada', 'P0012347'),
('Olivia', 'Davis', 2, 31, 'Australia', 'P0012348'),
('Noah', 'Evans', 1, 36, 'New Zealand', 'P0012349'),
('Sophia', 'Garcia', 2, 25, 'Spain', 'P0012350'),
('Francois', 'Lemoin', 1, 39, 'France', 'P0012351'),
('Lisa', 'Müller', 2, 30, 'Germany', 'P0012352'),
('Yoitiru', 'Nambu', 1, 27, 'Japan', 'P0012353'),
('Mia', 'Martinez', 2, 22, 'Brazil', 'P0012354'),
('Alexander', 'Ryabov', 1, 45, 'RUS', 'P0012355'),
('Hao', 'Kisao', 2, 33, 'China', 'P0012356'),
('Benjamin', 'Robinson', 1, 29, 'India', 'P0012357'),
('Amelia', 'Torres', 2, 26, 'Spain', 'P0012358'),
('Juiseppe', 'Mikilliano', 1, 38, 'Italy', 'P0012359'),
('Bjorn', 'Anderson', 2, 32, 'Sweden', 'P0012360'),
('Daniel', 'Young', 1, 41, 'Mexico', 'P0012361'),
('Harper', 'Adams', 2, 24, 'Norway', 'P0012362'),
('Nachim', 'Kim', 1, 37, 'South Korea', 'P0012363'),
('Abigail', 'Carter', 2, 29, 'Thailand', 'P0012364'),
('Michael', 'Mitchell', 1, 43, 'USA', 'P0012365'),
('Elizabeth', 'Perez', 2, 35, 'Argentina', 'P0012366'),
('David', 'Roberts', 1, 40, 'New Zealand', 'P0012367'),
('Sofia', 'Turner', 2, 28, 'Australia', 'P0012368'),
('Joseph', 'Phillips', 1, 31, 'UK', 'P0012369');

-- 22. Airplanes
INSERT INTO airplanes (airline, registration_number, model, total_capacity, type) VALUES
(1, 'N123AA', 'Boeing 737-800', 160, 1),
(2, 'N124AA', 'Airbus A320', 150, 1),
(3, 'N125AA', 'Boeing 777-300', 300, 1),
(4, 'N126AA', 'Boeing 747-400', 400, 1),
(5, 'N127AA', 'Airbus A380', 500, 1),
(6, 'N128AA', 'Boeing 787-9', 280, 1),
(7, 'N129AA', 'Cessna 172', 4, 3),
(8, 'N130AA', 'Gulfstream G650', 12, 3),
(9, 'N131AA', 'Bombardier Global 6000', 14, 3),
(10, 'N132AA', 'Cessna Citation X', 8, 3),
(11, 'N133AA', 'Boeing 767-300F', 200, 2),
(12, 'N134AA', 'McDonnell Douglas DC-10', 270, 2),
(13, 'N135AA', 'Airbus A330-200F', 250, 2),
(14, 'N136AA', 'Embraer E195', 120, 1),
(15, 'N137AA', 'Bombardier CRJ900', 90, 1),
(16, 'N138AA', 'Piper PA-46', 6, 3),
(17, 'N139AA', 'Dassault Falcon 7X', 12, 3),
(18, 'N140AA', 'Boeing 737 MAX 8', 180, 1),
(19, 'N141AA', 'Airbus A321', 185, 1),
(20, 'N142AA', 'Cessna 208 Caravan', 9, 2);

-- 23. Flights
INSERT INTO flights
(flight_number, service_date, airline, departure_airport, arrival_airport,
 scheduled_departure_time, actual_departure_time, scheduled_arrival_time, actual_arrival_time,
 airplane, responsible_dispatcher, type, customer, status, passport_control,
 departure_gate, arrival_gate, departure_terminal, arrival_terminal, runway)
VALUES
('LA100', '2025-09-05', 1, 1, 5,
 '2025-09-05 08:00:00-07', '2025-09-05 08:05:00-07', '2025-09-05 16:00:00+12', '2025-09-05 16:10:00+12',
 1, 3, 1, NULL, 1, 2,
 1, 6, 1, 2, 1),
('LA101', '2025-09-05', 2, 1, 6,
 '2025-09-05 09:30:00-07', NULL, '2025-09-05 17:30:00+12', NULL,
 2, 5, 1, NULL, 2, 2,
 2, 7, 1, 2, 2),
('LA200', '2025-09-06', 3, 1, 7,
 '2025-09-06 10:00:00-07', NULL, '2025-09-06 18:00:00+12', NULL,
 3, 3, 2, 1, 1, 2,
 3, 8, 1, 2, 3),
('LA201', '2025-09-06', 4, 1, 8,
 '2025-09-06 11:00:00-07', NULL, '2025-09-06 19:00:00+12', NULL,
 4, 4, 2, 2, 2, 2,
 4, 9, 1, 2, 4),
('LA300', '2025-09-07', 5, 1, 9,
 '2025-09-07 12:00:00-07', NULL, '2025-09-07 20:00:00+12', NULL,
 5, 5, 3, 3, 3, 1,
 5, 10, 1, 2, 5),
('LA102', '2025-09-05', 6, 1, 10,
 '2025-09-05 08:45:00-07', NULL, '2025-09-05 18:00:00+12', NULL,
 6, 6, 1, NULL, 1, 2,
 6, 11, 1, 2, 1),
('LA103', '2025-09-05', 7, 1, 11,
 '2025-09-05 09:15:00-07', NULL, '2025-09-05 19:30:00+12', NULL,
 7, 5, 1, NULL, 2, 2,
 7, 12, 1, 2, 2),
('LA104', '2025-09-05', 8, 1, 12,
 '2025-09-05 10:00:00-07', NULL, '2025-09-05 20:30:00+12', NULL,
 8, 3, 1, NULL, 1, 2,
 8, 13, 1, 2, 3),
('LA105', '2025-09-06', 9, 1, 13,
 '2025-09-06 11:00:00-07', NULL, '2025-09-06 21:00:00+12', NULL,
 9, 5, 2, 4, 3, 2,
 9, 14, 1, 2, 4),
('LA106', '2025-09-06', 10, 1, 14,
 '2025-09-06 12:30:00-07', NULL, '2025-09-06 22:30:00+12', NULL,
 10, 6, 2, 5, 2, 2,
 10, 15, 1, 2, 5),
('LA107', '2025-09-06', 11, 1, 15,
 '2025-09-06 13:15:00-07', NULL, '2025-09-06 23:15:00+12', NULL,
 11, 4, 1, NULL, 1, 1,
 11, 16, 1, 2, 1),
('LA108', '2025-09-07', 12, 1, 16,
 '2025-09-07 07:30:00-07', NULL, '2025-09-07 17:30:00+12', NULL,
 12, 3, 3, 6, 3, 2,
 12, 17, 1, 2, 2),
('LA109', '2025-09-07', 13, 1, 17,
 '2025-09-07 08:00:00-07', NULL, '2025-09-07 18:00:00+12', NULL,
 13, 4, 1, NULL, 2, 1,
 13, 18, 1, 2, 3),
('LA110', '2025-09-07', 14, 1, 18,
 '2025-09-07 09:30:00-07', NULL, '2025-09-07 19:30:00+12', NULL,
 14, 4, 2, 7, 1, 2,
 14, 19, 1, 2, 4),
('LA111', '2025-09-08', 15, 1, 19,
 '2025-09-08 10:00:00-07', NULL, '2025-09-08 20:00:00+12', NULL,
 15, 5, 1, NULL, 1, 2,
 15, 20, 1, 2, 5),
('LA112', '2025-09-08', 16, 1, 20,
 '2025-09-08 11:15:00-07', NULL, '2025-09-08 21:15:00+12', NULL,
 16, 3, 2, 8, 3, 2,
 16, 1, 1, 2, 1),
('LA113', '2025-09-08', 17, 1, 21,
 '2025-09-08 12:00:00-07', NULL, '2025-09-08 22:00:00+12', NULL,
 17, 4, 3, 9, 2, 2,
 17, 2, 1, 2, 2),
('LA114', '2025-09-09', 18, 1, 22,
 '2025-09-09 07:45:00-07', NULL, '2025-09-09 17:45:00+12', NULL,
 18, 6, 1, NULL, 1, 1,
 18, 3, 1, 2, 3),
('LA115', '2025-09-09', 19, 1, 23,
 '2025-09-09 08:30:00-07', NULL, '2025-09-09 18:30:00+12', NULL,
 19, 6, 2, 10, 3, 2,
 19, 4, 1, 2, 4),
('LA116', '2025-09-09', 20, 1, 24,
 '2025-09-09 09:00:00-07', NULL, '2025-09-09 19:00:00+12', NULL,
 20, 5, 1, NULL, 2, 1,
 20, 5, 1, 2, 5);

-- 24. Flight - check-in relations
INSERT INTO flight_check_in_link (flight_id, counter_id) VALUES
(1, 1),
(1, 2),
(2, 3),
(3, 2),
(4, 4),
(5, 5),
(6, 1),
(7, 2),
(8, 3),
(9, 4);

-- 25. Flight - baggage claim relations
INSERT INTO flight_bag_claims_link (flight_id, claim_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 1),
(5, 4),
(6, 5),
(7, 2),
(8, 3),
(9, 4),
(10, 5);

-- 26. Flight - crew relations
INSERT INTO flight_crew_link (flight_id, crew_id, role) VALUES
(1, 1, 1),
(1, 2, 2),
(1, 3, 3),
(1, 4, 3),
(2, 5, 1),
(2, 6, 2),
(2, 7, 3),
(3, 8, 1),
(3, 9, 2),
(3, 10, 3),
(4, 11, 1),
(4, 12, 2),
(4, 13, 4),
(5, 14, 1),
(5, 15, 2),
(5, 16, 3);

-- 27. Flight - passenger relations
INSERT INTO flight_passenger_link
(flight_id, passenger_id, checked_in, passed_control, boarded, boarding_group, boarding_time, seat_number)
VALUES
(1, 1, TRUE, TRUE, TRUE, 1, '2025-09-01 08:15:00-07', '12A'),
(1, 2, TRUE, TRUE, FALSE, 2, '2025-09-01 08:20:00-07', '14C'),
(1, 3, TRUE, FALSE, FALSE, 2, NULL, '15D'),
(2, 4, TRUE, TRUE, TRUE, 1, '2025-09-02 09:05:00-07', '10B'),
(2, 5, TRUE, TRUE, TRUE, 2, '2025-09-02 09:10:00-07', '11C'),
(3, 6, TRUE, TRUE, TRUE, 1, '2025-09-03 07:55:00-07', '2A'),
(3, 7, TRUE, TRUE, FALSE, 2, NULL, '16F'),
(3, 8, TRUE, FALSE, FALSE, 3, NULL, '18A'),
(4, 9, TRUE, TRUE, TRUE, 1, '2025-09-04 11:20:00-07', '7B'),
(4, 10, TRUE, TRUE, TRUE, 1, '2025-09-04 11:25:00-07', '8C');
