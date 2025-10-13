package airport.service.basic;

import airport.dao.basic.AirlinesDao;
import airport.dao.basic.AirplanesDao;
import airport.dao.dictionary.TypesDao;
import airport.entity.basic.Airplane;

import java.util.Map;

public class AirplanesService extends AbstractBasicService<Airplane> {

    private final AirlinesDao airlineDao;

    private final TypesDao typeDao;

    private final static String ENTITY_NAME = "Airplane";

    public AirplanesService(AirplanesDao airplaneDao, AirlinesDao airlineDao, TypesDao typeDao) {
        super(airplaneDao, ENTITY_NAME);
        this.airlineDao = airlineDao;
        this.typeDao = typeDao;

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("iata", 2),
                Map.entry("icao", 3),
                Map.entry("name", 60),
                Map.entry("contact_name", 100),
                Map.entry("contact_email", 100),
                Map.entry("contact_phone", 30),
                Map.entry("city", 25),
                Map.entry("notes", 1000),
                Map.entry("registration_number", 20),
                Map.entry("model", 30),
                Map.entry("type_name", 30)
        );

        integerFields = Map.ofEntries(
                Map.entry("total_capacity", new IntRange(0, 1000))
        );
    }

    /*// Add new airline (airline data + contact data)
    public void add(String airlineName, String registrationNumber, String model, int capacity, String typeName) {

        // Check airline
        List<Airline> airlines = airlineDao.findByField("name", airlineName);
        if(airlines.isEmpty()) {
            System.out.println("There is not an airline " + airlineName + ". You need to add the airline first.");
            return;
        }

        // Check type
        Type type = typeDao.findByName(typeName);
        if(type == null) {
            System.out.println("There is not such a type");
            return;
        }

        AirlineContact mockContact = new AirlineContact(0, "mockContactName", "mockEmail",
                "mockPhone", "mockCity", "mockNotes");
        Airline mockAirline = new Airline(0, "mockIata", "mockIcao", airlineName, mockContact);
        Airplane airplane = new Airplane(0, mockAirline, registrationNumber, model, capacity, type);

        addElement(airplane);
    }*/
}
