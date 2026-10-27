package stackAndQueue;

/**
 * Implements a queue using a singly linked list.
 * Queue follows the FIFO principle:
 * First In, First Out.
 */
public class MyQueue {

    Node head;
    Node tail;

    /**
     * Creates an empty queue.
     */
    public MyQueue() {
        head = tail = null;
    }

    /**
     * Checks whether the queue is empty.
     *
     * @return true if the queue is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Adds a value to the rear of the queue.
     *
     * @param info the value to add
     */
    public void enqueue(Object info) {
        Node p = new Node(info);

        if (isEmpty()) {
            head = tail = p;
        } else {
            tail.next = p;
            tail = p;
        }
    }

    /**
     * Removes and returns the front value.
     *
     * @return the removed value, or null if the queue is empty
     */
    public Object dequeue() {
        if (isEmpty()) {
            return null;
        }

        Object info = head.info;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        return info;
    }

    /**
     * Returns the front value without removing it.
     *
     * @return the front value, or null if the queue is empty
     */
    public Object front() {
        if (isEmpty()) {
            return null;
        }

        return head.info;
    }

    /**
     * Removes all elements from the queue.
     */
    public void clear() {
        head = tail = null;
    }
}
