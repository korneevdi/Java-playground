package airport.service.dictionary;

import airport.dao.dictionary.BaggageClaimsDao;
import airport.entity.dictionary.BaggageClaim;

public class BaggageClaimsService extends AbstractDictionaryService<BaggageClaim> {


    public BaggageClaimsService(BaggageClaimsDao dao) {
        super(dao);
    }

    @Override
    protected int getId(BaggageClaim entity) {
        return entity.getId();
    }

    @Override
    public void add(String name) {
        if (name != null && !name.isEmpty()) {
            if (name.length() > 3) {
                System.out.println("The name entered is too long: max 3 symbols");
                return;
            }
            super.add(name);
        } else {
            System.out.println("New element should not be NULL or empty");
        }
    }

    @Override
    public void update(String oldClaim, String newClaim) {
        if (oldClaim != null && newClaim != null && !oldClaim.isEmpty() && !newClaim.isEmpty()) {
            if (newClaim.length() > 3) {
                System.out.println("The name entered is too long: max 3 symbols");
                return;
            }
            super.update(oldClaim, newClaim);
        } else {
            System.out.println("'oldClaim' and 'newClaim' should not be NULL or empty");
        }
    }
}
