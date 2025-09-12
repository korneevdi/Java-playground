package airport.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TerminalsDao {

    private final Connection connection;

    public TerminalsDao(Connection connection) {
        this.connection = connection;
    }

    // Show the list of terminals
    public List<String> findAll() {
        String sql =
                """
                SELECT terminal_number FROM terminals
                """;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> allTerminals = new ArrayList<>();
            while (resultSet.next()) {
                String terminal = resultSet.getString("terminal_number");
                allTerminals.add(terminal);
            }
            return allTerminals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find terminal by id
    public String findById(int id) {
        String sql =
                """
                SELECT terminal_number FROM terminals
                WHERE terminal_id = %s
                """.formatted(id);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString("terminal_number");
            } else {
                return "";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find id by terminal
    public int findId(String terminal) {
        String sql =
                """
                SELECT terminal_id FROM terminals
                WHERE terminal_number = '%s'
                """.formatted(terminal);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("terminal_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Add new terminal
    public void insert(String newTerminal) {
        String sql =
                """
                INSERT INTO terminals (terminal_number) VALUES
                (?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newTerminal);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update terminal
    public void update(int id, String newTerminal) {
        String sql =
                """
                UPDATE terminals
                SET terminal_number = ?
                WHERE terminal_id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newTerminal);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete terminal
    public boolean delete(String terminal) {
        String sql =
                """
                DELETE FROM terminals
                WHERE terminal_number = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, terminal);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
