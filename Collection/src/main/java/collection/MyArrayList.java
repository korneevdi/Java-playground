package collection;

import java.util.Arrays;

public class MyArrayList implements MyList {

    private Car[] array = new Car[10];

    private int size = 0;

    // No args constructor
    public MyArrayList() {

    }

    // Constructor with initial capacity
    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + initialCapacity);
        }
        array = new Car[initialCapacity];
    }

    // Constructor based on another collection
    public MyArrayList(MyList anotherList) {
        Car[] array = new Car[anotherList.size()];
        for (int i = 0; i < anotherList.size(); i++) {
            array[i] = anotherList.get(i);
        }
        size = anotherList.size();
    }

    // Constructor based on an array
    public MyArrayList(Car[] anotherArray) {
        Car[] array = new Car[anotherArray.length];
        System.arraycopy(anotherArray, 0, array, 0, anotherArray.length);
        size = anotherArray.length;
    }

    @Override
    public Car get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void add(Car car) {
        if (size == array.length) {
            int newCapacity = array.length + (array.length >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
        array[size] = car;
        size++;
    }

    @Override
    public void add(Car car, int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (size == array.length) {
            int newCapacity = array.length + (array.length >> 1);
            array = Arrays.copyOf(array, newCapacity);
        }
        for (int i = size + 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = car;
        size++;
    }

    @Override
    public boolean remove(Car car) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(car)) {
                return removeAt(i);
            }
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        checkIndex(index);
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return true;
    }

    @Override
    public boolean removeAll(MyList anotherList) {
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
    public boolean retainAll(MyList anotherList) {
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
    public int size() {
        return size;
    }

    @Override
    public int getCapacity() {
        return array.length;
    }

    @Override
    public void clear() {
        array = new Car[10];
        size = 0;
    }

    @Override
    public void trimToSize() {
        array = Arrays.copyOf(array, size);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Car car) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(car)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(MyList list) {
        for (int i = 0; i < list.size(); i++) {
            if (!this.contains(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int indexOf(Car car) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(car)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Car car) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(car)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public MyList clone() {
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
