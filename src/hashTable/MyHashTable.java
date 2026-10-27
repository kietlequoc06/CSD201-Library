
package hashTable;

import java.util.ArrayList;

/**
 * Implements a hash table using separate chaining.
 *
 * Each bucket is an ArrayList that stores values with the same hash index.
 * The hash function is h(x) = floorMod(x, M).
 */
public class MyHashTable {

    // The number of buckets in the hash table.
    private final int M = 10;

    // Each array element stores a list of values in one bucket.
    private ArrayList<Integer>[] buckets;

    /**
     * Creates a hash table with M empty buckets.
     */
    @SuppressWarnings("unchecked")
    public MyHashTable() {
        // Java does not allow direct creation of generic arrays.
        buckets = new ArrayList[M];

        // Initialize every bucket to an empty list.
        for (int i = 0; i < M; i++) {
            buckets[i] = new ArrayList<>();
        }
    }

    /**
     * Calculates the bucket index for a value.
     *
     * Math.floorMod() also handles negative values correctly,
     * ensuring the result is between 0 and M - 1.
     *
     * @param x the value to hash
     * @return the corresponding bucket index
     */
    private int hash(int x) {
        return Math.floorMod(x, M);
    }

    /**
     * Adds a value to its corresponding bucket.
     *
     * Duplicate values are allowed.
     *
     * @param x the value to add
     */
    public void add(int x) {
        int index = hash(x);

        // Store the value in the bucket determined by the hash function.
        buckets[index].add(x);
    }

    /**
     * Prints all non-empty buckets.
     *
     * The bucket index is printed before its stored values.
     */
    public void traversal() {
        for (int i = 0; i < M; i++) {

            // Skip buckets that do not contain any values.
            if (!buckets[i].isEmpty()) {
                System.out.println(i + ": " + buckets[i]);
            }
        }
    }

    /**
     * Searches for a value in its corresponding bucket.
     *
     * @param x the value to search for
     * @return the value if found, or -1 if not found
     *
     * Note: Because -1 can also be stored in the table,
     * this return value cannot always distinguish the two cases.
     */
    public int search(int x) {
        int index = hash(x);

        // Search only the bucket associated with the value.
        int position = buckets[index].indexOf(x);

        if (position != -1) {
            // Return the value at the position where it was found.
            return buckets[index].get(position);
        }

        // Return -1 when the value does not exist.
        return -1;
    }

    /**
     * Removes one occurrence of a value from the hash table.
     *
     * If duplicate values exist, only the first matching occurrence
     * in the corresponding bucket is removed.
     *
     * @param x the value to remove
     * @return true if a value was removed; otherwise, false
     */
    public boolean remove(int x) {
        int index = hash(x);

        // Convert x to Integer so ArrayList removes the value,
        // rather than treating x as an element index.
        return buckets[index].remove(Integer.valueOf(x));
    }
}
