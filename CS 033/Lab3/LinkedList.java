/**
 * A class that stores data in a linked list
 * 
 * @param <T> a generic object which will act as the data to be stored
 * 
 * @author Jordan Marcelo, Evan Chou
 * @version 1.0
 * 3/3/25
 */
public class LinkedList<T>
{
    private Node<T> firstNode;
    private int currentSize;

    /**
     * Constructs an empty LinkedList.
     */
    public LinkedList()
    {
        // Empty constructor
    }

    /**
     * Checks if the List is empty.
     * 
     * @return true if the List is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return (currentSize == 0);
    }

    /**
     * Gets the number of elements in the List.
     * 
     * @return the current size of the List
     */
    public int size()
    {
        return currentSize;
    }

    /**
     * Adds a new element to the List.
     * 
     * @param data the element to add
     */
    public boolean add(T data)
    {
        Node<T> newNode = new Node<>(data);

        if (isEmpty())
        {
            // no nodes, so first node is new node
            firstNode = newNode;
        }
        else
        {
            // find last node
            Node<T> lastNode = firstNode;
            while (lastNode.nextNode != null)
            {
                lastNode = lastNode.nextNode;
            }
            // insert data after last node
            lastNode.nextNode = newNode;
        }
        currentSize++;
        return true;
    }

    /**
     * Adds an element at the beginning of the List.
     * 
     * @param data the element to add
     */
    public void addFirst(T data)
    {
        Node<T> newNode = new Node<>(data, firstNode);
        firstNode = newNode;
        currentSize++;
    }

    /**
     * Adds an element at a specific index.
     * 
     * @param index the position to insert the element
     * @param data the element to add
     * @throws IllegalArgumentException if the index is out of bounds
     */
    public void add(int index, T data)
    {
        if (index < 0 || index > currentSize)
        {
            throw new IllegalArgumentException("Out of bounds");
        }

        Node<T> newNode = new Node<>(data);
        if (index == 0)
        {
            newNode.nextNode = firstNode;
            firstNode = newNode;
        }
        else
        {
            // find node preceding index position
            Node<T> predecessorNode = firstNode;
            for (int i = 0; i < index - 1; i++)
            {
                predecessorNode = predecessorNode.nextNode;
            }
            // insert new node between preceding node and the node that was after it
            newNode.nextNode = predecessorNode.nextNode;
            predecessorNode.nextNode = newNode;
        }
        currentSize++;
    }

    /**
     * Removes the first element from the List.
     * 
     * @return the removed element, or null if the List is empty
     */
    public T remove()
    {
        T deletedData;
        if (firstNode == null)
        {
            deletedData = null;
        }
        else
        {
            deletedData = firstNode.data;
            firstNode = firstNode.nextNode;
            currentSize--;
        }
        return deletedData;
        
    }

    /**
     * Removes an element at a specific index.
     * 
     * @param index the position to remove the element from
     * @return the removed element
     * @throws IllegalArgumentException if the index is out of bounds
     */
    public T remove(int index)
    {
        if (index < 0 || index >= currentSize)
        {
            throw new IllegalArgumentException("Out of bounds");
        }
        
        if (index == 0)
        {
            return remove();
        }
        
        Node<T> predecessorNode = firstNode;
        for (int i = 0; i < index - 1; i++)
        {
            predecessorNode = predecessorNode.nextNode;
        }

        // gather data and delete node
        T deletedData = predecessorNode.nextNode.data;
        predecessorNode.nextNode = predecessorNode.nextNode.nextNode;
        currentSize--;

        return deletedData;
    }

    /**
     * Removes the first occurrence of a specific element from the List.
     * 
     * @param data the element to remove
     * @return true if the element was removed, false otherwise
     */
    public boolean remove(T data)
    {
        if (firstNode == null)
        {
            // cannot remove anything from empty linked List
            return false;
        }

        if (firstNode.data.equals(data))
        {
            // remove first node
            firstNode = firstNode.nextNode;
            currentSize--;
            return true;
        }

        Node<T> predecessorNode = firstNode;
        while (predecessorNode.nextNode != null && !predecessorNode.nextNode.data.equals(data))
        {
            // find node preceding the node with desired data, or the last node of the linked List
            predecessorNode = predecessorNode.nextNode;
        }

        if (predecessorNode.nextNode != null)
        {
            // remove desired node
            predecessorNode.nextNode = predecessorNode.nextNode.nextNode;
            currentSize--;
            return true;
        }
        else
        {
            // reached end of linked List with no matches
            return false;
        }
    }

    /**
     * Returns the data of the element at an index
     * 
     * @param index the index of the desired element
     * @return T the data belonging to the node at that desired index
     * @throws IllegalArgumentException if the index is out of bounds
     */
    public T get(int index)
    {
        if (index < 0 || index >= currentSize)
        {
            throw new IllegalArgumentException("Out of bounds");
        }

        Node<T> desiredNode = firstNode;
        for (int i = 0; i < index; i++)
        {
            // find node at that index
            desiredNode = desiredNode.nextNode;
        }

        /*
        T desiredData;
        if(desiredNode == null)
        {
            desiredData = null;
        }
        else
        {
            desiredData = desiredNode.data;
        }
        // Should be handled by above exception
        */

        return desiredNode.data;
    }

    /**
     * Adds the specified element to the end of the list.
     *
     * @param data the element to be added to the end of the list
     */
    public void addLast(T data)
    {
        add(data);
    }

    /**
     * Removes all elements from the linked list, effectively clearing it.
     * After calling this method, the list will be empty.
     */
    public void clear()
    {
        firstNode = null;
        currentSize = 0;
    }

    /**
     * Retrieves the first element in the linked list.
     *
     * @return the data of the first node in the linked list.
     */
    public T getFirst()
    {
        if (isEmpty())
        {
            throw new NullPointerException("The list is empty");
        }
        return firstNode.data;
    }

    /**
     * Retrieves the last element in the linked list.
     *
     * @return the last element in the linked list.
     * @throws IndexOutOfBoundsException if the list is empty.
     */
    public T getLast()
    {
        return get(currentSize-1);
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     *
     * @param data the element to search for
     * @return the index of the first occurrence of the specified element in this list,
     *         or -1 if this list does not contain the element
     */
    public int indexOf(T data)
    {
        int index = 0;
        Node<T> currentNode = firstNode;
        while (currentNode != null)
        {
            if (currentNode.data.equals(data))
            {
                return index;
            }
            currentNode = currentNode.nextNode;
            index++;
        }
        return -1;
    }
    

    /**
     * Returns the index of the last occurrence of the specified element in this list,
     * or -1 if this list does not contain the element.
     *
     * @param data the element to search for
     * @return the index of the last occurrence of the specified element in this list,
     *         or -1 if this list does not contain the element
     */
    public int lastIndexOf(T data)
    {
        int lastIndex = -1;
        int index = 0;
        Node<T> currentNode = firstNode;
    
        while (currentNode != null)
        {
            if (currentNode.data.equals(data))
            {
                lastIndex = index; // update last index when found
            }
            currentNode = currentNode.nextNode;
            index++;
        }
    
        return lastIndex;
    }
    

    /**
     * Converts the List into an array.
     * 
     * @return an array containing all elements in the List
     */
    public T[] toArray()
    {
        if (isEmpty())
        {
            return (T[]) new Object[0];
        }
        T[] returnable = (T[]) java.lang.reflect.Array.newInstance(firstNode.data.getClass(), currentSize);
        Node<T> currentNode = firstNode;
        for (int i = 0; i < currentSize; i++)
        {
            returnable[i] = (T) currentNode.data;
            currentNode = currentNode.nextNode;
        }
        return returnable;
    }

    public T element()
    {
        return getFirst();
    }

    /**
     * Inserts the specified element at the end of this list.
     *
     * @param data the element to be added to the end of this list
     * @return {@code true} (as specified by {@link java.util.Queue#offer})
     */
    public boolean offer(T data)
    {
        addLast(data);
        return true;
    }

    /**
     * Inserts the specified element at the beginning of this list.
     *
     * @param data the element to add
     * @return {@code true} (as specified by {@link java.util.Deque#offerFirst})
     */
    public boolean offerFirst(T data)
    {
        addFirst(data);
        return true;
    }

    /**
     * Inserts the specified element at the end of this list.
     * 
     * @param data the element to add
     * @return {@code true} (as specified by {@link java.util.Deque#offerLast})
     */
    public boolean offerLast(T data)
    {
        addLast(data);
        return true;
    }

    /**
     * Retrieves, but does not remove, the first element of this list.
     *
     * @return the first element of this list, or {@code null} if this list is empty
     */
    public T peek()
    {
        return getFirst();
    }

    /**
     * Retrieves, but does not remove, the first element of this list.
     * 
     * @return the first element of this list, or {@code null} if this list is empty
     */
    public T peekFirst()
    {
        return getFirst();
    }

    /**
     * Retrieves, but does not remove, the last element of this list.
     * 
     * @return the last element of this list, or {@code null} if this list is empty
     */
    public T peekLast()
    {
        return getLast();
    }

    /**
     * Retrieves and removes the first element of this list.
     * 
     * @return the removed first element of this list, or {@code null} if this list is empty
     */
    public T poll()
    {
        return remove(currentSize - 1);
    }

    /**
     * Retrieves and removes the first element of this list
     * 
     * @return the removed first element of this list, or {@code null} if this list is empty
     */
    public T pollFirst()
    {
        return remove();
    }

    /**
     * Retrieves and removes the last element of this list.
     * 
     * @return the removed last element of this list, or {@code null} if this list is empty
     */
    public T pollLast()
    {
        return remove(currentSize - 1);
    }

    /**
     * Retrieves and removes the first element of this list.
     * 
     * @return the removed first element of this list, or {@code null} if this list is empty
     */
    public T pop()
    {
        return remove();
    }

    /**
     * Adds an element to the beginning of the linked list.
     *
     * @param data the element to be added to the list
     */
    public void push(T data)
    {
        addFirst(data);
    }

    /**
     * Retrieves and removes the first element of this list.
     * 
     * @return the removed first element of this list, or {@code null} if this list is empty
     */
    public T removeFirst()
    {
        return remove(0);
    }

    /**
     * Removes the first occurrence of the specified element from this linked list, if it is present.
     * If the list does not contain the element, it is unchanged.
     *
     * @param data the element to be removed from this list, if present
     * @return {@code true} if the list contained the specified element and it was removed;
     *         {@code false} otherwise
     */
    public boolean removeFirstOccurrence(T data)
    {
        if (firstNode == null)
        {
            return false;
        }
        if (firstNode.data.equals(data))
        {
            firstNode = firstNode.nextNode;
            currentSize--;
            return true;
        }
        Node<T> currentNode = firstNode;
        while (currentNode.nextNode != null)
        {
            if (currentNode.nextNode.data.equals(data))
            {
                currentNode.nextNode = currentNode.nextNode.nextNode;
                currentSize--;
                return true;
            }
            currentNode = currentNode.nextNode;
        }
        return false;
    }
    

    /**
     * Removes and returns the last element in the list.
     *
     * @return the last element in the list
     * @throws IndexOutOfBoundsException if the list is empty
     */
    public T removeLast()
    {
        return remove(currentSize - 1);
    }

    /**
     * Removes the last occurrence of the specified element from the linked list.
     *
     * @param data the element to be removed from the list
     * @return {@code true} if the list contained the specified element and it was removed;
     *         {@code false} otherwise
     */
    public boolean removeLastOccurrence(T data)
    {
        if (firstNode == null)
        {
            return false;
        }

        Node<T> currentNode = firstNode;
        Node<T> lastFound = null;
        Node<T> lastFoundPrev = null;
        Node<T> prev = null;
        while (currentNode != null)
        {
            if (currentNode.data.equals(data))
            {
                lastFound = currentNode;
                lastFoundPrev = prev;
            }
            prev = currentNode;
            currentNode = currentNode.nextNode;
        }

        if (lastFound == null)
        {
            return false;
        }
        if (lastFoundPrev == null)
        {
            firstNode = firstNode.nextNode;
        }
        else
        {
            lastFoundPrev.nextNode = lastFound.nextNode;
        }
        currentSize--;
        return true;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     *
     * @param index the index of the element to replace
     * @param data the element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IllegalArgumentException if the index is out of bounds (index < 0 || index >= currentSize)
     */
    public T set(int index, T data)
    {
        if (index < 0 || index >= currentSize)
        {
            throw new IllegalArgumentException("Out of bounds");
        }
        T replacedData;
        if (index == 0)
        {
            replacedData = firstNode.data;
            firstNode.data = data;
        }
        else
        {
            // find node at index position
            Node<T> replacedNode = firstNode;
            for (int i = 0; i < index; i++)
            {
                replacedNode = replacedNode.nextNode;
            }
            // insert data at node with matching index
            replacedData = replacedNode.data;
            replacedNode.data = data;
        }
        return replacedData;
    }
    
    /**
     * A node that stores data and a pointer to the next node.
     */
    private class Node<T>
    {
        private T data;
        private Node<T> nextNode;

        /**
         * Creates a Node with data and a pointer
         * 
         * @param data the data to store
         * @param nextNode the node after this one
         */
        public Node(T data, Node<T> nextNode)
        {
            this.data = data;
            this.nextNode = nextNode;
        }

        /**
         * Creates a Node with data and a null pointer
         * 
         * @param data the data to store
         */
        public Node(T data)
        {
            this(data, null);
        }

        /**
         * Creates a Node with null data and a null pointer
         */
        public Node()
        {
            this(null, null);
        }
    }
}
