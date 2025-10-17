package airport;

import airport.dao.basic.*;
import airport.dao.contact.AirlineContactsDao;
import airport.dao.contact.CustomerContactsDao;
import airport.dao.contact.EmergencyContactsDao;
import airport.dao.contact.AirportEmployeeContactsDao;
import airport.dao.dictionary.*;
import airport.database.DbInitializer;
import airport.service.basic.*;
import airport.service.contact.AirlineContactsService;
import airport.service.contact.CustomerContactsService;
import airport.service.contact.EmergencyContactsService;
import airport.service.contact.AirportEmployeeContactsService;
import airport.service.dictionary.*;
import airport.utils.ConnectionManager;

import java.sql.Connection;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = ConnectionManager.openConnection()) {

            // Create objects of DAOs and services
            // 1.Sexes
            SexesDao sexesDao = new SexesDao(connection);
            SexesService sexesService = new SexesService(sexesDao);

            //sexesService.showAll();
            //sexesService.findAllByField("sex_name", "female");

            // 2.Types (of flights & airplanes)
            TypesDao typesDao = new TypesDao(connection);
            TypesService typesService = new TypesService(typesDao);

            //typesService.showAll();
            //typesService.findAllByField("type_name", "private");

            // 3.Statuses of flights
            StatusesDao statusesDao = new StatusesDao(connection);
            StatusesService statusesService = new StatusesService(statusesDao);

            //statusesService.showAll();
            //statusesService.findAllByField("status_name", "delayed");

            // 4.Crew roles
            CrewRolesDao crewRolesDao = new CrewRolesDao(connection);
            CrewRolesService crewRolesService = new CrewRolesService(crewRolesDao);

            //crewRolesService.showAll();
            //crewRolesService.findAllByField("role_name", "purser");

            // 5.Control types
            ControlTypesDao controlTypesDao = new ControlTypesDao(connection);
            ControlTypesService controlTypesService = new ControlTypesService(controlTypesDao);

            //controlTypesService.showAll();
            //controlTypesService.findAllByField("type_name", "none");

            // 6.Check-in counters
            CheckInCountersDao checkInCountersDao = new CheckInCountersDao(connection);
            CheckInCountersService checkInCountersService = new CheckInCountersService(checkInCountersDao);

            //checkInCountersService.showAll();
            //checkInCountersService.findAllByField("counter_number", "A1");

            // 7.Baggage claims
            BaggageClaimsDao baggageClaimsDao = new BaggageClaimsDao(connection);
            BaggageClaimsService baggageClaimsService = new BaggageClaimsService(baggageClaimsDao);

            //baggageClaimsService.showAll();
            //baggageClaimsService.findAllByField("claim_number", "8");

            // 8.Gates
            GatesDao gatesDao = new GatesDao(connection);
            GatesService gatesService = new GatesService(gatesDao);

            //gatesService.showAll();
            //gatesService.findAllByField("gate_number", "B7");

            // 9.Terminals
            TerminalsDao terminalsDao = new TerminalsDao(connection);
            TerminalsService terminalsService = new TerminalsService(terminalsDao);

            //terminalsService.showAll();
            //terminalsService.findAllByField("terminal_number", "D");

            // 10.Flight runways
            RunwaysDao runwaysDao = new RunwaysDao(connection);
            RunwaysService runwaysService = new RunwaysService(runwaysDao);

            //runwaysService.showAll();
            //runwaysService.findAllByField("runway_number", "27L");

            // 11.Airport employee roles
            AirportEmployeeRolesDao airportEmployeeRolesDao = new AirportEmployeeRolesDao(connection);
            AirportEmployeeRolesService airportEmployeeRolesService = new AirportEmployeeRolesService(airportEmployeeRolesDao);

            //airportEmployeeRolesService.showAll();
            //airportEmployeeRolesService.findAllByField("role_name", "Security Officer");

            // 12.Airline contacts
            AirlineContactsDao airlineContactsDao = new AirlineContactsDao(connection);
            AirlineContactsService airlineContactsService = new AirlineContactsService(airlineContactsDao);

            //airlineContactsService.showAll();
            //airlineContactsService.findAllByField("contact_name", "Japan Airlines Contact");
            //airlineContactsService.findAllByField("contact_email", "contact@cathaypacific.com");
            //airlineContactsService.findAllByField("contact_phone", "+65-6223-8888");
            //airlineContactsService.findAllByField("city", "Brisbane");
            //airlineContactsService.findAllByField("notes", "Australian carrier");

            // 13.Customer contacts
            CustomerContactsDao customerContactsDao = new CustomerContactsDao(connection);
            CustomerContactsService customerContactsService = new CustomerContactsService(customerContactsDao);

            //customerContactsService.showAll();
            //customerContactsService.findAllByField("contact_email", "ava.moore@example.com");
            //customerContactsService.findAllByField("contact_phone", "+1-310-555-0106");
            //customerContactsService.findAllByField("city", "Pasadena");
            //customerContactsService.findAllByField("address", "987 Colorado Blvd");
            //customerContactsService.findAllByField("notes", "Private flights");

            // 14.Employee contacts
            AirportEmployeeContactsDao airportEmployeeContactsDao = new AirportEmployeeContactsDao(connection);
            AirportEmployeeContactsService airportEmployeeContactsService = new AirportEmployeeContactsService(airportEmployeeContactsDao);

            //airportEmployeeContactsService.showAll();
            //airportEmployeeContactsService.findAllByField("contact_email", "olivia.johnson@airport.com");
            //airportEmployeeContactsService.findAllByField("contact_phone", "+1-310-555-1011");
            //airportEmployeeContactsService.findAllByField("city", "Los Angeles");
            //airportEmployeeContactsService.findAllByField("address", "106 Airport Rd");
            //airportEmployeeContactsService.findAllByField("notes", "Operations");

            // 15.Employee contacts
            EmergencyContactsDao emergencyContactsDao = new EmergencyContactsDao(connection);
            EmergencyContactsService emergencyContactsService = new EmergencyContactsService(emergencyContactsDao);

            //emergencyContactsService.showAll();
            //emergencyContactsService.findAllByField("contact_name", "Paul Johnson");
            //emergencyContactsService.findAllByField("relation", "Brother");
            //emergencyContactsService.findAllByField("contact_phone", "+1-310-555-2013");

            // 16.Airports
            AirportsDao airportsDao = new AirportsDao(connection);
            AirportsService airportsService = new AirportsService(airportsDao);

            //airportsService.showAll();
            //airportsService.findAllByField("iata", "LAS");
            //airportsService.findAllByField("icao", "FACT");
            //airportsService.findAllByField("name", "O. R. Tambo International Airport");
            //airportsService.findAllByField("city", "São Paulo");
            //airportsService.findAllByField("country", "Germany");
            //airportsService.findAllByField("timezone", "Europe/Amsterdam");

            // 17.Airlines
            AirlinesDao airlinesDao = new AirlinesDao(connection);
            AirlinesService airlinesService = new AirlinesService(airlinesDao);

            //airlinesService.showAll();
            //airlinesService.findAllByField("iata", "JL");
            //airlinesService.findAllByField("icao", "BAW");
            //airlinesService.findAllByField("name", "Cathay Pacific");
            //airlinesService.findAllByField("contact_name", "Cathay Pacific Contact");
            //airlinesService.findAllByField("contact_email", "contact@fijiairways.com.fj");
            //airlinesService.findAllByField("contact_phone", "+1-800-367-5320");
            //airlinesService.findAllByField("city", "Seattle");
            //airlinesService.findAllByField("notes", "US West Coast regional carrier");

            // 18.Customers
            CustomersDao customersDao = new CustomersDao(connection);
            CustomersService customersService = new CustomersService(customersDao);

            //customersService.showAll();
            //customersService.findAllByField("first_name", "Ava");
            //customersService.findAllByField("last_name", "Wilson");
            //customersService.findAllByField("passport_country", "US");
            //customersService.findAllByField("passport_number", "X7890123");
            //customersService.findAllByField("contact_email", "liam.davis@example.com");
            //customersService.findAllByField("contact_phone", "+1-310-555-0105");
            //customersService.findAllByField("city", "Beverly Hills");
            //customersService.findAllByField("address", "789 Rodeo Dr");
            //customersService.findAllByField("notes", "VIP cargo customer");

            // 19.Flight crews
            CrewsDao crewsDao = new CrewsDao(connection);
            CrewsService crewsService = new CrewsService(crewsDao);

            //crewsService.showAll();
            //crewsService.findAllByField("pilot_license_number", "PILOT002");
            //crewsService.findAllByField("first_name", "Isabella");
            //crewsService.findAllByField("last_name", "Martin");
            //crewsService.findAllByField("sex_name", "female");
            //crewsService.findAllByField("passport_country", "Finland");
            //crewsService.findAllByField("passport_number", "N45678901");
            //crewsService.findAllByField("birth_date", "1982-09-10");

            // 20.Airport employees
            AirportEmployeesDao airportEmployeesDao = new AirportEmployeesDao(connection);
            AirportEmployeesService airportEmployeesService = new AirportEmployeesService(airportEmployeesDao);

            //airportEmployeesService.showAll();
            //airportEmployeesService.findAllByField("first_name", "Olivia");
            //airportEmployeesService.findAllByField("last_name", "Smith");
            //airportEmployeesService.findAllByField("role_name", "Baggage Handler");
            //airportEmployeesService.findAllByField("sex_name", "female");
            //airportEmployeesService.findAllByField("passport_country", "Mexico");
            //airportEmployeesService.findAllByField("passport_number", "E3123456");
            //airportEmployeesService.findAllByField("emp_contact_email", "noah.smith@airport.com");
            //airportEmployeesService.findAllByField("emp_contact_phone", "+1-310-555-1014");
            //airportEmployeesService.findAllByField("city", "Moscow");
            //airportEmployeesService.findAllByField("address", "114 Airport Rd");
            //airportEmployeesService.findAllByField("notes", "Administration");
            //airportEmployeesService.findAllByField("emerg_contact_name", "Paul Johnson");
            //airportEmployeesService.findAllByField("emerg_contact_relation", "Husband");
            //airportEmployeesService.findAllByField("emerg_contact_phone", "+1-310-555-2011");
            //airportEmployeesService.findAllByField("birth_date", "1985-03-03");

            // 21.Passengers
            PassengersDao passengersDao = new PassengersDao(connection);
            PassengersService passengersService = new PassengersService(passengersDao);

            //passengersService.showAll();
            //passengersService.findAllByField("first_name", "Joseph");
            //passengersService.findAllByField("last_name", "Roberts");
            //passengersService.findAllByField("sex_name", "female");
            //passengersService.findAllByField("age", 15);
            //passengersService.findAllByField("passport_country", "New Zealand");
            //passengersService.findAllByField("passport_number", "P0012366");

            // 22.Airplanes
            AirplanesDao airplanesDao = new AirplanesDao(connection);
            AirplanesService airplanesService = new AirplanesService(airplanesDao);

            //airplanesService.showAll();
            //airplanesService.findAllByField("name", "Japan Airlines");
            //airplanesService.findAllByField("registration_number", "N142AA");
            //airplanesService.findAllByField("model", "Cessna 208 Caravan");
            //airplanesService.findAllByField("type_name", "private");
            //airplanesService.findAllByField("total_capacity", 99);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Create and initialize database
        //DbInitializer.createDatabase();
        //DbInitializer.initializeDatabase();
    }
}
