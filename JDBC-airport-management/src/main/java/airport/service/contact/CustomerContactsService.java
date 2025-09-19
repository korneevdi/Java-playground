package airport.service.contact;

import airport.dao.contact.CustomerContactsDao;
import airport.entity.contact.CustomerContact;

import java.util.List;
import java.util.Optional;

public class CustomerContactsService {

    private final CustomerContactsDao customerContactsDao;
    private final static int EMAIL_MAX_LENGTH = 100;
    private final static int PHONE_MAX_LENGTH = 30;
    private final static int CITY_MAX_LENGTH = 25;
    private final static int ADDRESS_MAX_LENGTH = 200;

    public CustomerContactsService(CustomerContactsDao customerContactsDao) {
        this.customerContactsDao = customerContactsDao;
    }

    // Show all saved contacts
    public void showAll() {
        List<CustomerContact> allElements = customerContactsDao.findAll();
        System.out.println("List of elements:");
        if (!allElements.isEmpty()) {
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
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

        if(!isValidContact(contact)) return;

        if (customerContactsDao.exists(contact)) {
            String output =
                    """
                    Element with the following properties

                    email: %s,
                    phone: %s,
                    city: %s,
                    address: %s

                    already exists.
                    """.formatted(contact.getEmail(), contact.getPhone(), contact.getCity(), contact.getAddress());
            System.out.println(output);
            return;
        }

        customerContactsDao.insert(contact);
        System.out.println("New element inserted successfully");
    }

    // Update contact
    public void update(String oldEmail, String oldPhone, String oldCity, String oldAddress, String oldNotes,
                       String newEmail, String newPhone, String newCity, String newAddress, String newNotes) {

        CustomerContact oldContact = new CustomerContact(0, oldEmail, oldPhone, oldCity, oldAddress, oldNotes);
        if (!customerContactsDao.exists(oldContact)) {
            String output =
                    """
                    Element with the following properties

                    email: %s,
                    phone: %s,
                    city: %s,
                    address: %s

                    does not exist.
                    """.formatted(oldEmail, oldPhone, oldCity, oldAddress);
            System.out.println(output);
            return;
        }

        if(oldEmail.equals(newEmail) && oldPhone.equals(newPhone) &&
                oldCity.equals(newCity) && oldAddress.equals(newAddress) && oldNotes.equals(newNotes)) {
            System.out.println("The old and new properties are identical. Nothing to update.");
            return;
        }

        int id = customerContactsDao.findByEmail(oldEmail).
                orElseThrow(() -> new RuntimeException("Contact to update is not found"))
                .getId();
        CustomerContact newContact = new CustomerContact(id, newEmail, newPhone, newCity, newAddress, newNotes);
        if(!isValidContact(newContact)) return;

        customerContactsDao.update(newContact);
        System.out.println("Element updated successfully");
    }

    // Delete contact
    public void delete(String email, String phone, String city, String address) {

        CustomerContact contact = new CustomerContact(0, email, phone, city, address, "");
        if(!customerContactsDao.exists(contact)) {
            String output =
                    """
                    Element with the following properties

                    email: %s,
                    phone: %s,
                    city: %s,
                    address: %s

                    does not exist.
                    """.formatted(email, phone, city, address);
            System.out.println(output);
            return;
        }

        int id = customerContactsDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Could not find element"))
                .getId();

        boolean deleted = customerContactsDao.deleteById(id);

        if(deleted) {
            System.out.println("Element deleted successfully");
        } else {
            System.out.println("Failed to delete element");
        }
    }

    private void printList(List<CustomerContact> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i));
        }
    }

    private boolean isValidContact(CustomerContact contact) {
        String email = contact.getEmail();
        String phone = contact.getPhone();
        String city = contact.getCity();
        String address = contact.getAddress();

        if(email == null || email.isEmpty()) {
            System.out.println("Email should not be NULL or empty");
            return false;
        }
        if(email.length() > EMAIL_MAX_LENGTH) {
            System.out.println("The email entered is too long: max length is " + EMAIL_MAX_LENGTH + " symbols");
            return false;
        }

        if(phone == null || phone.isEmpty()) {
            System.out.println("Phone should not be NULL or empty");
            return false;
        }
        if(phone.length() > PHONE_MAX_LENGTH) {
            System.out.println("The phone entered is too long: max length is " + PHONE_MAX_LENGTH + " symbols");
            return false;
        }

        if(city == null || city.isEmpty()) {
            System.out.println("City name should not be NULL or empty");
            return false;
        }
        if(city.length() > CITY_MAX_LENGTH) {
            System.out.println("The city entered is too long: max length is " + CITY_MAX_LENGTH + " symbols");
            return false;
        }

        if(address == null || address.isEmpty()) {
            System.out.println("Address should not be NULL or empty");
            return false;
        }
        if(address.length() > ADDRESS_MAX_LENGTH) {
            System.out.println("The address entered is too long: max length is " + ADDRESS_MAX_LENGTH + " symbols");
            return false;
        }
        return true;
    }
}
