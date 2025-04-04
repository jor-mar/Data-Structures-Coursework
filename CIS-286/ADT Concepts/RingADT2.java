/**
 * A class that stores data in a ring
 * 
 * @param T a generic object for storing data
 * 
 * @author Jordan Marcelo
 * @version 1.0
 * 2/3/25
 */
public class RingADT2<T>
{
    private Node firstNode;
    private int currentSize;
    private Node cursor;

    public RingADT2()
    {
        firstNode = null;
        cursor = null;
        currentSize = 0;
    }

    public boolean isEmpty()
    {
        return (currentSize == 0);
    }

    public int size()
    {
        return currentSize;
    }

    public void add(T data)
    {
        Node newNode = new Node(data);
        if (isEmpty())
        {
            firstNode = newNode;
            newNode.nextNode = firstNode;
            cursor = newNode;
        }
        else
        {
            Node afterNewNode = cursor.nextNode;
            cursor.nextNode = newNode;
            newNode.nextNode = afterNewNode;
        }
        cursor = newNode;
        currentSize++;
    }

    public T remove()
    {
        if (isEmpty())
        {
            throw new RuntimeException("Cannot remove from an empty ring.");
        }
        if (currentSize == 1)
        {
            firstNode = null;
            T removedData = cursor.data;
            cursor = null;
            return removedData;
        }
        Node priorNode = firstNode;
        Node priorNodeNext = firstNode.nextNode;
        while (!priorNodeNext.equals(cursor))
        {
            priorNode = priorNode.nextNode;
            priorNodeNext = priorNode.nextNode;
        }
        T removedData = cursor.data;
        priorNode.nextNode = priorNode.nextNode.nextNode;
        currentSize--;
        return removedData;
    }

    public void next()
    {
        cursor = cursor.nextNode;
    }

    public T getData()
    {
        if (cursor == null)
        {
            return null;
        }
        else
        {
            return cursor.data;
        }
    }


    private class Node
    {
        private T data;
        private Node nextNode;

        private Node(T data, Node nextNode)
        {
            this.data = data;
            this.nextNode = nextNode;
        }

        private Node(T data)
        {
            this.data = data;
            nextNode = null;
        }

        private Node()
        {
            data = null;
            nextNode = null;
        }

        private boolean equals(Node other)
        {
            return (other.data == data && other.nextNode == nextNode);
        }
        /*
        private void setData(T data)
        {
            this.data = data;
        }
        
        private void setNext(Node nextNode)
        {
            this.nextNode = nextNode;
        }

        private Node getNext()
        {
            return nextNode;
        }

        private T getData()
        {
            return data;
        }
        */
    }
}