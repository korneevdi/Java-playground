package airport.service.contact;

import airport.dao.contact.EmergencyContactsDao;
import airport.entity.contact.EmergencyContact;
import airport.service.AbstractService;

import java.util.Map;

public class EmergencyContactsService extends AbstractService<EmergencyContact> {

    private final static String ENTITY_NAME = "Emergency contact";

    public EmergencyContactsService(EmergencyContactsDao dao) {
        super(dao, ENTITY_NAME);

        // Set the map of the fields and max lengths
        stringFields = Map.of(
                "contact_name", 100,
                "relation", 30,
                "contact_phone", 30
        );
    }

    // Add new contact
    public void add(String contactName, String relation, String contactPhone) {
        addElement(new EmergencyContact(0, contactName, relation, contactPhone));
    }

    // Delete element
    public void delete(String contactName, String contactRelation, String contactPhone) {
        Map<String, String> map = Map.of(
                "contact_name", contactName,
                "contact_relation", contactRelation,
                "contact_phone", contactPhone);
        deleteElement(map);
    }

    /*
    // Update contact
    public void update(String oldContactName, String oldRelation, String oldPhone,
                       String newContactName, String newRelation, String newPhone) {

        EmergencyContact oldContact = new EmergencyContact(0, oldContactName, oldRelation, oldPhone);

        int id = emergencyContactsDao.findSingle(
                oldContact.getContactName(), oldContact.getRelation(), oldContact.getPhone())
                .orElseThrow(() -> new RuntimeException("Contact to update is not found"))
                .getId();

        EmergencyContact newContact = new EmergencyContact(id, newContactName, newRelation, newPhone);

        updateContact(oldContact, newContact);
    }
    */
}
