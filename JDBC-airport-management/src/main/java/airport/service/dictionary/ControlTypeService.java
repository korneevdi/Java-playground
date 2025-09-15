package airport.service.dictionary;

import airport.dao.dictionary.ControlTypesDao;
import airport.entity.dictionary.ControlType;

public class ControlTypeService extends AbstractDictionaryService<ControlType> {

    private final static int MAX_LENGTH = 40;

    public ControlTypeService(ControlTypesDao controlTypesDao) {
        super(controlTypesDao, MAX_LENGTH);
    }

    @Override
    protected int getId(ControlType entity) {
        return entity.getId();
    }
}
