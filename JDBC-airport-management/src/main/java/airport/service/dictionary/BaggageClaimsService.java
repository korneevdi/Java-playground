package airport.service.dictionary;

import airport.dao.dictionary.BaggageClaimsDao;
import airport.entity.dictionary.BaggageClaim;

import java.util.Map;

public class BaggageClaimsService extends AbstractDictionaryService<BaggageClaim> {

    private final static String ENTITY_NAME = "Baggage claim";

    public BaggageClaimsService(BaggageClaimsDao baggageClaimsDao) {
        super(baggageClaimsDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("claim_number", 3)
        );
    }
}
