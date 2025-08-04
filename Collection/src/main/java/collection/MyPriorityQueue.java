package collection;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class MyPriorityQueue<E> implements MyQueue<E>{

    private static final int DEFAULT_CAPACITY = 16;

    private final Comparator<? super E> comparator;

    private Object[] queue;

    private int size;

    public MyPriorityQueue() {
        this(null);
    }

    public MyPriorityQueue(Comparator<? super E> comparator) {
        queue = new Object[DEFAULT_CAPACITY];
        size = 0;
        this.comparator = comparator;
    }

    @Override
    public void add(Object e) {
        if(e == null) throw new NullPointerException("null is not allowed");

        if(size >= queue.length) grow();

        queue[size] = e;
        siftUp(size);
        size++;
    }

    @Override
    public boolean offer(Object e) {
        if(e == null) return false;

        if(size >= queue.length) grow();

        queue[size] = e;
        siftUp(size);
        size++;
        return true;
    }

    @Override
    public E remove() {
        if(size == 0) throw new NullPointerException("Queue is empty");

        return remove(0);
    }

    @Override
    public E poll() {
        if(size == 0) return null;

        E removedElement = (E) queue[0];
        queue[0] = queue[size - 1];
        queue[size - 1] = null;
        size--;
        siftDown(0);
        return removedElement;
    }

    @Override
    public E element() {
        if(size == 0) throw new NoSuchElementException("Queue is empty");
        return (E) queue[0];
    }

    @Override
    public E peek() {
        if(size == 0) return null;
        return (E) queue[0];
    }

    public E remove(Object e) {
        if(e == null) throw new NullPointerException();

        int index = -1;
        for(int i = 0; i < size; i++) {
            if(e.equals(queue[i])) {
                index = i;
                break;
            }
        }

        if(index == -1) {
            throw new NoSuchElementException("There is not such element in the queue");
        } else {
            return remove(index);
        }
    }

    public boolean contains(Object e) {
        for(int i = 0; i < size; i++) {
            if(e.equals(queue[i])) return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        queue = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    private int compare(E o1, E o2) {
        if(comparator != null) {
            return comparator.compare(o1, o2);
        } else {
            return ((Comparable<? super E>) o1).compareTo(o2);
        }
    }

    private void grow() {
        // Double size if small; else grow by 50%
        int newCapacity = queue.length > 64 ? (queue.length + queue.length / 2) : (queue.length * 2);
        Object[] newQueue = new Object[newCapacity];
        System.arraycopy(queue, 0, newQueue, 0, queue.length);
        queue = newQueue;
    }

    private void siftUp(int index) {
        E element = (E) queue[index];

        while(index > 0) {
            int parentIndex = (index - 1) / 2;
            E parent = (E) queue[parentIndex];

            if(compare(element, parent) >= 0) break;

            queue[index] = parent;
            index = parentIndex;
        }

        queue[index] = element;
    }

    private void siftDown(int index) {
        E element = (E) queue[index];

        while(true) {
            int leftIndex = index * 2 + 1;
            int rightIndex = index * 2 + 2;
            int smallestIndex = index;

            if(leftIndex < size && compare((E) queue[smallestIndex], (E) queue[leftIndex]) > 0) {
                smallestIndex = leftIndex;
            }

            if(rightIndex < size && compare((E) queue[smallestIndex], (E) queue[rightIndex]) > 0) {
                smallestIndex = rightIndex;
            }

            if(smallestIndex == index) break;

            queue[index] = queue[smallestIndex];
            index = smallestIndex;
        }

        queue[index] = element;
    }

    private E remove(int index) {
        E removedElement = (E) queue[index];

        if (index == size - 1) {
            queue[index] = null;
        } else {
            queue[index] = queue[size - 1];
            queue[size - 1] = null;
            siftDown(index);
            siftUp(index);
        }
        size--;

        return removedElement;
    }
}
