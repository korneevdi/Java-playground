package collection;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyHashMap implements MyMap {

    protected final int DEFAULT_INITIAL_CAPACITY = 16;

    protected final double LOAD_FACTOR = 0.75;

    protected Entry[] hashTable = new Entry[DEFAULT_INITIAL_CAPACITY];

    protected int size = 0;

    // No args constructor
    public MyHashMap() {

    }

    // Constructor with initial capacity
    public MyHashMap(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + initialCapacity);
        }
        hashTable = new Entry[initialCapacity];
    }

    @Override
    public void put(CarOwner key, Car value) {
        if (size >= hashTable.length * LOAD_FACTOR) {
            increaseHashTable();
        }
        boolean isPut = put(key, value, hashTable);
        if (isPut) {
            size++;
        }
    }

    @Override
    public Car get(CarOwner key) {
        int position = getElementPosition(key, hashTable.length);
        Entry currentElement = hashTable[position];
        while (currentElement != null) {
            if (currentElement.key.equals(key)) {
                return currentElement.value;
            }
            currentElement = currentElement.next;
        }
        return null;
    }

    @Override
    public Set<CarOwner> keySet() {
        Set<CarOwner> keySet = new HashSet<>();
        int position = 0;
        while (position < hashTable.length) {
            Entry currentElement = hashTable[position];
            while (currentElement != null) {
                keySet.add(currentElement.key);
                currentElement = currentElement.next;
            }
            position++;
        }
        return keySet;
    }

    @Override
    public List<Car> values() {
        List<Car> values = new ArrayList<>();
        int position = 0;
        while (position < hashTable.length) {
            Entry currentElement = hashTable[position];
            while (currentElement != null) {
                values.add(currentElement.value);
                currentElement = currentElement.next;
            }
            position++;
        }
        return values;
    }

    @Override
    public boolean remove(CarOwner key) {
        int position = getElementPosition(key, hashTable.length);
        Entry currentElement = hashTable[position];
        if (currentElement != null && currentElement.key.equals(key)) {
            hashTable[position] = currentElement.next;
            size--;
            return true;
        } else {
            while (currentElement != null) {
                Entry nextElement = currentElement.next;
                if (nextElement == null) {
                    return false;
                }
                if (nextElement.key.equals(key)) {
                    currentElement.next = nextElement.next;
                    size--;
                    return true;
                }
                currentElement = currentElement.next;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        hashTable = new Entry[DEFAULT_INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(CarOwner key) {
        int position = 0;
        while (position < hashTable.length) {
            Entry currentElement = hashTable[position];
            while (currentElement != null) {
                if (currentElement.key.equals(key)) {
                    return true;
                }
                currentElement = currentElement.next;
            }
            position++;
        }
        return false;
    }

    @Override
    public boolean containsValue(Car value) {
        int position = 0;
        while (position < hashTable.length) {
            Entry currentElement = hashTable[position];
            while (currentElement != null) {
                if (currentElement.value.equals(value)) {
                    return true;
                }
                currentElement = currentElement.next;
            }
            position++;
        }
        return false;
    }

    @Override
    public void putAll(MyMap map) {
        Set<CarOwner> keys = map.keySet();
        for (CarOwner k : keys) {
            this.put(k, map.get(k));
        }
    }

    @Override
    public CarOwner[] keysToArray() {
        Set<CarOwner> keys = this.keySet();
        CarOwner[] array = new CarOwner[keys.size()];
        int i = 0;
        for (CarOwner k : keys) {
            array[i] = k;
            i++;
        }
        return array;
    }

    @Override
    public Car[] valuesToArray() {
        List<Car> values = this.values();
        Car[] array = new Car[values.size()];
        int i = 0;
        for (Car c : values) {
            array[i] = c;
            i++;
        }
        return array;
    }

    private int getElementPosition(CarOwner carOwner, int arrayLength) {
        return Math.abs(carOwner.hashCode() % arrayLength);
    }

    private boolean put(CarOwner key, Car value, Entry[] dest) {
        int position = getElementPosition(key, dest.length);
        Entry currentElement = dest[position];
        if (currentElement == null) {
            dest[position] = new Entry(key, value, null);
            return true;
        } else {
            while (true) {
                if (currentElement.key.equals(key)) {
                    currentElement.value = value;
                    return false;
                }
                if (currentElement.next == null) {
                    currentElement.next = new Entry(key, value, null);
                    return true;
                }
                currentElement = currentElement.next;
            }
        }
    }

    private void increaseHashTable() {
        Entry[] newHashTable = new Entry[hashTable.length * 2];
        int position = 0;
        while (position < hashTable.length) {
            Entry currentElement = hashTable[position];
            while (currentElement != null) {
                put(currentElement.key, currentElement.value, newHashTable);
                currentElement = currentElement.next;
            }
            position++;
        }
        hashTable = newHashTable;
    }

    protected static class Entry {
        protected CarOwner key;
        protected Car value;
        protected Entry next;

        // Two fields for MyLinkedHashMap
        protected Entry before;
        protected Entry after;

        public Entry(CarOwner key, Car value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
}
