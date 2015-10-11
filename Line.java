import java.util.*;

/**
 * Write a description of class LineQueue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Line extends LinkedList {
    LinkedList CustomerLine;
    
    /**
     * Constructor for objects of class LineQueue
     */
    public Line( ) {
        super();
        CustomerLine = new LinkedList();
    }
}
