package collection;

import java.util.EmptyStackException;

public class MyStack extends MyVector {

    public MyStack() {

    }

    public Car push(Car car) {
        add(car);
        return car;
    }

    public Car pop() {
        int length = this.size();
        if(length == 0) {
            throw new EmptyStackException();
        } else {
            Car car = peek();
            removeAt(length - 1);
            return car;
        }
    }

    public Car peek() {
        int length = this.size();
        if(length == 0) {
            throw new EmptyStackException();
        } else {
            return get(length - 1);
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int search(Car car) {
        for (int i = size() - 1, pos = 1; i >= 0; i--, pos++) {
            if (get(i).equals(car)) {
                return pos;
            }
        }
        return -1;
    }
}
