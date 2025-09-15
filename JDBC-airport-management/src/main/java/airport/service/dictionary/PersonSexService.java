package airport.service.dictionary;

import airport.dao.dictionary.PersonSexDao;
import airport.entity.dictionary.PersonSex;

public class PersonSexService extends AbstractDictionaryService<PersonSex> {

    private final static int MAX_LENGTH = 20;

    public PersonSexService(PersonSexDao personSexDao) {
        super(personSexDao, MAX_LENGTH);
    }

    @Override
    protected int getId(PersonSex entity) {
        return entity.getId();
    }
}
