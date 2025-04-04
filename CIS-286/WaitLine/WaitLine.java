/** Simulates a waiting line. 
    @author Frank M. Carrano
    @author Timothy M. Henry
    @Modified on May 16, 2019
*/

public class WaitLine
{
   private final QueueInterface<Customer> line;
   private int numberOfArrivals;
   private int numberServed;
   private int totalTimeWaited;
   private Customer currentCustomer = null;
   private final String[] reasonsArr = {"getting a library card", "paying a fine", "checking out "};

   public WaitLine()
   {
      line = new LinkedQueue<>();
      reset();
    } // end default constructor

   /** Simulates a waiting line with one serving agent.
       @param duration  The number of minutes in the simulation
       @param arrivalProbability  A real number between 0 and 1, representing
                                  the probability that a customer arrives at
                                  a given time
   */
   public void simulate(int duration, double arrivalProbability)
   {
      int transactionTimeLeft = 0;
      boolean printed = false;
      currentCustomer = null;

      for (int clock = 0; clock < duration; clock++)
      {
         if (Math.random() < arrivalProbability)
         {
            //  Add new customer to queue

            // Determine reason for queuing based on initial additional transaction time
            int additionalTransactionTime = (int)(Math.random() * reasonsArr.length);
            String reason = reasonsArr[additionalTransactionTime];

            if (additionalTransactionTime == 2)
            {
               // How many books to check out?
               // Maximum 5 books, minimum 1 book
               additionalTransactionTime = (int)(Math.random() * 5) + 1;
               reason += additionalTransactionTime + " book(s)";
            }

            
            // Create customer
            numberOfArrivals++;
            int transactionTime = 1 + additionalTransactionTime;
            Customer nextArrival = new Customer(clock, transactionTime, reason);
            line.enqueue(nextArrival);
            System.out.println("Customer " + nextArrival.getCustomerNumber() +
                               " enters line at time " + clock +
                               " and is " + reason +
                               ". Transaction time required is " + transactionTime + ".");
         } // end if

         if (transactionTimeLeft > 0)
            transactionTimeLeft--;
         else
         {
            if (!printed)
            {
               if (currentCustomer != null)
               {
                  System.out.println("Customer "
                                     + currentCustomer.getCustomerNumber()
                                     + " has finished " + currentCustomer.getReason()
                                     + " and exits queue at time " + clock + ".");
                   printed = true;
               }

            }
            if (!line.isEmpty())
            {
               //  Process next customer in queue
               currentCustomer = line.dequeue();
               printed = false;
               transactionTimeLeft = currentCustomer.getTransactionTime() - 1;
               int timeWaited = clock - currentCustomer.getArrivalTime();
               totalTimeWaited += timeWaited;
               numberServed++;
               System.out.println("Customer " + currentCustomer.getCustomerNumber() +
                                  " begins " + currentCustomer.getReason() +
                                  " at time " + clock +
                                  ". Time waited is " + timeWaited + ".");
            }
         } // end if
      } // end for
      if (transactionTimeLeft > 0)
            System.out.println("Customer " + currentCustomer.getCustomerNumber() +
                                  " is still " + currentCustomer.getReason() + 
                                  " but simulation has ended.");
      numberServed--;

   } // end simulate

   /** Displays summary results of the simulation. */ 
   public void displayResults()
   {
      System.out.println();
      System.out.println("Number served = " + numberServed);
      System.out.println("Total time waited = " + totalTimeWaited);
      double averageTimeWaited = ((double)totalTimeWaited) / numberServed;
      System.out.format("Average time waited = %5.2f\n", averageTimeWaited);
      int leftInLine = numberOfArrivals - numberServed;
      System.out.println("Number left in line = " + leftInLine);
      if (!line.isEmpty())
      {
         System.out.println ("Customers left in line:");
         System.out.println ("   " + currentCustomer.getCustomerNumber());
         while (!line.isEmpty())
            {
            Customer tempCustomer = line.dequeue();
            System.out.println ("   " + tempCustomer.getCustomerNumber());
            }
      }
   } // end DisplayResults

   /** Initializes the simulation. */ 
   public final void reset()
   {
      line.clear();
      numberOfArrivals = 0;
      numberServed = 0;
      totalTimeWaited = 0;
   } // end reset
} // end WaitLine
