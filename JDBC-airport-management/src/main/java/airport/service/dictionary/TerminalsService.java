package airport.service.dictionary;

import airport.dao.dictionary.TerminalsDao;
import airport.entity.dictionary.Terminal;

public class TerminalsService extends AbstractDictionaryService<Terminal> {

    private final static int MAX_LENGTH = 3;

    public TerminalsService(TerminalsDao terminalsDao) {
        super(terminalsDao, MAX_LENGTH);
    }

    @Override
    protected int getId(Terminal entity) {
        return entity.getId();
    }
}
