package airport;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://localhost:5432/airport";
        String username = "postgres";
        String password = "+dima1755#";

        try(Connection connection = DriverManager.getConnection(
                url, username, password
        )) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            System.out.println("Connection failed...");
        }
    }
}
