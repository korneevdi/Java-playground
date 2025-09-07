// This class creates and initializes the database

package airport.database;

import airport.utils.ConnectionManager;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbInitializer {

    // Create the database and its structure
    public static void createDatabase() {
        runScript("database/schema.sql");
    }

    // Initialize the database
    public static void initializeDatabase() {
        runScript("database/data.sql");
    }

    // Run a script
    private static void runScript(String filename) {

        try(Connection connection = ConnectionManager.openConnection();
            Statement statement = connection.createStatement()) {

            // Get the full query from the file
            String sql = new String(Files.readAllBytes(Paths.get(
                    DbInitializer.class.getClassLoader().getResource(filename).toURI())));

            // Split the script by ; and execute each command separately
            for(String query : sql.split(";")) {
                query = query.trim();
                if(query != null && !query.isEmpty()) {
                    statement.executeUpdate(query);
                }
            }

            System.out.println("Script " + filename + "executed successfully.");
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}
