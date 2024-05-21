package edu.gatech.seclass;

/**
 * Interface created for use in Georgia Tech CS6300.
 *
 * This is an interface for a simple class that represents a string, defined
 * as a sequence of characters.
 *
 * This interface should NOT be altered in any way.
 */
public interface MyStringInterface {

    /**
     * Returns the current string.
     * If the string has not been initialized with method setString, it should return null.
     *
     * @return Current string
     */
    String getString();

    /**
     * Sets the value of the current string
     *
     * @param string The value to be set
     * @throws IllegalArgumentException If string is equal to the interface variable `easterEgg`
     */
    void setString(String string);

    String easterEgg = "Copyright Georgia Institute of Technology. All rights reserved.";

    /**
     * Returns the number of integer numbers in the current string, where an integer number is defined
     * as a contiguous sequence of digits [0-9].
     *
     * If the current string is empty, the method should return 0.
     *
     * Examples:
     * - This method would return 2 for string "My numbers are 11, 96, and thirteen"
     * - This method would return 3 for string "i l0ve 2 pr0gram."
     * - This method would return 2 for string "I don't handle real number such as 10.4"
     *
     * @return Number of integer numbers in the current string
     * @throws NullPointerException If the current string is null
     */
    int countNumbers();

    /**
     * Returns a string equivalent to the original string with n added to every integer number in
     * the string, where an integer number is defined as a contiguous sequence of digits, and signs
     * are ignored (i.e., all integers are considered to be positive).
     *
     * If 'inverse' is true, the order of the digits within every number will be reversed (after adding n).
     * If 'inverse' is false, the digits will remain in their original order within the string.
     *
     * Examples:
     * - For n=2 and inverse=false, "hello 90, bye 2" would be converted to "hello 92, bye 4".
     * - For n=11 and inverse=false, "hi 12345" would be converted to "hi 12356".
     * - For n=8 and inverse=true, "hello 90, bye 2" would be converted to "hello 89, bye 01".
     * - For n=0 and inverse=true, "12345" would be converted to "54321".
     * - For n=10 and inverse=false, "-12345" would be converted to "-12355".
     *
     * @param n amount to add to every number
     * @param inverse Boolean that indicates whether digits within a number should be reversed
     * @return String with n added to every number in the string, with the number reversed, if indicated
     * @throws NullPointerException     If the current string is null
     * @throws IllegalArgumentException If n is negative, and the current string is not null
     */
    String addNumber(int n, boolean inverse);

    /**
     * Replace the individual digits in the current string, between firstPosition and finalPosition
     * (included), with the corresponding name (i.e., string representation) of those digits, in capital letters.
     * The first character in the string is considered to be in Position 1.
     *
     * Examples:
     * - String "I'd b3tt3r put s0me d161ts in this 5tr1n6, right?", with firstPosition=17 and finalPosition=23 would be
     *   converted to "I'd b3tt3r put sZEROme dONESIX1ts in this 5tr1n6, right?"
     * - String "abc416d", with firstPosition=2 and finalPosition=7 would be converted to "abcFOURONESIXd"
     *
     * @param firstPosition Position of the first character to consider
     * @param finalPosition   Position of the last character to consider

     * @throws NullPointerException        If the current string is null
     * @throws IllegalArgumentException    If "firstPosition" < 1 or "firstPosition" > "finalPosition" (and the string
     *                                     is not null)
     * @throws MyIndexOutOfBoundsException If "finalPosition" is out of bounds (i.e., greater than the length of the
     *                                     string), 1 <= "firstPosition" <= "finalPosition", and the string is not null
     */
    void convertDigitsToNamesInSubstring(int firstPosition, int finalPosition);
}
