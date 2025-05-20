package collection;

import java.util.*;

public class MyLinkedHashMap extends MyHashMap {

    private Entry head;
    private Entry tail;

    public MyLinkedHashMap() {
        super();
    }

    public MyLinkedHashMap(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public void put(CarOwner key, Car value) {
        if (size() >= hashTable.length * LOAD_FACTOR) {
            increaseHashTable();
        }

        Entry existingEntry = findEntry(key);
        if (existingEntry != null) {
            existingEntry.value = value;
        } else {
            Entry newEntry = new Entry(key, value, null);
            int position = getElementPosition(key, hashTable.length);
            newEntry.next = hashTable[position];
            hashTable[position] = newEntry;
            linkLast(newEntry);
            size++;
        }
    }

    @Override
    public boolean remove(CarOwner key) {
        int position = getElementPosition(key, hashTable.length);
        Entry current = hashTable[position];

        if (current == null) return false;

        if (current.key.equals(key)) {
            hashTable[position] = current.next;
            unlink(current);
            size--;
            return true;
        }

        while (current.next != null) {
            if (current.next.key.equals(key)) {
                Entry toRemove = current.next;
                current.next = toRemove.next;
                unlink(toRemove);
                size--;
                return true;
            }
            current = current.next;
        }

        return false;
    }

    @Override
    public void clear() {
        super.clear();
        head = null;
        tail = null;
    }

    @Override
    public Set<CarOwner> keySet() {
        Set<CarOwner> keys = new LinkedHashSet<>();
        Entry current = head;
        while (current != null) {
            keys.add(current.key);
            current = current.after;
        }
        return keys;
    }

    @Override
    public List<Car> values() {
        List<Car> values = new ArrayList<>();
        Entry current = head;
        while (current != null) {
            values.add(current.value);
            current = current.after;
        }
        return values;
    }

    @Override
    public CarOwner[] keysToArray() {
        CarOwner[] array = new CarOwner[size()];
        Entry current = head;
        int i = 0;
        while (current != null) {
            array[i++] = current.key;
            current = current.after;
        }
        return array;
    }

    @Override
    public Car[] valuesToArray() {
        Car[] array = new Car[size()];
        Entry current = head;
        int i = 0;
        while (current != null) {
            array[i++] = current.value;
            current = current.after;
        }
        return array;
    }

    @Override
    public boolean containsKey(CarOwner key) {
        return findEntry(key) != null;
    }

    @Override
    public boolean containsValue(Car value) {
        Entry current = head;
        while (current != null) {
            if (current.value.equals(value)) return true;
            current = current.after;
        }
        return false;
    }

    private Entry findEntry(CarOwner key) {
        int position = getElementPosition(key, hashTable.length);
        Entry current = hashTable[position];
        while (current != null) {
            if (current.key.equals(key)) return current;
            current = current.next;
        }
        return null;
    }

    private void linkLast(Entry e) {
        if (tail == null) {
            head = e;
        } else {
            tail.after = e;
            e.before = tail;
        }
        tail = e;
    }

    private void unlink(Entry e) {
        if (e.before != null) {
            e.before.after = e.after;
        } else {
            head = e.after;
        }

        if (e.after != null) {
            e.after.before = e.before;
        } else {
            tail = e.before;
        }
    }

    private void increaseHashTable() {
        Entry[] oldTable = hashTable;
        Entry[] newTable = new Entry[oldTable.length * 2];
        hashTable = newTable;

        Entry current = head;
        while (current != null) {
            int position = getElementPosition(current.key, newTable.length);
            Entry next = current.after;
            current.next = newTable[position];
            newTable[position] = current;
            current = next;
        }
    }

    private int getElementPosition(CarOwner key, int length) {
        return Math.abs(key.hashCode() % length);
    }
}
