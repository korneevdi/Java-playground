package airport.service.basic;

import airport.dao.basic.AirplaneDao;
import airport.entity.basic.Airplane;

import java.util.Map;

public class AirplaneService extends AbstractBasicService<Airplane> {

    private final static String ENTITY_NAME = "Airplane";

    public AirplaneService(AirplaneDao airplaneDao) {
        super(airplaneDao, ENTITY_NAME);

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
}
