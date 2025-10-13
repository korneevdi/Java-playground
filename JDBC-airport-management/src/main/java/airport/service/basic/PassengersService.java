package airport.service.basic;

import airport.dao.basic.PassengersDao;
import airport.entity.basic.Passenger;

import java.util.Map;

public class PassengersService extends AbstractBasicService<Passenger>{

    private final static String ENTITY_NAME = "Passenger";

    public PassengersService(PassengersDao passengerDao) {
        super(passengerDao, ENTITY_NAME);

        // Set the map of the fields and max lengths
        stringFields = Map.ofEntries(
                Map.entry("first_name", 50),
                Map.entry("last_name", 50),
                Map.entry("sex_name", 20),
                Map.entry("passport_country", 20),
                Map.entry("passport_number", 30)
        );

        integerFields = Map.ofEntries(
                Map.entry("age", new IntRange(0, 130))
        );
    }
}
