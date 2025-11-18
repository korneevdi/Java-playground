package annotation.log;

import java.util.ArrayList;
import java.util.List;

public class CollectionCreator {

    private static final Integer NUMBER_OF_ELEMENTS = 100_000_000;

    @LogExecution
    private List<Integer> create() {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < NUMBER_OF_ELEMENTS; i++) {
            list.add(i);
        }
        return list;
    }
}
