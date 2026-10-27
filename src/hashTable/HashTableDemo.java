
package hashTable;

/**
 * Demonstrates the basic operations of MyHashTable.
 */
public class HashTableDemo {

    public static void main(String[] args) {

        // Create a hash table with 10 buckets.
        MyHashTable table = new MyHashTable();

        // Sample values to insert into the hash table.
        int[] values = {2, 4, 12, 21, 7, 9, 0, 72, 11, 17, 22};

        // Insert every value into its corresponding bucket.
        for (int x : values) {
            table.add(x);
        }

        // Display all non-empty buckets.
        System.out.println("Hash table:");
        table.traversal();

        // Search for a value that exists in the table.
        System.out.println("\nSearch 12: " + table.search(12));

        // Search for a value that does not exist in the table.
        System.out.println("Search 99: " + table.search(99));

        // Remove a value and display whether the operation succeeded.
        System.out.println("\nRemove 12: " + table.remove(12));

        // Search again to confirm that the value has been removed.
        System.out.println("Search 12 after removal: " + table.search(12));
    }
}
