// This class corresponds to the 'person_sex' table in the database.

package airport.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonSexDao {

    private final Connection connection;

    public PersonSexDao (Connection connection) {
        this.connection = connection;
    }

    // Show the list of sexes
    public List<String> findAll() {
        String sql =
                """
                SELECT sex_name FROM person_sex
                """;

        try(Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> allSexes = new ArrayList<>();
            while(resultSet.next()) {
                String sex = resultSet.getString("sex_name");
                allSexes.add(sex);
            }
            return allSexes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
