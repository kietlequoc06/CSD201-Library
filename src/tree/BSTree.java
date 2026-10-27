package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Implements a Binary Search Tree (BST).
 *
 * For every node:
 * - Values in the left subtree are smaller.
 * - Values in the right subtree are greater.
 * Duplicate values are not allowed.
 */
public class BSTree {

    Node root;

    /**
     * Creates an empty binary search tree.
     */
    public BSTree() {
        root = null;
    }

    /**
     * Checks whether the tree is empty.
     *
     * @return true if the tree is empty
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Prints the value stored in a node.
     *
     * @param node the node to print
     */
    public void visit(Node node) {
        if (node != null) {
            System.out.print(node.info + "  ");
        }
    }

    /**
     * Searches for a key in the tree.
     *
     * @param key the value to search for
     * @return the matching node, or null if not found
     */
    public Node search(int key) {
        Node current = root;

        while (current != null) {
            if (current.info == key) {
                return current;
            }

            if (key < current.info) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    /**
     * Inserts a value into the tree.
     * Duplicate values are ignored.
     *
     * @param value the value to insert
     */
    public void insert(int value) {
        Node newNode = new Node(value);

        if (isEmpty()) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;

            if (value == current.info) {
                System.out.println("Duplicate values are not allowed.");
                return;
            }

            if (value < current.info) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (value < parent.info) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    /**
     * Prints the tree in preorder: Root, Left, Right.
     *
     * @param node the root of the subtree
     */
    public void preOrder(Node node) {
        if (node == null) {
            return;
        }

        visit(node);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * Prints the tree in inorder: Left, Root, Right.
     *
     * For a BST, the values appear in ascending order.
     *
     * @param node the root of the subtree
     */
    public void inOrder(Node node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        visit(node);
        inOrder(node.right);
    }

    /**
     * Prints the tree in postorder: Left, Right, Root.
     *
     * @param node the root of the subtree
     */
    public void postOrder(Node node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        visit(node);
    }

    /**
     * Prints the tree level by level using a queue.
     *
     * @param node the root of the subtree
     */
    public void levelOrderTraversal(Node node) {
        if (node == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node current = queue.remove();
            visit(current);

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    /**
     * Calculates the height of a subtree.
     *
     * An empty tree has height 0.
     * A tree containing only the root has height 1.
     *
     * @param node the root of the subtree
     * @return the height of the subtree
     */
    public int height(Node node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Deletes a value using the deletion-by-copying method.
     *
     * When the node has two children, its value is replaced
     * with the largest value in its left subtree.
     *
     * @param value the value to delete
     */
    public void deleteByCopy(int value) {
        Node parent = null;
        Node current = root;

        while (current != null && current.info != value) {
            parent = current;

            if (value < current.info) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            System.out.println("Value not found. Deletion failed.");
            return;
        }

        // Find the largest value in the left subtree.
        if (current.left != null && current.right != null) {
            Node predecessorParent = current;
            Node predecessor = current.left;

            while (predecessor.right != null) {
                predecessorParent = predecessor;
                predecessor = predecessor.right;
            }

            current.info = predecessor.info;

            // Remove the predecessor from its old position.
            if (predecessorParent == current) {
                predecessorParent.left = predecessor.left;
            } else {
                predecessorParent.right = predecessor.left;
            }

            return;
        }

        // Handle a node with zero or one child.
        Node child;

        if (current.left != null) {
            child = current.left;
        } else {
            child = current.right;
        }

        if (parent == null) {
            root = child;
        } else if (parent.left == current) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    /**
     * Deletes a value using the deletion-by-merging method.
     *
     * When the node has two children, its left subtree replaces
     * the node, and the right subtree is attached to the
     * rightmost node of the left subtree.
     *
     * @param value the value to delete
     */
    public void deleteByMerging(int value) {
        Node parent = null;
        Node current = root;

        while (current != null && current.info != value) {
            parent = current;

            if (value < current.info) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            System.out.println("Value not found. Deletion failed.");
            return;
        }

        Node replacement;

        // If there is no left subtree, use the right subtree.
        if (current.left == null) {
            replacement = current.right;
        } else {
            // Use the left subtree and attach the right subtree.
            replacement = current.left;
            Node rightmost = current.left;

            while (rightmost.right != null) {
                rightmost = rightmost.right;
            }

            rightmost.right = current.right;
        }

        if (parent == null) {
            root = replacement;
        } else if (parent.left == current) {
            parent.left = replacement;
        } else {
            parent.right = replacement;
        }
    }

    /**
     * Collects the nodes in inorder traversal.
     *
     * @param node the root of the subtree
     * @param nodes the list that stores the nodes
     */
    private void collectInOrder(Node node, ArrayList<Node> nodes) {
        if (node == null) {
            return;
        }

        collectInOrder(node.left, nodes);
        nodes.add(node);
        collectInOrder(node.right, nodes);
    }

    /**
     * Builds a balanced tree from an ordered list of nodes.
     *
     * @param nodes the nodes sorted in ascending order
     * @param first the first index in the range
     * @param last the last index in the range
     * @return the root of the balanced subtree
     */
    private Node buildBalancedTree(
            ArrayList<Node> nodes, int first, int last) {

        if (first > last) {
            return null;
        }

        int middle = first + (last - first) / 2;
        Node node = nodes.get(middle);

        node.left = buildBalancedTree(nodes, first, middle - 1);
        node.right = buildBalancedTree(nodes, middle + 1, last);

        return node;
    }

    /**
     * Rebalances the entire tree.
     */
    public void balance() {
        ArrayList<Node> nodes = new ArrayList<>();
        collectInOrder(root, nodes);

        root = buildBalancedTree(nodes, 0, nodes.size() - 1);
    }

    /**
     * Rotates a subtree to the left.
     *
     * @param node the root of the subtree
     * @return the new root after rotation
     */
    public Node rotateLeft(Node node) {
        if (node == null || node.right == null) {
            return node;
        }

        Node newRoot = node.right;
        node.right = newRoot.left;
        newRoot.left = node;

        return newRoot;
    }

    /**
     * Rotates a subtree to the right.
     *
     * @param node the root of the subtree
     * @return the new root after rotation
     */
    public Node rotateRight(Node node) {
        if (node == null || node.left == null) {
            return node;
        }

        Node newRoot = node.left;
        node.left = newRoot.right;
        newRoot.right = node;

        return newRoot;
    }
}
