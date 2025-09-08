package airport;

import airport.dao.PersonSexDao;
import airport.dao.StatusDao;
import airport.dao.TypeDao;
import airport.database.DbInitializer;
import airport.service.PersonSexService;
import airport.service.StatusService;
import airport.service.TypeService;
import airport.utils.ConnectionManager;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        try(Connection connection = ConnectionManager.openConnection()) {

            // Create objects of DAO classes and services
            // Person sex
            PersonSexDao personSexDao = new PersonSexDao(connection);
            PersonSexService personSexService = new PersonSexService(personSexDao);
            // Call appropriate methods
            //personSexService.showAllSexes();
            //personSexService.findSex(2);
            //personSexService.addSex("new sex");
            //personSexService.updateSex("male", "ololo");
            //personSexService.deleteSex("new sex");

            // Types (of flights & airplanes)
            TypeDao typeDao = new TypeDao(connection);
            TypeService typeService = new TypeService(typeDao);
            // Call appropriate methods
            //typeService.showAllTypes();
            //typeService.findType(3);
            //typeService.addType("new type");
            //typeService.updateType("public", "private");
            //typeService.deleteType("new type");

            // Statuses of flights
            StatusDao statusDao = new StatusDao(connection);
            StatusService statusService = new StatusService(statusDao);
            // Call appropriate methods
            //statusService.showAllStatuses();
            //statusService.findStatus(2);
            //statusService.addStatus("new status");
            //statusService.updateStatus("boarding_complete", "boarding complete");
            //statusService.deleteStatus("new status");

        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        // Create and initialize database
        //DbInitializer.createDatabase();
        //DbInitializer.initializeDatabase();
    }
}
