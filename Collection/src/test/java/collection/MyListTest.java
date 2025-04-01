package collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {

    private MyList list;

    @BeforeEach
    private void setUp(){
        // init
        for(int i = 0; i < 100; i++) {
            list.add(new Car("Brand" + i, "Color" + i, i));
        }
    }

    // get()
    @Test
    void methodGetReturnsCorrectElement() {
        Car car = list.get(24);
        assertEquals("Brand24", car.getBrand());
        assertEquals("Color24", car.getColor());
        assertEquals(24, car.getNumber());
    }

    // Throw exception when element with non-existent index is called
    @Test
    void whenIndexOutOfBoundThenThrowException() {
        Exception e = assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(100));
        assertEquals("Index 100 out of bounds for length 100", e.getMessage());
    }

    // add()
    @Test
    void when100ElementsAddedThenSizeMustBe100() {
        assertEquals(100, list.size());
        list.add(new Car("BMW", "Black", 198));
        assertEquals(101, list.size());
    }

    // remove()
    @Test
    void whenRemoveSpecificElementSizeMustDecrease() {
        Car car = new Car("Brand8", "Color8", 8);
        assertTrue(list.remove(car));
        assertFalse(list.remove(car));
        assertEquals(99, list.size());
    }

    // remove()
    @Test
    void whenRemoveSpecificNonExistentElementSizeMustNotDecrease() {
        Car car = new Car("Brand", "Color", 11);
        assertFalse(list.remove(car));
        assertEquals(100, list.size());
    }

    // removeAt()
    @Test
    void whenRemoveElementWithSpecificIndexSizeMustDecrease() {
        assertTrue(list.removeAt(5));
        assertEquals(99, list.size());
    }

    // removeAt()
    @Test
    void whenRemoveElementWithSpecificNonExistentIndexSizeMustNotDecrease() {
        assertFalse(list.removeAt(115));
        assertEquals(100, list.size());
    }

    // clear()
    @Test
    void whenClearCollectionSizeMustBeZero() {
        list.clear();
        assertEquals(0, list.size());
    }

    // trimToSize()
    @Test
    void whenTrimToSizeCapacityMustBeEqualToSize() {
    }

    // isEmpty()
    @Test
    void whenCollectionIsEmptySizeMustBeZero() {
        list.clear();
        assertTrue(list.isEmpty());
    }

    // isEmpty()
    @Test
    void whenCollectionIsNotEmptySizeMustNotBeZero() {
        assertFalse(list.isEmpty());
        list.clear();
        list.add(new Car("Toyota", "White", 618));
        assertFalse(list.isEmpty());
    }

    @Test
    void contains() {
        Car car1 = new Car("Brand17", "Color17", 17);
        Car car2 = new Car("BMW", "White", 176);
        assertTrue(list.contains(car1));
        assertFalse(list.contains(car2));
    }

    // indexOf()
    @Test
    void methodIndexOfReturnsCorrectIndex() {
        Car car = new Car("Brand85", "Color85", 85);
        int index = list.indexOf(car);
        assertEquals(85, index);
    }

    // lastIndexOf()
    @Test
    void methodLastIndexOfReturnsCorrectIndex() {
        Car car = new Car("Brand69", "Color69", 69);
        int index = list.lastIndexOf(car);
        assertEquals(69, index);
    }

    // clone()
    @Test
    void whenCloneCollectionSizesMustBeEqual() {
        MyList copyList = list.clone();
        assertEquals(copyList.size(), list.size());
    }

    // clone()
    @Test
    void whenCloneCollectionElementsMustBeEqual() {
        MyList copyList = list.clone();
        assertEquals(copyList.get(19).getNumber(), list.get(19).getNumber());
        assertEquals(copyList.get(19).getColor(), list.get(19).getColor());
        assertEquals(copyList.get(19).getBrand(), list.get(19).getBrand());
    }

    // set()
    @Test
    void whenSetElementSizeMustIncrease() {
        list.set(new Car("Toyota", "White", 618), 15);
        assertEquals(101, list.size());
        assertEquals(18, list.get(17).getNumber());
    }
}