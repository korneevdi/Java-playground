package airport;

import airport.utils.ConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;

public class DbConnection {

    public static void main(String[] args) {

        try (Connection connection = ConnectionManager.openConnection()) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            System.out.println("Connection failed...");
        }
    }
}
