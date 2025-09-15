package airport.service.dictionary;

import airport.dao.dictionary.TypeDao;
import airport.entity.dictionary.Type;

public class TypeService extends AbstractDictionaryService<Type> {

    private final static int MAX_LENGTH = 30;

    public TypeService(TypeDao typeDao) {
        super(typeDao, MAX_LENGTH);
    }

    @Override
    protected int getId(Type entity) {
        return entity.getId();
    }
}
