package collection;

public interface MyList {

    // Getting an element by index
    Car get(int index);

    // Adding an element to collection
    void add(Car car);

    // Adding new element to a specific position
    void add(Car car, int index);

    // Removing a specific element from collection
    boolean remove(Car car);

    // Removing an element with a specific index from collection
    boolean removeAt(int index);

    // Removing a set of elements containing by another collection
    boolean removeAll(MyList anotherList);

    // Retaining a set of elements containing by another collection
    boolean retainAll(MyList anotherList);

    // Size of the collection
    int size();

    // Capacity of the array
    int getCapacity();

    // Clear the collection (remove all elements)
    void clear();

    // Trim capacity to size
    void trimToSize();

    // Whether the collection is empty
    boolean isEmpty();

    // Whether the collection contains an element
    boolean contains(Car car);

    // Whether the collection contains all elements of another collection
    boolean containsAll(MyList list);

    // Index of the first occurrence of the specified element
    int indexOf(Car car);

    // Index of the last occurrence of the specified element
    int lastIndexOf(Car car);

    // Clone collection
    MyList clone();
}
