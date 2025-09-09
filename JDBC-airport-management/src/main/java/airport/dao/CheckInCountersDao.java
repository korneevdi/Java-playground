package airport.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CheckInCountersDao {

    private final Connection connection;

    public CheckInCountersDao(Connection connection) {
        this.connection = connection;
    }

    // Show the list of check-in counters
    public List<String> findAll() {
        String sql =
                """
                SELECT counter_name FROM check_in_counters
                """;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> allCounters = new ArrayList<>();
            while (resultSet.next()) {
                String counter = resultSet.getString("counter_name");
                allCounters.add(counter);
            }
            return allCounters;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find check-in counter by id
    public String findById(int id) {
        String sql =
                """
                SELECT counter_name FROM check_in_counters
                WHERE counter_id = %s
                """.formatted(id);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString("counter_name");
            } else {
                return "";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find id by check-in counter
    public int findId(String counter) {
        String sql =
                """
                SELECT counter_id FROM check_in_counters
                WHERE counter_name = '%s'
                """.formatted(counter);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("counter_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Add new check-in counter
    public void insert(String newCounter) {
        String sql =
                """
                INSERT INTO check_in_counters (counter_name) VALUES
                (?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newCounter);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update check-in counter
    public void update(int id, String newCounter) {
        String sql =
                """
                UPDATE check_in_counters
                SET counter_name = ?
                WHERE counter_id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newCounter);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete check-in counter
    public boolean delete(String counter) {
        String sql =
                """
                DELETE FROM check_in_counters
                WHERE counter_name = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, counter);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
