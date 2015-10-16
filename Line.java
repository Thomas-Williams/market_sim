import java.util.*;

/**
 * Write a description of class LineQueue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Line
{
    LinkedList<Customer> line;
    
    /**
     * Constructor for objects of class LineQueue
     */
    public Line() {
        line = new LinkedList<Customer>();
    }
    
    public boolean addCustomer(Customer c)
    {
        line.add(c);
        return true;
    }
    
    public boolean removeCustomer(Customer c)
    {
        line.remove(c);
        return true;
    }
    
    public int getLength()
    {
        return line.size();
    }
    
    public Customer getCustomer(int i)
    {
        return line.get(i);
    }
    
    public boolean emptyLine()
    {
        return line.isEmpty();
    }
}
