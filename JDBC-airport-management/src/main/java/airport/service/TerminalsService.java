package airport.service;

import airport.dao.TerminalsDao;

import java.util.List;

public class TerminalsService {

    private final TerminalsDao terminalsDao;

    public TerminalsService(TerminalsDao terminalsDao) {
        this.terminalsDao = terminalsDao;
    }

    public void showAllTerminals() {
        List<String> allTerminals = terminalsDao.findAll();
        System.out.println("List of terminals:");
        if(allTerminals != null && allTerminals.size() != 0) {
            for(int i = 0; i < allTerminals.size(); i++) {
                System.out.println((i + 1) + ": " + allTerminals.get(i));
            }
        } else {
            System.out.println("No data found");
        }
    }

    public void findTerminal(int id) {
        String terminal = terminalsDao.findById(id);
        if(!terminal.isEmpty()) {
            System.out.println("Terminal: " + terminal);
        } else {
            System.out.println("No data found");
        }
    }

    public void addTerminal(String terminal) {
        if(terminal != null && !terminal.isEmpty()) {
            List<String> allTerminals = terminalsDao.findAll();
            if(!allTerminals.contains(terminal)) {
                terminalsDao.insert(terminal);
                System.out.println("New element '" + terminal + "' inserted successfully");
            } else {
                System.out.println("Element '" + terminal + "' already exists");
            }
        } else {
            System.out.println("New element should not be NULL or empty");
        }
    }

    public void updateTerminal(String oldTerminal, String newTerminal) {
        if(oldTerminal != null && newTerminal != null && !oldTerminal.isEmpty() && !newTerminal.isEmpty()) {
            int id = terminalsDao.findId(oldTerminal);
            if(id != -1) {
                terminalsDao.update(id, newTerminal);
                System.out.println("Terminal updated successfully");
            } else{
                System.out.println("Element '" + oldTerminal + "' not found");
            }
        } else {
            System.out.println("'oldTerminal' and 'newTerminal' should not be NULL or empty");
        }
    }

    public void deleteTerminal(String terminal) {
        if(terminal != null && !terminal.isEmpty()) {
            if(terminalsDao.delete(terminal)) {
                System.out.println("Terminal '" + terminal + "' deleted successfully");
            } else {
                System.out.println("Element '" + terminal + "' not found");
            }
        } else{
            System.out.println("Terminal number should not be NULL or empty");
        }
    }
}
