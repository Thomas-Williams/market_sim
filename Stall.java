import java.util.*;

/**
 * Write a description of class Stall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stall
{
    public String type;
    private int stallID;
    private int SERVETIMEMEAN;
    private int SERVETIMESTDV;
    
    LinkedList<Line> lines = new LinkedList<Line>();
    LinkedList workers = new LinkedList();
    RandomGaussian timeGen = new RandomGaussian();
    Register pos = new Register();

    /**
     * Constructor for objects of class Stall
     */
    public Stall(String t, int s, int d, int i)
    {
        type = t;
        SERVETIMEMEAN = s;
        SERVETIMESTDV = d;
        stallID = i;
        
        Line line = new Line();
        lines.add(line);
        
        Worker worker0 = new Worker();
        workers.add(worker0);
        Worker worker1 = new Worker();
        workers.add(worker1);
    }
    
    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public void addLines(int l)
    {
        for(int lineCount = 0; lineCount < l; lineCount++){
            Line line = new Line();
            Worker worker = new Worker();
            lines.add(line);
            workers.add(worker);
        }
    }
    
    public void customerArrives(Customer c)
    {
        findShortest().add(c);
    }
    
    public void serveTime(Customer c)
    {
        c.setServeTime((int) timeGen.getGaussian(SERVETIMEMEAN, SERVETIMESTDV));
    }
    
    public Line findShortest()
    {
        Line shortestLine = lines.get(0);
        for(int i = 1; i < lines.size(); i++){
            if(lines.get(i).size() < shortestLine.size()){
                shortestLine = lines.get(i);
            }
        }
        return shortestLine;
    }
}
