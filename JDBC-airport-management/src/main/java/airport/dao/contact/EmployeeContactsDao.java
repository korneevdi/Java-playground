package airport.dao.contact;

import airport.entity.contact.EmergencyContact;
import airport.entity.contact.EmployeeContact;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class EmployeeContactsDao extends AbstractContactDao<EmployeeContact> {

    private final static String TABLE_NAME = "employee_contacts";
    private final static String SELECTED_FIELDS = "contact_id, email, phone, city, address, notes";
    private final static String ID_NAME = "contact_id";

    public EmployeeContactsDao(Connection connection) {
        super(connection, TABLE_NAME, SELECTED_FIELDS, ID_NAME);
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

    @Override
    protected String buildInsertSql() {
        return """
                INSERT INTO %s (email, phone, city, address, notes) VALUES
                (?, ?, ?, ?, ?)
                """.formatted(TABLE_NAME);
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, EmployeeContact contact) throws SQLException {
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
    protected void setUpdateStatement(PreparedStatement ps, EmployeeContact contact) throws SQLException {
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
    protected void setExistsStatement(PreparedStatement ps, EmployeeContact contact) throws SQLException {
        ps.setString(1, contact.getEmail());
        ps.setString(2, contact.getPhone());
        ps.setString(3, contact.getCity());
        ps.setString(4, contact.getAddress());
    }

    @Override
    protected EmployeeContact mapRow(ResultSet resultSet) throws SQLException {
        return new EmployeeContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
                resultSet.getString("city"),
                resultSet.getString("address"),
                resultSet.getString("notes")
        );
    }

    @Override
    protected String buildFindIdSql() {
        return """
                SELECT %s FROM %s
                WHERE email = ? AND phone = ? AND city = ? AND address = ?
                """.formatted(ID_NAME, TABLE_NAME);
    }

    @Override
    protected void setFindIdStatement(PreparedStatement ps, EmployeeContact contact) throws SQLException {
        ps.setString(1, contact.getEmail());
        ps.setString(2, contact.getPhone());
        ps.setString(3, contact.getCity());
        ps.setString(4, contact.getAddress());
    }
}
