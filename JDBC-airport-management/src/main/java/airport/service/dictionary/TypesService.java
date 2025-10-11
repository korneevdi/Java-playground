package airport.service.dictionary;

import airport.dao.dictionary.TypesDao;
import airport.entity.dictionary.Type;

import java.util.Map;

public class TypesService extends AbstractDictionaryService<Type> {

    private final static String ENTITY_NAME = "Type";

    public TypesService(TypesDao typesDao) {
        super(typesDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("type_name", 30)
        );
    }
}
