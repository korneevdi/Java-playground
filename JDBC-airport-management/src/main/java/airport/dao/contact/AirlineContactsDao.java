package airport.dao.contact;

import airport.entity.contact.AirlineContact;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class AirlineContactsDao extends AbstractContactDao<AirlineContact> {

    private final static String TABLE_NAME = "airline_contacts";

    private final static String SELECTED_FIELDS = "contact_id, contact_name, email, phone, headquarter_city, notes";

    private final static String ID_NAME = "contact_id";

    public AirlineContactsDao(Connection connection) {
        super(connection, TABLE_NAME, SELECTED_FIELDS, ID_NAME);
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
    @Override
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (contact_name, email, phone, headquarter_city, notes) VALUES
                (?, ?, ?, ?, ?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, AirlineContact contact) throws SQLException {
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getEmail());
        ps.setString(3, contact.getPhone());
        ps.setString(4, contact.getCity());
        ps.setString(5, contact.getNotes());
    }

    // Update element
    @Override
    protected String buildUpdateSql() {
        return """
                UPDATE %s
                SET contact_name = ?, email = ?, phone = ?, headquarter_city = ?, notes = ?
                WHERE contact_id = ?
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, AirlineContact contact) throws SQLException {
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getEmail());
        ps.setString(3, contact.getPhone());
        ps.setString(4, contact.getCity());
        ps.setString(5, contact.getNotes());
        ps.setInt(6, contact.getId());
    }

    // Check duplicates (via service)
    @Override
    protected String buildExistsSql() {
        return """
                SELECT 1 FROM %s
                WHERE contact_name = ? AND email = ? AND phone = ? AND headquarter_city = ?
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, AirlineContact contact) throws SQLException {
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getEmail());
        ps.setString(3, contact.getPhone());
        ps.setString(4, contact.getCity());
    }

    @Override
    protected AirlineContact mapRow(ResultSet resultSet) throws SQLException {
        return new AirlineContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("contact_name"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getString("headquarter_city"),
                resultSet.getString("notes")
        );
    }

    @Override
    protected String buildFindIdSql() {
        return """
                SELECT %s FROM %s
                WHERE contact_name = ? AND email = ? AND phone = ? AND headquarter_city = ?
                """.formatted(ID_NAME, TABLE_NAME);
    }

    @Override
    protected void setFindIdStatement(PreparedStatement ps, AirlineContact contact) throws SQLException {
        ps.setString(1, contact.getContactName());
        ps.setString(2, contact.getEmail());
        ps.setString(3, contact.getPhone());
        ps.setString(4, contact.getCity());
    }
}
