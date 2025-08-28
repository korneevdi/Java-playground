package airport.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.username";
    private static final String PASSWORD_KEY = "db.password";

    // Prohibit to create objects of this class from outside,
    // since this is a utility class
    private ConnectionManager() {
    }

    public static Connection openConnection() {
        try {
            return DriverManager.getConnection(
                    PropertiesReader.getValue(URL_KEY),
                    PropertiesReader.getValue(USERNAME_KEY),
                    PropertiesReader.getValue(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
