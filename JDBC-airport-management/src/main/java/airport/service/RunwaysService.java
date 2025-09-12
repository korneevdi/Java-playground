package airport.service;

import airport.dao.RunwaysDao;

import java.util.List;

public class RunwaysService {

    private final RunwaysDao runwaysDao;

    public RunwaysService(RunwaysDao runwaysDao) {
        this.runwaysDao = runwaysDao;
    }

    public void showAllRunways() {
        List<String> allRunways = runwaysDao.findAll();
        System.out.println("List of flight runways:");
        if(allRunways != null && allRunways.size() != 0) {
            for(int i = 0; i < allRunways.size(); i++) {
                System.out.println((i + 1) + ": " + allRunways.get(i));
            }
        } else {
            System.out.println("No data found");
        }
    }

    public void findRunway(int id) {
        String runway = runwaysDao.findById(id);
        if(!runway.isEmpty()) {
            System.out.println("Runway: " + runway);
        } else {
            System.out.println("No data found");
        }
    }

    public void addRunway(String runway) {
        if(runway != null && !runway.isEmpty()) {
            List<String> allRunways = runwaysDao.findAll();
            if(!allRunways.contains(runway)) {
                runwaysDao.insert(runway);
                System.out.println("New element '" + runway + "' inserted successfully");
            } else {
                System.out.println("Element '" + runway + "' already exists");
            }
        } else {
            System.out.println("New element should not be NULL or empty");
        }
    }

    public void updateRunway(String oldRunway, String newRunway) {
        if(oldRunway != null && newRunway != null && !oldRunway.isEmpty() && !newRunway.isEmpty()) {
            int id = runwaysDao.findId(oldRunway);
            if(id != -1) {
                runwaysDao.update(id, newRunway);
                System.out.println("Flight runway updated successfully");
            } else{
                System.out.println("Element '" + oldRunway + "' not found");
            }
        } else {
            System.out.println("'oldRunway' and 'newRunway' should not be NULL or empty");
        }
    }

    public void deleteRunway(String runway) {
        if(runway != null && !runway.isEmpty()) {
            if(runwaysDao.delete(runway)) {
                System.out.println("Flight runway '" + runway + "' deleted successfully");
            } else {
                System.out.println("Element '" + runway + "' not found");
            }
        } else{
            System.out.println("Flight runway should not be NULL or empty");
        }
    }
}
