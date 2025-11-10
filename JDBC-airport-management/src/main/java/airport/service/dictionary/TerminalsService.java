package airport.service.dictionary;

import airport.dao.dictionary.TerminalsDao;
import airport.entity.dictionary.Terminal;
import airport.service.AbstractService;

import java.util.Map;

public class TerminalsService extends AbstractService<Terminal> {

    private final static String ENTITY_NAME = "Terminal";

    public TerminalsService(TerminalsDao terminalsDao) {
        super(terminalsDao, ENTITY_NAME);

        // Set the maps of the fields
        stringFields = Map.ofEntries(
                Map.entry("terminal_number", 30)
        );
    }

    // Add new element
    public void add(String terminalNumber) {
        addElement(new Terminal(0, terminalNumber));
    }
}
