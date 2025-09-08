package airport.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ControlTypesDao {

    private final Connection connection;

    public ControlTypesDao(Connection connection) {
        this.connection = connection;
    }

    // Show the list of control types
    public List<String> findAll() {
        String sql =
                """
                SELECT control_type FROM control_types
                """;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> allTypes = new ArrayList<>();
            while (resultSet.next()) {
                String type = resultSet.getString("control_type");
                allTypes.add(type);
            }
            return allTypes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find control type by id
    public String findById(int id) {
        String sql =
                """
                SELECT control_type FROM control_types
                WHERE control_type_id = %s
                """.formatted(id);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString("control_type");
            } else {
                return "";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find id by control_type
    public int findId(String type) {
        String sql =
                """
                SELECT control_type_id FROM control_types
                WHERE control_type = '%s'
                """.formatted(type);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("control_type_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Add new control type
    public void insert(String newType) {
        String sql =
                """
                INSERT INTO control_types (control_type) VALUES
                (?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newType);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update control type
    public void update(int id, String newType) {
        String sql =
                """
                UPDATE control_types
                SET control_type = ?
                WHERE control_type_id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newType);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete control type
    public boolean delete(String type) {
        String sql =
                """
                DELETE FROM control_types
                WHERE control_type = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, type);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
