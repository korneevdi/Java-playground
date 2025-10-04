package airport.service.basic;

import airport.dao.basic.CustomerDao;
import airport.entity.basic.Customer;

public class CustomerService extends AbstractBasicService<Customer>{

    public CustomerService(CustomerDao customerDao) {
        super(customerDao);
    }
}
