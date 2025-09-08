package airport.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusDao {

    private final Connection connection;

    public StatusDao(Connection connection) {
        this.connection = connection;
    }

    // Show the list of statuses
    public List<String> findAll() {
        String sql =
                """
                SELECT status_name FROM statuses
                """;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> allStatuses = new ArrayList<>();
            while (resultSet.next()) {
                String status = resultSet.getString("status_name");
                allStatuses.add(status);
            }
            return allStatuses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find status by id
    public String findById(int id) {
        String sql =
                """
                SELECT status_name FROM statuses
                WHERE status_id = %s
                """.formatted(id);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString("status_name");
            } else {
                return "";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find id by status_name
    public int findId(String status) {
        String sql =
                """
                SELECT status_id FROM statuses
                WHERE status_name = '%s'
                """.formatted(status);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("status_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Add new status
    public void insert(String newStatus) {
        String sql =
                """
                INSERT INTO statuses (status_name) VALUES
                (?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update status
    public void update(int id, String newStatus) {
        String sql =
                """
                UPDATE statuses
                SET status_name = ?
                WHERE status_id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete status
    public void delete(String status) {
        String sql =
                """
                DELETE FROM statuses
                WHERE status_name = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
