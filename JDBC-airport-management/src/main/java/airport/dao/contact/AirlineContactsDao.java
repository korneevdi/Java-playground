package airport.dao.contact;

import airport.entity.contact.AirlineContact;

import java.sql.*;
import java.util.List;

public class AirlineContactsDao extends AbstractContactDao<AirlineContact> {

    private final static String TABLE_NAME = "airline_contacts";

    private final static String ID_NAME = "contact_id";
    private final static List<String> UNIQUE_FIELDS = List.of(
            "contact_email"
    );

    public AirlineContactsDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
    }

    public List<String> getUniqueFields() {
        return UNIQUE_FIELDS;
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
    protected void setExistsStatement(PreparedStatement ps, AirlineContact airlineContact) throws SQLException {
        ps.setString(1, airlineContact.getEmail());
    }

    @Override
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (contact_name, contact_email, contact_phone, city, notes) VALUES
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

    /*
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
*/
}
