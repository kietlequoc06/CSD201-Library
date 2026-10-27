
package tree;

import java.util.Scanner;

/**
 * Demonstrates the operations of a Binary Search Tree.
 */
public class TreeDemo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BSTree tree = new BSTree();
        int choice;

        do {
            System.out.println("\n========== BINARY SEARCH TREE DEMO ==========");
            System.out.println("1. Insert a value");
            System.out.println("2. Search for a value");
            System.out.println("3. Preorder traversal");
            System.out.println("4. Inorder traversal");
            System.out.println("5. Postorder traversal");
            System.out.println("6. Level-order traversal");
            System.out.println("7. Get tree height");
            System.out.println("8. Delete by copying");
            System.out.println("9. Delete by merging");
            System.out.println("10. Balance the tree");
            System.out.println("11. Rotate the root left");
            System.out.println("12. Rotate the root right");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value: ");
                    tree.insert(sc.nextInt());
                    break;

                case 2: {
                    System.out.print("Enter value to search: ");
                    int value = sc.nextInt();
                    Node found = tree.search(value);

                    if (found == null) {
                        System.out.println("Value not found.");
                    } else {
                        System.out.println("Value found: " + found.info);
                    }
                    break;
                }

                case 3:
                    System.out.print("Preorder: ");
                    tree.preOrder(tree.root);
                    System.out.println();
                    break;

                case 4:
                    System.out.print("Inorder: ");
                    tree.inOrder(tree.root);
                    System.out.println();
                    break;

                case 5:
                    System.out.print("Postorder: ");
                    tree.postOrder(tree.root);
                    System.out.println();
                    break;

                case 6:
                    System.out.print("Level order: ");
                    tree.levelOrderTraversal(tree.root);
                    System.out.println();
                    break;

                case 7:
                    System.out.println("Tree height: " + tree.height(tree.root));
                    break;

                case 8:
                    System.out.print("Enter value to delete: ");
                    tree.deleteByCopy(sc.nextInt());
                    break;

                case 9:
                    System.out.print("Enter value to delete: ");
                    tree.deleteByMerging(sc.nextInt());
                    break;

                case 10:
                    tree.balance();
                    System.out.println("Tree balanced.");
                    System.out.print("Level order: ");
                    tree.levelOrderTraversal(tree.root);
                    System.out.println();
                    break;

                case 11:
                    tree.root = tree.rotateLeft(tree.root);
                    System.out.println("Left rotation applied if possible.");
                    tree.levelOrderTraversal(tree.root);
                    System.out.println();
                    break;

                case 12:
                    tree.root = tree.rotateRight(tree.root);
                    System.out.println("Right rotation applied if possible.");
                    tree.levelOrderTraversal(tree.root);
                    System.out.println();
                    break;

                case 0:
                    System.out.println("Exiting Tree Demo.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}
