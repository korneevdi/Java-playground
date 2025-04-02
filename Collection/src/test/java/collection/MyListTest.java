package collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyListTest {

    private MyList list;

    @BeforeEach
    private void setUp(){
        list = new MyArrayList();
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

    // add(Car car)
    @Test
    void when100ElementsAddedThenSizeMustBe100() {
        assertEquals(100, list.size());
        list.add(new Car("BMW", "Black", 198));
        assertEquals(101, list.size());
    }

    // add(Car car, int index)
    @Test
    void whenAddElementSizeMustIncrease() {
        list.add(new Car("Toyota", "White", 618), 15);
        assertEquals(101, list.size());
        assertEquals(18, list.get(19).getNumber());
        assertEquals(618, list.get(15).getNumber());
        assertEquals("Toyota", list.get(15).getBrand());
        assertEquals("White", list.get(15).getColor());
    }

    // add(Car car, int index)
    @Test
    void whenAddElementToFirstPositionSizeMustIncrease() {
        list.add(new Car("Toyota", "White", 618), 0);
        assertEquals(101, list.size());
        assertEquals(18, list.get(19).getNumber());
        assertEquals(618, list.get(0).getNumber());
        assertEquals("Toyota", list.get(0).getBrand());
        assertEquals("White", list.get(0).getColor());
    }

    // add(Car car, int index)
    @Test
    void whenAddElementToLastPositionSizeMustIncrease() {
        list.add(new Car("Toyota", "White", 618), 100);
        assertEquals(101, list.size());
        assertEquals(618, list.get(100).getNumber());
        assertEquals("Toyota", list.get(100).getBrand());
        assertEquals("White", list.get(100).getColor());
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

    // removeAll()
    @Test
    void whenRemoveAllElementsContainingByAnotherCollectionSizeMustDecrease() {
        MyList anotherList = new MyArrayList();
        for(int i = 0; i < 10; i++) {
            anotherList.add(new Car("Brand" + i, "Color" + i, i));
        }
        assertTrue(list.removeAll(anotherList));
        assertEquals(90, list.size());
        anotherList.add(new Car("BMW", "Blue", 111));
        assertFalse(list.removeAll(anotherList));
        assertEquals(90, list.size());
        anotherList.add(new Car("Brand44", "Color44", 44));
        assertTrue(list.removeAll(anotherList));
        assertEquals(89, list.size());
    }

    // retainAll()
    @Test
    void whenRetainAllElementsContainingByAnotherCollectionSizeMustDecrease() {
        MyList anotherList = new MyArrayList();
        for(int i = 0; i < 10; i++) {
            anotherList.add(new Car("Brand" + i, "Color" + i, i));
        }
        assertTrue(list.retainAll(anotherList));
        assertEquals(10, list.size());
        anotherList.add(new Car("BMW", "Blue", 111));
        assertFalse(list.retainAll(anotherList));
        assertEquals(10, list.size());
        anotherList.remove(new Car("Brand4", "Color4", 4));
        assertTrue(list.retainAll(anotherList));
        assertEquals(9, list.size());
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
        int initCapacity = list.getCapacity();
        int desiredCapacity = 80;
        for(int i = 99; i >= desiredCapacity; i--) {
            list.removeAt(i);
        }
        assertEquals(initCapacity, list.getCapacity());
        list.trimToSize();
        assertEquals(desiredCapacity, list.getCapacity());
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

    // contains()
    @Test
    void methodContainsReturnsCorrectBooleanValue() {
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
}