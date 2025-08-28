package airport.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL = "jdbc:postgresql://localhost:5432/airport";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "+dima1755#";

    // Prohibit to create objects of this class from outside,
    // since this is a utility class
    private ConnectionManager() {}

    public static Connection openConnection() {
        try{
            return DriverManager.getConnection(
                    URL, USERNAME, PASSWORD
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
