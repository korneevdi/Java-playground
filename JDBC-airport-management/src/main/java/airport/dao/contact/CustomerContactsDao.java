package airport.dao.contact;

import airport.entity.contact.CustomerContact;

import java.sql.*;
import java.util.List;

public class CustomerContactsDao extends AbstractContactDao<CustomerContact> {

    private final static String TABLE_NAME = "customer_contacts";
    private final static String ID_NAME = "contact_id";
    private final static List<String> UNIQUE_FIELDS = List.of(
            "contact_email"
    );

    public CustomerContactsDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT cc.contact_id, cc.contact_email, cc.contact_phone, cc.city, cc.address, cc.notes
                FROM customer_contacts cc
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT cc.contact_id, cc.contact_email, cc.contact_phone, cc.city, cc.address, cc.notes
                FROM customer_contacts cc
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT cc.contact_id, cc.contact_email, cc.contact_phone, cc.city, cc.address, cc.notes
                FROM customer_contacts cc
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, CustomerContact customerContact) throws SQLException {
        ps.setString(1, customerContact.getEmail());
    }

    @Override
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (contact_email, contact_phone, city, address, notes) VALUES
                (?, ?, ?, ?, ?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, CustomerContact contact) throws SQLException {
        ps.setString(1, contact.getEmail());
        ps.setString(2, contact.getPhone());
        ps.setString(3, contact.getCity());
        ps.setString(4, contact.getAddress());
        ps.setString(5, contact.getNotes());
    }

    @Override
    protected CustomerContact mapRow(ResultSet resultSet) throws SQLException {
        return new CustomerContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("contact_email"),
                resultSet.getString("contact_phone"),
                resultSet.getString("city"),
                resultSet.getString("address"),
                resultSet.getString("notes")
        );
    }


    // ----------------------------------------------------------------------------------------

    /*
    // Update element
    @Override
    protected String buildUpdateSql() {
        return """
                UPDATE %s
                SET email = ?, phone = ?, city = ?, address = ?, notes = ?
                WHERE contact_id = ?
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, CustomerContact contact) throws SQLException {
        ps.setString(1, contact.getEmail());
        ps.setString(2, contact.getPhone());
        ps.setString(3, contact.getCity());
        ps.setString(4, contact.getAddress());
        ps.setString(5, contact.getNotes());
        ps.setInt(6, contact.getId());
    }
*/
}
