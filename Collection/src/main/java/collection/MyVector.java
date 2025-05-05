package collection;

import java.util.Arrays;

public class MyVector implements MyList {

    private Car[] array = new Car[10];

    private int size = 0;

    // No args constructor
    public MyVector() {

    }

    // Constructor with initial capacity
    public MyVector(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + initialCapacity);
        }
        array = new Car[initialCapacity];
    }

    // Constructor based on another collection
    public MyVector(MyList anotherList) {
        Car[] array = new Car[anotherList.size()];
        for (int i = 0; i < anotherList.size(); i++) {
            array[i] = anotherList.get(i);
        }
        size = anotherList.size();
    }

    // Constructor based on an array
    public MyVector(Car[] anotherArray) {
        Car[] array = new Car[anotherArray.length];
        System.arraycopy(anotherArray, 0, array, 0, anotherArray.length);
        size = anotherArray.length;
    }

    @Override
    public synchronized Car get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public synchronized void add(Car car) {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[size] = car;
        size++;
    }

    @Override
    public synchronized void add(Car car, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        for (int i = size + 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = car;
        size++;
    }

    @Override
    public synchronized boolean remove(Car car) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(car)) {
                return removeAt(i);
            }
        }
        return false;
    }

    @Override
    public synchronized boolean removeAt(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return true;
    }

    @Override
    public synchronized boolean removeAll(MyList anotherList) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (anotherList.contains(array[i])) {
                removeAt(i);
                i--;
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public synchronized boolean retainAll(MyList anotherList) {
        boolean modified = false;
        for (int i = 0; i < size; i++) {
            if (!anotherList.contains(array[i])) {
                removeAt(i);
                i--;
                modified = true;
            }
        }
        return modified;
    }

    @Override
    public synchronized int size() {
        return size;
    }

    @Override
    public synchronized int getCapacity() {
        return array.length;
    }

    @Override
    public synchronized void clear() {
        array = new Car[10];
        size = 0;
    }

    @Override
    public synchronized void trimToSize() {
        array = Arrays.copyOf(array, size);
    }

    @Override
    public synchronized boolean isEmpty() {
        return size == 0;
    }

    @Override
    public synchronized boolean contains(Car car) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(car)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized boolean containsAll(MyList list) {
        for (int i = 0; i < list.size(); i++) {
            if (!this.contains(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public synchronized int indexOf(Car car) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(car)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public synchronized int lastIndexOf(Car car) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(car)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public synchronized MyList clone() {
        MyList clonedArray = new MyArrayList();
        for (int i = 0; i < this.size(); i++) {
            clonedArray.add(this.get(i));
        }
        return clonedArray;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
