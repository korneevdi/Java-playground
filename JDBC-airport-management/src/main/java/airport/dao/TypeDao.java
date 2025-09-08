package airport.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TypeDao {

    private final Connection connection;

    public TypeDao(Connection connection) {
        this.connection = connection;
    }

    // Show the list of types
    public List<String> findAll() {
        String sql =
                """
                SELECT type_name FROM types
                """;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> allTypes = new ArrayList<>();
            while (resultSet.next()) {
                String type = resultSet.getString("type_name");
                allTypes.add(type);
            }
            return allTypes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find type by id
    public String findById(int id) {
        String sql =
                """
                SELECT type_name FROM types
                WHERE type_id = %s
                """.formatted(id);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString("type_name");
            } else {
                return "";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find id by type_name
    public int findId(String type) {
        String sql =
                """
                SELECT type_id FROM types
                WHERE type_name = '%s'
                """.formatted(type);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("type_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Add new type
    public void insert(String newType) {
        String sql =
                """
                INSERT INTO types (type_name) VALUES
                (?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newType);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update type
    public void update(int id, String newType) {
        String sql =
                """
                UPDATE types
                SET type_name = ?
                WHERE type_id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newType);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete type
    public boolean delete(String type) {
        String sql =
                """
                DELETE FROM types
                WHERE type_name = ?
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
