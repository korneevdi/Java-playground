package airport.service.basic;

import airport.dao.basic.AirlinesDao;
import airport.dao.contact.AirlineContactsDao;
import airport.entity.basic.Airline;
import airport.entity.contact.AirlineContact;
import airport.service.AbstractService;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;


public class AirlinesService extends AbstractService<Airline> {

    private final AirlineContactsDao airlineContactsDao;

    private final static String ENTITY_NAME = "Airline";

    public AirlinesService(AirlinesDao airlinesDao, AirlineContactsDao airlineContactsDao) {
        super(airlinesDao, ENTITY_NAME);
        this.airlineContactsDao = airlineContactsDao;

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("iata", 2),
                Map.entry("icao", 3),
                Map.entry("name", 60)
        );
    }

    // Add new airline (airline data + contact data)
    public void add(String iata, String icao, String name, String contactName,
                    String contactEmail, String contactPhone, String city, String notes) {

        AirlineContact contact = new AirlineContact(0, contactName, contactEmail, contactPhone, city, notes);

        // Make sure the contact already exists or create it
        if(!airlineContactsDao.exists(contact)) {
            airlineContactsDao.insert(contact);
        }

        // Get the ID of an existing or new contact
        int contactId = airlineContactsDao.findId(Map.of("contact_email", contactEmail))
                .orElseThrow(() -> new IllegalStateException("Could not retrieve contact ID after insert"));

        contact.setId(contactId);

        Airline airline = new Airline(0, iata, icao, name, contact);

        addElement(airline);
    }

    // Delete element
    public void delete(String iata) {
        int contactId = 0;
        try{
            contactId = dao.findByField("iata", iata).get(0).getContact().getId();
        } catch (RuntimeException ignored) {}

        Map<String, String> map = Map.of("iata", iata);
        deleteElement(map);

        try{
            boolean contactDeleted = airlineContactsDao.deleteById(contactId);
            if(contactDeleted) {
                System.out.println("Airline contact deleted successfully");
            }
        } catch (RuntimeException e) {
            System.out.println("Airline contact was not deleted");
        }
    }

    /*
    // Update airline
    public void update(String oldIata, String oldIcao, String oldName, String oldContactName, String oldEmail, String oldPhone, String oldCity, String oldNotes,
                       String newIata, String newIcao, String newName, String newContactName, String newEmail, String newPhone, String newCity, String newNotes) {
        AirlineContact oldContact = new AirlineContact(0, oldContactName, oldEmail, oldPhone, oldCity, oldNotes);
        int contactId = airlineContactsDao.findId(oldContact);
        AirlineContact newContact = new AirlineContact(contactId, newContactName, newEmail, newPhone, newCity, newNotes);
        airlineContactsDao.update(newContact);

        Airline oldAirline = new Airline(0, oldIata, oldIcao, oldName, oldContact);
        int airlineId = airlineDao.
    }
    */
}
