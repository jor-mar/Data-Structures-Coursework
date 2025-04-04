import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * An implementation of a double linked list data structure
 * https://docs.oracle.com/javase/8/docs/api/java/util/List.html
 * @author Balaji.Srinivasan@evc.edu, Jordan Marcelo
 * 03/10/25
 */
public class MyDoubleLinkedList<T> implements List<T> {
    /**
     * Each node in the linked list is represented by this class. It contains a forward and backward pointer
     * to allow traversal in both directions
     */
    static class Node<T> {
        T data;
        Node<T> prev, next;

        /**
         * Creates a new node and set its prev/next pointers. 
         * NOTE: This does NOT adjust the pointers of the prev and next nodes by design.
         */
        public Node(T t, Node<T> prev, Node<T> next) {
            this.data = t;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * The head of the list.
     */ 
    Node<T> head;

    /**
     * The tail of the list.
     */
    Node<T> tail;

    /**
     * The number of elements in the list.
     */
    int numElements;

    /**
     * This class implements the ListIterator interface for a double-linked list.
     * It provides methods to traverse and manipulate the list in both forward and backward directions.
     * https://docs.oracle.com/javase/8/docs/api/java/util/ListIterator.html
     */
    class DoubleLinkedListIterator<T> implements ListIterator<T> {
        
        Node<T> current;
        // int index;

        /**
         * Constructs a new DoubleLinkedListIterator that starts at the given node.
         * 
         * @param startingNode the node at which to start iterating the list
         */
        public DoubleLinkedListIterator(Node<T> startingNode) {
            current = startingNode;
            // index = 0;
        }

        /**
         * Checks if there is another element when iterating forward.
         * 
         * @return true if there is a next element, false otherwise
         */
        @Override
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the list while advancing the iterator.
         * 
         * @return the next element
         * @throws NoSuchElementException if there is no next element
         */
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            // index++;
            return data;
        }

        /**
         * Checks if there is a previous element when iterating backward.
         * 
         * @return true if there is a previous element, false otherwise
         */
        @Override
        public boolean hasPrevious() {
            return current != null && current.prev != null;
        }

        /**
         * Returns the previous element in the list while moving the iterator backward.
         * 
         * @return the previous element
         * @throws NoSuchElementException if there is no previous element
         */
        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            current = current.prev;
            // index--;
            return current.data;
        }

        /**
         * This method is unsupported and is not implemented in this iterator.
         * 
         * @throws UnsupportedOperationException if called
         */
        @Override
        public int nextIndex() {
            throw new UnsupportedOperationException("Unimplemented method 'nextIndex'");
        }

        /**
         * This method is unsupported and is not implemented in this iterator.
         * 
         * @throws UnsupportedOperationException if called
         */
        @Override
        public int previousIndex() {
            throw new UnsupportedOperationException("Unimplemented method 'previousIndex'");
        }

        /**
         * This method is unsupported and is not implemented in this iterator.
         * 
         * @throws UnsupportedOperationException if called
         */
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Unimplemented method 'remove'");
        }

        /**
         * This method is unsupported and is not implemented in this iterator.
         * 
         * @throws UnsupportedOperationException if called
         */
        @Override
        public void set(T e) {
            throw new UnsupportedOperationException("Unimplemented method 'set'");
        }

        /**
         * This method is unsupported and is not implemented in this iterator.
         * 
         * @throws UnsupportedOperationException if called
         */
        @Override
        public void add(T e) {
            throw new UnsupportedOperationException("Unimplemented method 'add'");
        }
    }


    /**
     * Returns the number of elements in this list.
     * 
     * @return the number of elements in this list.
     */
    @Override
    public int size() {
        return numElements;
    }

    /**
     * Returns true if this list contains no elements.
     * 
     * @return true if this list is empty, otherwise false.
     */
    @Override
    public boolean isEmpty() {
        return numElements == 0;
    }

    /**
     * Returns true if this list contains the specified element.
     * 
     * @param o element whose presence in this list is to be tested.
     * @return true if this list contains the specified element, otherwise false.
     */
    @Override
    public boolean contains(Object o) {
        Node<T> current = head;
        while (current != null) {
            if ((o == null && current.data == null) || (o != null && o.equals(current.data))) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * 
     * @return an iterator over the elements in this list in proper sequence.
     */
    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedListIterator<>(head);
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence.
     * 
     * @return an array containing all of the elements in this list in proper sequence.
     */
    @Override
    public Object[] toArray() {
        Object[] arr = new Object[numElements];
        Node<T> current = head;
        for (int i = 0; i < numElements; i++) {
            arr[i] = current.data;
            current = current.next;
        }
        return arr;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence.
     * 
     * @param a the array into which the elements of the list are to be stored.
     * @return an array containing the elements of the list in proper sequence.
     * @throws UnsupportedOperationException if the method is not implemented.
     */
    @Override
    public <E> E[] toArray(E[] a) {
        throw new UnsupportedOperationException("Unimplemented method 'toArray'");
    }

    /**
     * Appends the specified element to the end of this list.
     * 
     * @param t element to be appended to this list.
     * @return true if the list changed as a result of the call.
     */
    @Override
    public boolean add(T t) {
        if (isEmpty()) {
            head = new Node<>(t, null, null);
            tail = head;
        }
        else {
            tail.next = new Node<>(t, tail, null);
            tail = tail.next;
        }
        numElements++;
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list.
     * 
     * @param o element to be removed from the list.
     * @return true if the list contained the specified element, otherwise false.
     */
    @Override
    public boolean remove(Object o) {
        Node<T> current = head;
        while (current != null) {
            if ((o == null && current.data == null) || (o != null && o.equals(current.data))) {
                // sole element
                if (current == head && current == tail) {
                    head = null;
                    tail = null;
                }
                // head
                else if (current == head) {
                    head = current.next;
                    head.prev = null;
                }
                // tail
                else if (current == tail) {
                    tail = current.prev;
                    tail.next = null;
                }
                // middle
                else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
                numElements--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /**
     * Returns true if this list contains all of the elements in the specified collection.
     * 
     * @param c collection to be checked for containment in this list.
     * @return true if this list contains all of the elements in the specified collection, otherwise false.
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position.
     * 
     * @param index index at which to insert the first element from the specified collection.
     * @param c collection containing elements to be inserted into this list.
     * @return true if this list changed as a result of the call.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > numElements) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + numElements);
        }

        if (c.isEmpty()) {
            return false;
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        for (T element : c) {
            Node<T> newNode = new Node<>(element, current == null ? null : current.prev, current);
            if (current == null) {
                if (tail == null) {
                    head = newNode;
                }
                else {
                    tail.next = newNode;
                }
                tail = newNode;
            }
        else {
                if (current.prev == null) {
                    head = newNode;
                }
                else {
                    current.prev.next = newNode;
                }
                current.prev = newNode;
            }
            numElements++;
        }

        return true;
    }

    /**
     * Appends all elements in the specified collection to the end of this list.
     * 
     * @param c collection containing elements to be appended to this list.
     * @return true if this list changed as a result of the call.
     */
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T element : c) {
            add(element);
            modified = true;
        }
        return modified;
    }

    /**
     * Removes all occurrences of the specified elements from this list.
     * 
     * @param c collection containing elements to be removed from this list.
     * @return true if the list was modified as a result of the call.
     */
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        for (Object o : c) {
            while (remove(o)) {
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Retains only the elements in this list that are contained in the specified collection.
     * 
     * @param c collection containing elements to be retained in this list.
     * @return true if the list was modified as a result of the call.
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Node<T> current = head;
        while (current != null) {
            if (!c.contains(current.data)) {
                Node<T> next = current.next;
                remove(current.data);
                current = next;
                modified = true;
            }
            else {
                current = current.next;
            }
        }
        return modified;
    }

    /**
     * Removes all elements from this list.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
        numElements = 0;
    }

    /**
     * Returns the element at the specified position in this list.
     * 
     * @param index index of the element to return.
     * @return the element at the specified position in this list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + numElements);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * 
     * @param index index of the element to replace.
     * @param element element to be stored at the specified position.
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + numElements);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        T oldData = current.data;
        current.data = element;
        return oldData;
    }

    /**
     * Inserts the specified element at the specified position in this list.
     * 
     * @param index index at which the specified element is to be inserted.
     * @param element element to be inserted.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > numElements) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + numElements);
        }

        if (index == numElements) {
            add(element);
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        Node<T> newNode = new Node<>(element, current.prev, current);
        if (current.prev == null) {
            head = newNode;
        }
        else {
            current.prev.next = newNode;
        }
        current.prev = newNode;
        numElements++;
    }

    /**
     * Removes the element at the specified position in this list.
     * 
     * @param index index of the element to be removed.
     * @return the element that was removed from the list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + numElements);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        
        if (current == head) {
            head = current.next;
            if (head != null) {
                head.prev = null;
            }
        }
        else {
            current.prev.next = current.next;
        }

        if (current == tail) {
            tail = current.prev;
            if (tail != null) {
                tail.next = null;
            }
        }
        else {
            current.next.prev = current.prev;
        }

        numElements--;
        return current.data;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this list.
     * 
     * @param o element to search for.
     * @return the index of the first occurrence of the specified element in this list, or -1 if not found.
     */
    @Override
    public int indexOf(Object o) {
        Node<T> current = head;
        int index = 0;

        while (current != null) {
            if ((o == null && current.data == null) || (o != null && o.equals(current.data))) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }

    /**
     * Returns the index of the last occurrence of the specified element in this list.
     * 
     * @param o element to search for.
     * @return the index of the last occurrence of the specified element in this list, or -1 if not found.
     */
    @Override
    public int lastIndexOf(Object o) {
        Node<T> current = tail;
        int index = numElements - 1;

        while (current != null) {
            if ((o == null && current.data == null) || (o != null && o.equals(current.data))) {
                return index;
            }
            current = current.prev;
            index--;
        }

        return -1;
    }

    /**
     * Returns a list iterator over the elements in this list in proper sequence.
     * 
     * @return a list iterator over the elements in this list in proper sequence.
     */
    @Override
    public ListIterator<T> listIterator() {
        return new DoubleLinkedListIterator<>(head);
    }

    /**
     * Returns a list iterator over the elements in this list starting at the specified position.
     * 
     * @param index index of the first element to be returned.
     * @return a list iterator over the elements in this list starting at the specified position.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    @Override
    public ListIterator<T> listIterator(int index) {
        if (index < 0 || index >= numElements) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + numElements);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return new DoubleLinkedListIterator<>(current);
    }

    /**
     * Returns a view of the portion of this list between the specified fromIndex (inclusive) and toIndex (exclusive).
     * 
     * @param fromIndex the starting index (inclusive).
     * @param toIndex the ending index (exclusive).
     * @return a view of the specified portion of the list.
     * @throws IndexOutOfBoundsException if either index is out of range.
     */
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > numElements || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("FromIndex: " + fromIndex + ", ToIndex: " + toIndex + ", Size: " + numElements);
        }

        MyDoubleLinkedList<T> subList = new MyDoubleLinkedList<>();
        Node<T> current = head;
        for (int i = 0; i < fromIndex; i++) {
            current = current.next;
        }

        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(current.data);
            current = current.next;
        }

        return subList;
    }
}