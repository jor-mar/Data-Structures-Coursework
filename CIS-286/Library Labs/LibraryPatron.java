import java.util.ArrayList;

/**
 * Represents a library patron with details such as name, address, card number,
 * books currently held, and money owed.
 */
public class LibraryPatron
{
    private static int currentCardNum = 999999999; // Every card number is 10 digits

    private String name;
    private final int cardNum;
    private String street;
    private String city;
    private int zip;
    private double moneyOwed;
    private ArrayList<Book> BooksHeld = new ArrayList<>();
    private String reasonInLine;

    /**
     * Constructs a LibraryPatron with the specified details.
     * Automatically assigns a unique card number with at least 10 digits.
     *
     * @param name   the name of the patron
     * @param street the street address of the patron
     * @param city   the city of the patron
     * @param zip    the zip code of the patron
     */
    public LibraryPatron(String name, String street, String city, int zip)
    {
        this.name = name;
        this.street = street;
        this.city = city;
        this.zip = zip;
        this.moneyOwed = 0;
        currentCardNum += 1;
        this.cardNum = currentCardNum;
    }

    /**
     * Sets the reason the patron is in line.
     *
     * @param reason the reason the patron is in line
     */
    public void setReason(String reason)
    {
        this.reasonInLine = reason;
    }

    /**
     * Gets the reason the patron is in line.
     *
     * @return the reason the patron is in line
     */
    public String getReason()
    {
        return reasonInLine;
    }

    /**
     * Adds a book to the patron's list of books currently held.
     *
     * @param held the book to add
     */
    public void addBook(Book held)
    {
        BooksHeld.add(held);
    }

    /**
     * Adds multiple books to the patron's list of books currently held.
     *
     * @param addedBooks a list of books to add
     */
    public void addBook(ArrayList<Book> addedBooks)
    {
        BooksHeld.addAll(addedBooks);
    }

    /**
     * Adds multiple books to the patron's list of books currently held.
     *
     * @param addedBooks an array of books to add
     */
    public void addBook(Book[] addedBooks)
    {
        for (Book addedBook : addedBooks)
        {
            BooksHeld.add(addedBook);
        }
    }

    /**
     * Removes a specific book from the patron's list of books currently held.
     *
     * @param returned the book to remove
     */
    public void removeBook(Book returned)
    {
        BooksHeld.remove(returned);
    }

    /**
     * Removes a book from the patron's list by its index.
     *
     * @param i the index of the book to remove
     */
    public void removeBook(int i)
    {
        if (0 <= i && i < BooksHeld.size())
        {
            BooksHeld.remove(i);
        }
    }

    /**
     * Removes an array of books from the patron's list
     * 
     * @param BooksToBeRemoved an array of books to be removed
     */
    public void removeBook(Book[] BooksToBeRemoved)
    {
        for (Book BookToBeRemoved : BooksToBeRemoved)
        {
            BooksHeld.remove(BookToBeRemoved);
        }
    }

    /**
     * Removes an ArrayList of books from the patron's list
     * 
     * @param BooksToBeRemoved an arrayList of books to be removed
     */
    public void removeBook(ArrayList<Book> BooksToBeRemoved)
    {
        for (Book BookToBeRemoved : BooksToBeRemoved)
        {
            BooksHeld.remove(BookToBeRemoved);
        }
    }

    /**
     * Gets the list of books currently held by the patron.
     *
     * @return the list of books held
     */
    public ArrayList<Book> getBooksHeld()
    {
        return BooksHeld;
    }

    /**
     * Gets the number of books currently held by the patron.
     *
     * @return the number of books held
     */
    public int getNumBooksHeld()
    {
        return BooksHeld.size();
    }

    /**
     * Replaces the current list of books held with a new list.
     *
     * @param BooksHeld the new list of books
     */
    public void setBooksHeld(ArrayList<Book> BooksHeld)
    {
        this.BooksHeld = BooksHeld;
    }

    /**
     * Replaces the current list of books held with a new array of books.
     *
     * @param BooksHeld the new array of books
     */
    public void setBooksHeld(Book[] BooksHeld)
    {
        this.BooksHeld.clear();
        for (Book HeldBook : BooksHeld)
        {
            this.BooksHeld.add(HeldBook);
        }
    }

    /**
     * Sets the amount of money owed by the patron.
     *
     * @param moneyOwed the amount of money owed
     */
    public void setMoneyOwed(double moneyOwed)
    {
        this.moneyOwed = moneyOwed;
    }

    /**
     * Sets the patron's name.
     *
     * @param name the name of the patron
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the patron's street address.
     *
     * @param street the street address
     */
    public void setStreet(String street)
    {
        this.street = street;
    }

    /**
     * Sets the patron's zip code.
     *
     * @param zip the zip code
     */
    public void setZip(int zip)
    {
        this.zip = zip;
    }

    /**
     * Sets the patron's city.
     *
     * @param city the city of the patron
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * Gets the patron's name.
     *
     * @return the name of the patron
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets the patron's street address.
     *
     * @return the street address of the patron
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * Gets the patron's city.
     *
     * @return the city of the patron
     */
    public String getCity()
    {
        return city;
    }

    /**
     * Gets the patron's zip code.
     *
     * @return the zip code of the patron
     */
    public int getZip()
    {
        return zip;
    }

    /**
     * Gets the patron's zip code.
     *
     * @return the zip code of the patron
     */
    public int getZipCode()
    {
        return zip;
    }

    /**
     * Gets the patron's library card number.
     *
     * @return the card number
     */
    public int getCardNumber()
    {
        return cardNum;
    }

    /**
     * Gets the amount of money owed by the patron.
     *
     * @return the amount of money owed
     */
    public double getMoneyOwed()
    {
        return moneyOwed;
    }

    /**
     * Gets the address of the patron.
     * 
     * @return the full address of the patron.
     */
    public String getAddress()
    {
        return (getStreet() + ", " + getCity()) + " " + getZip();
    }
}