package airport.service.basic;

import airport.dao.basic.AirlinesDao;
import airport.dao.basic.AirplanesDao;
import airport.dao.dictionary.TypesDao;
import airport.entity.basic.Airline;
import airport.entity.basic.Airplane;
import airport.entity.dictionary.Type;
import airport.service.AbstractService;

import java.util.Map;

public class AirplanesService extends AbstractService<Airplane> {

    private final AirlinesDao airlinesDao;
    private final TypesDao typesDao;
    private final static String ENTITY_NAME = "Airplane";

    public AirplanesService(AirplanesDao airplaneDao, AirlinesDao airlinesDao, TypesDao typesDao) {
        super(airplaneDao, ENTITY_NAME);
        this.airlinesDao = airlinesDao;
        this.typesDao = typesDao;

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("name", 60),
                Map.entry("registration_number", 20),
                Map.entry("model", 30),
                Map.entry("type_name", 30)
        );

        integerFields = Map.ofEntries(
                Map.entry("total_capacity", new IntRange(0, 1000))
        );
    }

    // Add new airplane
    public void add(String airlineName, String registrationNumber, String model, int capacity, String typeName) {

        // Check whether airline exists (using airline name)
        Airline airline = airlinesDao.findByField("name", airlineName)
                .stream().findFirst().orElse(null);
        if(airline == null) {
            System.out.printf("Airline '%s' does not exist. Please, add new airline first", airlineName);
            return;
        }

        // Check whether type exists (using type name)
        Type type = typesDao.findByField("type_name", typeName)
                .stream().findFirst().orElse(null);
        if(type == null) {
            System.out.printf("Type '%s' does not exist. Please, add new type first", typeName);
            return;
        }

        // Create Airplane object and insert it
        Airplane airplane = new Airplane(0, airline, registrationNumber, model, capacity, type);

        addElement(airplane);
    }

    // Delete element
    public void delete(String registrationNumber) {
        Map<String, String> map = Map.of("registration_number", registrationNumber);
        deleteElement(map);
    }
}
