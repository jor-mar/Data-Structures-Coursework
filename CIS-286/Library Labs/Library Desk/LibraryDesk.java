import java.util.Random;

/**
 * A representation of a LibraryDesk, whereupon patrons or staff stack up books,
 * which must be handled by the librarian operating the front desk 
 * 
 * @author Jordan Marcelo
 * @version 1.0
 * 2/20/25
 * CIS 286 Professor Hoffman
 */
public class LibraryDesk
{
    public static void main(String[] args)
    {
        // Ensure results are consistent but do not have to be hard-coded
        Random rand = new Random(05102006);

        // List of possible books with randomized IDs
        // These IDs could be hard-coded
        Book[] Books =
        {
            new Book("The Great Gatsby", Math.abs(rand.nextInt())),
            new Book("To Kill a Mockingbird", Math.abs(rand.nextInt())),
            new Book("Pride and Prejudice", Math.abs(rand.nextInt())),
            new Book("East of Eden", Math.abs(rand.nextInt())),
            new Book("Wuthering Heights", Math.abs(rand.nextInt())),
            new Book("Hamlet", Math.abs(rand.nextInt())),
            new Book("Othello", Math.abs(rand.nextInt())),
            new Book("Romeo and Juliet", Math.abs(rand.nextInt())),
            new Book("Leviathan", Math.abs(rand.nextInt())),
            new Book("Lord of the Flies", Math.abs(rand.nextInt()))
        };

        LinkedStack<Book> stackOfBooks = new LinkedStack<>();
        
        // Randomly add and remove books to the stack
        for (int i = 0; i < rand.nextInt(3, 5); i++)
        {
            // Adding books to the stack
            for (int j = 0; j < rand.nextInt(5, 7); j++)
            {
                printPushBook(stackOfBooks, Books[rand.nextInt(0, Books.length)]);
            }

            System.out.println();

            // Removing books from the stack
            for (int j = 0; j < rand.nextInt(3, 5); j++)
            {
                printPopBook(stackOfBooks);
            }

            System.out.println();
        }

        // Remove all remaining books from the stack
        while (!stackOfBooks.isEmpty())
        {
            printPopBook(stackOfBooks);
        }
    }

    /**
     * Pops and prints details of a book that is popped from the top of the stack
     * 
     * @param stack The stack to be popped from
     * @return The book that was popped from the stack
     */
    public static Book printPopBook(LinkedStack<Book> stack)
    {
        Book popped = stack.pop();
        System.out.println("-\t\"" + popped.toString() + "\" was removed from the stack.");
        return popped;
    }

    /**
     * Pushes a book to the top of the stack and prints its title and ID
     * 
     * @param stack The stack to have a book pushed to its top
     * @param newBook The book to be pushed to the top of the stack
     */
    public static void printPushBook(LinkedStack<Book> stack, Book newBook)
    {
        stack.push(newBook);
        System.out.println("+\t\"" + newBook.toString() + "\" was added to the stack.");
    }
}
