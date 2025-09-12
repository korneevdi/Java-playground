package airport.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RunwaysDao {

    private final Connection connection;

    public RunwaysDao(Connection connection) {
        this.connection = connection;
    }

    // Show the list of runways
    public List<String> findAll() {
        String sql =
                """
                SELECT runway_number FROM flight_runways
                """;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> allRunways = new ArrayList<>();
            while (resultSet.next()) {
                String runway = resultSet.getString("runway_number");
                allRunways.add(runway);
            }
            return allRunways;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find runway by id
    public String findById(int id) {
        String sql =
                """
                SELECT runway_number FROM flight_runways
                WHERE runway_id = %s
                """.formatted(id);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString("runway_number");
            } else {
                return "";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find id by runway_number
    public int findId(String runway) {
        String sql =
                """
                SELECT runway_id FROM flight_runways
                WHERE runway_number = '%s'
                """.formatted(runway);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("runway_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Add new runway
    public void insert(String newRunway) {
        String sql =
                """
                INSERT INTO flight_runways (runway_number) VALUES
                (?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newRunway);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update runway
    public void update(int id, String newRunway) {
        String sql =
                """
                UPDATE flight_runways
                SET runway_number = ?
                WHERE runway_id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newRunway);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete runway
    public boolean delete(String runway) {
        String sql =
                """
                DELETE FROM flight_runways
                WHERE runway_number = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, runway);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
