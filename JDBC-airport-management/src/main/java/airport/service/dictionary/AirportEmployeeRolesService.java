package airport.service.dictionary;

import airport.dao.dictionary.AirportEmployeeRolesDao;
import airport.entity.dictionary.AirportEmployeeRole;
import airport.service.AbstractService;

import java.util.Map;

public class AirportEmployeeRolesService extends AbstractService<AirportEmployeeRole> {

    private final static String ENTITY_NAME = "Airport employee role";

    public AirportEmployeeRolesService(AirportEmployeeRolesDao airportEmployeeRolesDao) {
        super(airportEmployeeRolesDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("role_name", 100)
        );
    }

    // Add new element
    public void add(String roleName) {
        addElement(new AirportEmployeeRole(0, roleName));
    }

    // Delete element
    public void delete(String roleName) {
        Map<String, String> map = Map.of("role_name", roleName);
        deleteElement(map);
    }
}
