package collection;

import java.util.NoSuchElementException;

public class MyArrayDeque implements MyDeque {

    private static final int INITIAL_CAPACITY = 8;

    private int head = 0;
    private int tail = 0;
    private int size = 0;

    private Car[] array = new Car[INITIAL_CAPACITY];

    @Override
    public void addFirst(Car car) {
        if (car == null) throw new NullPointerException();

        if (size == array.length) resize();

        head = (head - 1 + array.length) % array.length;
        array[head] = car;
        size++;
    }

    @Override
    public void addLast(Car car) {
        if (car == null) throw new NullPointerException();

        if (size == array.length) resize();

        array[tail] = car;
        tail = (tail + 1) % array.length;
        size++;
    }

    @Override
    public boolean offerFirst(Car car) {
        addFirst(car);
        return true;
    }

    @Override
    public boolean offerLast(Car car) {
        addLast(car);
        return true;
    }

    @Override
    public Car removeFirst() {
        if (size == 0) throw new NoSuchElementException();

        Car removedCar = array[head];
        array[head] = null;
        head = (head + 1) % array.length;
        size--;
        return removedCar;
    }

    @Override
    public Car removeLast() {
        if (size == 0) throw new NoSuchElementException();

        tail = (tail - 1 + array.length) % array.length;
        Car removedCar = array[tail];
        array[tail] = null;
        size--;
        return removedCar;
    }

    @Override
    public Car pollFirst() {
        if (size == 0) return null;

        Car removedCar = array[head];
        array[head] = null;
        head = (head + 1) % array.length;
        size--;
        return removedCar;
    }

    @Override
    public Car pollLast() {
        if (size == 0) return null;

        tail = (tail - 1 + array.length) % array.length;
        Car removedCar = array[tail];
        array[tail] = null;
        size--;
        return removedCar;
    }

    @Override
    public Car getFirst() {
        if (size == 0) throw new NoSuchElementException();
        return array[head];
    }

    @Override
    public Car getLast() {
        if (size == 0) throw new NoSuchElementException();
        return array[(tail - 1 + array.length) % array.length];
    }

    @Override
    public Car peekFirst() {
        if (size == 0) return null;
        return array[head];
    }

    @Override
    public Car peekLast() {
        if (size == 0) return null;
        return array[(tail - 1 + array.length) % array.length];
    }

    @Override
    public void push(Car car) {
        addFirst(car);
    }

    @Override
    public Car pop() {
        return removeFirst();
    }

    @Override
    public boolean contains(Car car) {
        if (car == null || size == 0) return false;

        for (int i = 0; i < size; i++) {
            int actualIndex = (head + i) % array.length;
            if (array[actualIndex] != null && array[actualIndex].equals(car)) return true;
        }

        return false;
    }

    @Override
    public boolean removeFirstOccurrence(Car car) {
        if (car == null || size == 0) return false;

        // Find the actual index of 'car'
        int index = -1;
        for (int i = 0; i < size; i++) {
            int actualIndex = (head + i) % array.length;
            if (array[actualIndex].equals(car)) {
                index = actualIndex;
                break;
            }
        }

        if (index == -1) return false;

        // Shift all the elements after 'index' to the left
        for (int j = index; j != ((tail - 1 + array.length) % array.length); j = (j + 1) % array.length) {
            array[j] = array[(j + 1) % array.length];
        }

        // New tail
        tail = (tail - 1 + array.length) % array.length;
        array[tail] = null;
        size--;

        return true;
    }

    @Override
    public boolean removeLastOccurrence(Car car) {
        if (car == null || size == 0) return false;

        // Find the actual index of 'car'
        int index = -1;
        for (int i = 0; i < size; i++) {
            int actualIndex = (tail - 1 - i + array.length) % array.length;
            if (array[actualIndex].equals(car)) {
                index = actualIndex;
                break;
            }
        }

        if (index == -1) return false;

        // Shift all the elements after 'index' to the left
        for (int j = index; j != ((tail - 1 + array.length) % array.length); j = (j + 1) % array.length) {
            array[j] = array[(j + 1) % array.length];
        }

        // New tail
        tail = (tail - 1 + array.length) % array.length;
        array[tail] = null;
        size--;

        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(Car car) {
        return removeFirstOccurrence(car);
    }

    @Override
    public boolean add(Car car) {
        return offerLast(car);
    }

    @Override
    public boolean offer(Car car) {
        return offerLast(car);
    }

    @Override
    public Car remove() {
        return removeFirst();
    }

    @Override
    public Car poll() {
        return pollFirst();
    }

    @Override
    public Car element() {
        return getFirst();
    }

    @Override
    public Car peek() {
        return peekFirst();
    }

    @Override
    public void clear() {
        array = new Car[INITIAL_CAPACITY];
        head = 0;
        tail = 0;
        size = 0;
    }

    private void resize() {
        int oldCapacity = array.length;
        int newCapacity = oldCapacity * 2;
        Car[] newArray = new Car[newCapacity];

        for (int i = 0; i < size; i++) {
            newArray[i] = array[(head + i) % oldCapacity];
        }
        array = newArray;
        head = 0;
        tail = size;
    }
}
