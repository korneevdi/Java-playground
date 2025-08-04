package array;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArraySortingTest {

    // Arrays for testing
    Integer[] array = {-3, 6, 7, 0, -11, 4, 8, 3, -55, 101, 83, -11, -17};
    Integer[] sortedArray = {-55, -17, -11, -11, -3, 0, 3, 4, 6, 7, 8, 83, 101};
    Integer[] oneElementArray = {-256};
    Integer[] emptyArray = {};


    // bubbleSort(int[] array)
    @Test
    @DisplayName("The bubbleSort() method must sort the array")
    public void testBubbleSortWithRegularArray() {

        ArraySorting.bubbleSort(array);
        for(int i = 0; i < array.length; i++) {
            assertEquals(sortedArray[i], array[i]);
        }
    }

    @Test
    @DisplayName("The bubbleSort() method must leave sorted array untouched")
    public void testBubbleSortWithSortedArray() {

        Integer[] copyArray = sortedArray;
        ArraySorting.bubbleSort(sortedArray);

        for(int i = 0; i < sortedArray.length; i++) {
            assertEquals(copyArray[i], sortedArray[i]);
        }
    }

    @Test
    @DisplayName("The bubbleSort() method must leave array with one element untouched")
    public void testBubbleSortWithArrayOfOneElement() {

        ArraySorting.bubbleSort(oneElementArray);
        assertEquals(-256, oneElementArray[0]);
    }

    @Test
    @DisplayName("The bubbleSort() method must leave empty array untouched")
    public void testBubbleSortWithEmptyArray() {

        ArraySorting.bubbleSort(emptyArray);
        assertTrue(emptyArray.length == 0);
    }


    // selectionSort(int[] array)
    @Test
    @DisplayName("The selectionSort() method must sort the array")
    public void testSelectionSortWithRegularArray() {

        ArraySorting.selectionSort(array);
        for(int i = 0; i < array.length; i++) {
            assertEquals(sortedArray[i], array[i]);
        }
    }

    @Test
    @DisplayName("The selectionSort() method must leave sorted array untouched")
    public void testSelectionSortWithSortedArray() {

        Integer[] copyArray = sortedArray;
        ArraySorting.selectionSort(sortedArray);

        for(int i = 0; i < sortedArray.length; i++) {
            assertEquals(copyArray[i], sortedArray[i]);
        }
    }

    @Test
    @DisplayName("The selectionSort() method must leave array with one element untouched")
    public void testSelectionSortWithArrayOfOneElement() {

        ArraySorting.selectionSort(oneElementArray);
        assertEquals(-256, oneElementArray[0]);
    }

    @Test
    @DisplayName("The selectionSort() method must leave empty array untouched")
    public void testSelectionSortWithEmptyArray() {

        ArraySorting.selectionSort(emptyArray);
        assertTrue(emptyArray.length == 0);
    }


    // insertionSort(int[] array)
    @Test
    @DisplayName("The insertionSort() method must sort the array")
    public void testInsertionSortWithRegularArray() {

        ArraySorting.insertionSort(array);
        for(int i = 0; i < array.length; i++) {
            assertEquals(sortedArray[i], array[i]);
        }
    }

    @Test
    @DisplayName("The insertionSort() method must leave sorted array untouched")
    public void testInsertionSortWithSortedArray() {

        Integer[] copyArray = sortedArray;
        ArraySorting.insertionSort(sortedArray);

        for(int i = 0; i < sortedArray.length; i++) {
            assertEquals(copyArray[i], sortedArray[i]);
        }
    }

    @Test
    @DisplayName("The insertionSort() method must leave array with one element untouched")
    public void testInsertionSortWithArrayOfOneElement() {

        ArraySorting.insertionSort(oneElementArray);
        assertEquals(-256, oneElementArray[0]);
    }

    @Test
    @DisplayName("The insertionSort() method must leave empty array untouched")
    public void testInsertionSortWithEmptyArray() {

        ArraySorting.insertionSort(emptyArray);
        assertTrue(emptyArray.length == 0);
    }


    // mergeSort(int[] array)
    @Test
    @DisplayName("The mergeSort() method must sort the array")
    public void testMergeSortWithRegularArray() {

        ArraySorting.mergeSort(array);
        for(int i = 0; i < array.length; i++) {
            assertEquals(sortedArray[i], array[i]);
        }
    }

    @Test
    @DisplayName("The mergeSort() method must leave sorted array untouched")
    public void testMergeSortWithSortedArray() {

        Integer[] copyArray = sortedArray;
        ArraySorting.mergeSort(sortedArray);

        for(int i = 0; i < sortedArray.length; i++) {
            assertEquals(copyArray[i], sortedArray[i]);
        }
    }

    @Test
    @DisplayName("The mergeSort() method must leave array with one element untouched")
    public void testMergeSortWithArrayOfOneElement() {

        ArraySorting.mergeSort(oneElementArray);
        assertEquals(-256, oneElementArray[0]);
    }

    @Test
    @DisplayName("The mergeSort() method must leave empty array untouched")
    public void testMergeSortWithEmptyArray() {

        ArraySorting.mergeSort(emptyArray);
        assertTrue(emptyArray.length == 0);
    }


    // quickSort(int[] array)
    @Test
    @DisplayName("The quickSort() method must sort the array")
    public void testQuickSortWithRegularArray() {

        ArraySorting.quickSort(array);
        for(int i = 0; i < array.length; i++) {
            assertEquals(sortedArray[i], array[i]);
        }
    }

    @Test
    @DisplayName("The quickSort() method must leave sorted array untouched")
    public void testQuickSortWithSortedArray() {

        Integer[] copyArray = sortedArray;
        ArraySorting.quickSort(sortedArray);

        for(int i = 0; i < sortedArray.length; i++) {
            assertEquals(copyArray[i], sortedArray[i]);
        }
    }

    @Test
    @DisplayName("The quickSort() method must leave array with one element untouched")
    public void testQuickSortWithArrayOfOneElement() {

        ArraySorting.quickSort(oneElementArray);
        assertEquals(-256, oneElementArray[0]);
    }

    @Test
    @DisplayName("The quickSort() method must leave empty array untouched")
    public void testQuickSortWithEmptyArray() {

        ArraySorting.quickSort(emptyArray);
        assertTrue(emptyArray.length == 0);
    }


    // heapSort(int[] array)
    @Test
    @DisplayName("The heapSort() method must sort the array")
    public void testHeapSortWithRegularArray() {

        ArraySorting.heapSort(array);
        for(int i = 0; i < array.length; i++) {
            assertEquals(sortedArray[i], array[i]);
        }
    }

    @Test
    @DisplayName("The heapSort() method must leave sorted array untouched")
    public void testHeapSortWithSortedArray() {

        Integer[] copyArray = sortedArray;
        ArraySorting.heapSort(sortedArray);

        for(int i = 0; i < sortedArray.length; i++) {
            assertEquals(copyArray[i], sortedArray[i]);
        }
    }

    @Test
    @DisplayName("The heapSort() method must leave array with one element untouched")
    public void testHeapSortWithArrayOfOneElement() {

        ArraySorting.heapSort(oneElementArray);
        assertEquals(-256, oneElementArray[0]);
    }

    @Test
    @DisplayName("The heapSort() method must leave empty array untouched")
    public void testHeapSortWithEmptyArray() {

        ArraySorting.heapSort(emptyArray);
        assertTrue(emptyArray.length == 0);
    }
}