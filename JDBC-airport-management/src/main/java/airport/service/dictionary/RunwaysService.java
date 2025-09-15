package airport.service.dictionary;

import airport.dao.dictionary.RunwaysDao;
import airport.entity.dictionary.Runway;

public class RunwaysService extends AbstractDictionaryService<Runway> {

    private final static int MAX_LENGTH = 4;

    public RunwaysService(RunwaysDao runwaysDao) {
        super(runwaysDao, MAX_LENGTH);
    }

    @Override
    protected int getId(Runway entity) {
        return entity.getId();
    }
}
