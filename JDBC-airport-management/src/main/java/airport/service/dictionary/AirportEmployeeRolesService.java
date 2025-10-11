package airport.service.dictionary;

import airport.dao.dictionary.AirportEmployeeRolesDao;
import airport.entity.dictionary.AirportEmployeeRole;

import java.util.Map;

public class AirportEmployeeRolesService extends AbstractDictionaryService<AirportEmployeeRole> {

    private final static String ENTITY_NAME = "Airport employee role";

    public AirportEmployeeRolesService(AirportEmployeeRolesDao airportEmployeeRolesDao) {
        super(airportEmployeeRolesDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("role_name", 100)
        );
    }
}
