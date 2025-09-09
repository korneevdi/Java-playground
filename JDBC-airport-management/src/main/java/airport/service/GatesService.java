package airport.service;

import airport.dao.GatesDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class GatesService {

    private final GatesDao gatesDao;

    public GatesService(GatesDao gatesDao) {
        this.gatesDao = gatesDao;
    }

    public void showAllGates() {
        List<String> allGates = gatesDao.findAll();
        if(allGates != null && allGates.size() != 0) {
            // Group gates into terminals: use TreeMap, so that terminals are sorted
            Map<Character, List<String>> groupedGates = new TreeMap<>();
            for(String gate : allGates) {
                char terminal = gate.charAt(0);
                groupedGates.computeIfAbsent(terminal, k -> new ArrayList<>()).add(gate);
            }

            // Print terminals & gates
            System.out.println("List of gates");
            for(var entry: groupedGates.entrySet()) {
                System.out.println("Terminal " + entry.getKey() + ":");
                for(String gate : entry.getValue()) {
                    System.out.print(gate + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No data found");
        }
    }

    public void findGate(int id) {
        String gate = gatesDao.findById(id);
        if(!gate.isEmpty()) {
            System.out.println("Gate: " + gate);
        } else {
            System.out.println("No data found");
        }
    }

    public void addGate(String gate) {
        if(gate != null && !gate.isEmpty()) {
            List<String> allGates = gatesDao.findAll();
            if(!allGates.contains(gate)) {
                gatesDao.insert(gate);
                System.out.println("New element '" + gate + "' inserted successfully");
            } else {
                System.out.println("Element '" + gate + "' already exists");
            }
        } else {
            System.out.println("New element should not be NULL or empty");
        }
    }

    public void updateGate(String oldGate, String newGate) {
        if(oldGate != null && newGate != null && !oldGate.isEmpty() && !newGate.isEmpty()) {
            int id = gatesDao.findId(oldGate);
            if(id != -1) {
                gatesDao.update(id, newGate);
                System.out.println("Gate updated successfully");
            } else{
                System.out.println("Element '" + oldGate + "' not found");
            }
        } else {
            System.out.println("'oldGate' and 'newGate' should not be NULL or empty");
        }
    }

    public void deleteGate(String gate) {
        if(gate != null && !gate.isEmpty()) {
            if(gatesDao.delete(gate)) {
                System.out.println("Gate '" + gate + "' deleted successfully");
            } else {
                System.out.println("Element '" + gate + "' not found");
            }
        } else{
            System.out.println("Gate should not be NULL or empty");
        }
    }
}
