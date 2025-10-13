package airport.service.basic;

import airport.dao.basic.CustomersDao;
import airport.entity.basic.Customer;

import java.util.Map;

public class CustomersService extends AbstractBasicService<Customer>{

    private final static String ENTITY_NAME = "Customer";

    public CustomersService(CustomersDao customerDao) {
        super(customerDao, ENTITY_NAME);

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
}
