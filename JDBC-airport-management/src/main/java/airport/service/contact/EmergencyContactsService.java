package airport.service.contact;

import airport.dao.contact.EmergencyContactsDao;
import airport.entity.contact.EmergencyContact;

import java.util.List;
import java.util.Optional;

public class EmergencyContactsService extends AbstractContactService<EmergencyContact> {

    private final EmergencyContactsDao emergencyContactsDao;

    public EmergencyContactsService(EmergencyContactsDao dao) {
        super(dao);
        this.emergencyContactsDao = dao;

        // Set the map of the fields and max lengths
        fieldMaxLengths.put("contact_name", 100);
        fieldMaxLengths.put("relation", 30);
        fieldMaxLengths.put("phone", 30);
    }

    // All contacts with certain contact name
    public void findAllElementsByName(String name) {
        List<EmergencyContact> allElements = emergencyContactsDao.findAllByName(name);
        if (!allElements.isEmpty()) {
            System.out.println("All contacts found with contact name '" + name + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // All contacts with certain phone
    public void findAllElementsByPhone(String phone) {
        List<EmergencyContact> allElements = emergencyContactsDao.findAllByPhone(phone);
        if (!allElements.isEmpty()) {
            System.out.println("All contacts found with phone '" + phone + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // Add new contact
    public void add(String contactName, String relation, String phone) {
        EmergencyContact contact = new EmergencyContact(0, contactName, relation, phone);
        addContact(contact);
    }

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

    // Delete contact
    public void delete(String contactName, String relation, String phone) {
        EmergencyContact contact = new EmergencyContact(0, contactName, relation, phone);
        deleteContact(contact);
    }

    @Override
    protected boolean isValidContact(EmergencyContact contact) {
        return validateField("contact_name", contact.getContactName()) &&
                validateField("relation", contact.getRelation()) &&
                validateField("phone", contact.getPhone());
    }

    @Override
    protected void existsOrNotOutput(EmergencyContact contact, boolean isExists) {
        String output =
                """
                Element with the following properties

                contact name: %s,
                relation: %s,
                phone: %s
                """.formatted(contact.getContactName(), contact.getRelation(), contact.getPhone());

        if(isExists) {
            output = output + "\nalready exists.";
        } else {
            output = output + "\ndoes not exist.";
        }
        System.out.println(output);
    }

    @Override
    protected boolean areContactsIdentical(EmergencyContact oldContact, EmergencyContact newContact) {
        return oldContact.equals(newContact);
    }

    @Override
    protected Optional<Integer> findId(EmergencyContact contact) {
        return emergencyContactsDao.findSingle(
                contact.getContactName(), contact.getRelation(), contact.getPhone()
        ).map(EmergencyContact::getId);
    }
}
