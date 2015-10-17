
/**
 * Write a description of class Space here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Space
{
    // instance variables - replace the example below with your own
    private int space = 750;

    /**
     * Constructor for objects of class Space
     */
    public Space()
    {
        
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int addStall()
    {
        space = space - 15;
        return space;
    }
    
    public int addCustomer()
    {
        space = space - 1;
        return space;
    }
    
    public int addWorker()
    {
        space = space - 1;
        return space;
    }
}
