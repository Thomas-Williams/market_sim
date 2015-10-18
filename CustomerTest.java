

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
    public void nextShorTest()
    {
        Market marketForTesting = new Market(7.3);
        marketForTesting.addAll(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1);
        Stall stall1 = new Stall("vegi", 1, 61, 31);
        Stall stall2 = new Stall("vegi", 1, 61, 31);
        stall1.addLines(1);
        Customer customer0 = new Customer(1, 1, marketForTesting.marketStalls);
        stall1.customerArrives(customer0);
        assertNotNull(customer0.nextShortest());
        assertEquals(stall2, customer0.moveStall(stall2));
        assertEquals(4, customer0.setServeTime(4));
        assertEquals(4, customer0.getServeTime());
    }
}

