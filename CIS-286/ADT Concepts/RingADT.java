/**
 * A class that stores data in a ring
 * 
 * @param T a generic object for storing data
 * 
 * @author Jordan Marcelo
 * @version 1.0
 * 2/3/25
 */
abstract class Ring<T>
{
    private Node firstNode;
    private int currentSize;
    private Node cursor;

    public abstract boolean isEmpty();

    public abstract int size();

    public abstract void add(T data);

    public abstract T remove();

    public abstract T next();

    public abstract T getData();


    private class Node
    {
        private T data;
        private Node nextNode;

        protected Node(T data, Node nextNode)
        {
            this.data = data;
            this.nextNode = nextNode;
        }

        protected Node(T data)
        {
            this.data = data;
            nextNode = null;
        }

        protected Node()
        {
            data = null;
            nextNode = null;
        }

        protected boolean equals(Node other)
        {
            return (other.data == data && other.nextNode == nextNode);
        }
    }
}