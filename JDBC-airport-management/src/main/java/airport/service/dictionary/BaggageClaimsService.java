package airport.service.dictionary;

import airport.dao.dictionary.BaggageClaimsDao;
import airport.entity.dictionary.BaggageClaim;

public class BaggageClaimsService extends AbstractDictionaryService<BaggageClaim> {

    private final static int MAX_LENGTH = 3;

    public BaggageClaimsService(BaggageClaimsDao baggageClaimsDao) {
        super(baggageClaimsDao, MAX_LENGTH);
    }

    @Override
    protected int getId(BaggageClaim entity) {
        return entity.getId();
    }
}
