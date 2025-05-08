package collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class MyStackTest {

    private MyStack stack;

    @BeforeEach
    private void setUp() {
        stack = new MyStack();
        for (int i = 0; i < 100; i++) {
            stack.push(new Car("Brand" + i, "Color" + i, i));
        }
    }

    @Test
    @DisplayName("The push() method should increase the stack size by one")
    public void whenPushOneElementThenSizeMustIncrease() {
        Car car = new Car("BMW", "Red", 114);

        stack.push(car);
        assertEquals(101, stack.size());

        MyStack newStack = new MyStack();
        newStack.push(car);
        assertEquals(1, newStack.size());
    }

    @Test
    @DisplayName("The push() method should return the added element")
    public void whenPushOneElementThenLastElementShouldBeReturned() {
        Car car = new Car("BMW", "Red", 114);

        Car newCar = stack.push(car);
        assertEquals(car, newCar);

        MyStack newStack = new MyStack();
        Car anotherAddedCar = newStack.push(car);
        assertEquals(car, anotherAddedCar);
    }

    @Test
    @DisplayName("The pop() method should decrease the non-empty stack size by one")
    public void whenPopNonEmptyStackThenSizeMustDecrease() {
        stack.pop();
        assertEquals(99, stack.size());
    }

    @Test
    @DisplayName("The pop() method should throw an exception being applied to an empty stack")
    public void whenPopEmptyStackThenExceptionMustBeThrown() {
        MyStack newStack = new MyStack();
        assertThrows(EmptyStackException.class, newStack::pop);
    }

    @Test
    @DisplayName("The peek() method should return the last element from a non-empty stack")
    public void whenPeekNonEmptyStackThenLastElementShouldBeReturned() {
        stack.peek();
        assertEquals(100, stack.size());
        Car car = new Car("Brand99", "Color99", 99);
        assertEquals(car, stack.peek());
    }

    @Test
    @DisplayName("The peek() method should throw an exception being applied to an empty stack")
    public void whenPeekEmptyStackThenExceptionMustBeThrown() {
        MyStack newStack = new MyStack();
        assertThrows(EmptyStackException.class, newStack::peek);
    }

    @Test
    @DisplayName("The isEmpty() method should return true if stack is empty")
    public void whenStackIsEmptyThenIsEmptyMustReturnTrue() {
        MyStack newStack = new MyStack();
        assertTrue(newStack.isEmpty());
    }

    @Test
    @DisplayName("The isEmpty() method should return false if stack is not empty")
    public void whenStackIsNotEmptyThenIsEmptyMustReturnFalse() {
        assertFalse(stack.isEmpty());
    }

    @Test
    @DisplayName("The search() method should return the element position, if stack contains this element")
    public void searchMustReturnTheElementPositionIfElementIsInStack() {
        Car car = new Car("Brand98", "Color98", 98);
        int position = stack.search(car);
        assertEquals(2, position);
    }

    @Test
    @DisplayName("The search() method should return -1, if stack does not contain this element")
    public void searchMustReturnMinusOneIfElementIsNotInStack() {
        Car car = new Car("Brand", "Color", 0);
        int position = stack.search(car);
        assertEquals(-1, position);
    }
}
