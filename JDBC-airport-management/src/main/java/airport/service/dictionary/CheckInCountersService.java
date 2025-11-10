package airport.service.dictionary;

import airport.dao.dictionary.CheckInCountersDao;
import airport.entity.dictionary.CheckInCounter;
import airport.service.AbstractService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CheckInCountersService extends AbstractService<CheckInCounter> {

    private final static String ENTITY_NAME = "Check-in counter";

    public CheckInCountersService(CheckInCountersDao checkInCountersDao) {
        super(checkInCountersDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("counter_number", 5)
        );
    }

    @Override
    public void showAll() {
        List<CheckInCounter> allCounters = dao.findAll();
        if (allCounters != null && allCounters.size() != 0) {
            // Group check-in counters into zones: use TreeMap, so that zones are sorted
            Map<Character, List<CheckInCounter>> groupedCounters = new TreeMap<>();
            for (CheckInCounter counter : allCounters) {
                char zone = counter.getNumber().charAt(0);
                groupedCounters.computeIfAbsent(zone, k -> new ArrayList<>()).add(counter);
            }

            // Print zones & counters
            System.out.println("List of check-in counters");
            for (var entry : groupedCounters.entrySet()) {
                System.out.println("Zone " + entry.getKey() + ":");
                for (CheckInCounter counter : entry.getValue()) {
                    System.out.print(counter.getNumber() + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No data found");
        }
    }

    // Add new element
    public void add(String counterNumber) {
        addElement(new CheckInCounter(0, counterNumber));
    }
}
