package airport.service;

import airport.dao.BaggageClaimsDao;

import java.util.List;

public class BaggageClaimsService {

    private final BaggageClaimsDao baggageClaimsDao;

    public BaggageClaimsService(BaggageClaimsDao baggageClaimsDao) {
        this.baggageClaimsDao = baggageClaimsDao;
    }

    public void showAllClaims() {
        List<String> allClaims = baggageClaimsDao.findAll();
        System.out.println("List of baggage claims:");
        if(allClaims != null && allClaims.size() != 0) {
            for(int i = 0; i < allClaims.size(); i++) {
                System.out.println((i + 1) + ": " + allClaims.get(i));
            }
        } else {
            System.out.println("No data found");
        }
    }

    public void findClaim(int id) {
        String claim = baggageClaimsDao.findById(id);
        if(!claim.isEmpty()) {
            System.out.println("Baggage claim: " + claim);
        } else {
            System.out.println("No data found");
        }
    }

    public void addClaim(String claim) {
        if(claim != null && !claim.isEmpty()) {
            baggageClaimsDao.insert(claim);
            System.out.println("New element '" + claim + "' inserted successfully");
        } else {
            System.out.println("New element should not be NULL or empty");
        }
    }

    public void updateClaim(String oldClaim, String newClaim) {
        if(oldClaim != null && newClaim != null && !oldClaim.isEmpty() && !newClaim.isEmpty()) {
            int id = baggageClaimsDao.findId(oldClaim);
            if(id != -1) {
                baggageClaimsDao.update(id, newClaim);
                System.out.println("Baggage claim updated successfully");
            } else{
                System.out.println("Element '" + oldClaim + "' not found");
            }
        } else {
            System.out.println("'oldClaim' and 'newClaim' should not be NULL or empty");
        }
    }

    public void deleteClaim(String claim) {
        if(claim != null && !claim.isEmpty()) {
            if(baggageClaimsDao.delete(claim)) {
                System.out.println("Baggage claim '" + claim + "' deleted successfully");
            } else {
                System.out.println("Element '" + claim + "' not found");
            }
        } else{
            System.out.println("Baggage claim should not be NULL or empty");
        }
    }
}
