package collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class MyQueueTest {

    private MyQueue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new MyPriorityQueue<>();
        for(int i = 10; i < 50; i++){
            queue.add(i);
        }
    }

    //void add(E element);
    @Test
    void testAddSingleElement() {
        queue.add(6);
        assertEquals(6, queue.peek());
    }

    @Test
    void testAddMultipleElements() {
        MyPriorityQueue<Integer> newQueue = new MyPriorityQueue<>();
        newQueue.add(7);
        newQueue.add(5);
        newQueue.add(8);

        assertEquals(5, newQueue.poll());
        assertEquals(7, newQueue.poll());
        assertEquals(8, newQueue.poll());
    }

    @Test
    void testAddNullThrowsException() {
        assertThrows(NullPointerException.class, () -> queue.add(null));
    }

    @Test
    void testAddDuplicateElements() {
        MyPriorityQueue<Integer> newQueue = new MyPriorityQueue<>();
        newQueue.add(10);
        newQueue.add(10);
        newQueue.add(10);

        assertEquals(10, newQueue.poll());
        assertEquals(10, newQueue.poll());
        assertEquals(10, newQueue.poll());
    }

    //boolean offer(E element);
    @Test
    void testOfferSingleElement() {
        queue.offer(6);
        assertEquals(6, queue.peek());
    }

    @Test
    void testOfferMultipleElements() {
        MyPriorityQueue<Integer> newQueue = new MyPriorityQueue<>();
        newQueue.offer(7);
        newQueue.offer(5);
        newQueue.offer(8);

        assertEquals(5, newQueue.poll());
        assertEquals(7, newQueue.poll());
        assertEquals(8, newQueue.poll());
    }

    @Test
    void testOfferNullThrowsException() {
        assertFalse(queue.offer(null));
    }

    @Test
    void testOfferDuplicateElements() {
        MyPriorityQueue<Integer> newQueue = new MyPriorityQueue<>();
        newQueue.offer(10);
        newQueue.offer(10);
        newQueue.offer(10);

        assertEquals(10, newQueue.poll());
        assertEquals(10, newQueue.poll());
        assertEquals(10, newQueue.poll());
    }

    //E remove();
    @Test
    void testRemoveSingleElement() {
        assertEquals(10, queue.remove());
    }

    /*@Test
    void testRemoveInPriorityOrder() {
        MyPriorityQueue<Integer> newQueue = new MyPriorityQueue<>();
        newQueue.add(1);
        newQueue.add(2);
        newQueue.add(3);

        assertEquals(1, newQueue.remove());
        assertEquals(2, newQueue.remove());
        assertEquals(3, newQueue.remove());
    }*/

    @Test
    void testRemoveFromEmptyQueueThrows() {
        MyPriorityQueue<Integer> newQueue = new MyPriorityQueue<>();
        assertThrows(NullPointerException.class, newQueue::remove);
    }

    /*@Test
    void testRemoveWithDuplicates() {
        MyPriorityQueue<Integer> newQueue = new MyPriorityQueue<>();
        newQueue.add(1);
        newQueue.add(1);
        newQueue.add(2);

        assertEquals(1, newQueue.remove());
        assertEquals(1, newQueue.remove());
        assertEquals(2, newQueue.remove());
    }*/

    //E poll();
    @Test
    void testPollSingleElement() {
        assertEquals(10, queue.poll());
    }

    @Test
    void testPollInPriorityOrder() {
        MyPriorityQueue<Integer> newQueue = new MyPriorityQueue<>();
        newQueue.add(51);
        newQueue.add(52);
        newQueue.add(53);

        assertEquals(51, newQueue.poll());
        assertEquals(52, newQueue.poll());
        assertEquals(53, newQueue.poll());
    }

    @Test
    void testPollFromEmptyQueueThrows() {
        MyPriorityQueue<Integer> newQueue = new MyPriorityQueue<>();
        assertNull(newQueue.poll());
    }

    @Test
    void testPollWithDuplicates() {
        MyPriorityQueue<Integer> newQueue = new MyPriorityQueue<>();
        newQueue.add(1);
        newQueue.add(1);
        newQueue.add(2);

        assertEquals(1, newQueue.poll());
        assertEquals(1, newQueue.poll());
        assertEquals(2, newQueue.poll());
    }

    //E element();
    @Test
    void testElementSingleElement() {
        assertEquals(10, queue.element());
    }

    @Test
    void testElementInPriorityOrder() {
        queue.add(1);
        queue.add(2);
        queue.add(3);

        assertEquals(1, queue.element());
        assertEquals(1, queue.element());
        assertNotEquals(2, queue.element());
    }

    @Test
    void testElementFromEmptyQueueThrows() {
        MyPriorityQueue<Integer> newQueue = new MyPriorityQueue<>();
        assertThrows(NoSuchElementException.class, newQueue::element);
    }

    //E peek();
    @Test
    void testPeekSingleElement() {
        assertEquals(10, queue.peek());
    }

    @Test
    void testPeekInPriorityOrder() {
        queue.add(1);
        queue.add(2);
        queue.add(3);

        assertEquals(1, queue.peek());
        assertEquals(1, queue.peek());
        assertNotEquals(2, queue.peek());
    }

    @Test
    void testPeekFromEmptyQueueThrows() {
        MyPriorityQueue<Integer> newQueue = new MyPriorityQueue<>();
        assertNull(newQueue.peek());
    }
}