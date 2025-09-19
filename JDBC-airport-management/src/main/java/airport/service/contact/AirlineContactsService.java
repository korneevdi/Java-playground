package airport.service.contact;

import airport.dao.contact.AirlineContactsDao;
import airport.entity.contact.AirlineContact;

import java.util.List;
import java.util.Optional;

public class AirlineContactsService {

    private final AirlineContactsDao airlineContactsDao;
    private final static int CONTACT_NAME_MAX_LENGTH = 100;
    private final static int EMAIL_MAX_LENGTH = 100;
    private final static int PHONE_MAX_LENGTH = 30;
    private final static int CITY_MAX_LENGTH = 25;

    public AirlineContactsService(AirlineContactsDao airlineContactsDao) {
        this.airlineContactsDao = airlineContactsDao;
    }

    // Show all saved contacts
    public void showAll() {
        List<AirlineContact> allElements = airlineContactsDao.findAll();
        System.out.println("List of elements:");
        if (!allElements.isEmpty()) {
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // All contacts with certain contact name
    public void findAllElementsByName(String name) {
        List<AirlineContact> allElements = airlineContactsDao.findAllByName(name);
        if (!allElements.isEmpty()) {
            System.out.println("All contacts found with contact name '" + name + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // Contact with certain email
    public void findElementByEmail(String email) {
        Optional<AirlineContact> contact = airlineContactsDao.findByEmail(email);
        if (contact.isPresent()) {
            System.out.println("Contact found with email '" + email + "':");
            System.out.println(contact.get());
        } else {
            System.out.println("No data found");
        }
    }

    // All contacts with certain phone
    public void findAllElementsByPhone(String phone) {
        List<AirlineContact> allElements = airlineContactsDao.findAllByPhone(phone);
        if (!allElements.isEmpty()) {
            System.out.println("All contacts found with phone '" + phone + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // All contacts with certain city
    public void findAllElementsByCity(String city) {
        List<AirlineContact> allElements = airlineContactsDao.findAllByCity(city);
        if (!allElements.isEmpty()) {
            System.out.println("All contacts found with headquarter in '" + city + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // Add new contact
    public void add(String contactName, String email, String phone, String city, String notes) {
        AirlineContact contact = new AirlineContact(0, contactName, email, phone, city, notes);

        if(!isValidContact(contact)) return;

        if (airlineContactsDao.exists(contact)) {
            String output =
                    """
                    Element with the following properties
                    
                    contact name: %s,
                    email: %s,
                    phone: %s,
                    headquarter city: %s
                    
                    already exists.
                    """.formatted(contact.getContactName(), contact.getEmail(), contact.getPhone(), contact.getCity());
            System.out.println(output);
            return;
        }

        airlineContactsDao.insert(contact);
        System.out.println("New element inserted successfully");
    }

    // Update contact
    public void update(String oldContactName, String oldEmail, String oldPhone, String oldCity, String oldNotes,
                       String newContactName, String newEmail, String newPhone, String newCity, String newNotes) {

        AirlineContact oldContact = new AirlineContact(0, oldContactName, oldEmail, oldPhone, oldCity, oldNotes);
        if (!airlineContactsDao.exists(oldContact)) {
            String output =
                    """
                    Element with the following properties
                    
                    contact name: %s,
                    email: %s,
                    phone: %s,
                    headquarter city: %s
                    
                    does not exist.
                    """.formatted(oldContactName, oldEmail, oldPhone, oldCity);
            System.out.println(output);
            return;
        }

        if(oldContactName.equals(newContactName) && oldEmail.equals(newEmail) &&
        oldPhone.equals(newPhone) && oldCity.equals(newCity) && oldNotes.equals(newNotes)) {
            System.out.println("The old and new properties are identical. Nothing to update.");
            return;
        }

        int id = airlineContactsDao.findByEmail(oldEmail).
                orElseThrow(() -> new RuntimeException("Contact to update is not found"))
                .getId();
        AirlineContact newContact = new AirlineContact(id, newContactName, newEmail, newPhone, newCity, newNotes);
        if(!isValidContact(newContact)) return;

        airlineContactsDao.update(newContact);
        System.out.println("Element updated successfully");
    }

    // Delete contact
    public void delete(String contactName, String email, String phone, String city) {

        AirlineContact contact = new AirlineContact(0, contactName, email, phone, city, "");
        if(!airlineContactsDao.exists(contact)) {
            String output =
                    """
                    Element with the following properties
                    
                    contact name: %s,
                    email: %s,
                    phone: %s,
                    headquarter city: %s
                    
                    does not exist.
                    """.formatted(contactName, email, phone, city);
            System.out.println(output);
            return;
        }

        int id = airlineContactsDao.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Could not find element"))
                .getId();

        boolean deleted = airlineContactsDao.deleteById(id);

        if(deleted) {
            System.out.println("Element deleted successfully");
        } else {
            System.out.println("Failed to delete element");
        }
    }

    private void printList(List<AirlineContact> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i));
        }
    }

    private boolean isValidContact(AirlineContact contact) {
        String contactName = contact.getContactName();
        String email = contact.getEmail();
        String phone = contact.getPhone();
        String city = contact.getCity();

        if(contactName == null || contactName.isEmpty()) {
            System.out.println("Element name should not be NULL or empty");
            return false;
        }
        if(contactName.length() > CONTACT_NAME_MAX_LENGTH) {
            System.out.println("The contact name entered is too long: max length is " + CONTACT_NAME_MAX_LENGTH + " symbols");
            return false;
        }

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
        return true;
    }
}
