package tree;

/**
 * Represents a node in a binary tree.
 *
 * Each node contains an integer value and references
 * to its left and right child nodes.
 */
public class Node {

    int info; // The value stored in this node.
    Node left; // Reference to the left child.
    Node right; // Reference to the right child.

    /**
     * Creates a node with a value and two child nodes.
     *
     * @param info the value stored in the node
     * @param left the left child node
     * @param right the right child node
     */
    public Node(int info, Node left, Node right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

    /**
     * Creates a node with no child nodes.
     *
     * @param info the value stored in the node
     */
    public Node(int info) {
        this(info, null, null);
    }
}
