package airport.service.basic;

import airport.dao.basic.AirlinesDao;
import airport.dao.contact.AirlineContactsDao;
import airport.entity.basic.Airline;
import airport.service.contact.AirlineContactsService;

import java.util.Map;


public class AirlinesService extends AbstractBasicService<Airline> {

    private final static String ENTITY_NAME = "Airline";

    public AirlinesService(AirlinesDao airlineDao) {
        super(airlineDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("iata", 2),
                Map.entry("icao", 3),
                Map.entry("name", 60),
                Map.entry("contact_name", 100),
                Map.entry("contact_email", 100),
                Map.entry("contact_phone", 30),
                Map.entry("city", 25),
                Map.entry("notes", 1000)
        );
    }
/*
    // Add new airline (airline data + contact data)
    public void add(String iata, String icao, String name, String contactName,
                    String contactEmail, String contactPhone, String city, String notes) {

        AirlineContact contact = new AirlineContact(0, contactName, contactEmail, contactPhone, city, notes);
        Airline airline = new Airline(0, iata, icao, name, contact);

        if(!airlineContactsService.isValidContact(contact)) return;

        int contactId;
        if(airlineContactsDao.exists(contact)) {
            // get id
            contactId = airlineContactsDao.findId(contact);
            contact.setId(contactId);
            airline.setContact(contact);
            addElement(airline);
        } else {
            // insert & get id
            airlineContactsDao.insert(contact);
            contactId = airlineContactsDao.findId(contact);
            contact.setId(contactId);
            airline.setContact(contact);

            boolean inserted = addElement(airline);
            if(!inserted) {
                airlineContactsDao.deleteById(contactId);
            }
        }
    }*/

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

    @Override
    protected boolean isValidContact(Airline airline) {
        boolean validAirline = validateField("iata", airline.getIata()) &&
                validateField("icao", airline.getIcao()) &&
                validateField("name", airline.getName());

        boolean validContact = airlineContactsService.isValidContact(airline.getContact());

        return validAirline && validContact;
    }

    @Override
    protected void existsOrNotOutput(Airline airline, boolean isExists) {
        String output =
                """
                Element with the following properties

                iata: %s,
                icao: %s,
                name: %s,
                contact name: %s,
                email: %s,
                phone: %s,
                city: %s
                """.formatted(airline.getIata(), airline.getIcao(), airline.getName(),
                        airline.getContact().getContactName(), airline.getContact().getEmail(),
                        airline.getContact().getPhone(), airline.getContact().getCity());

        if(isExists) {
            output = output + "\nalready exists.";
        } else {
            output = output + "\ndoes not exist.";
        }
        System.out.println(output);
    }*/
}
