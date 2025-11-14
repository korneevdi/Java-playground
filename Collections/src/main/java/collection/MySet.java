package collection;

public interface MySet {

    boolean add(Car car);

    boolean remove(Car car);

    int size();

    void clear();

    boolean isEmpty();

    boolean contains(Car car);

    Car[] toArray();
}
