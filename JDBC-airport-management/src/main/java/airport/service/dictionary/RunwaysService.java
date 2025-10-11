package airport.service.dictionary;

import airport.dao.dictionary.RunwaysDao;
import airport.entity.dictionary.Runway;

import java.util.Map;

public class RunwaysService extends AbstractDictionaryService<Runway> {

    private final static String ENTITY_NAME = "Runway";

    public RunwaysService(RunwaysDao runwaysDao) {
        super(runwaysDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("runway_number", 4)
        );
    }
}
