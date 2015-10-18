

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class LineTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class LineTest
{
    /**
     * Default constructor for test class LineTest
     */
    public LineTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void isEmptyTest()
    {
        Line line1 = new Line();
        assertEquals(true, line1.emptyLine());
    }

    @Test
    public void addCustomerTest()
    {
        Market marketForTesting = new Market(7.3);
        marketForTesting.addAll(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        Line line1 = new Line();
        Customer customer = new Customer(1, 1, marketForTesting.marketStalls);
        line1.addCustomer(customer);
        assertEquals(1, line1.getLength());
    }
    
    @Test
    public void addCustomerTest2()
    {
        Market marketForTesting = new Market(7.3);
        marketForTesting.addAll(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        Line line1 = new Line();
        Customer customer0 = new Customer(1, 1, marketForTesting.marketStalls);
        Customer customer1 = new Customer(2, 2, marketForTesting.marketStalls);
        line1.addCustomer(customer0);
        line1.addCustomer(customer1);
        assertEquals(2, line1.getLength());
    }
    
    @Test
    public void removeCustomerTest()
    {
        Market marketForTesting = new Market(7.3);
        marketForTesting.addAll(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        Line line1 = new Line();
        Customer customer0 = new Customer(1, 1, marketForTesting.marketStalls);
        Customer customer1 = new Customer(2, 2, marketForTesting.marketStalls);
        line1.addCustomer(customer0);
        line1.addCustomer(customer1);
        assertEquals(true, line1.removeCustomer(customer0));
        assertEquals(1, line1.getLength());
    }
}


