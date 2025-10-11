package airport.service.dictionary;

import airport.dao.dictionary.CrewRolesDao;
import airport.entity.dictionary.CrewRole;

import java.util.Map;

public class CrewRolesService extends AbstractDictionaryService<CrewRole> {

    private final static String ENTITY_NAME = "Crew role";

    public CrewRolesService(CrewRolesDao crewRolesDao) {
        super(crewRolesDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("role_name", 50)
        );
    }
}
