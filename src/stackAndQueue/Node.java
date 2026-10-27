package stackAndQueue;

/**
 * Represents a node in a linked data structure.
 *
 * Each node stores a value and a reference to the next node.
 */
public class Node {

    Object info; // The value stored in this node.
    Node next;   // Reference to the next node.

    /**
     * Creates a node with a value and a reference to the next node.
     *
     * @param info the value stored in the node
     * @param next the next node
     */
    Node(Object info, Node next) {
        this.info = info;
        this.next = next;
    }

    /**
     * Creates a node with a value and no next node.
     *
     * @param info the value stored in the node
     */
    Node(Object info) {
        this(info, null);
    }
}
