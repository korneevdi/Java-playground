package airport.dao.contact;

import airport.entity.contact.EmergencyContact;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class EmergencyContactsDao extends AbstractContactDao<EmergencyContact> {

    private final static String TABLE_NAME = "emergency_contacts";
    private final static String SELECTED_FIELDS = "contact_id, contact_name, relation, phone";
    private final static String ID_NAME = "contact_id";

    public EmergencyContactsDao(Connection connection) {
        super(connection, TABLE_NAME, SELECTED_FIELDS, ID_NAME);
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
    @Override
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (contact_name, relation, phone) VALUES
                (?, ?, ?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, EmergencyContact contact) throws SQLException {
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getRelation());
        ps.setString(3, contact.getPhone());
    }

    // Update element
    @Override
    protected String buildUpdateSql() {
        return """
                UPDATE %s
                SET contact_name = ?, relation = ?, phone = ?
                WHERE contact_id = ?
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, EmergencyContact contact) throws SQLException {
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getRelation());
        ps.setString(3, contact.getPhone());
        ps.setInt(4, contact.getId());
        ps.setInt(6, contact.getId());
    }

    // Check duplicates (via service)
    @Override
    protected String buildExistsSql() {
        return """
                SELECT 1 FROM %s
                WHERE contact_name = ? AND relation = ? AND phone = ?
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, EmergencyContact contact) throws SQLException {
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getRelation());
        ps.setString(3, contact.getPhone());
    }

    @Override
    protected EmergencyContact mapRow(ResultSet resultSet) throws SQLException {
        return new EmergencyContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("contact_name"),
                resultSet.getString("relation"),
                resultSet.getString("phone")
        );
    }

    @Override
    protected String buildFindIdSql() {
        return """
                SELECT %s FROM %s
                WHERE contact_name = ? AND relation = ? AND phone = ?
                """.formatted(ID_NAME, TABLE_NAME);
    }

    @Override
    protected void setFindIdStatement(PreparedStatement ps, EmergencyContact contact) throws SQLException {
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getRelation());
        ps.setString(3, contact.getPhone());
    }
}
