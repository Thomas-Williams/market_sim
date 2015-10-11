import java.util.*;

/**
 * Write a description of class LineQueue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Line<E> extends AbstractQueue<E> {
    LinkedList<E> CustomerQueue;
    
    /**
     * Constructor for objects of class LineQueue
     */
    public Line( ) {
        super();
        CustomerQueue = new LinkedList<E>();
    }

    /**
     * Method Info
     * 
     * @param
     * @return 
     */
    public E peek( ) {
        return CustomerQueue.peek();
    }
    
    /**
     * Method Info
     * 
     * @param
     * @return 
     */
    public E poll( ) {
        return CustomerQueue.poll();
    }
    
    /**
     * Method Info
     * 
     * @param
     * @return 
     */
    public E remove( ) {
        return CustomerQueue.remove();
    }
    
    /**
     * Method Info
     * 
     * @param
     * @return 
     */
    public int size( ) {
        return CustomerQueue.size();
    }
    
    /**
     * Method Info
     * 
     * @param
     * @return 
     */
    public Iterator<E> iterator( ) {
        return CustomerQueue.iterator( );
    }
    
    /**
     * Method Info
     * 
     * @param
     * @return 
     */
    public boolean offer( E e ) {
        return CustomerQueue.offer( e );
    }
}
