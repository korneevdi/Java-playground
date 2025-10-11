package airport.service.dictionary;

import airport.dao.dictionary.SexesDao;
import airport.entity.dictionary.Sex;

import java.util.Map;

public class SexesService extends AbstractDictionaryService<Sex> {

    private final static String ENTITY_NAME = "Sex";

    public SexesService(SexesDao sexesDao) {
        super(sexesDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("sex_name", 20)
        );
    }
}
