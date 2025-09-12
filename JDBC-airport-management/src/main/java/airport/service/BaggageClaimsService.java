package airport.service;

import airport.dao.dictionary.BaggageClaimsDao;
import airport.entity.BaggageClaim;

import java.util.List;

public class BaggageClaimsService {

    private final BaggageClaimsDao baggageClaimsDao;

    public BaggageClaimsService(BaggageClaimsDao baggageClaimsDao) {
        this.baggageClaimsDao = baggageClaimsDao;
    }

    public void showAllClaims() {
        List<BaggageClaim> allClaims = baggageClaimsDao.findAll();
        System.out.println("List of baggage claims:");
        if (!allClaims.isEmpty()) {
            for (int i = 0; i < allClaims.size(); i++) {
                System.out.println((i + 1) + ": " + allClaims.get(i));
            }
        } else {
            System.out.println("No data found");
        }
    }

    public void findClaimByName(String name) {
        BaggageClaim claim = baggageClaimsDao.findByName(name);
        if (claim != null) {
            System.out.println("Found baggage claim: " + claim);
        } else {
            System.out.println("No data found");
        }
    }

    public void addClaim(String claim) {
        if (claim != null && !claim.isEmpty()) {
            if (claim.length() < 4) {
                if (baggageClaimsDao.findByName(claim) != null) {
                    System.out.println("Element '" + claim + "' already exists");
                    return;
                }
                baggageClaimsDao.insert(claim);
                System.out.println("New element '" + claim + "' inserted successfully");
            } else {
                System.out.println("The name entered is too long: max 3 symbols");
            }
        } else {
            System.out.println("New element should not be NULL or empty");
        }
    }

    public void updateClaim(String oldClaim, String newClaim) {
        if (oldClaim != null && newClaim != null && !oldClaim.isEmpty() && !newClaim.isEmpty()) {
            if (baggageClaimsDao.findByName(newClaim) == null) {
                if (newClaim.length() < 4) {
                    BaggageClaim claimToUpdate = baggageClaimsDao.findByName(oldClaim);
                    if (claimToUpdate != null) {
                        int id = claimToUpdate.getId();
                        baggageClaimsDao.update(id, newClaim);
                        System.out.println("Baggage claim updated successfully");
                    } else {
                        System.out.println("Element '" + oldClaim + "' not found");
                    }
                } else {
                    System.out.println("The name entered is too long: max 3 symbols");
                }
            } else {
                System.out.println("Element '" + newClaim + "' already exists");
            }
        } else {
            System.out.println("'oldClaim' and 'newClaim' should not be NULL or empty");
        }
    }

    public void deleteClaim(String claim) {
        if (claim != null && !claim.isEmpty()) {
            if (baggageClaimsDao.deleteByName(claim)) {
                System.out.println("Baggage claim '" + claim + "' deleted successfully");
            } else {
                System.out.println("Element '" + claim + "' not found");
            }
        } else {
            System.out.println("Baggage claim should not be NULL or empty");
        }
    }
}
