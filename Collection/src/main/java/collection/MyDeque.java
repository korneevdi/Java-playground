package collection;

public interface MyDeque {

    // Adds the element at the front of the deque
    void addFirst(Car car);

    // Adds the element at the end of the deque
    void addLast(Car car);

    // Inserts element at the front, returns false if not possible
    boolean offerFirst(Car car);

    // Inserts element at the end, returns false if not possible
    boolean offerLast(Car car);

    // Retrieves and removes the first element
    Car removeFirst();

    // Retrieves and removes the last element
    Car removeLast();

    // Retrieves and removes the first element or returns null if deque is empty
    Car pollFirst();

    // Retrieves and removes the last element or returns null if deque is empty
    Car pollLast();

    // Retrieves, but does not remove, the first element
    Car getFirst();

    // Retrieves, but does not remove, the last element
    Car getLast();

    // Retrieves, but does not remove, the first element or returns null if deque is empty
    Car peekFirst();

    // Retrieves, but does not remove, the last element or returns null if deque is empty
    Car peekLast();

    // Pushes an element onto the stack (adds to front)
    void push(Car car);

    // Pops an element from the stack (removes from front)
    Car pop();

    // Returns true if the deque contains the specified element
    boolean contains(Car car);

    // Removes the first occurrence of the specified element
    boolean removeFirstOccurrence(Car car);

    // Removes the last occurrence of the specified element
    boolean removeLastOccurrence(Car car);

    // Returns the number of elements in the deque
    int size();

    // Returns true if the deque contains no elements
    boolean isEmpty();

    // Removes a single instance of the specified element if present
    boolean remove(Car car);

    // Adds an element to the tail of the deque (equivalent to addLast)
    boolean add(Car car);

    // Offers an element to the tail (returns false if not possible)
    boolean offer(Car car);

    // Retrieves and removes the head of the deque (same as removeFirst)
    Car remove();

    // Retrieves and removes the head or returns null if empty (same as pollFirst)
    Car poll();

    // Retrieves, but does not remove, the head of the deque (same as getFirst)
    Car element();

    // Retrieves, but does not remove, the head or returns null if empty (same as peekFirst)
    Car peek();

    // Clears all elements from the deque
    void clear();
}
