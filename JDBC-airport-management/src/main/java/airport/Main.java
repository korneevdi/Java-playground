package airport;

import airport.dao.*;
import airport.database.DbInitializer;
import airport.service.*;
import airport.utils.ConnectionManager;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        try(Connection connection = ConnectionManager.openConnection()) {

            // Create objects of DAO classes and services
            // 1.Person sex
            PersonSexDao personSexDao = new PersonSexDao(connection);
            PersonSexService personSexService = new PersonSexService(personSexDao);
            // Call appropriate methods
            //personSexService.showAllSexes();
            //personSexService.findSex(2);
            //personSexService.addSex("new sex");
            //personSexService.updateSex("male", "ololo");
            //personSexService.deleteSex("new sex");

            // 2.Types (of flights & airplanes)
            TypeDao typeDao = new TypeDao(connection);
            TypeService typeService = new TypeService(typeDao);
            // Call appropriate methods
            //typeService.showAllTypes();
            //typeService.findType(3);
            //typeService.addType("new type");
            //typeService.updateType("public", "private");
            //typeService.deleteType("new type");

            // 3.Statuses of flights
            StatusDao statusDao = new StatusDao(connection);
            StatusService statusService = new StatusService(statusDao);
            // Call appropriate methods
            //statusService.showAllStatuses();
            //statusService.findStatus(2);
            //statusService.addStatus("new status");
            //statusService.updateStatus("boarding_complete", "boarding complete");
            //statusService.deleteStatus("new status");

            // 4.Crew roles
            CrewRolesDao crewRolesDao = new CrewRolesDao(connection);
            CrewRolesService crewRolesService = new CrewRolesService(crewRolesDao);
            // Call appropriate methods
            //crewRolesService.showAllRoles();
            //crewRolesService.findRole(2);
            //crewRolesService.addRole("manager");
            //crewRolesService.updateRole("manager", "mAnAgEr");
            //crewRolesService.deleteRole("mAnAgEr");

            // 5.Control types
            ControlTypesDao controlTypesDao = new ControlTypesDao(connection);
            ControlTypeService controlTypeService = new ControlTypeService(controlTypesDao);
            // Call appropriate methods
            //controlTypeService.showAllTypes();
            //controlTypeService.findType(2);
            //controlTypeService.addType("strict control");
            //controlTypeService.updateType("strict control", "not really strict control");
            //controlTypeService.deleteType("not really strict control");

            // 6.Check-in counters
            CheckInCountersDao checkInCountersDao = new CheckInCountersDao(connection);
            CheckInCountersService checkInCountersService = new CheckInCountersService(checkInCountersDao);
            // Call appropriate methods
            //checkInCountersService.showAllCounters();
            //checkInCountersService.findCounter(51);
            //checkInCountersService.addCounter("С1");
            //checkInCountersService.updateCounter("С1", "D5");
            //checkInCountersService.deleteCounter("D5");

            // 7.Baggage claims
            BaggageClaimsDao baggageClaimsDao = new BaggageClaimsDao(connection);
            BaggageClaimsService baggageClaimsService = new BaggageClaimsService(baggageClaimsDao);
            // Call appropriate methods
            //baggageClaimsService.showAllClaims();
            //baggageClaimsService.findClaim(8);
            //baggageClaimsService.addClaim("С1");
            //baggageClaimsService.updateClaim("С1", "D5");
            //baggageClaimsService.deleteClaim("D5");

        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        // Create and initialize database
        //DbInitializer.createDatabase();
        //DbInitializer.initializeDatabase();
    }
}
