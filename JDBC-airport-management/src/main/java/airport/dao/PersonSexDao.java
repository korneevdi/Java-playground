// This class corresponds to the 'person_sex' table in the database.

package airport.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonSexDao {

    private final Connection connection;

    public PersonSexDao(Connection connection) {
        this.connection = connection;
    }

    // Show the list of sexes
    public List<String> findAll() {
        String sql =
                """
                SELECT sex_name FROM person_sex
                """;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> allSexes = new ArrayList<>();
            while (resultSet.next()) {
                String sex = resultSet.getString("sex_name");
                allSexes.add(sex);
            }
            return allSexes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find sex by id
    public String findById(int id) {
        String sql =
                """
                SELECT sex_name FROM person_sex
                WHERE sex_id = %s
                """.formatted(id);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString("sex_name");
            } else {
                return "";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find id by sex_name
    public int findId(String sex) {
        String sql =
                """
                SELECT sex_id FROM person_sex
                WHERE sex_name = '%s'
                """.formatted(sex);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("sex_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Add new sex
    public void insert(String newSex) {
        String sql =
                """
                INSERT INTO person_sex (sex_name) VALUES
                (?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newSex);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update sex
    public void update(int id, String newSex) {
        String sql =
                """
                UPDATE person_sex
                SET sex_name = ?
                WHERE sex_id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newSex);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete sex
    public boolean delete(String sex) {
        String sql =
                """
                DELETE FROM person_sex
                WHERE sex_name = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, sex);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
