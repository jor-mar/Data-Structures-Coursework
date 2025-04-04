import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Represents a library system simulation with patrons and books. 
 * Simulates patrons entering and leaving the library, managing their books, 
 * and handling their interactions such as checking out books, getting a library card, or paying fines.
 */
public class Library
{
    public static void main(String args[])
    {
        // Declares an ArrayList to store current patrons
        ArrayList<LibraryPatron> PatronList = new ArrayList<>();

        // List of reasons to be in line
        String[] lineReasons =
        {
            "Checking out books",
            "Getting a library card",
            "Paying a fine"
        };

        // List of possible patrons
        LibraryPatron[] PatronArray =
        {
            new LibraryPatron("John Doe", "Mulberry Ave", "Seusville", 91006),
            new LibraryPatron("Barack Obama", "Pennsylvania Avenue", "Washington", 20500),
            new LibraryPatron("John Deere", "Colorado Blvd", "Pasadena", 91106),
            new LibraryPatron("Jordan Marcelo", "Artesia Blvd", "Arcadia", 91007),
            new LibraryPatron("Tom Sawyer", "South Mississippi Lane", "Jackson", 39056),
            new LibraryPatron("Jack Smith", "New Jersey Avenue", "Houston", 35310),
            new LibraryPatron("Jane Doe", "Maryland Avenue", "Baltimore", 12345),
            new LibraryPatron("John DeNero", "First Avenue", "Berkeley", 654321),
            new LibraryPatron("Jeff Bezos", "Pine Street", "Seattle", 456123),
            new LibraryPatron("Jill Stein", "Sunset Blvd", "Culver City", 90136)
        };

        // List of possible books
        Book[] Books =
        {
            new Book("The Great Gatsby", "F. Scott Fitzgerald", 420.014, 3, 7),
            new Book("To Kill a Mockingbird", "Harper Lee", 823.912, 2, 5),
            new Book("Pride and Prejudice", "Jane Austen", 791.447, 1, 1),
            new Book("East of Eden", "John Steinbeck", 808.832, 3, 1),
            new Book("Wuthering Heights", "Emily Bronte", 823.009, 2, 9),
            new Book("Hamlet", "William Shakespeare", 822.33, 4, 6),
            new Book("Othello", "William Shakespeare", 822.33, 2, 7),
            new Book("Romeo and Juliet", "William Shakespeare", 822.33, 1, 9),
            new Book("Leviathan", "Thomas Hobbes", 320.1, 4, 4),
            new Book("Lord of the Flies", "William Golding", 823.91, 2, 3)
        };

        // Manipulate the patron list to simulate "random" patrons entering and leaving the library
        PatronList.add(PatronArray[4]);
        PatronList.add(PatronArray[2]);
        PatronList.add(PatronArray[8]);
        PatronList.remove(0);
        printPatronList(PatronList);

        PatronList.add(PatronArray[9]);
        PatronList.add(PatronArray[1]);
        PatronList.add(PatronArray[0]);
        PatronList.remove(3);
        PatronList.remove(0);
        printPatronList(PatronList);

        PatronList.add(PatronArray[3]);
        PatronList.add(PatronArray[5]);
        PatronList.add(PatronArray[7]);
        PatronList.add(1, PatronArray[6]);
        PatronList.remove(5);
        PatronList.remove(2);
        PatronList.remove(4);
        printPatronList(PatronList);

        // Simulate a patron checking out books
        PatronList.get(0).setBooksHeld(Books);
        PatronList.get(0).setReason(lineReasons[0]);
        managePatronList(PatronList, 1);

        // Simulate a patron getting a new membership
        PatronList.get(0).setReason(lineReasons[1]);
        managePatronList(PatronList, 1);

        // Simulate a patron paying a fine
        PatronList.get(0).setReason(lineReasons[2]);
        PatronList.get(0).setMoneyOwed(30.55);
        managePatronList(PatronList, 1);
    }

    /**
     * Prints the list of patrons currently in the library.
     *
     * @param Patrons the list of patrons
     */
    public static void printPatronList(ArrayList<LibraryPatron> Patrons)
    {
        System.out.println("Current library patron list:\n");
        for (LibraryPatron Patron : Patrons)
        {
            printPatron(Patron);
        }
    }

    /**
     * Prints the details of a single patron.
     *
     * @param Patron the patron whose details are to be printed
     */
    public static void printPatron(LibraryPatron Patron)
    {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        System.out.println("\tName: " + Patron.getName());
        System.out.println("\tCard Number: " + Patron.getCardNumber());
        System.out.println("\tMoney owed: " + formatter.format(Patron.getMoneyOwed()));
        System.out.println("\tAddress: " + Patron.getAddress());
        System.out.println();
    }

    /**
     * Manages the patron line by simulating their interaction(s) at the front desk in FIFO order
     *
     * @param PatronList the list of patrons in line
     * @param numPatrons the number of patrons to process in FIFO order
     */
    public static void managePatronList(ArrayList<LibraryPatron> PatronList, int numPatrons)
    {
        if (0 < numPatrons && numPatrons <= PatronList.size())
        {
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            String[] lineReasons = {"Checking out books", "Getting a library card", "Paying a fine"};
            for (int i = 0; i < numPatrons; i++)
            {
                LibraryPatron Patron = PatronList.get(0);
                String reason = Patron.getReason();
                String statement;
                if (reason.equals(lineReasons[0])) // reason is "Checking out books"
                {
                    statement = " wishes to check out " + Patron.getNumBooksHeld() + " books";
                }
                else if (reason.equals(lineReasons[1])) // reason is "Getting a library card"
                {
                    statement = " lives in " + Patron.getCity();
                }
                else if (reason.equals(lineReasons[2])) // reason is "Paying a fine"
                {
                    statement = " owes " + formatter.format(Patron.getMoneyOwed());
                }
                else
                {
                    statement = " has no reason to be in line";
                }
                System.out.println(Patron.getName() + statement + ".");
                PatronList.remove(0);
            } // End for loop
        } // End if statement
    } // End managePatronList
} // End class Library
