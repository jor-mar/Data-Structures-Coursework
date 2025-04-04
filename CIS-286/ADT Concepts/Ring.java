/**
 * An interface for a ring ADT, which stores elements repeated infinitely in order
 * 
 * @param T a generic object acting as the data to be stored
 * 
 * @author Jordan Marcelo
 * @version 2.0
 * 3/25/25
 * CIS 286 - Professor Hoffman
 */
public interface Ring<T>
{
    /**
     * Sequentially moves the cursor to the next element in the ring
     *
     */
    public void advance();

    /**
     * Gets the data at the cursor without modifying it
     * 
     * @return the data at the cursor
     */
    public T getCurrent();

    /**
     * Removes the data at the cursor, leaving the selected element empty but still present in the ring
     * 
     * @return the data that has been cleared
     */
    public T remove();

    /**
     * Writes data at the cursor's selected element,
     * overwriting any existing data at this element
     * 
     * @param data the data to be stored at this element
     */
    public void add(T data);


    /**
     * A node wherein data of type T and a pointer to the next node in the ADT will be stored
     */
    class Node<T>
    {
        private T data;
        private Node<T> nextNode;

        /**
         * A constructor for a node meant to store data and a pointer to the next node in an ADT
         * 
         * @param data the data the node will hold
         * @param nextNode the node that this current node points to; the next node in the ADT's order
         */
        public Node(T data, Node<T> nextNode)
        {
            this.data = data;
            this.nextNode = nextNode;
        }

        /**
         * A constructor for a node meant to store data and a pointer to the next node in an ADT
         * Sets the nextNode attribute to null
         * 
         * @param data the data the node will hold
         */
        public Node(T data)
        {
            this.data = data;
            nextNode = null;
        }

        /**
         * A constructor for a node meant to store data and a pointer to the next node in an ADT
         * Sets the data and nextNode attributes to null
         */
        public Node()
        {
            data = null;
            nextNode = null;
        }

        /**
         * Retrieves the next node in the sequence
         *
         * @return the next node of type T
         */
        public Node<T> getNextNode()
        {
            return nextNode;
        }

        /**
         * Sets the next node in the sequence
         *
         * @param nextNode the next node to be linked
         */
        public void setNextNode(Node<T> nextNode)
        {
            this.nextNode = nextNode;
        }

        /**
         * Retrieves the data stored in this node
         *
         * @return the data of type T
         */
        public T getData() {
            return data;
        }

        /**
         * Sets the data for this node
         *
         * @param data the data to set
         */
        public void setData(T data) {
            this.data = data;
        }
    } // end Node
} // end Ring