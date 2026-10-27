
package recursion;

import java.util.Scanner;

/**
 * Demonstrates the recursive algorithms in Recursion.
 */
public class RecursionDemo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n========== RECURSION DEMO ==========");
            System.out.println("1. Calculate factorial");
            System.out.println("2. Print recursive pattern");
            System.out.println("3. Generate recursive string pattern");
            System.out.println("4. Calculate sum from 1 to n");
            System.out.println("5. Find minimum in an array");
            System.out.println("6. Check palindrome");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1: {
                    System.out.print("Enter n (0 to 12): ");
                    int n = sc.nextInt();

                    if (n < 0 || n > 12) {
                        System.out.println("Please enter a value from 0 to 12.");
                    } else {
                        System.out.println(
                                "Factorial: " + Recursion.factorial(n)
                        );
                    }
                    break;
                }

                case 2: {
                    System.out.print("Enter n: ");
                    int n = sc.nextInt();

                    Recursion.printRecursivePattern(n);
                    System.out.println();
                    break;
                }

                case 3: {
                    System.out.print("Enter n: ");
                    int n = sc.nextInt();

                    System.out.println(Recursion.generatePattern(n));
                    break;
                }

                case 4: {
                    System.out.print("Enter n: ");
                    int n = sc.nextInt();

                    System.out.println(
                            "Sum: " + Recursion.calculateSum(n)
                    );
                    break;
                }

                case 5: {
                    System.out.print("Enter array size: ");
                    int size = sc.nextInt();

                    if (size <= 0) {
                        System.out.println("Array size must be positive.");
                        break;
                    }

                    int[] numbers = new int[size];

                    for (int i = 0; i < size; i++) {
                        System.out.print("numbers[" + i + "] = ");
                        numbers[i] = sc.nextInt();
                    }

                    System.out.println(
                            "Minimum value: "
                            + Recursion.findMin(numbers, size)
                    );
                    break;
                }

                case 6: {
                    System.out.print("Enter a word: ");
                    String word = sc.next();

                    char[] characters = word.toCharArray();

                    if (Recursion.isPalindrome(
                            characters, characters.length)) {
                        System.out.println("It is a palindrome.");
                    } else {
                        System.out.println("It is not a palindrome.");
                    }
                    break;
                }

                case 0:
                    System.out.println("Exiting Recursion Demo.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}
