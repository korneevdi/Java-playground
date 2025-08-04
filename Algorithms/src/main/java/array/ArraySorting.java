package array;

import java.util.Arrays;

public class ArraySorting {

    // Bubble sorting
    public static <T extends Comparable<T>> void bubbleSort(T [] array) {
        int size = array.length;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if(array[j].compareTo(array[j + 1]) > 0) swap(array, j, j + 1);
            }
        }
    }

    // Selection sorting
    public static <T extends Comparable<T>> void selectionSort(T [] array) {
        for(int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for(int j = i + 1; j < array.length; j++) {
                if(array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(array, i, minIndex);
        }
    }

    // Insertion sorting
    public static <T extends Comparable<T>> void insertionSort(T [] array) {
        for(int i = 1; i < array.length; i++) {
            T temp = array[i];
            int index = -1;
            for(int j = i - 1; j >= 0 && temp.compareTo(array[j]) < 0; j--) {
                array[j + 1] = array[j];
                index = j;
            }
            if(index != -1) array[index] = temp;
        }
    }

    // Merge sorting
    public static <T extends Comparable<T>> void mergeSort(T [] array) {

        if(array.length <= 1) return;                                           // if the array length is less than 2, just return it, since it need not be sorted
                                                                                // if not, then:
        int mid = array.length / 2;                                             // find the middle of the array
        T[] left = Arrays.copyOfRange(array, 0, mid);                      // and split it into two arrays: left part
        T[] right = Arrays.copyOfRange(array, mid, array.length);               // and right part

        mergeSort(left);                                                       // recursively sort left part
        mergeSort(right);                                                      // recursively sort right part

        T[] merged = ArrayMethods.mergeTwoSortedArrays(left, right);           // merge sorted arrays
        System.arraycopy(merged, 0, array, 0, merged.length);     // copy the merged array to 'array'
    }

    // Quick sorting
    public static <T extends Comparable<T>> void quickSort(T [] array) {

        quickSortArray(array, 0, array.length - 1);
    }

    // Heap sorting
    public static <T extends Comparable<T>> void heapSort(T [] array) {

        int n = array.length;
        if(n <= 1) return;

        for(int i = n/2 - 1; i >= 0; i--) {
            heapifyMax(array, n, i);
        }

        for(int i = n - 1; i >= 0; i--) {
            swap(array, i, 0);
            heapifyMax(array, i, 0);
        }
    }

    // Counting sorting
    public static void countingSort(int [] array) {

    }

    // Radix sorting
    public static void radixSort(int [] array) {

    }

    // Bucket sorting
    public static void bucketSort(int [] array) {

    }


    // ### Auxiliary methods ###

    // Method to swap two elements in an array
    private static <T> void swap(T[] array, int index1, int index2) {
        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    private static <T extends Comparable<T>> void quickSortArray(T [] array, int start, int end) {

        if(start >= end) return;

        int pivotIndex = partition(array, start, end);

        quickSortArray(array, start, pivotIndex - 1);
        quickSortArray(array, pivotIndex + 1, end);
    }

    private static <T extends Comparable<T>> int partition(T[] array, int start, int end) {
        T pivot = array[end];
        int i = start - 1;

        for(int j = start; j < end; j++) {
            if(array[j].compareTo(pivot) < 0) swap(array, ++i, j);
        }
        swap(array, i + 1, end);
        return i + 1;
    }

    private static <T extends Comparable<T>> void heapifyMax(T [] array, int size, int rootIndex) {

        int largest = rootIndex;
        int left = largest * 2 + 1;
        int right = largest * 2 + 2;

        if(left < size && array[left].compareTo(array[largest]) > 0) {
            largest = left;
        }

        if(right < size && array[right].compareTo(array[largest]) > 0) {
            largest = right;
        }

        if(largest != rootIndex) {
            swap(array, rootIndex, largest);
            heapifyMax(array, size, largest);
        }
    }

    public static void main(String[] args){
        Integer[] array = {-3, 6, 7, 0, -11, 4, 8, 3, -55, 101, 83, -11, -17};

        ArraySorting.quickSort(array);
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
    }
}
