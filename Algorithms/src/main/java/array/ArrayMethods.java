package array;

public class ArrayMethods {

    public static <T extends Comparable<T>> T[] mergeTwoSortedArrays(T[] arr1, T[] arr2) {

        if(!isSortedAcs(arr1) && !isSortedAcs(arr2)) {
            throw new IllegalArgumentException(
                    "Arrays must be sorted in ascending order\nBoth arrays is not sorted in ascending order");
        }

        if(!isSortedAcs(arr1)) {
            throw new IllegalArgumentException(
                    "Arrays must be sorted in ascending order\nFirst array is not sorted in ascending order");
        }

        if(!isSortedAcs(arr2)) {
            throw new IllegalArgumentException(
                    "Arrays must be sorted in ascending order\nSecond array is not sorted in ascending order");
        }

        Class<?> type = arr1.getClass().getComponentType();

        T[] result = (T[]) java.lang.reflect.Array.newInstance(type, arr1.length + arr2.length);

        int i = 0, j = 0, k = 0;
        while (i < arr1.length && j < arr2.length) {
            if (arr1[i].compareTo(arr2[j]) < 0) {
                result[k++] = arr1[i++];
            } else {
                result[k++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            result[k++] = arr1[i++];
        }

        while (j < arr2.length) {
            result[k++] = arr2[j++];
        }

        return result;
    }

    // Checks whether an array is sorted in ascending order
    public static <T extends Comparable<T>> boolean isSortedAcs(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if(array[i].compareTo(array[i + 1]) > 0) return false;
        }
        return true;
    }

    // Checks whether an array is sorted in descending order
    public static <T extends Comparable<T>> boolean isSortedDes(T[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if(array[i].compareTo(array[i + 1]) < 0) return false;
        }
        return true;
    }
}
