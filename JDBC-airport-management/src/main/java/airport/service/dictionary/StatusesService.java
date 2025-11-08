package airport.service.dictionary;

import airport.dao.dictionary.StatusesDao;
import airport.entity.dictionary.Status;
import airport.service.AbstractService;

import java.util.Map;

public class StatusesService extends AbstractService<Status> {

    private final static String ENTITY_NAME = "Status";

    public StatusesService(StatusesDao statusesDao) {
        super(statusesDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("status_name", 40)
        );
    }
}
