public class Customer
{
    private final int number, arrivalTime, transactionTime;
    private final String reason;
    private static int customerCount = 0;
    

    //  Constructor
    Customer (int newArrivalTime, int newTransactionTime, String reason)
    {
        arrivalTime = newArrivalTime;
        transactionTime = newTransactionTime;
        this.reason = reason;
        number = ++customerCount;
    }
    
    public int getTransactionTime()
    {
        return transactionTime;
    }
    
    public int getArrivalTime()
    {
        return arrivalTime;
    }
    
    public int getCustomerNumber()
    {
       return number;
    }

    public String getReason()
    {
        return reason;
    }
}
