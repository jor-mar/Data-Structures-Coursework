
import java.util.Arrays;

public class Driver extends DriverBase {

   private static Stack<Integer> makeStack(Integer... elements) {
      Stack<Integer> result = new Stack<>();
      for (Integer elem : elements) {
         result.push(elem);
      }
      return result;
   }

    /**
     * Compares the elements in the stack with the array of passed in values.
     * The arrays have to be passed top to bottom.
     */
   private static void checkStack(String testCase, Stack<Integer> s1, Integer... elements) {
      if (s1.size() != elements.length) {
          testOutput.put(testCase, "Size of stack " + s1.toString() + " does not match passed in elements " + Arrays.toString(elements));
          return;
      }
      Stack<Integer> origStack = s1.clone();
      for (int i = elements.length - 1; i >= 0; i--) {
	 Integer element = elements[i];
         Integer e1 = s1.pop();
         if (!e1.equals(element)) {
            testOutput.put(testCase, "Element in stack " + e1 + " does not match expected element " + element + " in stack " + origStack.toString());
            return;
         }
      }
      testOutput.put(testCase, null);
   }

   public static void main(String[] args) {
      // check merge with stacks of equal size      
      checkDoesNotThrowException("Merging stacks of same size", () -> {
         Stack<Integer> s1 = makeStack(1, 3, 5);
         Stack<Integer> s2 = makeStack(2, 4, 6);
         checkStack("Merging stacks of same size", 
                     TestStack.merge(s1, s2), 1, 2, 3, 4, 5, 6);
      });
      
      // check merge with stacks of unequal size
      checkDoesNotThrowException("Merging stacks of different size", () -> {
         Stack<Integer> s1 = makeStack(1, 5);
         Stack<Integer> s2 = makeStack(2, 4, 6);
         checkStack("Merging stacks of different size", 
                     TestStack.merge(s1, s2), 1, 2, 4, 5, 6);
      });

      // check merge with one stack of 0 size.
      checkDoesNotThrowException("Merging stacks (one empty stack)", () -> {
         Stack<Integer> s1 = makeStack();
         Stack<Integer> s2 = makeStack(2, 4, 6);
         checkStack("Merging stacks (one stack empty)", 
                     TestStack.merge(s1, s2), 2, 4, 6);
      });

      // check merge with both stacks having the same element
      checkDoesNotThrowException("Merging stacks with same elements", () -> {
         Stack<Integer> s1 = makeStack(2, 4, 6);
         Stack<Integer> s2 = makeStack(2, 4, 6);
         checkStack("Merging stacks with same elements", 
                     TestStack.merge(s1, s2), 2, 2, 4, 4, 6, 6);
      });

      // Check merge with both stacks having duplicates of an element
      checkDoesNotThrowException("Merging stacks with duplicate elements", () -> {
         Stack<Integer> s1 = makeStack(1, 1, 2, 2, 3, 3);
         Stack<Integer> s2 = makeStack(4, 4, 5, 5, 6, 6);
         checkStack("Merging stacks with duplicate elements", 
                     TestStack.merge(s1, s2), 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6);
      });

      // Check reverse with stack of elements
      checkDoesNotThrowException("Reversing stack", () -> {
         Stack<Integer> s1 = makeStack(2, 4, 6);
         checkStack("Reversing stack", 
                     TestStack.reverse(s1), 6, 4, 2);
      });
      // Check reverse with an empty stack
      checkDoesNotThrowException("Reversing empty stack", () -> {
         Stack<Integer> s1 = makeStack();
         checkStack("Reversing empty stack", 
                     TestStack.reverse(s1));
      });

      printJsonOutput();
   }
}
