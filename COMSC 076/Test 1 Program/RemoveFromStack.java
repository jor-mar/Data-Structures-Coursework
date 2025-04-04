/*
 * The algorithm used in removeItem in RemoveFromStack.java is O(n).
 * This is because it clones the stack,
 * checks the stack for matching elements,
 * adds the elements that don't match the item to a resultant stack,
 * then reverses the resultant stack (which also involves another cloning process).
 * None of these loops through the problem set (or a smaller set) are nested,
 * therefore the dominant term is kn, where k is an integer and n is the problem set size.
 * 
 * Longer explanation:
 * All of these operations are O(n) in all scenarios, except
 * when elements are removed from the stack before it is reversed, in which case
 * the time complexity of reversing the stack is O(n-z) where z is the number
 * of items removed from the stack that match the passed T element.
 * This is because all of those aforementioned operations involve looping through
 * the contents of the stack once.
 * Thus, since kn is the dominant term in a map of the number of operations
 * espoused by this algorithm, for k is some integer number of loops through the stack's contents,
 * we can express the time complexity of this algorithm (given sufficiently large n) as O(n)
 * where n is the length of the problem set, ie. the size of the stack argument.
 * This suggests that the time complexity grows linearly with respect to the problem set's growth.
 */

/**
 * A utility class designed to remove all instances of one item from a generic type stack
 * 
 * @author Jordan Marcelo
 * @version 1.0
 * 3/3/25
 * COMSC 076
 * Professor Srinivasan
 */
public class RemoveFromStack {
    /**
     * A method designed to remove all instances of one item from a stack
     * @param <T> the type of item being dealt with
     * @param original the stack from which to remove the item
     * @param item the item to remove from the stack
     * @return the same stack but without any instances of the item
     */
    public static <T> Stack<T> removeItem(Stack<T> original, T item) {
        // throw null exception if the original stack is not a stack
        if (original == null) {
            throw new IllegalArgumentException("Original stack cannot be null");
        }

        // clone the stack so the original is unedited
        // instantiate resultant stack
        Stack<T> clone = (Stack<T>) original.clone();
        Stack<T> withoutItem = new Stack<>();

        // check each element of cloned stack, including nulls,
        // to see if they are different from T item.
        // if they are different, they are pushed to the resultant stack.
        while (!clone.isEmpty()) {
            T checking = clone.pop();
            if ((item == null && checking != null) || (item != null && !item.equals(checking))) {
                withoutItem.push(checking);
            }
        }

        // reverse the resultant stack to maintain same order as original
        // and return it
        return reverseStack(withoutItem);
    }

    /**
     * A method designed to reverse the order a stack is given in
     * @param original the stack to be reversed
     * @return the original stack but in reverse order
     */
    private static <T> Stack<T> reverseStack(Stack<T> original) {
        // clone stack so original is not altered
        // create a new resultant stack, reversed
        Stack<T> clone = (Stack<T>)original.clone();
        Stack<T> reversed = new Stack<>();

        // push each element of cloned stack into new stack
        // thereby reversing the order (by stack LIFO priority)
        while (!clone.isEmpty()) {
            reversed.push(clone.pop());
        }

        // return reversed version of original stack
        return reversed;
    }

    /**
     * The main method is dedicated to declaring data to help with testing
     * and running the test method through the appropriate parallel data
     */
    public static void main(String[] args) {
        // create stack contents for reference in testing
        final Integer[] basicPrimes = {2, 3, 2, 5, 7, 11, 2, 13, 17, 2, 19, 2}; // removing 2
        final Integer[] noOccurrence = {1, 3, 5}; // removing 2
        final Integer[] allOccurrences = {2, 2, 2}; // removing 2
        final String[] nullElements = {"Hello", null, "World", null}; // removing null
        final Double[] doubleElements = {1.1, 2.2, 3.3, 2.2}; // removing 2.2
        final String[] stringElements = {"apple", "banana", "apple", "cherry", "apple"}; // removing "apple"
        final String[] nullElements2 = nullElements; // removing "Hello"
        final String[] nothing = {}; // removing "Hi"
        final String[] removeNothing = {"Hello", "Hi"}; // removing ""
        final String[] removeNothing2 = {}; // removing null
        final String[] nullStack = null;

        // create stack contents for expected/correct answers
        final Integer[] basicPrimesC = {3, 5, 7, 11, 13, 17, 19};
        final Integer[] noOccurrenceC = {1, 3, 5};
        final Integer[] allOccurrencesC = {};
        final String[] nullElementsC = {"Hello", "World"};
        final Double[] doubleElementsC = {1.1, 3.3};
        final String[] stringElementsC = {"banana", "cherry"};
        final String[] nullElements2C = {null, "World", null};
        final String[] nothingC = nothing;
        final String[] removeNothingC = removeNothing;
        final String[] removeNothing2C = removeNothing2;
        final String[] nullStackC = null;

        // create parallel arrays of test names, stack contents, correct stack contents, and items to be removed
        // so that they can be looped through in testing

        // array of all the contents of each stack initially
        final Object[] arrays = {
            basicPrimes,
            noOccurrence,
            allOccurrences,
            nullElements,
            doubleElements,
            stringElements,
            nullElements2,
            nothing,
            removeNothing,
            removeNothing2,
            nullStack
        };
        // array of elements to remove from each array, respectively
        final Object[] removeTest = {
            2,
            2,
            2,
            null,
            2.2,
            "apple",
            "Hello",
            "Hi",
            "",
            null,
            null
        };
        // array of names of each stack test, respectively (vanity)
        final String[] names = {
            "basic primes",
            "no occurrence",
            "all occurrences",
            "null elements",
            "double elements",
            "string elements",
            "non-null string",
            "empty stack",
            "remove nothing",
            "empty stack 2",
            "null stack"
        };
        // array of all the contents of each stack afterwards (assuming the method is correct)
        final Object[] correctAnswers = {
            basicPrimesC,
            noOccurrenceC,
            allOccurrencesC,
            nullElementsC,
            doubleElementsC,
            stringElementsC,
            nullElements2C,
            nothingC,
            removeNothingC,
            removeNothing2C,
            nullStackC
        };

        // test each combination from the parallel arrays, keeping track of number correct for summary
        int correct = 0;
        for (int i = 0; i < arrays.length; i++) {
            if (testStack(names[i], arrays[i], removeTest[i], correctAnswers[i])) {
                correct++;
            }
        }

        // summarize percent correct
        System.out.println("\nTests complete!");
        System.out.println("Percent correct: " + 100.0*correct/arrays.length);
    }

    /**
     * A helper method for the main method, designed to test the removeItem method on an individual stack
     * 
     * @param <T> the generic type to be tested
     * @param stackName the name of the stack to be announced during testing
     * @param arrays an array of desired stack contents of type T
     * @param removeTest the object to be removed from the array using removeItem
     * @param correctAnswer a corresponding 2d array with removeTest removed from it, used to check answers for post-proc arrays
     */
    private static <T> boolean testStack(String stackName, Object arrays, Object removeTest, Object correctAnswers) {
        // stackName is only used to announce the title of the test
        // arrays is a 2d array containing the content of each stack to be tested
        // removeTest is an array of the items to be removed from each corresponding array
        // correctAnswer is a 2d array containing the content of each stack's result

        // announce start of test
        System.out.println("Testing : " + stackName + ", removing \"" + removeTest + "\"");

        // cast objects to T, assuming I wrote main method correctly
        @SuppressWarnings("unchecked")
        T[] arr = (T[])(arrays);
        @SuppressWarnings("unchecked")
        T removable = (T)(removeTest);
        @SuppressWarnings("unchecked")
        T[] arrC = (T[])(correctAnswers);

        // check for null array/stack and handle the exception accordingly
        if (arr == null) {
            try {
                Stack<T> resultant = RemoveFromStack.removeItem(null, removable);
                System.out.println("\tBefore: " + null);
                System.out.println("\tAfter: " + resultant.toString());
                return false;
            }
            catch (Exception e) {
                System.out.println("\tAn error occurred: " + e.getMessage());
                System.out.println("Expected answer (correct): " + true + "\n");
                return true;
            }
        }

        // create the stack for testing
        Stack<T> testStack = new Stack<>();
        for (T item : arr) {
            testStack.push(item);
        }

        // get the resultant from test stack
        Stack<T> finalStack = RemoveFromStack.removeItem(testStack, removable);

        // announce results
        System.out.println("\tBefore: " + testStack.toString());
        System.out.println("\tAfter: " + finalStack.toString());

        // correct results? assume so until proven otherwise
        // get ready to compare correct answers to final stack
        boolean correct = true;
        Stack<T> fStackClone = (Stack<T>) finalStack.clone();

        // check if arrC[i] == finalStack.pop() != removable, handling nulls
        // ie. ensures that each element of the final stack is correct, in correct order, and was not supposed to be removed
        // sets boolean correct to false if there is a discrepency between correct answer and result
        // counts from the end of the correct answer array to the beginning to account for stack LIFO priority
        for (int i = finalStack.size() - 1; i >= 0; i--) {
            T comparable = fStackClone.pop();
            // for each element, ensure that it matches the correct element array's order and was not supposed to be removed
            // (something that was supposed to be removed is equal to removable)

            // if element in the stack is null, compare it appropriately
            if (comparable == null) {
                if (arrC[i] != null || removable == null) {
                    correct = false;
                    break;
                }
            }
            // otherwise compare it as a non-null element
            else if (!comparable.equals(arrC[i]) || comparable.equals(removable)) {
                correct = false;
                break;
            }
        }

        // summarize correctness of testing
        System.out.println("Expected answer (correct): " + correct + "\n");
        return correct;
    }
}