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

    /*
    // Add new contact
    public void add(String email, String phone, String city, String address, String notes) {
        AirportEmployeeContact contact = new AirportEmployeeContact(0, email, phone, city, address, notes);
        addContact(contact);
    }

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

    // Delete contact
    public void delete(String email, String phone, String city, String address) {
        AirportEmployeeContact contact = new AirportEmployeeContact(0, email, phone, city, address, "");
        deleteContact(contact);
    }

    @Override
    protected boolean isValidContact(AirportEmployeeContact contact) {
        return validateField("email", contact.getEmail()) &&
                validateField("phone", contact.getPhone()) &&
                validateField("city", contact.getCity()) &&
                validateField("address", contact.getAddress());
    }

    @Override
    protected void existsOrNotOutput(AirportEmployeeContact contact, boolean isExists) {
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
    protected boolean areContactsIdentical(AirportEmployeeContact oldContact, AirportEmployeeContact newContact) {
        return oldContact.equals(newContact);
    }

    @Override
    protected Optional<Integer> findId(AirportEmployeeContact contact) {
        return employeeContactsDao.findByEmail(contact.getEmail()).map(AirportEmployeeContact::getId);
    }*/
}
