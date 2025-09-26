package airport.service.contact;

import airport.dao.contact.CustomerContactsDao;
import airport.entity.contact.CustomerContact;

import java.util.List;
import java.util.Optional;

public class CustomerContactsService extends AbstractContactService<CustomerContact> {

    private final CustomerContactsDao customerContactsDao;

    public CustomerContactsService(CustomerContactsDao dao) {
        super(dao);
        this.customerContactsDao = dao;

        // Set the map of the fields and max lengths
        fieldMaxLengths.put("email", 100);
        fieldMaxLengths.put("phone", 30);
        fieldMaxLengths.put("city", 25);
        fieldMaxLengths.put("address", 200);
    }

    // Contact with certain email
    public void findElementByEmail(String email) {
        Optional<CustomerContact> contact = customerContactsDao.findByEmail(email);
        if (contact.isPresent()) {
            System.out.println("Contact found with email '" + email + "':");
            System.out.println(contact.get());
        } else {
            System.out.println("No data found");
        }
    }

    // All contacts with certain phone
    public void findAllElementsByPhone(String phone) {
        List<CustomerContact> allElements = customerContactsDao.findAllByPhone(phone);
        if (!allElements.isEmpty()) {
            System.out.println("All contacts found with phone '" + phone + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // All contacts with certain city
    public void findAllElementsByCity(String city) {
        List<CustomerContact> allElements = customerContactsDao.findAllByCity(city);
        if (!allElements.isEmpty()) {
            System.out.println("All contacts found from '" + city + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // All contacts with certain address
    public void findAllElementsByAddress(String address) {
        List<CustomerContact> allElements = customerContactsDao.findAllByAddress(address);
        if (!allElements.isEmpty()) {
            System.out.println("All contacts found with address '" + address + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // Add new contact
    public void add(String email, String phone, String city, String address, String notes) {
        CustomerContact contact = new CustomerContact(0, email, phone, city, address, notes);
        addContact(contact);
    }

    // Update contact
    public void update(String oldEmail, String oldPhone, String oldCity, String oldAddress, String oldNotes,
                       String newEmail, String newPhone, String newCity, String newAddress, String newNotes) {

        CustomerContact oldContact = new CustomerContact(0, oldEmail, oldPhone, oldCity, oldAddress, oldNotes);

        int id = customerContactsDao.findByEmail(oldEmail).
                orElseThrow(() -> new RuntimeException("Contact to update is not found"))
                .getId();

        CustomerContact newContact = new CustomerContact(id, newEmail, newPhone, newCity, newAddress, newNotes);

        updateContact(oldContact, newContact);
    }

    // Delete contact
    public void delete(String email, String phone, String city, String address) {
        CustomerContact contact = new CustomerContact(0, email, phone, city, address, "");
        deleteContact(contact);
    }

    @Override
    protected boolean isValidContact(CustomerContact contact) {
        return validateField("email", contact.getEmail()) &&
                validateField("phone", contact.getPhone()) &&
                validateField("city", contact.getCity()) &&
                validateField("address", contact.getAddress());
    }

    @Override
    protected void existsOrNotOutput(CustomerContact contact, boolean isExists) {
        String output =
                """
                Element with the following properties

                email: %s,
                phone: %s,
                city: %s,
                address: %s
                """.formatted(contact.getEmail(), contact.getPhone(), contact.getCity(), contact.getAddress());

        if(isExists) {
            output = output + "\nalready exists.";
        } else {
            output = output + "\ndoes not exist.";
        }
        System.out.println(output);
    }

    @Override
    protected boolean areContactsIdentical(CustomerContact oldContact, CustomerContact newContact) {
        return oldContact.equals(newContact);
    }

    @Override
    protected Optional<Integer> findId(CustomerContact contact) {
        return customerContactsDao.findByEmail(contact.getEmail()).map(CustomerContact::getId);
    }
}
