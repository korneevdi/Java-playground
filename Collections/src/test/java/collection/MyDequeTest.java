package collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyDequeTest {

    private MyDeque deque;

    @BeforeEach
    private void setUp() {
        deque = new MyArrayDeque();
        for (int i = 0; i < 50; i++) {
            deque.add(new Car("Brand" + i, "Color" + i, i));
        }
    }

    //void addFirst(Car car);
    @Test
    @DisplayName("Add an element to an empty deque")
    void testAddFirstToEmptyDeque() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        Car car = new Car("Toyota", "Black", 144);
        emptyDeque.addFirst(car);

        assertEquals(1, emptyDeque.size());
        assertEquals(car, emptyDeque.getFirst());
        assertEquals(car, emptyDeque.getLast());
    }

    @Test
    @DisplayName("Add multiple elements to the deque and check the order")
    void testAddFirstMultiple() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        Car car1 = new Car("Toyota", "White", 546);
        Car car2 = new Car("BMW", "Blue", 564);
        Car car3 = new Car("Audi", "Black", 463);

        emptyDeque.addFirst(car1);
        emptyDeque.addFirst(car2);
        emptyDeque.addFirst(car3);

        assertEquals(3, emptyDeque.size());
        assertEquals(car3, emptyDeque.getFirst());
        assertEquals(car1, emptyDeque.getLast());
    }

    @Test
    @DisplayName("Try to add 'null', an exception should be thrown")
    void testAddFirstNullThrows() {
        assertThrows(NullPointerException.class, () -> deque.addFirst(null));
    }

    //void addLast(Car car);
    @Test
    @DisplayName("Add an element to an empty deque")
    void testAddLastToEmptyDeque() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        Car car = new Car("Toyota", "Black", 144);
        emptyDeque.addLast(car);

        assertEquals(1, emptyDeque.size());
        assertEquals(car, emptyDeque.getFirst());
        assertEquals(car, emptyDeque.getLast());
    }

    @Test
    @DisplayName("Add multiple elements to the deque and check the order")
    void testAddLastMultiple() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        Car car1 = new Car("Toyota", "White", 546);
        Car car2 = new Car("BMW", "Blue", 564);
        Car car3 = new Car("Audi", "Black", 463);

        emptyDeque.addLast(car1);
        emptyDeque.addLast(car2);
        emptyDeque.addLast(car3);

        assertEquals(3, emptyDeque.size());
        assertEquals(car3, emptyDeque.getLast());
        assertEquals(car1, emptyDeque.getFirst());
    }

    @Test
    @DisplayName("Try to add 'null', an exception should be thrown")
    void testAddLastNullThrows() {
        assertThrows(NullPointerException.class, () -> deque.addLast(null));
    }

    //boolean offerFirst(Car car);
    @Test
    @DisplayName("Offer an element to an empty deque")
    void testOfferFirstToEmptyDeque() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        Car car = new Car("Toyota", "Black", 144);
        emptyDeque.offerFirst(car);

        assertEquals(1, emptyDeque.size());
        assertEquals(car, emptyDeque.getFirst());
        assertEquals(car, emptyDeque.getLast());
    }

    @Test
    @DisplayName("Offer multiple elements to the deque and check the order")
    void testOfferFirstMultiple() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        Car car1 = new Car("Toyota", "White", 546);
        Car car2 = new Car("BMW", "Blue", 564);
        Car car3 = new Car("Audi", "Black", 463);

        assertTrue(emptyDeque.offerFirst(car1));
        assertTrue(emptyDeque.offerFirst(car2));
        assertTrue(emptyDeque.offerFirst(car3));

        assertEquals(3, emptyDeque.size());
        assertEquals(car3, emptyDeque.getFirst());
        assertEquals(car1, emptyDeque.getLast());
    }

    @Test
    @DisplayName("Try to offer 'null', an exception should be thrown")
    void testOfferFirstNullThrows() {
        assertThrows(NullPointerException.class, () -> deque.offerFirst(null));
    }


    //boolean offerLast(Car car);
    @Test
    @DisplayName("Offer an element to an empty deque")
    void testOfferLastToEmptyDeque() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        Car car = new Car("Toyota", "Black", 144);
        emptyDeque.offerLast(car);

        assertEquals(1, emptyDeque.size());
        assertEquals(car, emptyDeque.getFirst());
        assertEquals(car, emptyDeque.getLast());
    }

    @Test
    @DisplayName("Offer multiple elements to the deque and check the order")
    void testOfferLastMultiple() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        Car car1 = new Car("Toyota", "White", 546);
        Car car2 = new Car("BMW", "Blue", 564);
        Car car3 = new Car("Audi", "Black", 463);

        assertTrue(emptyDeque.offerLast(car1));
        assertTrue(emptyDeque.offerLast(car2));
        assertTrue(emptyDeque.offerLast(car3));

        assertEquals(3, emptyDeque.size());
        assertEquals(car3, emptyDeque.getLast());
        assertEquals(car1, emptyDeque.getFirst());
    }

    @Test
    @DisplayName("Try to offer 'null', an exception should be thrown")
    void testOfferLastNullThrows() {
        assertThrows(NullPointerException.class, () -> deque.offerLast(null));
    }

    //Car removeFirst();
    @Test
    @DisplayName("Try to remove 3 elements from the deque")
    void testRemoveFirstToRemoveThreeElements() {
        for (int i = 0; i < 3; i++) {
            deque.removeFirst();
        }
        assertEquals(47, deque.size());
    }

    @Test
    @DisplayName("Try to remove 50 elements from the deque")
    void testRemoveFirstToRemoveAllFiftyElements() {
        for (int i = 0; i < 50; i++) {
            deque.removeFirst();
        }
        assertEquals(0, deque.size());
    }

    @Test
    @DisplayName("Try to remove element from an empty deque")
    void testRemoveFirstRemoveNullFromEmptyDeque() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        assertThrows(NoSuchElementException.class, emptyDeque::removeFirst);
    }

    //Car removeLast();
    @Test
    @DisplayName("Try to remove 3 elements from the deque")
    void testRemoveLastToRemoveThreeElements() {
        for (int i = 0; i < 3; i++) {
            deque.removeLast();
        }
        assertEquals(47, deque.size());
    }

    @Test
    @DisplayName("Try to remove 50 elements from the deque")
    void testRemoveLastToRemoveAllFiftyElements() {
        for (int i = 0; i < 50; i++) {
            deque.removeLast();
        }
        assertEquals(0, deque.size());
    }

    @Test
    @DisplayName("Try to remove element from an empty deque")
    void testRemoveLastRemoveNullFromEmptyDeque() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        assertThrows(NoSuchElementException.class, emptyDeque::removeLast);
    }

    //Car pollFirst();
    @Test
    @DisplayName("Try to poll 3 elements from the deque")
    void testPollFirstToRemoveThreeElements() {
        for (int i = 0; i < 3; i++) {
            deque.pollFirst();
        }
        assertEquals(47, deque.size());
    }

    @Test
    @DisplayName("Try to poll 50 elements from the deque")
    void testPollFirstToRemoveAllFiftyElements() {
        for (int i = 0; i < 50; i++) {
            deque.pollFirst();
        }
        assertEquals(0, deque.size());
    }

    @Test
    @DisplayName("Try to poll element from an empty deque")
    void testPollFirstRemoveNullFromEmptyDeque() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        assertNull(emptyDeque.pollFirst());
    }

    //Car pollLast();
    @Test
    @DisplayName("Try to poll 3 elements from the deque")
    void testPollLastToRemoveThreeElements() {
        for (int i = 0; i < 3; i++) {
            deque.pollLast();
        }
        assertEquals(47, deque.size());
    }

    @Test
    @DisplayName("Try to poll 50 elements from the deque")
    void testPollLastToRemoveAllFiftyElements() {
        for (int i = 0; i < 50; i++) {
            deque.pollLast();
        }
        assertEquals(0, deque.size());
    }

    @Test
    @DisplayName("Try to poll element from an empty deque")
    void testPollLastRemoveNullFromEmptyDeque() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        assertNull(emptyDeque.pollFirst());
    }

    //Car getFirst();
    @Test
    @DisplayName("Get the first element from the deque")
    void testGetFirstElement() {
        Car car = new Car("Brand0", "Color0", 0);
        assertEquals(car, deque.getFirst());
    }

    @Test
    @DisplayName("Get the first element from an empty deque")
    void testGetFirstFromEmptyDeque() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        assertThrows(NoSuchElementException.class, emptyDeque::getFirst);
    }

    //Car getLast();
    @Test
    @DisplayName("Get the last element from the deque")
    void testGetLastElement() {
        Car car = new Car("Brand49", "Color49", 49);
        assertEquals(car, deque.getLast());
    }

    @Test
    @DisplayName("Get the last element from an empty deque")
    void testGetLastFromEmptyDeque() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        assertThrows(NoSuchElementException.class, emptyDeque::getLast);
    }

    //Car peekFirst();
    @Test
    @DisplayName("Peek the first element from the deque")
    void testPeekFirstElement() {
        Car car = new Car("Brand0", "Color0", 0);
        assertEquals(car, deque.peekFirst());
    }

    @Test
    @DisplayName("Peek the first element from an empty deque")
    void testPeekFirstFromEmptyDeque() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        assertNull(emptyDeque.peekFirst());
    }

    //Car peekLast();
    @Test
    @DisplayName("Peek the last element from the deque")
    void testPeekLastElement() {
        Car car = new Car("Brand49", "Color49", 49);
        assertEquals(car, deque.peekLast());
    }

    @Test
    @DisplayName("Peek the last element from an empty deque")
    void testPeekLastFromEmptyDeque() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        assertNull(emptyDeque.peekLast());
    }

    //boolean contains(Car car);
    @Test
    @DisplayName("contains() method returns true if element exists")
    void testContainsWithExistentElement() {
        Car car = new Car("Brand18", "Color18", 18);
        assertTrue(deque.contains(car));
    }

    @Test
    @DisplayName("contains() method returns false if element does not exist")
    void testContainsWithNonExistentElement() {
        Car car = new Car("Brand17", "Color18", 18);
        assertFalse(deque.contains(car));
    }

    @Test
    @DisplayName("contains() method returns false if deque is empty")
    void testContainsWithEmptyDeque() {
        MyArrayDeque emptyDeque = new MyArrayDeque();
        Car car = new Car("Brand0", "Color0", 0);
        assertFalse(emptyDeque.contains(car));
    }

    @Test
    @DisplayName("contains() method returns false if element is null")
    void testContainsWithNullArgument() {
        assertFalse(deque.contains(null));
    }

    //boolean removeFirstOccurrence(Car car);
    @Test
    @DisplayName("removeFirstOccurrence() method returns true if element exists")
    void testRemoveFirstOccurrenceWithExistentElement() {
        Car car1 = new Car("Brand1", "Color1", 1);
        Car car2 = new Car("Brand2", "Color2", 2);
        Car car3 = new Car("Brand3", "Color3", 3);

        MyArrayDeque newDeque = new MyArrayDeque();
        newDeque.add(car1);
        newDeque.add(car2);
        newDeque.add(car3);
        newDeque.add(car1);
        newDeque.add(car2);
        newDeque.add(car3);
        newDeque.add(car1);
        newDeque.add(car1);
        newDeque.add(car3);

        assertTrue(newDeque.removeFirstOccurrence(car2));
        assertEquals(8, newDeque.size());

        assertTrue(newDeque.removeFirstOccurrence(car2));
        assertEquals(7, newDeque.size());

        assertFalse(newDeque.removeFirstOccurrence(car2));
        assertEquals(7, newDeque.size());
    }

    @Test
    @DisplayName("removeFirstOccurrence() method returns false if element does not exist")
    void testRemoveFirstOccurrenceWithNonExistentElement() {
        Car car = new Car("Brand", "Color", 0);
        assertFalse(deque.removeFirstOccurrence(car));
    }

    @Test
    @DisplayName("removeFirstOccurrence() method returns false if element is null")
    void testRemoveFirstOccurrenceWithNullArgument() {
        assertFalse(deque.removeFirstOccurrence(null));
    }

    //boolean removeLastOccurrence(Car car);
    @Test
    @DisplayName("removeLastOccurrence() method returns true if element exists")
    void testRemoveLastOccurrenceWithExistentElement() {
        Car car1 = new Car("Brand1", "Color1", 1);
        Car car2 = new Car("Brand2", "Color2", 2);
        Car car3 = new Car("Brand3", "Color3", 3);

        MyArrayDeque newDeque = new MyArrayDeque();
        newDeque.add(car1);
        newDeque.add(car2);
        newDeque.add(car3);
        newDeque.add(car1);
        newDeque.add(car2);
        newDeque.add(car3);
        newDeque.add(car1);
        newDeque.add(car1);
        newDeque.add(car3);

        assertTrue(newDeque.removeLastOccurrence(car2));
        assertEquals(8, newDeque.size());

        assertTrue(newDeque.removeLastOccurrence(car2));
        assertEquals(7, newDeque.size());

        assertFalse(newDeque.removeLastOccurrence(car2));
        assertEquals(7, newDeque.size());
    }

    @Test
    @DisplayName("removeLastOccurrence() method returns false if element does not exist")
    void testRemoveLastOccurrenceWithNonExistentElement() {
        Car car = new Car("Brand", "Color", 0);
        assertFalse(deque.removeLastOccurrence(car));
    }

    @Test
    @DisplayName("removeLastOccurrence() method returns false if element is null")
    void testRemoveLastOccurrenceWithNullArgument() {
        assertFalse(deque.removeLastOccurrence(null));
    }

    //int size();
    @Test
    @DisplayName("size() method must return correct size of the collection")
    void testSize() {
        assertEquals(50, deque.size());

        Car car = new Car("Toyota", "Red", 50);
        deque.add(car);
        assertEquals(51, deque.size());

        deque.removeFirst();
        deque.removeLast();
        assertEquals(49, deque.size());

        deque.clear();
        assertEquals(0, deque.size());
    }

    //boolean isEmpty();
    @Test
    @DisplayName("isEmpty() method returns false if deque is not empty")
    void testIsEmptyWithNonEmptyDeque() {
        assertFalse(deque.isEmpty());
    }

    @Test
    @DisplayName("isEmpty() method returns true if deque is not empty")
    void testIsEmptyWithEmptyDeque() {
        deque.clear();
        assertTrue(deque.isEmpty());

        MyArrayDeque newDeque = new MyArrayDeque();
        assertTrue(newDeque.isEmpty());
    }

    //void clear();
    @Test
    @DisplayName("clear() method must make every deque empty")
    void testClear() {
        deque.clear();
        assertEquals(0, deque.size());

        MyArrayDeque newDeque = new MyArrayDeque();
        newDeque.clear();
        assertEquals(0, newDeque.size());
    }
}