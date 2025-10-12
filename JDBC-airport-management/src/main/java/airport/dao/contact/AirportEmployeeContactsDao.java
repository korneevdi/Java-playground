package airport.dao.contact;

import airport.entity.contact.AirportEmployeeContact;

import java.sql.*;

public class AirportEmployeeContactsDao extends AbstractContactDao<AirportEmployeeContact> {

    private final static String TABLE_NAME = "airport_employee_contacts";
    private final static String ID_NAME = "contact_id";

    public AirportEmployeeContactsDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT aec.contact_id, aec.contact_email, aec.contact_phone, aec.city, aec.address, aec.notes
                FROM airport_employee_contacts aec
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT aec.contact_id, aec.contact_email, aec.contact_phone, aec.city, aec.address, aec.notes
                FROM airport_employee_contacts aec
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT aec.contact_id, aec.contact_email, aec.contact_phone, aec.city, aec.address, aec.notes
                FROM airport_employee_contacts aec
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected AirportEmployeeContact mapRow(ResultSet resultSet) throws SQLException {
        return new AirportEmployeeContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("contact_email"),
                resultSet.getString("contact_phone"),
                resultSet.getString("city"),
                resultSet.getString("address"),
                resultSet.getString("notes")
        );
    }


    // ----------------------------------------------------------------------------------------------------

    /*@Override
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (email, phone, city, address, notes) VALUES
                (?, ?, ?, ?, ?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, AirportEmployeeContact contact) throws SQLException {
        ps.setString(1, contact.getEmail());
        ps.setString(2, contact.getPhone());
        ps.setString(3, contact.getCity());
        ps.setString(4, contact.getAddress());
        ps.setString(5, contact.getNotes());
    }

    @Override
    protected String buildUpdateSql() {
        return """
                UPDATE %s
                SET email = ?, phone = ?, city = ?, address = ?, notes = ?
                WHERE contact_id = ?
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setUpdateStatement(PreparedStatement ps, AirportEmployeeContact contact) throws SQLException {
        ps.setString(1, contact.getEmail());
        ps.setString(2, contact.getPhone());
        ps.setString(3, contact.getCity());
        ps.setString(4, contact.getAddress());
        ps.setString(5, contact.getNotes());
        ps.setInt(6, contact.getId());
    }

    @Override
    protected String buildExistsSql() {
        return """
                SELECT 1 FROM %S
                WHERE email = ? AND phone = ? AND city = ? AND address = ?
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, AirportEmployeeContact contact) throws SQLException {
        ps.setString(1, contact.getEmail());
        ps.setString(2, contact.getPhone());
        ps.setString(3, contact.getCity());
        ps.setString(4, contact.getAddress());
    }

    @Override
    protected String buildFindIdSql() {
        return """
                SELECT %s FROM %s
                WHERE email = ? AND phone = ? AND city = ? AND address = ?
                """.formatted(ID_NAME, TABLE_NAME);
    }

    @Override
    protected void setFindIdStatement(PreparedStatement ps, AirportEmployeeContact contact) throws SQLException {
        ps.setString(1, contact.getEmail());
        ps.setString(2, contact.getPhone());
        ps.setString(3, contact.getCity());
        ps.setString(4, contact.getAddress());
    }*/
}
