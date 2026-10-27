
package list;

import java.util.Scanner;

/**
 * Demonstrates the operations supported by MyList.
 *
 * Users can select operations from a menu to add, search,
 * update, remove, reverse, sort, and inspect linked-list nodes.
 */
public class ListDemo {

    /**
     * Displays the menu and processes the user's choices.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MyList list = new MyList();
        int choice;

        do {
            displayMenu();
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a node at the beginning.
                    System.out.print("Enter value: ");
                    list.addFirst(scanner.nextInt());
                    displayList(list);
                    break;

                case 2:
                    // Add a node at the end.
                    System.out.print("Enter value: ");
                    list.addLast(scanner.nextInt());
                    displayList(list);
                    break;

                case 3:
                    // Display all nodes.
                    displayList(list);
                    break;

                case 4:
                    // Display the number of nodes.
                    System.out.println("List size: " + list.size());
                    break;

                case 5:
                    // Retrieve a node by its zero-based index.
                    System.out.print("Enter index (starting from 0): ");
                    Node node = list.get(scanner.nextInt());

                    if (node == null) {
                        System.out.println("Invalid index or node not found.");
                    } else {
                        System.out.println("Node value: " + node.info);
                    }
                    break;

                case 6:
                    // Search for the first node containing a value.
                    System.out.print("Enter value to search: ");
                    node = list.search(scanner.nextInt());

                    if (node == null) {
                        System.out.println("Value not found.");
                    } else {
                        System.out.println("Value found: " + node.info);
                    }
                    break;

                case 7:
                    // Sort the entire list in ascending order.
                    list.sort();
                    System.out.println("List sorted in ascending order.");
                    displayList(list);
                    break;

                case 8:
                    // Remove the first occurrence of a value.
                    System.out.print("Enter value to remove: ");
                    int valueToRemove = scanner.nextInt();

                    if (list.removeValue(valueToRemove)) {
                        System.out.println("Value removed successfully.");
                    } else {
                        System.out.println("Value not found.");
                    }

                    displayList(list);
                    break;

                case 9:
                    // Reverse the links between nodes.
                    list.reverse();
                    System.out.println("List reversed.");
                    displayList(list);
                    break;

                case 10:
                    // Remove the second node whose value is less than x.
                    System.out.print("Enter x: ");
                    int x = scanner.nextInt();

                    if (list.removeAfter(x)) {
                        System.out.println("The second matching node was removed.");
                    } else {
                        System.out.println("Fewer than two matching nodes were found.");
                    }

                    displayList(list);
                    break;

                case 11:
                    // Insert a value at a specified position.
                    System.out.print("Enter index: ");
                    int insertIndex = scanner.nextInt();

                    System.out.print("Enter value: ");
                    int insertValue = scanner.nextInt();

                    if (list.insertAt(insertIndex, insertValue)) {
                        System.out.println("Node inserted successfully.");
                    } else {
                        System.out.println("Invalid index.");
                    }

                    displayList(list);
                    break;

                case 12:
                    // Insert a value before the first matching value.
                    System.out.print("Enter the target value: ");
                    int beforeTarget = scanner.nextInt();

                    System.out.print("Enter the new value: ");
                    int beforeValue = scanner.nextInt();

                    if (list.insertBefore(beforeTarget, beforeValue)) {
                        System.out.println("Node inserted successfully.");
                    } else {
                        System.out.println("Target value not found.");
                    }

                    displayList(list);
                    break;

                case 13:
                    // Insert a value after a specified node position.
                    System.out.print("Enter the existing node index: ");
                    int afterIndex = scanner.nextInt();

                    System.out.print("Enter the new value: ");
                    int afterValue = scanner.nextInt();

                    if (list.insertAfter(afterIndex, afterValue)) {
                        System.out.println("Node inserted successfully.");
                    } else {
                        System.out.println("Invalid index.");
                    }

                    displayList(list);
                    break;

                case 14:
                    // Remove a node at a specified position.
                    System.out.print("Enter index to remove: ");
                    int removeIndex = scanner.nextInt();

                    if (list.removeAt(removeIndex)) {
                        System.out.println("Node removed successfully.");
                    } else {
                        System.out.println("Invalid index.");
                    }

                    displayList(list);
                    break;

                case 15:
                    // Update the value of a node at a specified position.
                    System.out.print("Enter index to update: ");
                    int updateIndex = scanner.nextInt();

                    System.out.print("Enter the new value: ");
                    int newValue = scanner.nextInt();

                    if (list.updateAt(updateIndex, newValue)) {
                        System.out.println("Node updated successfully.");
                    } else {
                        System.out.println("Invalid index.");
                    }

                    displayList(list);
                    break;

                case 16:
                    // Display the minimum and maximum values.
                    if (list.isEmpty()) {
                        System.out.println("The list is empty.");
                    } else {
                        System.out.println("Minimum value: " + list.min());
                        System.out.println("Maximum value: " + list.max());
                    }
                    break;

                case 17:
                    // Remove all nodes whose values are less than x.
                    System.out.print("Enter x: ");
                    int threshold = scanner.nextInt();

                    int removedCount = list.removeAllLessThan(threshold);

                    System.out.println("Number of nodes removed: " + removedCount);
                    displayList(list);
                    break;

                case 18:
                    // Swap the values of the first minimum and maximum nodes.
                    if (list.swapMinMax()) {
                        System.out.println("Minimum and maximum values swapped.");
                    } else {
                        System.out.println("The list is empty.");
                    }

                    displayList(list);
                    break;

                case 19:
                    // Sort a selected section of the list.
                    System.out.print("Enter start index: ");
                    int startIndex = scanner.nextInt();

                    System.out.print("Enter end index: ");
                    int endIndex = scanner.nextInt();

                    if (list.sortRange(startIndex, endIndex)) {
                        System.out.println("Selected section sorted.");
                    } else {
                        System.out.println("Invalid range.");
                    }

                    displayList(list);
                    break;

                case 20:
                    // Sort the first three nodes in ascending order.
                    list.sortFirstThree();
                    System.out.println("The first three nodes were sorted if available.");
                    displayList(list);
                    break;

                case 21:
                    // Append copies of another list to the current list.
                    MyList otherList = new MyList();

                    System.out.print("How many values should the second list contain? ");
                    int count = scanner.nextInt();

                    if (count < 0) {
                        System.out.println("The number of values cannot be negative.");
                        break;
                    }

                    for (int i = 0; i < count; i++) {
                        System.out.print("Enter value " + (i + 1) + ": ");
                        otherList.addLast(scanner.nextInt());
                    }

                    list.appendAll(otherList);
                    System.out.println("The second list was appended.");
                    displayList(list);
                    break;

                case 22:
                    // Remove every node and reset the list.
                    list.clear();
                    System.out.println("List cleared.");
                    displayList(list);
                    break;

                case 0:
                    // Exit the application.
                    System.out.println("Exiting Linked List Demo.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 0);

        // Close the scanner after the user exits the menu.
        scanner.close();
    }

    /**
     * Displays all available linked-list operations.
     */
    private static void displayMenu() {
        System.out.println("\n========== LINKED LIST DEMO ==========");
        System.out.println(" 1. Add a node at the beginning");
        System.out.println(" 2. Add a node at the end");
        System.out.println(" 3. Display the list");
        System.out.println(" 4. Get the list size");
        System.out.println(" 5. Get a node by index");
        System.out.println(" 6. Search for a value");
        System.out.println(" 7. Sort the entire list");
        System.out.println(" 8. Remove the first node with a value");
        System.out.println(" 9. Reverse the list");
        System.out.println("10. Remove the second node with value < x");
        System.out.println("11. Insert a node at an index");
        System.out.println("12. Insert before a value");
        System.out.println("13. Insert after an index");
        System.out.println("14. Remove a node by index");
        System.out.println("15. Update a node by index");
        System.out.println("16. Find minimum and maximum");
        System.out.println("17. Remove all nodes with value < x");
        System.out.println("18. Swap minimum and maximum values");
        System.out.println("19. Sort a section of the list");
        System.out.println("20. Sort the first three nodes");
        System.out.println("21. Append another list");
        System.out.println("22. Clear the list");
        System.out.println(" 0. Exit");
        System.out.println("======================================");
    }

    /**
     * Displays the list and its current size.
     *
     * @param list the linked list to display
     */
    private static void displayList(MyList list) {
        System.out.print("Current list: ");
        list.traversal();
        System.out.println("Size: " + list.size());
    }
}
