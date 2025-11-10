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

public class Main {
    public static void main(String[] args) {

        try (Connection connection = ConnectionManager.openConnection()) {

            // Create objects of DAOs and services
            // 1.Sexes
            SexesDao sexesDao = new SexesDao(connection);
            SexesService sexesService = new SexesService(sexesDao);

            //sexesService.showAll();
            //sexesService.findAllByField("sex_name", "female");
            //sexesService.add("tyty");

            // 2.Types (of flights & airplanes)
            TypesDao typesDao = new TypesDao(connection);
            TypesService typesService = new TypesService(typesDao);

            //typesService.showAll();
            //typesService.findAllByField("type_name", "private");
            //typesService.add("noch ein type");

            // 3.Statuses of flights
            StatusesDao statusesDao = new StatusesDao(connection);
            StatusesService statusesService = new StatusesService(statusesDao);

            //statusesService.showAll();
            //statusesService.findAllByField("status_name", "delayed");
            //statusesService.add("service");

            // 4.Crew roles
            CrewRolesDao crewRolesDao = new CrewRolesDao(connection);
            CrewRolesService crewRolesService = new CrewRolesService(crewRolesDao);

            //crewRolesService.showAll();
            //crewRolesService.findAllByField("role_name", "purser");
            //crewRolesService.add("crew");

            // 5.Control types
            ControlTypesDao controlTypesDao = new ControlTypesDao(connection);
            ControlTypesService controlTypesService = new ControlTypesService(controlTypesDao);

            //controlTypesService.showAll();
            //controlTypesService.findAllByField("type_name", "none");
            //controlTypesService.add("control type");

            // 6.Check-in counters
            CheckInCountersDao checkInCountersDao = new CheckInCountersDao(connection);
            CheckInCountersService checkInCountersService = new CheckInCountersService(checkInCountersDao);

            //checkInCountersService.showAll();
            //checkInCountersService.findAllByField("counter_number", "A1");
            //checkInCountersService.add("check-in");

            // 7.Baggage claims
            BaggageClaimsDao baggageClaimsDao = new BaggageClaimsDao(connection);
            BaggageClaimsService baggageClaimsService = new BaggageClaimsService(baggageClaimsDao);

            //baggageClaimsService.showAll();
            //baggageClaimsService.findAllByField("claim_number", "8");
            //baggageClaimsService.add("ree");

            // 8.Gates
            GatesDao gatesDao = new GatesDao(connection);
            GatesService gatesService = new GatesService(gatesDao);

            //gatesService.showAll();
            //gatesService.findAllByField("gate_number", "B7");
            //gatesService.add("75");

            // 9.Terminals
            TerminalsDao terminalsDao = new TerminalsDao(connection);
            TerminalsService terminalsService = new TerminalsService(terminalsDao);

            //terminalsService.showAll();
            //terminalsService.findAllByField("terminal_number", "D");
            //terminalsService.add("GG");

            // 10.Flight runways
            RunwaysDao runwaysDao = new RunwaysDao(connection);
            RunwaysService runwaysService = new RunwaysService(runwaysDao);

            //runwaysService.showAll();
            //runwaysService.findAllByField("runway_number", "27L");
            //runwaysService.add("7r8");

            // 11.Airport employee roles
            AirportEmployeeRolesDao airportEmployeeRolesDao = new AirportEmployeeRolesDao(connection);
            AirportEmployeeRolesService airportEmployeeRolesService = new AirportEmployeeRolesService(airportEmployeeRolesDao);

            //airportEmployeeRolesService.showAll();
            //airportEmployeeRolesService.findAllByField("role_name", "Security Officer");
            //airportEmployeeRolesService.add("security");

            // 12.Airline contacts
            AirlineContactsDao airlineContactsDao = new AirlineContactsDao(connection);
            AirlineContactsService airlineContactsService = new AirlineContactsService(airlineContactsDao);

            //airlineContactsService.showAll();
            //airlineContactsService.findAllByField("contact_name", "Japan Airlines Contact");
            //airlineContactsService.add("British Airways Contact", "email", "phone", "city", "notes");

            // 13.Customer contacts
            CustomerContactsDao customerContactsDao = new CustomerContactsDao(connection);
            CustomerContactsService customerContactsService = new CustomerContactsService(customerContactsDao);

            //customerContactsService.showAll();
            //customerContactsService.findAllByField("contact_email", "ava.moore@example.com");
            //customerContactsService.add("ava.moore@example.com", "phone", "city", "address", "notes");

            // 14.Employee contacts
            AirportEmployeeContactsDao airportEmployeeContactsDao = new AirportEmployeeContactsDao(connection);
            AirportEmployeeContactsService airportEmployeeContactsService = new AirportEmployeeContactsService(airportEmployeeContactsDao);

            //airportEmployeeContactsService.showAll();
            //airportEmployeeContactsService.findAllByField("contact_email", "olivia.johnson@airport.com");
            //airportEmployeeContactsService.add("olivia.johnson@airport.com", "phone", "city", "address", "notes");

            // 15.Employee contacts
            EmergencyContactsDao emergencyContactsDao = new EmergencyContactsDao(connection);
            EmergencyContactsService emergencyContactsService = new EmergencyContactsService(emergencyContactsDao);

            //emergencyContactsService.showAll();
            //emergencyContactsService.findAllByField("contact_name", "Paul Johnson");
            //emergencyContactsService.add("Paul Johnson", "brother", "phone");

            // 16.Airports
            AirportsDao airportsDao = new AirportsDao(connection);
            AirportsService airportsService = new AirportsService(airportsDao);

            //airportsService.showAll();
            //airportsService.findAllByField("iata", "LAS");
            //airportsService.add("iat", "icao", "name", "city", "country", "timezone");

            // 17.Airlines
            AirlinesDao airlinesDao = new AirlinesDao(connection);
            AirlinesService airlinesService = new AirlinesService(airlinesDao, airlineContactsDao);

            //airlinesService.showAll();
            //airlinesService.findAllByField("iata", "JL");
            //airlineContactsService.add("Korean Air", "email", "phone", "city", "notes");

            // 18.Customers
            CustomersDao customersDao = new CustomersDao(connection);
            CustomersService customersService = new CustomersService(customersDao, customerContactsDao);

            //customersService.showAll();
            //customersService.findAllByField("first_name", "Ava");
            //customersService.add("name", "lastName", "Moldova", "efeoifeor",
            //        "email", "phone", "city", "address", "notes");

            // 19.Flight crews
            CrewsDao crewsDao = new CrewsDao(connection);
            CrewsService crewsService = new CrewsService(crewsDao, sexesDao);

            //crewsService.showAll();
            //crewsService.findAllByField("pilot_license_number", "PILOT002");
            //crewsService.add("number", "name", "name", "male",
            //        "08.08.1996", "Finland", "passport");

            // 20.Airport employees
            AirportEmployeesDao airportEmployeesDao = new AirportEmployeesDao(connection);
            AirportEmployeesService airportEmployeesService = new AirportEmployeesService(
                    airportEmployeesDao, airportEmployeeRolesDao, sexesDao,
                    airportEmployeeContactsDao, emergencyContactsDao);

            //airportEmployeesService.showAll();
            //airportEmployeesService.findAllByField("first_name", "Olivia");
            //airportEmployeesService.add("name", "name", "Administrator", "female", "19.03.1980",
            //        "Italy", "iefweiofj", "email", "phone", "Milano",
            //        "address", "notes", "emContName", "relation", "phone");

            // 21.Passengers
            PassengersDao passengersDao = new PassengersDao(connection);
            PassengersService passengersService = new PassengersService(passengersDao, sexesDao);

            //passengersService.showAll();
            //passengersService.findAllByField("first_name", "Joseph");
            //passengersService.add("name", "name", "male", 46, "Spain", "number");

            // 22.Airplanes
            AirplanesDao airplanesDao = new AirplanesDao(connection);
            AirplanesService airplanesService = new AirplanesService(airplanesDao, airlinesDao, typesDao);

            //airplanesService.showAll();
            //airplanesService.findAllByField("name", "Japan Airlines");
            //airplanesService.add("Korean Air", "eriferiof45", "tutu", 180, "private");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Create and initialize database
        //DbInitializer.createDatabase();
        //DbInitializer.initializeDatabase();
    }
}
