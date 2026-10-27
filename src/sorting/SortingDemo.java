package sorting;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

/**
 * Demonstrates the sorting algorithms provided by Sorting.
 */
public class SortingDemo {

    public static void main(String[] args) throws IOException {

        // Create an array containing 104 random integers.
        int size = 104;
        int[] numbers = new int[size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            numbers[i] = random.nextInt(size);
        }

        // Display the original array before sorting.
        System.out.println("Original array:");
        System.out.println(Arrays.toString(numbers));

        // Create the sorting utility.
        Sorting sorting = new Sorting();

        // Select one sorting algorithm to test.
        long startTime = System.nanoTime();

        sorting.mergeSort(numbers);
        // sorting.quickSort(numbers);
        // sorting.selectionSort(numbers);
        // sorting.insertionSort(numbers);
        // sorting.bubbleSort(numbers);

        long endTime = System.nanoTime();

        // Convert the elapsed time from nanoseconds to milliseconds.
        double elapsedMilliseconds
                = (endTime - startTime) / 1_000_000.0;

        System.out.println("\nSorted array:");
        System.out.println(Arrays.toString(numbers));

        System.out.printf(
                "\nTime consumed: %.3f ms%n",
                elapsedMilliseconds
        );

        // Save the sorted array to data.txt in the working directory.
        sorting.saveToFile(numbers, "data.txt");

        System.out.println("Sorted data saved to data.txt.");
    }
}
