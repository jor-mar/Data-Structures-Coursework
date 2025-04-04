public class TestWaitLine
{
    public static void main (String [] args)
    {
        WaitLine customerLine = new WaitLine();
        //  Simulate a waiting line with 20 minutes, 50% arrival probability,
        //  and 5 minute maximum transactop time
        customerLine.simulate(20, 0.6);
        customerLine.displayResults();
    }
}