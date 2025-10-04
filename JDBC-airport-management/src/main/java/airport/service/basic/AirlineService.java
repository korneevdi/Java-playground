package airport.service.basic;

import airport.dao.basic.AirlineDao;
import airport.dao.contact.AirlineContactsDao;
import airport.entity.basic.Airline;
import airport.entity.basic.Airport;
import airport.entity.contact.AirlineContact;
import airport.service.contact.AirlineContactsService;

import java.util.List;
import java.util.Optional;


public class AirlineService extends AbstractBasicService<Airline> {

    //private final AirlineContactsDao airlineContactsDao;
    //private final AirlineContactsService airlineContactsService;

    public AirlineService(AirlineDao airlineDao) {
        super(airlineDao);
        //this.airlineContactsDao = airlineContactsDao;
        //this.airlineContactsService = airlineContactsService;

        //fieldMaxLengths.put("iata", 2);
        //fieldMaxLengths.put("icao", 3);
        //fieldMaxLengths.put("name", 60);
    }
/*
    // Airline with certain IATA
    public void findElementByIata(String iata) {
        Optional<Airline> airline = airlineDao.findByIata(iata);
        if (airline.isPresent()) {
            System.out.println("Airline found with IATA '" + iata + "':");
            System.out.println(airline.get());
        } else {
            System.out.println("No data found");
        }
    }

    // Airline with certain ICAO
    public void findElementByIcao(String icao) {
        Optional<Airline> airline = airlineDao.findByIcao(icao);
        if (airline.isPresent()) {
            System.out.println("Airline found with ICAO '" + icao + "':");
            System.out.println(airline.get());
        } else {
            System.out.println("No data found");
        }
    }

    // Airline with certain name
    public void findElementByName(String name) {
        Optional<Airline> airline = airlineDao.findByName(name);
        if (airline.isPresent()) {
            System.out.println("Airline found with name '" + name + "':");
            System.out.println(airline.get());
        } else {
            System.out.println("No data found");
        }
    }

    // All airlines with certain contact name
    public void findAllElementsByContactName(String contactName) {
        List<Airline> allElements = airlineDao.findAllByContactName(contactName);
        if (!allElements.isEmpty()) {
            System.out.println("All airlines found with contact name '" + contactName + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // Airline with certain email
    public void findElementByEmail(String email) {
        Optional<Airline> airline = airlineDao.findByEmail(email);
        if (airline.isPresent()) {
            System.out.println("Airline found with email '" + email + "':");
            System.out.println(airline.get());
        } else {
            System.out.println("No data found");
        }
    }

    // All airlines with certain phone
    public void findAllElementsByPhone(String phone) {
        List<Airline> allElements = airlineDao.findAllByPhone(phone);
        if (!allElements.isEmpty()) {
            System.out.println("All airlines found with contact phone '" + phone + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // All airlines in certain city
    public void findAllElementsByCity(String city) {
        List<Airline> allElements = airlineDao.findAllByCity(city);
        if (!allElements.isEmpty()) {
            System.out.println("All airlines found in city '" + city + "':");
            printList(allElements);
        } else {
            System.out.println("No data found");
        }
    }

    // Add new airline
    public void add(String iata, String icao, String name, String contactName,
                    String email, String phone, String city, String notes) {
        AirlineContact contact = new AirlineContact(0, contactName, email, phone, city, notes);
        airlineContactsDao.insert(contact);

        int contactId = airlineContactsDao.findId(contact);
        contact.setId(contactId);
        Airline airline = new Airline(0, iata, icao, name, contact);
        airlineDao.insert(airline);
    }

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
