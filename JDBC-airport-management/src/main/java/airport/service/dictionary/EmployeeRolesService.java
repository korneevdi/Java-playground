package airport.service.dictionary;

import airport.dao.dictionary.CrewRolesDao;
import airport.dao.dictionary.EmployeeRolesDao;
import airport.entity.dictionary.CrewRole;
import airport.entity.dictionary.EmployeeRole;

public class EmployeeRolesService extends AbstractDictionaryService<EmployeeRole> {

    private final static int MAX_LENGTH = 100;

    public EmployeeRolesService(EmployeeRolesDao employeeRolesDao) {
        super(employeeRolesDao, MAX_LENGTH);
    }

    @Override
    protected int getId(EmployeeRole entity) {
        return entity.getId();
    }
}
