/**
 * Uses a stack and implements methods to merge two stacks or reverse a stack.
 * @author Jordan Marcelo, Balaji Srinivasan
 */
public class TestStack {
    /**
     * Given two stacks containing Integer objects in increasing order
     * from the bottom up, create a third stack such that the Integer objects
     * are in increasing order from the bottom up. If an item appears n times in
     * the two given stacks, it will appear n times in the new stack.
     *
     * @param s1 the first stack
     * @param s2 the second stack
     * @return the new stack, with the items from the two given stacks merged.
     */
    public static <E extends Comparable<E>> Stack<E> merge(Stack<E> s1, Stack<E> s2) {
        Stack<E> s1Clone = (Stack<E>)s1.clone();
        Stack<E> s2Clone = (Stack<E>)s2.clone(); 
        Stack<E> s3 = new Stack<>();

        while (!s1Clone.isEmpty() && !s2Clone.isEmpty()) {
            // pop greatest value from top of s1Clone or top of s2Clone, then push to s3
            if (s1Clone.peek().compareTo(s2Clone.peek()) > 0) {
                s3.push(s1Clone.pop());
            }
            else {
                s3.push(s2Clone.pop());
            }
        }
        
        // push remaining s1 or s2 to s3
        while (!s1Clone.isEmpty()) {
            s3.push(s1Clone.pop());
        }
        while (!s2Clone.isEmpty()) {
            s3.push(s2Clone.pop());
        }

        // convert s3 to increasing order from bottom up by reversing
        return reverse(s3);
    }

    /**
     * Given a stack of Integer objects, create and return a stack with
     * the same objects in reverse order.
     *
     * @param1 s1 the input stack
     * @return the new stack, with the items in reverse order
     */
    public static <E extends Comparable<E>> Stack<E> reverse(Stack<E> s1) {
        Stack<E> s1Clone = (Stack<E>)s1.clone();
        Stack<E> reverseStack = new Stack<>();

        while (!s1Clone.isEmpty()) {
            reverseStack.push(s1Clone.pop());
        }

        return reverseStack;
    }

    /**
     * makeStack: Given an array of Integer objects, create and return a stack with
     * the same objects. The first item in the array will be at the bottom of the
     * stack, the last item at the top.
     *
     * @param1 arr the Integer array
     * @return the new stack
     */
    public static <E extends Comparable<E>> Stack<E> makeStack(E[] arr) {
        Stack<E> newStack = new Stack<>();

        for (E object : arr) {
            newStack.push(object);
        }
        
        return newStack;
    }
}
