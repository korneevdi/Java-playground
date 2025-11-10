package airport.service.contact;

import airport.dao.contact.AirlineContactsDao;
import airport.entity.contact.AirlineContact;
import airport.service.AbstractService;

import java.util.Map;

public class AirlineContactsService extends AbstractService<AirlineContact> {

    private final static String ENTITY_NAME = "Airline contact";

    public AirlineContactsService(AirlineContactsDao dao) {
        super(dao, ENTITY_NAME);

        // Set the map of the fields and max lengths
        stringFields = Map.of(
                "contact_name", 100,
                "contact_email", 100,
                "contact_phone", 30,
                "city", 25,
                "notes", 1000
        );
    }

    // Add new contact
    public void add(String contactName, String contactEmail, String contactPhone, String city, String notes) {
        addElement(new AirlineContact(0, contactName, contactEmail, contactPhone, city, notes));
    }

    /*
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
    protected Optional<Integer> findId(AirlineContact contact) {
        return airlineContactsDao.findByEmail(contact.getEmail()).map(AirlineContact::getId);
    }*/
}
