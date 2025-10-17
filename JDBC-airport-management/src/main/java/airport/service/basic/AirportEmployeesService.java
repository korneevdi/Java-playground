package airport.service.basic;

import airport.dao.basic.AirportEmployeesDao;
import airport.entity.basic.AirportEmployee;

import java.util.Map;

public class AirportEmployeesService extends AbstractBasicService<AirportEmployee>{

    private final static String ENTITY_NAME = "Airport Employee";

    public AirportEmployeesService(AirportEmployeesDao airportEmployeesDao) {
        super(airportEmployeesDao, ENTITY_NAME);

        // Set the map of the fields and max lengths
        stringFields = Map.ofEntries(
                Map.entry("first_name", 50),
                Map.entry("last_name", 50),
                Map.entry("role_name", 100),
                Map.entry("sex_name", 20),
                Map.entry("passport_country", 20),
                Map.entry("passport_number", 20),
                Map.entry("emp_contact_email", 100),
                Map.entry("emp_contact_phone", 30),
                Map.entry("city", 25),
                Map.entry("address", 200),
                Map.entry("notes", 1000),
                Map.entry("emerg_contact_name", 100),
                Map.entry("emerg_contact_relation", 30),
                Map.entry("emerg_contact_phone", 30)
        );

        dateFields.add("birth_date");
    }
}
