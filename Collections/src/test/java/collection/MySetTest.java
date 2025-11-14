package collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MySetTest {

    private MySet set;

    @BeforeEach
    private void setUp() {
        //set = new MyHashSet();
        set = new MyLinkedHashSet();
        for (int i = 0; i < 100; i++) {
            set.add(new Car("brand" + i, "color" + i, i));
        }
    }

    //void add(Car car)
    @Test
    @DisplayName("If new element added, collection size must increase by one")
    public void whenAddNewElementThenSizeMustIncrease() {
        assertEquals(100, set.size());
        set.add(new Car("BMW", "black", 518));
        assertEquals(101, set.size());
    }

    @Test
    @DisplayName("If an existent element added, collection size must does not change")
    public void whenAddExistentElementThenSizeDoesNotChange() {
        Car car = new Car("brand14", "color14", 14);
        assertFalse(set.add(car));
        assertEquals(100, set.size());
    }

    //boolean remove(Car car)
    @Test
    @DisplayName("If one element is removed, collection size must decrease")
    public void whenRemoveOneElementThenSizeMustDecrease() {
        Car car = new Car("brand15", "color15", 15);
        assertTrue(set.remove(car));
        assertEquals(99, set.size());
        assertFalse(set.remove(car));
        assertEquals(99, set.size());

        // Remove the first element
        Car car0 = new Car("brand0", "color0", 0);
        assertTrue(set.remove(car0));
        assertEquals(98, set.size());
        assertFalse(set.remove(car0));
        assertEquals(98, set.size());

        // Remove the last element
        Car car99 = new Car("brand99", "color99", 99);
        assertTrue(set.remove(car99));
        assertEquals(97, set.size());
        assertFalse(set.remove(car99));
        assertEquals(97, set.size());
    }

    //int size()
    @Test
    @DisplayName("The size() method returns correct collection size")
    public void methodSizeMustReturnCorrectSize() {
        assertEquals(100, set.size());
    }

    //void clear()
    @Test
    @DisplayName("The clear() method removes all element from the collection")
    public void whenClearCollectionThenSizeMustBeZero() {
        set.clear();
        assertEquals(0, set.size());
    }

    //boolean isEmpty()
    @Test
    @DisplayName("The isEmpty() method must return true, if the collection is empty")
    public void whenCollectionHasNoElementIsEmptyMustReturnTrue() {
        set = new MyHashSet();
        assertTrue(set.isEmpty());
    }

    @Test
    @DisplayName("The isEmpty() method must return false, if the collection contains elements")
    public void whenCollectionHasElementsIsEmptyMustReturnFalse() {
        assertFalse(set.isEmpty());
    }

    //boolean contains(CarOwner key)
    @Test
    @DisplayName("The contains() method must return correct boolean value")
    public void methodContainsMustReturnCorrectValue() {
        Car car1 = new Car("brand15", "color15", 15);
        Car car2 = new Car("brand17", "color15", 15);
        assertTrue(set.contains(car1));
        assertFalse(set.contains(car2));

        assertTrue(set.remove(car1));
        assertFalse(set.contains(car1));
    }

    //Car[] toArray()
    @Test
    @DisplayName("The toArray() method must return correct array")
    public void methodToArrayMustReturnArrayWithCorrectSize() {
        assertEquals(100, set.toArray().length);
    }
}