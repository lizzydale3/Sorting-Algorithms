import java.util.Arrays;
import java.util.Random;

public class QuickSortAnalysis {

    // Constant variable for array size
    private static final int SIZE = 1000;

    public static void main(String[] args) {
        // Create arrays for sorted, reverse-ordered, and random input
        int[] sortedInput = createSortedArray(SIZE);
        int[] reverseInput = createReverseArray(SIZE);
        int[] randomInput = createRandomArray(SIZE);
        
        // Test and print timing for sorting each type of array
        testing("Sorted Array", sortedInput);
        testing("Reverse-Ordered Array", reverseInput);
        testing("Random Array", randomInput);
    }

    // Method to test and print timing for sorting an array
    private static void testing(String arrayType, int[] inputArray) {
        long startTime = System.nanoTime();
        quickSort(inputArray, 0, inputArray.length - 1);
        long endTime = System.nanoTime();

        // Print the result with an explanation comment
        System.out.println(arrayType + ": " + (endTime - startTime) + " nanoseconds");
    }

    // Quicksort algorithm
    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            // Partition the array and get the partition index
            int partitionIndex = median3(arr, left, right);
            
            // Recursively sort the subarrays on both sides of the partition
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
    }

    // Helper method to perform partitioning using median-of-three pivot
    private static int median3(int[] arr, int left, int right) {
        // Choose the last element as the pivot
        int pivot = arr[right];
        int i = left - 1;

        // Iterate through the array to partition elements around the pivot
        for (int j = left; j < right; j++) {
            if (arr[j] < pivot) {
                i++;

                // Swap elements if smaller than the pivot
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap the pivot to its correct position in the array
        int temp = arr[i + 1];
        arr[i + 1] = arr[right];
        arr[right] = temp;

        // Return the index where the pivot is now placed
        return i + 1;
    }

    // Helper method to create a sorted array
    private static int[] createSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }

    // Helper method to create a reverse-ordered array
    private static int[] createReverseArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = size - i;
        }
        return arr;
    }

    // Helper method to create a random array
    private static int[] createRandomArray(int size) {
        int[] arr = new int[size];
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size);
        }
        return arr;
    }
}
