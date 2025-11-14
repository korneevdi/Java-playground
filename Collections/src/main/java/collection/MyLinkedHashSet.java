package collection;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyLinkedHashSet extends MyHashSet{

    private static final Object PRESENT = new Object();
    private final Map<Car, Object> map = new LinkedHashMap<>();

    @Override
    public boolean add(Car car) {
        if(map.containsKey(car)) {
            return false;
        }
        map.put(car, PRESENT);
        return true;
    }

    @Override
    public boolean remove(Car car) {
        Object removed = map.remove(car);
        return removed != null;
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Car car) {
        return map.containsKey(car);
    }

    @Override
    public Car[] toArray() {
        return map.keySet().toArray(new Car[0]);
    }
}
