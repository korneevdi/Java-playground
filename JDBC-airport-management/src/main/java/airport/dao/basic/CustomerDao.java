package airport.dao.basic;

import airport.entity.basic.Customer;
import airport.entity.contact.CustomerContact;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao extends AbstractBasicDao<Customer> {

    public CustomerDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String buildFindAllSql(){
        return """
                SELECT customer_id, first_name, last_name, passport_country, passport_number,
                       contact_id, email, phone, city, address, notes
                FROM customers
                JOIN customer_contacts ON customers.contact = customer_contacts.contact_id
                """;
    }

    @Override
    protected Customer mapRow(ResultSet resultSet) throws SQLException {
        CustomerContact contact = new CustomerContact(
                resultSet.getInt("contact_id"),
                resultSet.getString("email"),
                resultSet.getString("phone"),
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
