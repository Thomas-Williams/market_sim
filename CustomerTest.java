

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CustomerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CustomerTest
{
    /**
     * Default constructor for test class CustomerTest
     */
    public CustomerTest()
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
    public void nextShortest()
    {
        Market marketForTesting = new Market(7.3);
        marketForTesting.addAll(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        Stall stall1 = new Stall("vegi", 1, 61, 31);
        Stall stall2 = new Stall("vegi", 1, 61, 31);
        stall1.addLines(1);
        Customer customer0 = new Customer(1, 1, marketForTesting.marketStalls);
        Customer customer1 = new Customer(2, 2, marketForTesting.marketStalls);
        Customer customer2 = new Customer(3, 3, marketForTesting.marketStalls);
        stall1.customerArrives(customer0);
        assertEquals(1, stall1.lines.get(0).getLength());
        stall1.customerArrives(customer1);
        assertEquals(1, stall1.lines.get(1).getLength());
        stall1.customerArrives(customer2);
        assertNotSame(stall1.lines.get(0).getLength(), stall1.lines.get(1).getLength());
        assertNotNull(stall1.findShortest());
    }
}
