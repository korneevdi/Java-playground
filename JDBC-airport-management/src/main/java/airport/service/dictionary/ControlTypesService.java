package airport.service.dictionary;

import airport.dao.dictionary.ControlTypesDao;
import airport.entity.dictionary.ControlType;

import java.util.Map;

public class ControlTypesService extends AbstractDictionaryService<ControlType> {

    private final static String ENTITY_NAME = "Control type";

    public ControlTypesService(ControlTypesDao controlTypesDao) {
        super(controlTypesDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("type_name", 40)
        );
    }
}
