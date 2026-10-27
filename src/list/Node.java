package list;

/**
 * Represents a node in a singly linked list.
 *
 * Each node contains:
 * - info: the integer value stored in the node.
 * - next: a reference to the next node in the list.
 */
public class Node {

    int info; // The data stored in this node.
    Node next; // A reference to the next node.

    /**
     * Creates a node with the specified data and next node.
     *
     * @param info the integer value stored in this node
     * @param next the reference to the next node, or null if there is no next node
     */
    Node(int info, Node next) {
        this.info = info;
        this.next = next;
    }

    /**
     * Creates a node containing the specified data.
     * The next reference is initialized to null.
     *
     * @param info the integer value stored in this node
     */
    Node(int info) {
        this(info, null);
    }
}
