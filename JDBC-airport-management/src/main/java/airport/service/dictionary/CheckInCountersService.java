package airport.service.dictionary;

import airport.dao.dictionary.CheckInCountersDao;
import airport.entity.dictionary.CheckInCounter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CheckInCountersService extends AbstractDictionaryService<CheckInCounter> {

    private final static int MAX_LENGTH = 5;

    public CheckInCountersService(CheckInCountersDao checkInCountersDao) {
        super(checkInCountersDao, MAX_LENGTH);
    }

    @Override
    protected int getId(CheckInCounter entity) {
        return entity.getId();
    }

    @Override
    public void showAll() {
        List<CheckInCounter> allCounters = dao.findAll();
        if (allCounters != null && allCounters.size() != 0) {
            // Group check-in counters into zones: use TreeMap, so that zones are sorted
            Map<Character, List<CheckInCounter>> groupedCounters = new TreeMap<>();
            for (CheckInCounter counter : allCounters) {
                char zone = counter.getName().charAt(0);
                groupedCounters.computeIfAbsent(zone, k -> new ArrayList<>()).add(counter);
            }

            // Print zones & counters
            System.out.println("List of check-in counters");
            for (var entry : groupedCounters.entrySet()) {
                System.out.println("Zone " + entry.getKey() + ":");
                for (CheckInCounter counter : entry.getValue()) {
                    System.out.print(counter.getName() + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No data found");
        }
    }
}
