package stackAndQueue;

import java.util.Scanner;

/**
 * Demonstrates the operations of Stack and Queue.
 */
public class StackAndQueueDemo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MyStack stack = new MyStack();
        MyQueue queue = new MyQueue();

        int choice;

        do {
            System.out.println("\n========== STACK AND QUEUE DEMO ==========");
            System.out.println("1. Push to Stack");
            System.out.println("2. Pop from Stack");
            System.out.println("3. View Stack top");
            System.out.println("4. Clear Stack");
            System.out.println("5. Enqueue to Queue");
            System.out.println("6. Dequeue from Queue");
            System.out.println("7. View Queue front");
            System.out.println("8. Clear Queue");
            System.out.println("9. Check Stack and Queue status");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    stack.push(sc.next());
                    System.out.println("Value pushed.");
                    break;

                case 2: {
                    Object value = stack.pop();

                    if (value == null) {
                        System.out.println("Stack is empty.");
                    } else {
                        System.out.println("Popped value: " + value);
                    }
                    break;
                }

                case 3:
                    if (stack.isEmpty()) {
                        System.out.println("Stack is empty.");
                    } else {
                        System.out.println("Stack top: " + stack.top());
                    }
                    break;

                case 4:
                    stack.clear();
                    System.out.println("Stack cleared.");
                    break;

                case 5:
                    System.out.print("Enter value: ");
                    queue.enqueue(sc.next());
                    System.out.println("Value enqueued.");
                    break;

                case 6: {
                    Object value = queue.dequeue();

                    if (value == null) {
                        System.out.println("Queue is empty.");
                    } else {
                        System.out.println("Dequeued value: " + value);
                    }
                    break;
                }

                case 7:
                    if (queue.isEmpty()) {
                        System.out.println("Queue is empty.");
                    } else {
                        System.out.println("Queue front: " + queue.front());
                    }
                    break;

                case 8:
                    queue.clear();
                    System.out.println("Queue cleared.");
                    break;

                case 9:
                    System.out.println("Stack empty: " + stack.isEmpty());
                    System.out.println("Queue empty: " + queue.isEmpty());
                    break;

                case 0:
                    System.out.println("Exiting Stack and Queue Demo.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}
