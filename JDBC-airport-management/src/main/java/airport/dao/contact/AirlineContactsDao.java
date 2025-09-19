package airport.dao.contact;

import airport.entity.contact.AirlineContact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirlineContactsDao {

    private final Connection connection;

    private final static String TABLE_NAME = "airline_contacts";

    private final static String SELECTED_FIELDS = "contact_id, contact_name, email, phone, headquarter_city, notes";

    public AirlineContactsDao(Connection connection) {
        this.connection = connection;
    }

    // Show the list of elements
    public List<AirlineContact> findAll() {
        String sql =
                """
                SELECT %s FROM %s
                """.formatted(SELECTED_FIELDS, TABLE_NAME);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<AirlineContact> allElements = new ArrayList<>();
            while (resultSet.next()) {
                allElements.add(mapRow(resultSet));
            }
            return allElements;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find element by id
    public AirlineContact findById(int id) {
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

    // Find element by name
    public List<AirlineContact> findAllByName(String name) {
        return findByField("contact_name", name);
    }

    // Find element by email
    public Optional<AirlineContact> findByEmail(String email) {
        return findByField("email", email).stream().findFirst();
    }

    // Find element by phone
    public List<AirlineContact> findAllByPhone(String phone) {
        return findByField("phone", phone);
    }

    // Find element by city
    public List<AirlineContact> findAllByCity(String city) {
        return findByField("headquarter_city", city);
    }

    // Add new element
    public void insert(AirlineContact contact) {
        String sql =
                """
                INSERT INTO %s (contact_name, email, phone, headquarter_city, notes) VALUES
                (?, ?, ?, ?, ?)
                """.formatted(TABLE_NAME);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, contact.getContactName());
            ps.setString(2, contact.getEmail());
            ps.setString(3, contact.getPhone());
            ps.setString(4, contact.getCity());
            ps.setString(5, contact.getNotes());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update element
    // TODO: update only those fields that have actually changed
    public void update(AirlineContact contact) {
        String sql =
                """
                UPDATE %s
                SET contact_name = ?, email = ?, phone = ?, headquarter_city = ?, notes = ?
                WHERE contact_id = ?
                """.formatted(TABLE_NAME);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, contact.getContactName());
            ps.setString(2, contact.getEmail());
            ps.setString(3, contact.getPhone());
            ps.setString(4, contact.getCity());
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
    public boolean exists(AirlineContact contact) {
        String sql =
                """
                SELECT 1 FROM %s
                WHERE contact_name = ? AND email = ? AND phone = ? AND headquarter_city = ?
                """.formatted(TABLE_NAME);

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, contact.getContactName());
            ps.setString(2, contact.getEmail());
            ps.setString(3, contact.getPhone());
            ps.setString(4, contact.getCity());
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next(); // 'true' if something found, 'false' otherwise
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private AirlineContact mapRow(ResultSet resultSet) throws SQLException {
        return new AirlineContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("contact_name"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getString("headquarter_city"),
                resultSet.getString("notes")
        );
    }

    private List<AirlineContact> findByField(String fieldName, String fieldValue) {
        String sql =
                """
                SELECT %s FROM %s
                WHERE %s = ?
                """.formatted(SELECTED_FIELDS, TABLE_NAME, fieldName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, fieldValue);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<AirlineContact> allContacts = new ArrayList<>();
            while (resultSet.next()) {
                allContacts.add(mapRow(resultSet));
            }
            return allContacts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
