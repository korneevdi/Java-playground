package airport.service.dictionary;

import airport.dao.dictionary.StatusesDao;
import airport.entity.dictionary.Status;

import java.util.Map;

public class StatusesService extends AbstractDictionaryService<Status> {

    private final static String ENTITY_NAME = "Status";

    public StatusesService(StatusesDao statusesDao) {
        super(statusesDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("status_name", 40)
        );
    }
}
