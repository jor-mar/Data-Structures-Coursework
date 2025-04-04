/**
 * Represents a book with an author and an ID
 * 
 * @author Jordan Marcelo
 * @version 1.0
 * 2/20/25
 * CIS 286 Professor Hoffman
 */
public class Book
{
    private final String title;
    private final int bookID;

    /**
     * Constructs a Book object with the specified details.
     *
     * @param title the title of the book
     * @param bookID the ID of the book
     */
    public Book(String title, int bookID)
    {
        this.title = title;
        this.bookID = bookID;
    }

    /**
     * Gets the title of the book
     *
     * @return the title of the book
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Gets the ID of the book
     * 
     * @return the ID of the book
     */
    public int getBookID()
    {
        return bookID;
    }

    /**
     * Creates a string representation of the book with its title and ID
     * 
     * @return a string representing the book
     */
    @Override
    public String toString()
    {
        return title + " [" + bookID + "]";
    }
}
