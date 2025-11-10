package airport.service.dictionary;

import airport.dao.dictionary.ControlTypesDao;
import airport.entity.dictionary.ControlType;
import airport.service.AbstractService;

import java.util.Map;

public class ControlTypesService extends AbstractService<ControlType> {

    private final static String ENTITY_NAME = "Control type";

    public ControlTypesService(ControlTypesDao controlTypesDao) {
        super(controlTypesDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("type_name", 40)
        );
    }

    // Add new element
    public void add(String typeName) {
        addElement(new ControlType(0, typeName));
    }
}
