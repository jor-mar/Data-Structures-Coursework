/**
 * A stack ADT data structure with a linked list underlying it
 * 
 * @param T a generic object which will act as the data to be stored
 * 
 * @author Jordan Marcelo
 * @version 1.0
 */
public class LinkedStack<T>
{
    private final LinkedBag<T> contents = new LinkedBag<>();

    /**
     * Initializes an empty linked stack
     * 
     */
    public LinkedStack()
    {
        // empty constructor
    }

    /**
     * Determines whether the linked stack is empty
     * 
     * @return true if the linked stack is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return contents.isEmpty();
    }

    /**
     * Determines the number of elements in the linked stack
     * 
     * @return the number of elements in the linked stack
     */
    public int size()
    {
        return contents.size();
    }

    /**
     * Pushes data to the top of the linked stack
     * 
     * @param addition the data to push onto the linked stack
     */
    public void push(T addition)
    {
        contents.add(addition);
    }

    /**
     * Gets the data at the top of the linked stack
     * 
     * @return the data at the top of the linked stack
     */
    public T peek()
    {
        return contents.get(contents.size()-1);
    }

    /**
     * Gets and removes the data at the top of the linked stack
     * 
     * @return the data that was removed from the top of the linked stack
     */
    public T pop()
    {
        T deletedData = contents.get(contents.size()-1);
        contents.remove(contents.size()-1);
        return deletedData;
    }

    /**
     * Empties the linked stack
     */
    public void clear()
    {
        while(!contents.isEmpty())
        {
            contents.remove();
        }
    }

    /**
     * Searches the linked stack for the entry with the data
     * 
     * @param data the data to search for in the linked stack
     * @return the element of the entry with the data, where 1 is the top item and -1 means the data was not found in the linked stack
     */
    public int search(T data)
    {
        int counter = -1;
        for (int i = 0; i < contents.size(); i++)
        {
            if(contents.get(i).equals(data))
            {
                counter = contents.size() - i;
            }
        }
        return counter;
    }
}