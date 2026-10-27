package stackAndQueue;

/**
 * Implements a stack using a singly linked list.
 * Stack follows the LIFO principle:
 * Last In, First Out.
 */
public class MyStack {

    Node head;

    /**
     * Creates an empty stack.
     */
    public MyStack() {
        head = null;
    }

    /**
     * Checks whether the stack is empty.
     *
     * @return true if the stack is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Adds a value to the top of the stack.
     *
     * @param info the value to add
     */
    public void push(Object info) {
        Node p = new Node(info);

        if (isEmpty()) {
            head = p;
        } else {
            p.next = head;
            head = p;
        }
    }

    /**
     * Removes and returns the top value.
     *
     * @return the removed value, or null if the stack is empty
     */
    public Object pop() {
        if (isEmpty()) {
            return null;
        }

        Object info = head.info;
        head = head.next;

        return info;
    }

    /**
     * Returns the top value without removing it.
     *
     * @return the top value, or null if the stack is empty
     */
    public Object top() {
        if (isEmpty()) {
            return null;
        }

        return head.info;
    }

    /**
     * Removes all elements from the stack.
     */
    public void clear() {
        head = null;
    }
}
