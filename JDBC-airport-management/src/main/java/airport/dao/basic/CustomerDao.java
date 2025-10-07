package airport.dao.basic;

import airport.entity.basic.Customer;
import airport.entity.contact.CustomerContact;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao extends AbstractBasicDao<Customer> {

    private final static String TABLE_NAME = "customers";
    private final static String ID_NAME = "customer_id";

    public CustomerDao(Connection connection) {
        super(connection, TABLE_NAME, ID_NAME);
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
