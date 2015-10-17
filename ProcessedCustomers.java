
/**
 * Write a description of class ProcessedCustomers here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ProcessedCustomers
{
    public int processed = 0;

    /**
     * Constructor for objects of class ProcessedCustomers
     */
    public ProcessedCustomers()
    {
        
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int addOne()
    {
        processed++;
        return processed;
    }
    
    public void printSatisfied()
    {
        System.out.println(processed);
    }
}
