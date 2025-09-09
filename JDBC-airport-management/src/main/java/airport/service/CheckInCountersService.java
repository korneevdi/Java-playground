package airport.service;

import airport.dao.CheckInCountersDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CheckInCountersService {

    private final CheckInCountersDao checkInCountersDao;

    public CheckInCountersService(CheckInCountersDao checkInCountersDao) {
        this.checkInCountersDao = checkInCountersDao;
    }

    public void showAllCounters() {
        List<String> allCounters = checkInCountersDao.findAll();
        if(allCounters != null && allCounters.size() != 0) {
            // Group check-in counters into zones: use TreeMap, so that zones are sorted
            Map<Character, List<String>> groupedCounters = new TreeMap<>();
            for(String counter : allCounters) {
                char zone = counter.charAt(0);
                groupedCounters.computeIfAbsent(zone, k -> new ArrayList<>()).add(counter);
            }

            // Print zones & counters
            System.out.println("List of check-in counters");
            for(var entry: groupedCounters.entrySet()) {
                System.out.println("Zone " + entry.getKey() + ":");
                for(String counter : entry.getValue()) {
                    System.out.print(counter + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No data found");
        }
    }

    public void findCounter(int id) {
        String counter = checkInCountersDao.findById(id);
        if(!counter.isEmpty()) {
            System.out.println("Check-in counter: " + counter);
        } else {
            System.out.println("No data found");
        }
    }

    public void addCounter(String counter) {
        if(counter != null && !counter.isEmpty()) {
            List<String> allCounters = checkInCountersDao.findAll();
            if(!allCounters.contains(counter)) {
                checkInCountersDao.insert(counter);
                System.out.println("New element '" + counter + "' inserted successfully");
            } else {
                System.out.println("Element '" + counter + "' already exists");
            }
        } else {
            System.out.println("New element should not be NULL or empty");
        }
    }

    public void updateCounter(String oldCounter, String newCounter) {
        if(oldCounter != null && newCounter != null && !oldCounter.isEmpty() && !newCounter.isEmpty()) {
            int id = checkInCountersDao.findId(oldCounter);
            if(id != -1) {
                checkInCountersDao.update(id, newCounter);
                System.out.println("Check-in counter updated successfully");
            } else{
                System.out.println("Element '" + oldCounter + "' not found");
            }
        } else {
            System.out.println("'oldCounter' and 'newCounter' should not be NULL or empty");
        }
    }

    public void deleteCounter(String counter) {
        if(counter != null && !counter.isEmpty()) {
            if(checkInCountersDao.delete(counter)) {
                System.out.println("Check-in counter '" + counter + "' deleted successfully");
            } else {
                System.out.println("Element '" + counter + "' not found");
            }
        } else{
            System.out.println("Check-in counter should not be NULL or empty");
        }
    }
}
