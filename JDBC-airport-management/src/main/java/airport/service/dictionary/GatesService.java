package airport.service.dictionary;

import airport.dao.dictionary.GatesDao;
import airport.entity.dictionary.Gate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GatesService extends AbstractDictionaryService<Gate> {

    private final static int MAX_LENGTH = 5;

    public GatesService(GatesDao gatesDao) {
        super(gatesDao, MAX_LENGTH);
    }

    @Override
    protected int getId(Gate entity) {
        return entity.getId();
    }

    @Override
    public void showAll() {
        List<Gate> allGates = dao.findAll();
        if(allGates != null && allGates.size() != 0) {
            // Group gates into terminals: use TreeMap, so that terminals are sorted
            Map<Character, List<Gate>> groupedGates = new TreeMap<>();
            for(Gate gate : allGates) {
                char terminal = gate.getName().charAt(0);
                groupedGates.computeIfAbsent(terminal, k -> new ArrayList<>()).add(gate);
            }

            // Print terminals & gates
            System.out.println("List of gates");
            for(var entry: groupedGates.entrySet()) {
                System.out.println("Terminal " + entry.getKey() + ":");
                for(Gate gate : entry.getValue()) {
                    System.out.print(gate.getName() + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No data found");
        }
    }
}
