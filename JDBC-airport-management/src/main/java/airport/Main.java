package airport;

import airport.dao.PersonSexDao;
import airport.database.DbInitializer;
import airport.service.PersonSexService;
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
            personSexService.showAllSexes();

        } catch(Exception e) {
            throw new RuntimeException(e);
        }

        // Create and initialize database
        //DbInitializer.createDatabase();
        //DbInitializer.initializeDatabase();
    }
}
