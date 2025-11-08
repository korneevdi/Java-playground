package airport.service.dictionary;

import airport.dao.dictionary.GatesDao;
import airport.entity.dictionary.Gate;
import airport.service.AbstractService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GatesService extends AbstractService<Gate> {

    private final static String ENTITY_NAME = "Gate";

    public GatesService(GatesDao gatesDao) {
        super(gatesDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("gate_number", 5)
        );
    }

    @Override
    public void showAll() {
        List<Gate> allGates = dao.findAll();
        if(allGates != null && allGates.size() != 0) {
            // Group gates into terminals: use TreeMap, so that terminals are sorted
            Map<Character, List<Gate>> groupedGates = new TreeMap<>();
            for(Gate gate : allGates) {
                char terminal = gate.getNumber().charAt(0);
                groupedGates.computeIfAbsent(terminal, k -> new ArrayList<>()).add(gate);
            }

            // Print terminals & gates
            System.out.println("List of gates");
            for(var entry: groupedGates.entrySet()) {
                System.out.println("Terminal " + entry.getKey() + ":");
                for(Gate gate : entry.getValue()) {
                    System.out.print(gate.getNumber() + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No data found");
        }
    }
}
