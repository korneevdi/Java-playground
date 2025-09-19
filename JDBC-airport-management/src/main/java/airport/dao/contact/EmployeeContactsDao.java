package airport.dao.contact;

import airport.entity.contact.EmployeeContact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeContactsDao {

    private final Connection connection;

    private final static String TABLE_NAME = "employee_contacts";

    private final static String SELECTED_FIELDS = "contact_id, email, phone, city, address, notes";

    public EmployeeContactsDao(Connection connection) {
        this.connection = connection;
    }

    // Show the list of elements
    public List<EmployeeContact> findAll() {
        String sql =
                """
                SELECT %s FROM %s
                """.formatted(SELECTED_FIELDS, TABLE_NAME);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<EmployeeContact> allElements = new ArrayList<>();
            while (resultSet.next()) {
                allElements.add(mapRow(resultSet));
            }
            return allElements;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find element by id
    public EmployeeContact findById(int id) {
        String sql =
                """
                SELECT %s FROM %s
                WHERE %s = ?
                """.formatted(SELECTED_FIELDS, TABLE_NAME, "contact_id");

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return mapRow(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find element by email
    public Optional<EmployeeContact> findByEmail(String email) {
        return findByField("email", email).stream().findFirst();
    }

    // Find element by phone
    public List<EmployeeContact> findAllByPhone(String phone) {
        return findByField("phone", phone);
    }

    // Find element by city
    public List<EmployeeContact> findAllByCity(String city) {
        return findByField("city", city);
    }

    // Find element by address
    public List<EmployeeContact> findAllByAddress(String address) {
        return findByField("address", address);
    }

    // Add new element
    public void insert(EmployeeContact contact) {
        String sql =
                """
                INSERT INTO %s (email, phone, city, address, notes) VALUES
                (?, ?, ?, ?, ?)
                """.formatted(TABLE_NAME);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, contact.getEmail());
            ps.setString(2, contact.getPhone());
            ps.setString(3, contact.getCity());
            ps.setString(4, contact.getAddress());
            ps.setString(5, contact.getNotes());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update element
    // TODO: update only those fields that have actually changed
    public void update(EmployeeContact contact) {
        String sql =
                """
                UPDATE %s
                SET email = ?, phone = ?, city = ?, address = ?, notes = ?
                WHERE contact_id = ?
                """.formatted(TABLE_NAME);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, contact.getEmail());
            ps.setString(2, contact.getPhone());
            ps.setString(3, contact.getCity());
            ps.setString(4, contact.getAddress());
            ps.setString(5, contact.getNotes());
            ps.setInt(6, contact.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete element by id
    public boolean deleteById(int id) {
        String sql =
                """
                DELETE FROM %s
                WHERE %s = ?
                """.formatted(TABLE_NAME, "contact_id");

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Check duplicates (via service)
    public boolean exists(EmployeeContact contact) {
        String sql =
                """
                SELECT 1 FROM %S
                WHERE email = ? AND phone = ? AND city = ? AND address = ?
                """.formatted(TABLE_NAME);

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, contact.getEmail());
            ps.setString(2, contact.getPhone());
            ps.setString(3, contact.getCity());
            ps.setString(4, contact.getAddress());
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next(); // 'true' if something found, 'false' otherwise
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private EmployeeContact mapRow(ResultSet resultSet) throws SQLException {
        return new EmployeeContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getString("city"),
                resultSet.getString("address"),
                resultSet.getString("notes")
        );
    }

    private List<EmployeeContact> findByField(String fieldName, String fieldValue) {
        String sql =
                """
                SELECT %s FROM %s
                WHERE %s = ?
                """.formatted(SELECTED_FIELDS, TABLE_NAME, fieldName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, fieldValue);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<EmployeeContact> allContacts = new ArrayList<>();
            while (resultSet.next()) {
                allContacts.add(mapRow(resultSet));
            }
            return allContacts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
