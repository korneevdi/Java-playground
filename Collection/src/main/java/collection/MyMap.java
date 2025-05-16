package collection;

import java.util.List;
import java.util.Set;

public interface MyMap {

    void put(CarOwner key, Car value);

    Car get(CarOwner key);

    Set<CarOwner> keySet();

    List<Car> values();

    boolean remove(CarOwner key);

    int size();

    void clear();

    boolean isEmpty();

    boolean containsKey(CarOwner key);

    boolean containsValue(Car value);

    void putAll(MyMap map);

    CarOwner[] keysToArray();

    Car[] valuesToArray();
}
