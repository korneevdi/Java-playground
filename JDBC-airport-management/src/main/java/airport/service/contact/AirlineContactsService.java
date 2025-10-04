package airport.service.contact;

import airport.dao.contact.AirlineContactsDao;
import airport.entity.contact.AirlineContact;

import java.util.List;
import java.util.Optional;

public class AirlineContactsService extends AbstractContactService<AirlineContact> {

    private final AirlineContactsDao airlineContactsDao;

    public AirlineContactsService(AirlineContactsDao dao) {
        super(dao);
        this.airlineContactsDao = dao;

        // Set the map of the fields and max lengths
        fieldMaxLengths.put("contact_name", 100);
        fieldMaxLengths.put("email", 100);
        fieldMaxLengths.put("phone", 30);
        fieldMaxLengths.put("headquarter_city", 25);
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
        addContact(contact);
    }

    // Update contact
    public void update(String oldContactName, String oldEmail, String oldPhone, String oldCity, String oldNotes,
                       String newContactName, String newEmail, String newPhone, String newCity, String newNotes) {

        AirlineContact oldContact = new AirlineContact(0, oldContactName, oldEmail, oldPhone, oldCity, oldNotes);

        int id = airlineContactsDao.findByEmail(oldEmail).
                orElseThrow(() -> new RuntimeException("Contact to update is not found"))
                .getId();

        AirlineContact newContact = new AirlineContact(id, newContactName, newEmail, newPhone, newCity, newNotes);

        updateContact(oldContact, newContact);
    }

    // Delete contact
    public void delete(String contactName, String email, String phone, String city) {
        AirlineContact contact = new AirlineContact(0, contactName, email, phone, city, "");
        deleteContact(contact);
    }

    @Override
    public boolean isValidContact(AirlineContact contact) {
        return validateField("contact_name", contact.getContactName()) &&
                validateField("email", contact.getEmail()) &&
                validateField("phone", contact.getPhone()) &&
                validateField("headquarter_city", contact.getCity());
    }

    @Override
    protected void existsOrNotOutput(AirlineContact contact, boolean isExists) {
        String output =
                """
                Element with the following properties

                contact name: %s,
                email: %s,
                phone: %s,
                headquarter city: %s
                """.formatted(contact.getContactName(), contact.getEmail(), contact.getPhone(), contact.getCity());

        if(isExists) {
            output = output + "\nalready exists.";
        } else {
            output = output + "\ndoes not exist.";
        }
        System.out.println(output);
    }

    @Override
    protected boolean areContactsIdentical(AirlineContact oldContact, AirlineContact newContact) {
        return oldContact.equals(newContact);
    }

    @Override
    protected Optional<Integer> findId(AirlineContact contact) {
        return airlineContactsDao.findByEmail(contact.getEmail()).map(AirlineContact::getId);
    }
}
