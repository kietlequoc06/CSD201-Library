
package sorting;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Provides common sorting algorithms for integer arrays.
 * All sorting methods arrange elements in ascending order.
 */
public class Sorting {

    /**
     * Sorts the entire array using Merge Sort.
     * A temporary array is created once and reused during merging.
     *
     * Time complexity: O(n log n).
     * Space complexity: O(n).
     *
     * @param array the array to sort
     */
    public void mergeSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        int[] temporary = new int[array.length];
        mergeSort(array, temporary, 0, array.length - 1);
    }

    /**
     * Recursively divides the array into smaller sections,
     * sorts each section, and merges the sorted sections.
     *
     * @param array the original array
     * @param temporary the reusable temporary array
     * @param low the first index of the current section
     * @param high the last index of the current section
     */
    private void mergeSort(
            int[] array, int[] temporary, int low, int high) {

        if (low >= high) {
            return;
        }

        // Calculate the middle index without risking integer overflow.
        int mid = low + (high - low) / 2;

        // Sort the left and right halves recursively.
        mergeSort(array, temporary, low, mid);
        mergeSort(array, temporary, mid + 1, high);

        // Combine the two sorted halves.
        merge(array, temporary, low, mid, high);
    }

    /**
     * Merges two sorted sections into one sorted section.
     * The first section is [low, mid] and the second is [mid + 1, high].
     *
     * @param array the array being sorted
     * @param temporary the reusable temporary array
     * @param low the first index of the left section
     * @param mid the last index of the left section
     * @param high the last index of the right section
     */
    private void merge(
            int[] array, int[] temporary,
            int low, int mid, int high) {

        int left = low;
        int right = mid + 1;
        int current = low;

        // Compare elements from both sections and copy the smaller one.
        while (left <= mid && right <= high) {
            if (array[left] <= array[right]) {
                temporary[current++] = array[left++];
            } else {
                temporary[current++] = array[right++];
            }
        }

        // Copy any remaining elements from the left section.
        while (left <= mid) {
            temporary[current++] = array[left++];
        }

        // Copy any remaining elements from the right section.
        while (right <= high) {
            temporary[current++] = array[right++];
        }

        // Copy the merged section back into the original array.
        for (int i = low; i <= high; i++) {
            array[i] = temporary[i];
        }
    }

    /**
     * Sorts the entire array using Quick Sort.
     *
     * Average time complexity: O(n log n).
     * Worst-case time complexity: O(n^2).
     *
     * @param array the array to sort
     */
    public void quickSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        quickSort(array, 0, array.length - 1);
    }

    /**
     * Recursively sorts the sections before and after the pivot.
     *
     * @param array the array being sorted
     * @param low the first index of the current section
     * @param high the last index of the current section
     */
    private void quickSort(int[] array, int low, int high) {
        // A section with zero or one element is already sorted.
        if (low >= high) {
            return;
        }

        int pivotIndex = partition(array, low, high);

        // Sort the elements on each side of the pivot.
        quickSort(array, low, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, high);
    }

    /**
     * Places the first element in its correct partition position.
     * Elements smaller than or equal to the pivot go to its left;
     * larger elements go to its right.
     *
     * @param array the array being partitioned
     * @param low the first index of the current section
     * @param high the last index of the current section
     * @return the final index of the pivot
     */
    private int partition(int[] array, int low, int high) {
        int pivot = array[low];
        int left = low;
        int right = high;

        while (left < right) {

            // Find an element greater than the pivot from the left.
            while (left < high && array[left] <= pivot) {
                left++;
            }

            // Find an element smaller than the pivot from the right.
            while (right > low && array[right] >= pivot) {
                right--;
            }

            // Exchange misplaced elements while the pointers have not crossed.
            if (left < right) {
                swap(array, left, right);
            }
        }

        // Place the pivot between the two partitions.
        swap(array, low, right);

        return right;
    }

    /**
     * Sorts the array using Selection Sort.
     * Time complexity: O(n^2).
     *
     * @param array the array to sort
     */
    public void selectionSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        for (int i = 0; i < array.length - 1; i++) {
            // Assume the current position contains the minimum element.
            int minIndex = i;

            // Find the smallest element in the unsorted section.
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // Move the smallest element to the current position.
            if (minIndex != i) {
                swap(array, i, minIndex);
            }
        }
    }

    /**
     * Sorts the array using Insertion Sort.
     * Time complexity: O(n^2) in the average and worst cases.
     *
     * @param array the array to sort
     */
    public void insertionSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        for (int i = 1; i < array.length; i++) {
            // Store the element that must be inserted into the sorted section.
            int key = array[i];
            int j = i - 1;

            // Shift elements greater than the key one position to the right.
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }

            // Insert the key into its correct position.
            array[j + 1] = key;
        }
    }

    /**
     * Sorts the array using Bubble Sort.
     * Time complexity: O(n^2) in the average and worst cases.
     * An early-stop check improves performance when the array is sorted.
     *
     * @param array the array to sort
     */
    public void bubbleSort(int[] array) {
        if (array == null || array.length < 2) {
            return;
        }

        for (int end = array.length - 1; end > 0; end--) {
            boolean swapped = false;

            // Compare adjacent elements in the unsorted section.
            for (int i = 0; i < end; i++) {
                if (array[i] > array[i + 1]) {
                    swap(array, i, i + 1);
                    swapped = true;
                }
            }

            // Stop early if no elements needed to be exchanged.
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Exchanges the elements at two positions in the array.
     *
     * @param array the array containing the elements
     * @param firstIndex the index of the first element
     * @param secondIndex the index of the second element
     */
    public void swap(int[] array, int firstIndex, int secondIndex) {
        int temporary = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temporary;
    }

    /**
     * Saves all array elements to a text file.
     * Each element is written on a separate line.
     *
     * @param array the array whose elements will be saved
     * @param fileName the destination file name
     * @throws IOException if an error occurs while writing the file
     */
    public void saveToFile(int[] array, String fileName)
            throws IOException {

        if (array == null) {
            throw new IllegalArgumentException("Array must not be null.");
        }

        // Automatically close the writer even if an error occurs.
        try (BufferedWriter writer =
                new BufferedWriter(new FileWriter(fileName))) {

            for (int value : array) {
                writer.write(String.valueOf(value));
                writer.newLine();
            }
        }
    }
}
