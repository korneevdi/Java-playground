package collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyMapTest {

    private MyMap map;

    @BeforeEach
    private void setUp() {
        map = new MyHashMap();
        for (int i = 0; i < 100; i++) {
            map.put(
                    new CarOwner(i, "firstName" + i, "lastName" + i),
                    new Car("brand" + i, "color" + i, i));
        }
    }

    //void put(CarOwner key, Car value)
    @Test
    @DisplayName("If new pair key-value added, collection size must increase by one")
    public void whenPutNewElementThenSizeMustIncrease() {
        assertEquals(100, map.size());
        map.put(
                new CarOwner(119, "Richard", "Brown"),
                new Car("BMW", "black", 518));
        assertEquals(101, map.size());
    }

    @Test
    @DisplayName("If a pair key-value with existent value added, collection size must does not change")
    public void whenPutNewElementWithExistentValueThenSizeDoesNotChange() {
        CarOwner key = new CarOwner(19, "firstName19", "lastName19");
        Car value = new Car("BMW", "black", 518);
        map.put(key, value);
        assertEquals(100, map.size());
    }

    //Car get(CarOwner key)
    @Test
    @DisplayName("The get() method must return correct value by a key")
    public void methodGetMustReturnCorrectValue() {
        CarOwner key = new CarOwner(15, "firstName15", "lastName15");
        Car car = map.get(key);
        assertEquals(car.getBrand(), "brand15");
        assertEquals(car.getColor(), "color15");
        assertEquals(car.getNumber(), 15);
    }

    //Set<CarOwner> keySet() & List<Car> values()
    @Test
    @DisplayName("The keySet() and values() methods must return all keys and values")
    public void methodsKeySetAndValuesMustReturnAllKeysAndValues() {
        assertEquals(100, map.size());
        assertEquals(100, map.keySet().size());
        assertEquals(100, map.values().size());
    }

    //boolean remove(CarOwner key)
    @Test
    @DisplayName("If one element is removed, collection size must decrease")
    public void whenRemoveOneElementThenSizeMustDecrease() {
        CarOwner key = new CarOwner(15, "firstName15", "lastName15");
        assertTrue(map.remove(key));
        assertEquals(99, map.size());
        assertFalse(map.remove(key));
        assertEquals(99, map.size());

        // Remove the first element
        CarOwner key0 = new CarOwner(0, "firstName0", "lastName0");
        assertTrue(map.remove(key0));
        assertEquals(98, map.size());
        assertFalse(map.remove(key0));
        assertEquals(98, map.size());

        // Remove the last element
        CarOwner key99 = new CarOwner(99, "firstName99", "lastName99");
        assertTrue(map.remove(key99));
        assertEquals(97, map.size());
        assertFalse(map.remove(key99));
        assertEquals(97, map.size());
    }

    //int size()
    @Test
    @DisplayName("The size() method returns correct collection size")
    public void methodSizeMustReturnCorrectSize() {
        assertEquals(100, map.size());
    }

    //void clear()
    @Test
    @DisplayName("The clear() method removes all element from the collection")
    public void whenClearCollectionThenSizeMustBeZero() {
        map.clear();
        assertEquals(0, map.size());
    }

    //boolean isEmpty()
    @Test
    @DisplayName("The isEmpty() method must return true, if the collection is empty")
    public void whenCollectionHasNoElementIsEmptyMustReturnTrue() {
        map = new MyHashMap();
        assertTrue(map.isEmpty());
    }

    @Test
    @DisplayName("The isEmpty() method must return false, if the collection contains elements")
    public void whenCollectionHasElementsIsEmptyMustReturnFalse() {
        assertFalse(map.isEmpty());
    }

    //boolean containsKey(CarOwner key)
    @Test
    @DisplayName("The containsKey() method must return correct boolean value")
    public void methodContainsKeyMustReturnCorrectValue() {
        CarOwner carOwner1 = new CarOwner(14, "firstName14", "lastName14");
        CarOwner carOwner2 = new CarOwner(22, "firstName14", "lastName14");
        assertTrue(map.containsKey(carOwner1));
        assertFalse(map.containsKey(carOwner2));

        assertTrue(map.remove(carOwner1));
        assertFalse(map.containsKey(carOwner1));
    }

    //boolean containsValue(Car value)
    @Test
    @DisplayName("The containsValue() method must return correct boolean value")
    public void methodContainsValueMustReturnCorrectValue() {
        Car car1 = new Car("brand27", "color27", 27);
        Car car2 = new Car("brand29", "color27", 27);
        assertTrue(map.containsValue(car1));
        assertFalse(map.containsValue(car2));

        CarOwner carOwner = new CarOwner(27, "firstName27", "lastName27");
        map.put(carOwner, car2);
        assertFalse(map.containsValue(car1));
    }

    //void putAll(MyMap map)
    @Test
    @DisplayName("The putAll() method must return new map of certain size")
    public void methodPutAllMustReturnMapOfCorrectSize() {
        MyMap newMap = new MyHashMap();
        newMap.putAll(map);
        assertEquals(100, newMap.size());

        Car car1 = new Car("BMW", "red", 648);
        Car car2 = new Car("Toyota", "white", 605);
        Car car3 = new Car("Audi", "black", 218);
        Car car4 = new Car("Lada", "red", 313);
        CarOwner owner1 = new CarOwner(1, "FirstName1", "LastName1");
        CarOwner owner2 = new CarOwner(2, "FirstName2", "LastName2");
        CarOwner owner3 = new CarOwner(3, "FirstName3", "LastName3");

        MyMap map1 = new MyHashMap();
        map1.put(owner1, car1);
        map1.put(owner2, car2);
        MyMap map2 = new MyHashMap();
        map2.put(owner3, car3);
        map2.put(owner1, car4);

        map1.putAll(map2);

        assertEquals(3, map1.size());
        assertEquals(map1.get(owner1), car4);
        assertEquals(map1.get(owner2), car2);
        assertEquals(map1.get(owner3), car3);
    }

    //CarOwner[] keysToArray()
    @Test
    @DisplayName("The keysToArray() method must return correct array")
    public void methodKeysToArrayMustReturnArrayWithCorrectSize() {
        assertEquals(100, map.keysToArray().length);
    }

    //Car[] valuesToArray()
    @Test
    @DisplayName("The valuesToArray() method must return correct array")
    public void methodValuesToArrayMustReturnCorrectArray() {
        assertEquals(100, map.valuesToArray().length);
    }
}