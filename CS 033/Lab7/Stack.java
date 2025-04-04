
import java.util.EmptyStackException;

/**
 * A generic unbounded stack data structure implementation that doubles in size when necessary.
 *
 * @param <T> the type of elements stored in the stack
 * @author Jordan Marcelo
 * 04/01/25
 * CS 033
 * Professor Ashraf
 */
public class Stack<T> {
    // contents is the array of data in the stack
    private T[] contents;
    // size is the number of occupied spaces in the stack
    private int size;

    /**
     * Constructs an empty stack with an initial capacity of 10.
     */
    @SuppressWarnings("unchecked")
    public Stack() {
        // declares array for storage, starting with a capacity of 10
        contents = (T[]) new Object[10];
    }

    /**
     * Pushes an element onto the top of the stack. If the stack is full, its
     * capacity is doubled to accommodate the new element.
     *
     * @param value the element to be pushed onto the stack
     */
    public void push(T data) {
        // check if doubling array size is necessary
        if (size == contents.length) {
            // doubles array size if necessary
            resize();
        }
        // store data at the end of the array (top of stack)
        contents[size] = data;
        // increment size to flag this slot as being declared
        size++;
    }

    /**
     * Removes and returns the top element of the stack.
     *
     * @return the top element of the stack
     * @throws EmptyStackException if stack is empty
     */
    public T pop() {
        // throw EmptyStackException if stack is empty
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        // otherwise retrieve top (latest) element
        T topElement = contents[size - 1];
        // makes latest element slot empty (null),
        // since it was removed from stack
        contents[size - 1] = null;
        // decrement size to account for this change
        size--;
        // return the removed element to caller
        return topElement;
    }

    /**
     * Returns the top element of the stack without removing it.
     *
     * @return the top element of the stack
     * @throws EmptyStackException if the stack is empty
     */
    public T peek() {
        // if the stack is empty, there is no element to peek at
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        // otherwise returns top (latest) element to caller
        return contents[size - 1];
    }

    /**
     * Checks if the stack is empty.
     *
     * @return {@code true} if the stack is empty, {@code false} otherwise
     */
    public boolean isEmpty() {
        // the stack is empty if it has nothing stored, ie. its size is 0
        return size == 0;
    }

    /**
     * Returns the current number of elements in the stack.
     *
     * @return the size of the stack
     */
    public int size() {
        // returns the number of occupied spots in the stack
        return size;
    }

    /**
     * Resizes the internal array to twice the size. This method is
     * called internally when the stack needs to grow to accommodate more elements.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        // declares a new array with twice the capacity
        T[] newContents = (T[]) new Object[2 * size];
        // copies every element to new array
        for (int i = 0; i < size; i++) {
            newContents[i] = contents[i];
        }
        // replaces reference to old array with new one
        contents = newContents;
        // size is unchanged but capacity is doubled
    }
}