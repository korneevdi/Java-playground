package airport.service.contact;

import airport.dao.contact.EmergencyContactsDao;
import airport.entity.contact.AirlineContact;
import airport.entity.contact.CustomerContact;
import airport.entity.contact.EmergencyContact;

import java.util.List;
import java.util.Optional;

public class EmergencyContactsService {

    private EmergencyContactsDao emergencyContactsDao;
    private final static int CONTACT_NAME_MAX_LENGTH = 100;
    private final static int RELATION_MAX_LENGTH = 30;
    private final static int PHONE_MAX_LENGTH = 30;

    public EmergencyContactsService(EmergencyContactsDao emergencyContactsDao) {
        this.emergencyContactsDao = emergencyContactsDao;
    }

    // Show all saved contacts
    public void showAll() {
        List<EmergencyContact> allElements = emergencyContactsDao.findAll();
        System.out.println("List of elements:");
        if (!allElements.isEmpty()) {
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
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

        if(!isValidContact(contact)) return;

        if (emergencyContactsDao.exists(contact)) {
            String output =
                    """
                    Element with the following properties
                    
                    contact name: %s,
                    relation: %s,
                    phone: %s
                    
                    already exists.
                    """.formatted(contact.getContactName(), contact.getRelation(), contact.getPhone());
            System.out.println(output);
            return;
        }

        emergencyContactsDao.insert(contact);
        System.out.println("New element inserted successfully");
    }

    // Update contact
    public void update(String oldContactName, String oldRelation, String oldPhone,
                       String newContactName, String newRelation, String newPhone) {

        // ищем старый контакт по уникальной комбинации
        Optional<EmergencyContact> optOldContact = emergencyContactsDao.findSingle(oldContactName, oldRelation, oldPhone);
        if (optOldContact.isEmpty()) {
            String output = """
                Element with the following properties
                
                contact name: %s,
                relation: %s,
                phone: %s
                
                does not exist.
                """.formatted(oldContactName, oldRelation, oldPhone);
            System.out.println(output);
            return;
        }

        EmergencyContact oldContact = optOldContact.get();

        // проверяем, есть ли вообще изменения
        if (oldContact.getContactName().equals(newContactName)
                && oldContact.getRelation().equals(newRelation)
                && oldContact.getPhone().equals(newPhone)) {
            System.out.println("The old and new properties are identical. Nothing to update.");
            return;
        }

        // формируем обновлённый объект с тем же id
        EmergencyContact newContact = new EmergencyContact(
                oldContact.getId(),
                newContactName,
                newRelation,
                newPhone
        );

        if (!isValidContact(newContact)) return;

        emergencyContactsDao.update(newContact);
        System.out.println("Element updated successfully");
    }

    // Delete contact
    public void delete(String contactName, String relation, String phone) {

        EmergencyContact contact = new EmergencyContact(0, contactName, relation, phone);
        if(!emergencyContactsDao.exists(contact)) {
            String output =
                    """
                    Element with the following properties
                    
                    contact name: %s,
                    relation: %s,
                    phone: %s
                    
                    does not exist.
                    """.formatted(contactName, relation, phone);
            System.out.println(output);
            return;
        }

        int id = emergencyContactsDao.findSingle(contactName, relation, phone)
                .orElseThrow(() -> new RuntimeException("No element found"))
                .getId();

        boolean deleted = emergencyContactsDao.deleteById(id);

        if(deleted) {
            System.out.println("Element deleted successfully");
        } else {
            System.out.println("Failed to delete element");
        }
    }

    private void printList(List<EmergencyContact> list) {
        for(int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i));
        }
    }

    private boolean isValidContact(EmergencyContact contact) {
        String contactName = contact.getContactName();
        String relation = contact.getRelation();
        String phone = contact.getPhone();

        if(contactName == null || contactName.isEmpty()) {
            System.out.println("Element name should not be NULL or empty");
            return false;
        }
        if(contactName.length() > CONTACT_NAME_MAX_LENGTH) {
            System.out.println("The contact name entered is too long: max length is " + CONTACT_NAME_MAX_LENGTH + " symbols");
            return false;
        }

        if(relation == null || relation.isEmpty()) {
            System.out.println("Email should not be NULL or empty");
            return false;
        }
        if(relation.length() > RELATION_MAX_LENGTH) {
            System.out.println("The email entered is too long: max length is " + RELATION_MAX_LENGTH + " symbols");
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
        return true;
    }
}
