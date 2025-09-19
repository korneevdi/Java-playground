package airport.dao.contact;

import airport.entity.contact.AirlineContact;
import airport.entity.contact.EmergencyContact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmergencyContactsDao {

    private final Connection connection;

    private final static String TABLE_NAME = "emergency_contacts";

    private final static String SELECTED_FIELDS = "contact_id, contact_name, relation, phone";

    public EmergencyContactsDao(Connection connection) {
        this.connection = connection;
    }

    // Show the list of elements
    public List<EmergencyContact> findAll() {
        String sql =
                """
                SELECT %s FROM %s
                """.formatted(SELECTED_FIELDS, TABLE_NAME);

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<EmergencyContact> allElements = new ArrayList<>();
            while (resultSet.next()) {
                allElements.add(mapRow(resultSet));
            }
            return allElements;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Find element by id
    public EmergencyContact findById(int id) {
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
    public List<EmergencyContact> findAllByName(String name) {
        return findByField("contact_name", name);
    }

    // Find element by phone
    public List<EmergencyContact> findAllByPhone(String phone) {
        return findByField("phone", phone);
    }

    // Find the only element by name, phone and relation
    public Optional<EmergencyContact> findSingle(String name, String relation, String phone) {
        String sql = """
            SELECT %s FROM %s
            WHERE contact_name = ? AND relation = ? AND phone = ?
            """.formatted(SELECTED_FIELDS, TABLE_NAME);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, relation);
            ps.setString(3, phone);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                EmergencyContact contact = new EmergencyContact(
                        rs.getInt("contact_id"),
                        rs.getString("contact_name"),
                        rs.getString("relation"),
                        rs.getString("phone")
                );
                return Optional.of(contact);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // Add new element
    public void insert(EmergencyContact contact) {
        String sql =
                """
                INSERT INTO %s (contact_name, relation, phone) VALUES
                (?, ?, ?)
                """.formatted(TABLE_NAME);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, contact.getContactName());
            ps.setString(2, contact.getRelation());
            ps.setString(3, contact.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update element
    // TODO: update only those fields that have actually changed
    public void update(EmergencyContact contact) {
        String sql =
                """
                UPDATE %s
                SET contact_name = ?, relation = ?, phone = ?
                WHERE contact_id = ?
                """.formatted(TABLE_NAME);

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, contact.getContactName());
            ps.setString(2, contact.getRelation());
            ps.setString(3, contact.getPhone());
            ps.setInt(4, contact.getId());
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
    public boolean exists(EmergencyContact contact) {
        String sql =
                """
                SELECT 1 FROM %s
                WHERE contact_name = ? AND relation = ? AND phone = ?
                """.formatted(TABLE_NAME);

        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, contact.getContactName());
            ps.setString(2, contact.getRelation());
            ps.setString(3, contact.getPhone());
            ResultSet resultSet = ps.executeQuery();
            return resultSet.next(); // 'true' if something found, 'false' otherwise
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private EmergencyContact mapRow(ResultSet resultSet) throws SQLException {
        return new EmergencyContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("contact_name"),
                resultSet.getString("relation"),
                resultSet.getString("phone")
        );
    }

    private List<EmergencyContact> findByField(String fieldName, String fieldValue) {
        String sql =
                """
                SELECT %s FROM %s
                WHERE %s = ?
                """.formatted(SELECTED_FIELDS, TABLE_NAME, fieldName);

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, fieldValue);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<EmergencyContact> allContacts = new ArrayList<>();
            while (resultSet.next()) {
                allContacts.add(mapRow(resultSet));
            }
            return allContacts;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
