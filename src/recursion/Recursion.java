
package recursion;

/**
 * Contains recursive algorithms for practice.
 */
public class Recursion {

    /**
     * Calculates the factorial of n.
     *
     * @param n a non-negative integer
     * @return n factorial
     */
    public static int factorial(int n) {
        if (n <= 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    /**
     * Prints numbers in descending order and then ascending order.
     *
     * @param n the starting number
     */
    public static void printRecursivePattern(int n) {
        if (n > 0) {
            System.out.print(n);
            printRecursivePattern(n - 1);
            System.out.print(n);
        }
    }

    /**
     * Generates a string using a recursive pattern.
     *
     * @param n the input number
     * @return the generated string
     */
    public static String generatePattern(int n) {
        if (n <= 0) {
            return "";
        }

        return generatePattern(n - 3) + n
                + generatePattern(n - 2) + n;
    }

    /**
     * Calculates the sum of integers from 1 to n.
     *
     * @param n the upper limit
     * @return the sum from 1 to n, or 0 if n <= 0
     */
    public static int calculateSum(int n) {
        if (n <= 0) {
            return 0;
        }

        return n + calculateSum(n - 1);
    }

    /**
     * Finds the minimum value among the first n array elements.
     *
     * @param a the input array
     * @param n the number of elements to examine
     * @return the minimum value
     */
    public static int findMin(int[] a, int n) {
        if (n == 1) {
            return a[0];
        }

        int previousMin = findMin(a, n - 1);

        if (a[n - 1] < previousMin) {
            return a[n - 1];
        }

        return previousMin;
    }

    /**
     * Checks whether the first n characters form a palindrome.
     *
     * @param characters the character array
     * @param n the number of characters to examine
     * @return true if the first n characters form a palindrome
     */
    public static boolean isPalindrome(char[] characters, int n) {
        return isPalindromeRange(characters, 0, n - 1);
    }

    /**
     * Compares characters from both ends toward the center.
     *
     * @param characters the character array
     * @param left the index from the left
     * @param right the index from the right
     * @return true if the selected range is a palindrome
     */
    private static boolean isPalindromeRange(
            char[] characters, int left, int right) {

        if (left >= right) {
            return true;
        }

        if (characters[left] != characters[right]) {
            return false;
        }

        return isPalindromeRange(characters, left + 1, right - 1);
    }
}
