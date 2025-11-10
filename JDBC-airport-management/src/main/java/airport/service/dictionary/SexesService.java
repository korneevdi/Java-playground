package airport.service.dictionary;

import airport.dao.dictionary.SexesDao;
import airport.entity.dictionary.Sex;
import airport.service.AbstractService;

import java.util.Map;

public class SexesService extends AbstractService<Sex> {

    private final static String ENTITY_NAME = "Sex";

    public SexesService(SexesDao sexesDao) {
        super(sexesDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("sex_name", 20)
        );
    }

    // Add new element
    public void add(String sexName) {
        addElement(new Sex(0, sexName));
    }
}
