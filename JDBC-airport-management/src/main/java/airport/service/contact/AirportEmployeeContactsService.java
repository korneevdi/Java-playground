package airport.service.contact;

import airport.dao.contact.AirportEmployeeContactsDao;
import airport.entity.contact.AirportEmployeeContact;
import airport.service.AbstractService;

import java.util.Map;

public class AirportEmployeeContactsService extends AbstractService<AirportEmployeeContact> {

    private final static String ENTITY_NAME = "Airport employee contact";

    public AirportEmployeeContactsService(AirportEmployeeContactsDao dao) {
        super(dao, ENTITY_NAME);

        // Set the map of the fields and max lengths
        stringFields = Map.of(
                "contact_email", 100,
                "contact_phone", 30,
                "city", 25,
                "address", 200,
                "notes", 1000
        );
    }

    // Add new contact
    public void add(String contactEmail, String contactPhone, String city, String address, String notes) {
        addElement(new AirportEmployeeContact(0, contactEmail, contactPhone, city, address, notes));
    }

    // Delete element
    public void delete(String contactEmail) {
        Map<String, String> map = Map.of("contact_email", contactEmail);
        deleteElement(map);
    }

    /*
    // Update contact
    public void update(String oldEmail, String oldPhone, String oldCity, String oldAddress, String oldNotes,
                       String newEmail, String newPhone, String newCity, String newAddress, String newNotes) {

        AirportEmployeeContact oldContact = new AirportEmployeeContact(0, oldEmail, oldPhone, oldCity, oldAddress, oldNotes);

        int id = employeeContactsDao.findByEmail(oldContact.getEmail())
                .orElseThrow(() -> new RuntimeException("Contact to update is not found"))
                .getId();

        AirportEmployeeContact newContact = new AirportEmployeeContact(id, newEmail, newPhone, newCity, newAddress, newNotes);

        updateContact(oldContact, newContact);
    }
    */
}
