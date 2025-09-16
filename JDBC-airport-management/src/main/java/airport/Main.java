package airport;

import airport.dao.dictionary.*;
import airport.database.DbInitializer;
import airport.service.dictionary.*;
import airport.utils.ConnectionManager;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        try (Connection connection = ConnectionManager.openConnection()) {

            // Create objects of DAO classes and services
            // 1.Person sex
            PersonSexDao personSexDao = new PersonSexDao(connection);
            PersonSexService personSexService = new PersonSexService(personSexDao);
            // Call appropriate methods
            //personSexService.showAll();
            //personSexService.findByName("male");
            //personSexService.add("malefs");
            //personSexService.update("mal", "ololo");
            //personSexService.delete("malefs");

            // 2.Types (of flights & airplanes)
            TypeDao typeDao = new TypeDao(connection);
            TypeService typeService = new TypeService(typeDao);
            // Call appropriate methods
            //typeService.showAll();
            //typeService.findByName("private");
            //typeService.add("private");
            //typeService.update("public", "private");
            //typeService.delete("new type");

            // 3.Statuses of flights
            StatusDao statusDao = new StatusDao(connection);
            StatusService statusService = new StatusService(statusDao);
            // Call appropriate methods
            //statusService.showAll();
            //statusService.findByName(2);
            //statusService.add("in air");
            //statusService.update("boarding_complete", "boarding complete");
            //statusService.delete("new status");

            // 4.Crew roles
            CrewRolesDao crewRolesDao = new CrewRolesDao(connection);
            CrewRolesService crewRolesService = new CrewRolesService(crewRolesDao);
            // Call appropriate methods
            //crewRolesService.showAll();
            //crewRolesService.findByName(2);
            //crewRolesService.add("pilot");
            //crewRolesService.update("manager", "mAnAgEr");
            //crewRolesService.delete("mAnAgEr");

            // 5.Control types
            ControlTypesDao controlTypesDao = new ControlTypesDao(connection);
            ControlTypeService controlTypeService = new ControlTypeService(controlTypesDao);
            // Call appropriate methods
            //controlTypeService.showAll();
            //controlTypeService.findByName(2);
            //controlTypeService.add("standard");
            //controlTypeService.update("strict control", "not really strict control");
            //controlTypeService.delete("not really strict control");

            // 6.Check-in counters
            CheckInCountersDao checkInCountersDao = new CheckInCountersDao(connection);
            CheckInCountersService checkInCountersService = new CheckInCountersService(checkInCountersDao);
            // Call appropriate methods
            //checkInCountersService.showAll();
            //checkInCountersService.findByName("C1");
            //checkInCountersService.add("C881");
            //checkInCountersService.update("C881", "D55");
            //checkInCountersService.delete("C1");

            // 7.Baggage claims
            BaggageClaimsDao baggageClaimsDao = new BaggageClaimsDao(connection);
            BaggageClaimsService baggageClaimsService = new BaggageClaimsService(baggageClaimsDao);
            // Call appropriate methods
            //baggageClaimsService.showAll();
            //baggageClaimsService.findByName("8");
            //baggageClaimsService.add("4-4");
            //baggageClaimsService.update("D+5", "98");
            //baggageClaimsService.delete("66");

            // 8.Gates
            GatesDao gatesDao = new GatesDao(connection);
            GatesService gatesService = new GatesService(gatesDao);
            // Call appropriate methods
            //gatesService.showAll();
            //gatesService.findByName(8);
            //gatesService.add("C8");
            //gatesService.update("C11", "R25");
            //gatesService.delete("R25");

            // 9.Terminals
            TerminalsDao terminalsDao = new TerminalsDao(connection);
            TerminalsService terminalsService = new TerminalsService(terminalsDao);
            // Call appropriate methods
            //terminalsService.showAll();
            //terminalsService.findByName(3);
            //terminalsService.add("C8");
            //terminalsService.update("C8", "EE");
            //terminalsService.delete("EE");

            // 10.Flight runways
            RunwaysDao runwaysDao = new RunwaysDao(connection);
            RunwaysService runwaysService = new RunwaysService(runwaysDao);
            // Call appropriate methods
            //runwaysService.showAll();
            //runwaysService.findByName(-3);
            //runwaysService.add("C8");
            //runwaysService.update("C8", "EE");
            //runwaysService.delete("EE");

            // 11.Airport employee roles
            EmployeeRolesDao employeeRolesDao = new EmployeeRolesDao(connection);
            EmployeeRolesService employeeRolesService = new EmployeeRolesService(employeeRolesDao);
            // Call appropriate methods
            //employeeRolesService.showAll();
            //employeeRolesService.findByName(-3);
            //employeeRolesService.add("C8");
            //employeeRolesService.update("C8", "EE");
            //employeeRolesService.delete("EE");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Create and initialize database
        //DbInitializer.createDatabase();
        //DbInitializer.initializeDatabase();
    }
}
