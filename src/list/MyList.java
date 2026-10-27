package list;

/**
 * Implements a singly linked list of integers.
 *
 * The list maintains references to the first node (head)
 * and the last node (tail).
 *
 * Positions are zero-based: the first node is at position 0.
 */
public class MyList {

    private Node head;
    private Node tail;

    /**
     * Creates an empty linked list.
     */
    public MyList() {
        head = null;
        tail = null;
    }

    /**
     * Checks whether the list contains no nodes.
     *
     * @return true if the list is empty; otherwise, false
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the number of nodes in the list.
     *
     * @return the number of nodes
     */
    public int size() {
        int count = 0;
        Node current = head;

        while (current != null) {
            count++;
            current = current.next;
        }

        return count;
    }

    /**
     * Prints all node values from the head to the tail.
     */
    public void traversal() {
        Node current = head;

        while (current != null) {
            System.out.print(current.info + "  ");
            current = current.next;
        }

        System.out.println();
    }

    /**
     * Adds a new node to the beginning of the list.
     *
     * @param x the value to add
     */
    public void addFirst(int x) {
        Node newNode = new Node(x);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    /**
     * Adds a new node to the end of the list.
     *
     * @param x the value to add
     */
    public void addLast(int x) {
        Node newNode = new Node(x);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    /**
     * Inserts a new node at the specified zero-based position.
     *
     * Position 0 inserts at the beginning.
     * Position size() inserts at the end.
     *
     * @param index the position at which to insert
     * @param x the value to insert
     * @return true if insertion succeeds; otherwise, false
     */
    public boolean insertAt(int index, int x) {
        if (index < 0 || index > size()) {
            return false;
        }

        if (index == 0) {
            addFirst(x);
            return true;
        }

        if (index == size()) {
            addLast(x);
            return true;
        }

        Node previous = get(index - 1);
        Node newNode = new Node(x);

        newNode.next = previous.next;
        previous.next = newNode;

        return true;
    }

    /**
     * Inserts a new node immediately before the first node
     * containing the specified value.
     *
     * @param targetValue the value to find
     * @param newValue the value to insert
     * @return true if the target is found; otherwise, false
     */
    public boolean insertBefore(int targetValue, int newValue) {
        if (isEmpty()) {
            return false;
        }

        if (head.info == targetValue) {
            addFirst(newValue);
            return true;
        }

        Node previous = head;

        while (previous.next != null) {
            if (previous.next.info == targetValue) {
                Node newNode = new Node(newValue);

                newNode.next = previous.next;
                previous.next = newNode;

                return true;
            }

            previous = previous.next;
        }

        return false;
    }

    /**
     * Inserts a new node immediately after the specified position.
     *
     * @param index the position of the existing node
     * @param x the value to insert
     * @return true if insertion succeeds; otherwise, false
     */
    public boolean insertAfter(int index, int x) {
        Node current = get(index);

        if (current == null) {
            return false;
        }

        Node newNode = new Node(x);
        newNode.next = current.next;
        current.next = newNode;

        if (current == tail) {
            tail = newNode;
        }

        return true;
    }

    /**
     * Returns the node at the specified zero-based position.
     *
     * @param index the position to retrieve
     * @return the node at the position, or null if invalid
     */
    public Node get(int index) {
        if (index < 0) {
            return null;
        }

        Node current = head;
        int position = 0;

        while (current != null && position < index) {
            current = current.next;
            position++;
        }

        return current;
    }

    /**
     * Searches for the first node containing the specified value.
     *
     * @param x the value to search for
     * @return the first matching node, or null if not found
     */
    public Node search(int x) {
        Node current = head;

        while (current != null) {
            if (current.info == x) {
                return current;
            }

            current = current.next;
        }

        return null;
    }

    /**
     * Returns the value at the specified position.
     *
     * @param index the zero-based position
     * @return the value at the position
     * @throws IndexOutOfBoundsException if the position is invalid
     */
    public int getValue(int index) {
        Node current = get(index);

        if (current == null) {
            throw new IndexOutOfBoundsException(
                    "Invalid list position: " + index);
        }

        return current.info;
    }

    /**
     * Updates the value of the node at the specified position.
     *
     * @param index the zero-based position
     * @param newValue the replacement value
     * @return true if the node exists and is updated
     */
    public boolean updateAt(int index, int newValue) {
        Node current = get(index);

        if (current == null) {
            return false;
        }

        current.info = newValue;
        return true;
    }

    /**
     * Removes the first node containing the specified value.
     *
     * @param x the value to remove
     * @return true if a node is removed; otherwise, false
     */
    public boolean removeValue(int x) {
        Node target = search(x);

        if (target == null) {
            return false;
        }

        remove(target);
        return true;
    }

    /**
     * Removes the node at the specified zero-based position.
     *
     * @param index the position to remove
     * @return true if a node is removed; otherwise, false
     */
    public boolean removeAt(int index) {
        Node target = get(index);

        if (target == null) {
            return false;
        }

        remove(target);
        return true;
    }

    /**
     * Removes the specified node if it belongs to this list.
     *
     * @param target the node to remove
     */
    public void remove(Node target) {
        if (target == null || head == null) {
            return;
        }

        // Handle removal of the first node.
        if (head == target) {
            head = head.next;

            if (head == null) {
                tail = null;
            }

            target.next = null;
            return;
        }

        // Find the node immediately before the target.
        Node previous = head;

        while (previous.next != null && previous.next != target) {
            previous = previous.next;
        }

        // Stop if the target does not belong to this list.
        if (previous.next == null) {
            return;
        }

        // Bypass the target node.
        previous.next = target.next;

        // Update the tail if the last node is removed.
        if (target == tail) {
            tail = previous;
        }

        target.next = null;
    }

    /**
     * Removes every node whose value is less than the given value.
     *
     * @param x the comparison value
     * @return the number of nodes removed
     */
    public int removeAllLessThan(int x) {
        int removedCount = 0;

        // Remove matching nodes from the beginning.
        while (head != null && head.info < x) {
            Node removed = head;
            head = head.next;
            removed.next = null;
            removedCount++;
        }

        if (head == null) {
            tail = null;
            return removedCount;
        }

        // Remove matching nodes from the remaining list.
        Node current = head;

        while (current.next != null) {
            if (current.next.info < x) {
                Node removed = current.next;
                current.next = removed.next;
                removed.next = null;
                removedCount++;
            } else {
                current = current.next;
            }
        }

        // The last remaining node becomes the tail.
        tail = current;

        return removedCount;
    }

    /**
     * Removes the second node whose value is less than x.
     *
     * This preserves the behavior of the original removeAfter method.
     *
     * @param x the comparison value
     * @return true if a node is removed; otherwise, false
     */
    public boolean removeAfter(int x) {
        int count = 0;
        Node current = head;

        while (current != null) {
            if (current.info < x) {
                count++;

                if (count == 2) {
                    remove(current);
                    return true;
                }
            }

            current = current.next;
        }

        return false;
    }

    /**
     * Returns the minimum value in the list.
     *
     * @return the smallest value
     * @throws IllegalStateException if the list is empty
     */
    public int min() {
        if (isEmpty()) {
            throw new IllegalStateException(
                    "Cannot find the minimum of an empty list.");
        }

        int minimum = head.info;
        Node current = head.next;

        while (current != null) {
            if (current.info < minimum) {
                minimum = current.info;
            }

            current = current.next;
        }

        return minimum;
    }

    /**
     * Returns the maximum value in the list.
     *
     * @return the largest value
     * @throws IllegalStateException if the list is empty
     */
    public int max() {
        if (isEmpty()) {
            throw new IllegalStateException(
                    "Cannot find the maximum of an empty list.");
        }

        int maximum = head.info;
        Node current = head.next;

        while (current != null) {
            if (current.info > maximum) {
                maximum = current.info;
            }

            current = current.next;
        }

        return maximum;
    }

    /**
     * Swaps the values of the first minimum node and the first
     * maximum node in the list.
     *
     * If multiple nodes contain the same minimum or maximum,
     * the first occurrence is used.
     *
     * @return true if the operation succeeds; otherwise, false
     */
    public boolean swapMinMax() {
        if (isEmpty()) {
            return false;
        }

        Node minNode = head;
        Node maxNode = head;
        Node current = head.next;

        // Find the first nodes containing the minimum and maximum.
        while (current != null) {
            if (current.info < minNode.info) {
                minNode = current;
            }

            if (current.info > maxNode.info) {
                maxNode = current;
            }

            current = current.next;
        }

        // Exchange their values without changing node links.
        int temp = minNode.info;
        minNode.info = maxNode.info;
        maxNode.info = temp;

        return true;
    }

    /**
     * Sorts the entire list in ascending order.
     *
     * Node links remain unchanged; only node values are exchanged.
     * Time complexity: O(n^2).
     */
    public void sort() {
        sortRange(0, size() - 1);
    }

    /**
     * Sorts a section of the list in ascending order.
     * Both startIndex and endIndex are included.
     *
     * Only values in the selected section are changed.
     *
     * @param startIndex the first position in the section
     * @param endIndex the last position in the section
     * @return true if the range is valid; otherwise, false
     */
    public boolean sortRange(int startIndex, int endIndex) {
        int length = size();

        if (startIndex < 0 || endIndex < startIndex
                || endIndex >= length) {
            return false;
        }

        // Compare each value with the remaining values in the range.
        for (int i = startIndex; i < endIndex; i++) {
            Node current = get(i);
            Node nextNode = current.next;

            for (int j = i + 1; j <= endIndex; j++) {
                if (current.info > nextNode.info) {
                    int temp = current.info;
                    current.info = nextNode.info;
                    nextNode.info = temp;
                }

                nextNode = nextNode.next;
            }
        }

        return true;
    }

    /**
     * Sorts the first three nodes in ascending order.
     *
     * If the list contains fewer than three nodes, it remains unchanged.
     */
    public void sortFirstThree() {
        if (size() < 3) {
            return;
        }

        sortRange(0, 2);
    }

    /**
     * Reverses the linked list by changing node links.
     *
     * Time complexity: O(n).
     * Auxiliary space complexity: O(1).
     */
    public void reverse() {
        Node previous = null;
        Node current = head;
        Node nextNode;

        // The old head becomes the new tail.
        tail = head;

        // Reverse each link to point to the previous node.
        while (current != null) {
            nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }

        // The old tail becomes the new head.
        head = previous;
    }

    /**
     * Appends copies of all values from another list.
     *
     * The source list remains unchanged, and the two lists
     * do not share nodes after the operation.
     *
     * @param other the list whose values will be appended
     * @return true if the operation succeeds; false if other is null
     *         or refers to this same list
     */
    public boolean appendAll(MyList other) {
        // Reject null and self-appending to prevent an infinite loop.
        if (other == null || other == this) {
            return false;
        }

        // Copy values instead of sharing nodes between lists.
        Node current = other.head;

        while (current != null) {
            addLast(current.info);
            current = current.next;
        }

        return true;
    }

    /**
     * Removes all nodes and resets the list to an empty state.
     */
    public void clear() {
        Node current = head;

        // Detach each node from the list.
        while (current != null) {
            Node nextNode = current.next;
            current.next = null;
            current = nextNode;
        }

        head = null;
        tail = null;
    }
}
