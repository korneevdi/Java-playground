package airport.service.basic;

import airport.dao.basic.CustomersDao;
import airport.dao.contact.CustomerContactsDao;
import airport.entity.basic.Customer;
import airport.entity.contact.CustomerContact;

import java.util.Map;

public class CustomersService extends AbstractBasicService<Customer>{

    private final CustomerContactsDao customerContactsDao;
    private final static String ENTITY_NAME = "Customer";

    public CustomersService(CustomersDao customerDao, CustomerContactsDao customerContactsDao) {
        super(customerDao, ENTITY_NAME);
        this.customerContactsDao = customerContactsDao;

        // Set the map of the fields and max lengths
        stringFields = Map.ofEntries(
                Map.entry("first_name", 50),
                Map.entry("last_name", 50),
                Map.entry("passport_country", 20),
                Map.entry("passport_number", 20),
                Map.entry("contact_email", 100),
                Map.entry("contact_phone", 30),
                Map.entry("city", 25),
                Map.entry("address", 200),
                Map.entry("notes", 1000)
        );
    }

    // Add new customer (customer data + contact data)
    public void add(String firstName, String lastName, String passportCountry, String passportNumber,
                    String contactEmail, String contactPhone, String city, String address, String notes) {

        CustomerContact contact = new CustomerContact(0, contactEmail, contactPhone, city, address, notes);

        // Make sure the contact already exists or create it
        if(!customerContactsDao.exists(contact)) {
            customerContactsDao.insert(contact);
        }

        // Get the ID of an existing or new contact
        int contactId = customerContactsDao.findId(Map.of("contact_email", contactEmail))
                .orElseThrow(() -> new IllegalStateException("Could not retrieve contact ID after insert"));

        contact.setId(contactId);

        Customer customer = new Customer(0, firstName, lastName, passportCountry, passportNumber, contact);

        addElement(customer);
    }
}
