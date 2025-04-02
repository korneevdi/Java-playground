package collection;

import java.util.Arrays;

public class MyArrayList implements MyList {

    private Car[] array = new Car[10];
    private int size = 0;

    @Override
    public Car get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public void add(Car car) {
        if(size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[size] = car;
        size++;
    }

    @Override
    public boolean remove(Car car) {
        for(int i = 0; i < size; i++) {
            if(array[i].equals(car)) {
                return removeAt(i);
            }
        }
        return false;
    }

    @Override
    public boolean removeAt(int index) {
        checkIndex(index);
        for(int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        return true;
    }

    @Override
    public boolean removeAll(MyList anotherList) {
        boolean modified = false;
        for(int i = 0; i < size; i++) {
            if(anotherList.contains(array[i])) {
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
        for(int i = 0; i < size; i++) {
            if(array[i].equals(car)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Car car) {
        for(int i = 0; i < size; i++) {
            if (array[i].equals(car)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Car car) {
        for(int i = size - 1; i >= 0; i--) {
            if (array[i].equals(car)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public MyList clone() {
        MyList clonedArray = new MyArrayList();
        for(int i = 0; i < this.size(); i++) {
            clonedArray.add(this.get(i));
        }
        return clonedArray;
    }

    @Override
    public void add(Car car, int index) {
        if(index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if(size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        for(int i = size + 1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = car;
        size++;
    }

    private void checkIndex(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
