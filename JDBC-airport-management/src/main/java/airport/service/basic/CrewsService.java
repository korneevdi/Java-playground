package airport.service.basic;

import airport.dao.basic.CrewsDao;
import airport.entity.basic.Crew;

import java.util.Map;

public class CrewsService extends AbstractBasicService<Crew>{

    private final static String ENTITY_NAME = "Flight Crew";

    public CrewsService(CrewsDao crewDao) {
        super(crewDao, ENTITY_NAME);

        // Set the map of the fields and max lengths
        stringFields = Map.ofEntries(
                Map.entry("pilot_license_number", 20),
                Map.entry("first_name", 50),
                Map.entry("last_name", 50),
                Map.entry("sex_name", 20),
                Map.entry("passport_country", 20),
                Map.entry("passport_number", 20)
        );

        dateFields.add("birth_date");
    }
}
