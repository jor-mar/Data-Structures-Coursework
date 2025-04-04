/**
 * A class that stores data in a linked list
 * 
 * @param <T> a generic object which will act as the data to be stored
 * 
 * @author Jordan Marcelo
 * @version 1.0
 * 2/19/25
 * CIS 286 Professor Hoffman
 */
public class LinkedBag<T>
{
    private Node<T> firstNode;
    private int currentSize;

    /**
     * Constructs an empty LinkedBag.
     */
    public LinkedBag()
    {
        // Empty constructor
    }

    /**
     * Checks if the bag is empty.
     * 
     * @return true if the bag is empty, false otherwise
     */
    public boolean isEmpty()
    {
        return (currentSize == 0);
    }

    /**
     * Gets the number of elements in the bag.
     * 
     * @return the current size of the bag
     */
    public int size()
    {
        return currentSize;
    }

    /**
     * Adds a new element to the bag.
     * 
     * @param data the element to add
     */
    public void add(T data)
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
    }

    /**
     * Adds an element at the beginning of the bag.
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
     * Removes the first element from the bag.
     * 
     * @return the removed element, or null if the bag is empty
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
     * Removes the first occurrence of a specific element from the bag.
     * 
     * @param data the element to remove
     * @return true if the element was removed, false otherwise
     */
    public boolean remove(T data)
    {
        if (firstNode == null)
        {
            // cannot remove anything from empty linked bag
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
            // find node preceding the node with desired data, or the last node of the linked bag
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
            // reached end of linked bag with no matches
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
     * A node that stores data and a pointer to the next node.
     */
    private static class Node<T>
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
