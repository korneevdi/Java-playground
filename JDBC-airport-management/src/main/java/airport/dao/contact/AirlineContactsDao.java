package airport.dao.contact;

import airport.entity.contact.AirlineContact;

import java.sql.*;

public class AirlineContactsDao extends AbstractContactDao<AirlineContact> {

    private final static String TABLE_NAME = "airline_contacts";

    private final static String ID_NAME = "contact_id";

    public AirlineContactsDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT ac.contact_id, ac.contact_name, ac.contact_email, ac.contact_phone, ac.city, ac.notes
                FROM airline_contacts ac
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT ac.contact_id, ac.contact_name, ac.contact_email, ac.contact_phone, ac.city, ac.notes
                FROM airline_contacts ac
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT ac.contact_id, ac.contact_name, ac.contact_email, ac.contact_phone, ac.city, ac.notes
                FROM airline_contacts ac
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected AirlineContact mapRow(ResultSet resultSet) throws SQLException {
        return new AirlineContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("contact_name"),
                resultSet.getString("contact_email"),
                resultSet.getString("contact_phone"),
                resultSet.getString("city"),
                resultSet.getString("notes")
        );
    }


    // ------------------------------------------------------------------------------------------------------

    /*// Add new element
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
    }*/
}
