package collection;

public interface MyQueue<E> {

    // Inserts the specified element into the queue if possible (or throws an exception)
    void add(E element);

    // Inserts the specified element into the queue if possible (or returns false)
    boolean offer(E element);

    // Retrieves and removes the head of the queue (or throws an exception)
    E remove();

    // Retrieves and removes the head of the queue (or returns null)
    E poll();

    // Retrieves, but does not remove, the head of the queue (or throws an exception)
    E element();

    // Retrieves, but does not remove, the head of the queue (or returns null)
    E peek();
}
