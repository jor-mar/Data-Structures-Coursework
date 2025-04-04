/**
 * Represents a book with details such as title, author, Dewey Decimal number,
 * floor, and section within a library.
 */
public class Book
{
    private final String title;
    private final String author;
    private final double deweyDecimal;
    private int floor;
    private int section;

    /**
     * Constructs a Book object with the specified details.
     *
     * @param title        the title of the book
     * @param author       the author of the book
     * @param deweyDecimal the Dewey Decimal classification number of the book
     * @param floor        the floor where the book is located
     * @param section      the section where the book is located
     */
    public Book(String title, String author, double deweyDecimal, int floor, int section)
    {
        this.title = title;
        this.author = author;
        this.deweyDecimal = deweyDecimal;
        this.floor = floor;
        this.section = section;
    }

    /**
     * Gets the title of the book.
     *
     * @return the title of the book
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Gets the author of the book.
     *
     * @return the author of the book
     */
    public String getAuthor()
    {
        return author;
    }

    /**
     * Gets the Dewey Decimal classification number of the book.
     *
     * @return the Dewey Decimal classification number
     */
    public double getDeweyDecimal()
    {
        return deweyDecimal;
    }

    /**
     * Gets the floor where the book is located.
     *
     * @return the floor where the book is located
     */
    public int getFloor()
    {
        return floor;
    }

    /**
     * Gets the section where the book is located.
     *
     * @return the section where the book is located
     */
    public int getSection()
    {
        return section;
    }

    /**
     * Sets the floor where the book is located.
     *
     * @param floor the new floor for the book
     */
    public void setFloor(int floor)
    {
        this.floor = floor;
    }

    /**
     * Sets the section where the book is located.
     *
     * @param section the new section for the book
     */
    public void setSection(int section)
    {
        this.section = section;
    }
}
