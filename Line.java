import java.util.*;

/**
 * Write a description of class LineQueue here.
 * 
 * @author Thomas Williams 
 * @version v1.0
 */
public class Line
{
    LinkedList<Customer> line;
    
    /**
     * The default constructor for a line. This just makes a LinkedList that customers will be added to
     */
    public Line() {
        line = new LinkedList<Customer>();
    }
    
    /**
     * Add a given customer to the line at the end of the line
     * 
     * @param c  the customer to be added
     * @return  if the if the customer was added
     */
    public boolean addCustomer(Customer c)
    {
        line.add(this.getLength(), c);
        return true;
    }
    
    /**
     * Removes a given customer from the line, independent of index of that customer
     * 
     * @param c  the customer to be removed
     * @return if the customer was removed or not
     */
    public boolean removeCustomer(Customer c)
    {
        line.remove(c);
        return true;
    }
    
    /**
     * Return the legnth of THIS instance of the line class
     * 
     * @return  the length of THIS line
     */
    public int getLength()
    {
        return line.size();
    }
    
    /**
     * Retreives a customer at a specified index
     * 
     * @param i  the index to get a customer at
     * @return  the customer at the given index
     */
    public Customer getCustomer(int i)
    {
        return line.get(i);
    }
    
    /**
     * Checks is the list of customers is empty
     * 
     * @return is the list is empty or not
     */
    public boolean emptyLine()
    {
        return line.isEmpty();
    }
}
