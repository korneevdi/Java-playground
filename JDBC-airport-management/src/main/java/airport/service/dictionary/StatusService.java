package airport.service.dictionary;

import airport.dao.dictionary.StatusDao;
import airport.entity.dictionary.Status;

public class StatusService extends AbstractDictionaryService<Status> {

    private final static int MAX_LENGTH = 40;

    public StatusService(StatusDao statusDao) {
        super(statusDao, MAX_LENGTH);
    }

    @Override
    protected int getId(Status entity) {
        return entity.getId();
    }
}
