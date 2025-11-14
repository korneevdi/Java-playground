package airport.service.basic;

import airport.dao.basic.PassengersDao;
import airport.dao.dictionary.SexesDao;
import airport.entity.basic.Passenger;
import airport.entity.dictionary.Sex;
import airport.service.AbstractService;

import java.util.Map;

public class PassengersService extends AbstractService<Passenger> {

    private final SexesDao sexesDao;
    private final static String ENTITY_NAME = "Passenger";

    public PassengersService(PassengersDao passengerDao, SexesDao sexesDao) {
        super(passengerDao, ENTITY_NAME);
        this.sexesDao = sexesDao;

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

    // Add new passenger
    public void add(String firstName, String lastName, String sexName,
                    int age, String passportCountry, String passportNumber) {

        // Check whether sex exists (using sex name)
        Sex sex = sexesDao.findByField("sex_name", sexName)
                .stream().findFirst().orElse(null);
        if(sex == null) {
            System.out.printf("Sex '%s' does not exist. Please, add new sex first", sexName);
            return;
        }

        // Create Passenger object and insert it
        Passenger passenger = new Passenger(0, firstName, lastName, sex, age, passportCountry, passportNumber);

        addElement(passenger);
    }

    // Delete element
    public void delete(String passportCountry, String passportNumber) {
        Map<String, String> map = Map.of(
                "passport_country", passportCountry,
                "passport_number", passportNumber);
        deleteElement(map);
    }
}
