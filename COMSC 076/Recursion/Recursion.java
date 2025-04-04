import java.util.HashSet;
import java.util.Set;

/**
 * Recursion assignment
 * COMSC 076
 * Balaji Srinivasan
 * 04/06/25
 * @version 1.0
 * @author Jordan Marcelo
 */
public class Recursion {

    /**
     * Reverses the given string.
     * @param str The string to reverse
     * @return The reversed string
     */
    public static String reverseString(String str) {
        // return str if it cannot be reversed or reversing is meaningless
        if (str == null || str.length() <= 1) {
            return str;
        }
        // use helper method to reverse the string
        return reverseString(str, str.length() - 1, "");
    }

    /**
     * Recursively reverses the given string starting at the index and moving backwards.
     * Precondition: str.length() >= 2
     * @param str The string to reverse
     * @param int The index from which to start concatenating a reversed string
     * @param accumulator The string with which to start building the reversed string
     * @return The reversed string
     */
    private static String reverseString(String str, int index, String accumulator) {
        // base case to stop recursion if index ends up negative from decrementing
        if (index < 0) {
            return accumulator;
        }
        // use tail recursion so the intermediate frames do not need to be saved to stack
        // while decrementing index and adding correct char to accumulator
        // to properly reverse the string
        return reverseString(str, index - 1, accumulator + str.charAt(index));
    }

    /**
     * Gets a hash set of all permutations of the given string
     * @param str The string to find all permutations of
     * @return A hash set containing all permutations of the given string
     */
    public static Set<String> allPermutations(String str) {
        // declare result to store permutations
        Set<String> result = new HashSet<>();
        // use helper method to make result meaningful
        allPermutations("", str, result);
        return result;
    }
    
    /**
     * Edits result to be a hash set containing all permutations involving prefix and remaining
     * @param prefix The string to be moved around remaining to provide permutations
     * @param remaining The string to be permutated with respect to prefix
     * @param result The hash set to be edited
     */
    private static void allPermutations(String prefix, String remaining, Set<String> result) {
        // base case: no remaining permutations exist when moving prefix around a null/empty string
        // so prefix by itself is the final permutation
        if (remaining == null || remaining.isEmpty()) {
            result.add(prefix);
            return;
        }
        // loops through choosing a character at index i
        for (int i = 0; i < remaining.length(); i++) {
            // finds relevant permutations by moving chosen char around string - chosen,
            // which is substring(0, i) + substring(i + 1),
            // by recursively calling allPermutations,
            // with chosen char as prefix and string - chosen as remaining
            // adds these permutations to result which accumulates
            // and avoids duplicates by being a HashSet
            allPermutations(prefix + remaining.charAt(i),
                            remaining.substring(0, i) + remaining.substring(i + 1),
                            result);
        }
    }
}
