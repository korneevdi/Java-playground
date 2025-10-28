package airport.dao.basic;

import airport.entity.basic.Customer;
import airport.entity.contact.CustomerContact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomersDao extends AbstractBasicDao<Customer> {

    private final static String TABLE_NAME = "customers";
    private final static String ID_NAME = "customer_id";
    private final static List<String> UNIQUE_FIELDS = new ArrayList<>() {{
        add("passport_country");
        add("passport_number");
    }};

    public CustomersDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME, UNIQUE_FIELDS);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT c.customer_id, c.first_name, c.last_name, c.passport_country, c.passport_number,
                       cc.contact_id, cc.contact_email, cc.contact_phone, cc.city, cc.address, cc.notes
                FROM customers c
                JOIN customer_contacts cc ON c.contact = cc.contact_id
                """;
    }

    @Override
    protected String buildFindByIdSql(){
        return """
                SELECT c.customer_id, c.first_name, c.last_name, c.passport_country, c.passport_number,
                       cc.contact_id, cc.contact_email, cc.contact_phone, cc.city, cc.address, cc.notes
                FROM customers c
                JOIN customer_contacts cc ON c.contact = cc.contact_id
                WHERE %s = ?
                """.formatted(ID_NAME);
    }

    @Override
    protected String buildFindByFieldSql(String fieldName) {
        return """
                SELECT c.customer_id, c.first_name, c.last_name, c.passport_country, c.passport_number,
                       cc.contact_id, cc.contact_email, cc.contact_phone, cc.city, cc.address, cc.notes
                FROM customers c
                JOIN customer_contacts cc ON c.contact = cc.contact_id
                WHERE %s = ?
                """.formatted(fieldName);
    }

    @Override
    protected String buildExistsSql() {
        return """
                SELECT 1 FROM %s
                WHERE %s = ? AND %s = ?
                """.formatted(TABLE_NAME, UNIQUE_FIELDS.get(0), UNIQUE_FIELDS.get(1));
    }

    @Override
    protected void setExistsStatement(PreparedStatement ps, Customer customer) throws SQLException {
        ps.setString(1, customer.getPassportCountry());
        ps.setString(2, customer.getPassportNumber());
    }

    @Override
    protected String buildInsertSql() {
        return """
        INSERT INTO customers (first_name, last_name, passport_country, passport_number, contact)
        VALUES (?, ?, ?, ?, ?)
        """;
    }

    @Override
    protected void setInsertStatement(PreparedStatement ps, Customer customer) throws SQLException {
        ps.setString(1, customer.getFirstName());
        ps.setString(2, customer.getLastName());
        ps.setString(3, customer.getPassportCountry());
        ps.setString(4, customer.getPassportNumber());
        ps.setInt(5, customer.getContact().getId());
    }

    @Override
    protected Customer mapRow(ResultSet resultSet) throws SQLException {
        CustomerContact contact = new CustomerContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("contact_email"),
                resultSet.getString("contact_phone"),
                resultSet.getString("city"),
                resultSet.getString("address"),
                resultSet.getString("notes")
        );
        return new Customer(
                resultSet.getInt("customer_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("passport_country"),
                resultSet.getString("passport_number"),
                contact
        );
    }
}
