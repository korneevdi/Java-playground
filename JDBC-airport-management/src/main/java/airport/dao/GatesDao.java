package airport.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GatesDao {

    private final Connection connection;

    public GatesDao(Connection connection) {
        this.connection = connection;
    }

    // Show the list of gates
    public List<String> findAll() {
        String sql =
                """
                SELECT gate_number FROM gates
                """;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> allGates = new ArrayList<>();
            while (resultSet.next()) {
                String gate = resultSet.getString("gate_number");
                allGates.add(gate);
            }
            return allGates;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find gate by id
    public String findById(int id) {
        String sql =
                """
                SELECT gate_number FROM gates
                WHERE gate_id = %s
                """.formatted(id);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString("gate_number");
            } else {
                return "";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find id by gate
    public int findId(String gate) {
        String sql =
                """
                SELECT gate_id FROM gates
                WHERE gate_number = '%s'
                """.formatted(gate);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("gate_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Add new gate
    public void insert(String newGate) {
        String sql =
                """
                INSERT INTO gates (gate_number) VALUES
                (?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newGate);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update gate
    public void update(int id, String newGate) {
        String sql =
                """
                UPDATE gates
                SET gate_number = ?
                WHERE gate_id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newGate);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete gate
    public boolean delete(String gate) {
        String sql =
                """
                DELETE FROM gates
                WHERE gate_number = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, gate);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
