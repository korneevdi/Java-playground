package airport.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CrewRolesDao {

    private final Connection connection;

    public CrewRolesDao(Connection connection) {
        this.connection = connection;
    }

    // Show the list of crew roles
    public List<String> findAll() {
        String sql =
                """
                SELECT role_name FROM crew_roles
                """;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<String> allRoles = new ArrayList<>();
            while (resultSet.next()) {
                String role = resultSet.getString("role_name");
                allRoles.add(role);
            }
            return allRoles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find role by id
    public String findById(int id) {
        String sql =
                """
                SELECT role_name FROM crew_roles
                WHERE role_id = %s
                """.formatted(id);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getString("role_name");
            } else {
                return "";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find id by role_name
    public int findId(String role) {
        String sql =
                """
                SELECT role_id FROM crew_roles
                WHERE role_name = '%s'
                """.formatted(role);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt("role_id");
            } else {
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Add new role
    public void insert(String newRole) {
        String sql =
                """
                INSERT INTO crew_roles (role_name) VALUES
                (?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newRole);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update role
    public void update(int id, String newRole) {
        String sql =
                """
                UPDATE crew_roles
                SET role_name = ?
                WHERE role_id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newRole);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete role
    public void delete(String role) {
        String sql =
                """
                DELETE FROM crew_roles
                WHERE role_name = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, role);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
