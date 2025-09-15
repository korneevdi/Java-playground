package airport.service.dictionary;

import airport.dao.dictionary.CrewRolesDao;
import airport.entity.dictionary.CrewRole;

public class CrewRolesService extends AbstractDictionaryService<CrewRole> {

    private final static int MAX_LENGTH = 50;

    public CrewRolesService(CrewRolesDao crewRolesDao) {
        super(crewRolesDao, MAX_LENGTH);
    }

    @Override
    protected int getId(CrewRole entity) {
        return entity.getId();
    }
}
