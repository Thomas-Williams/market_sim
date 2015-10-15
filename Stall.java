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
    
    LinkedList<LinkedList> lines = new LinkedList<LinkedList>();
    LinkedList workers = new LinkedList();
    RandomGaussian timeGen = new RandomGaussian();

    /**
     * Constructor for objects of class Stall
     */
    public Stall(String t, int s, int d, int i)
    {
        type = t;
        SERVETIMEMEAN = s;
        SERVETIMESTDV = d;
        stallID = i;
        
        LinkedList<Customer> line = new LinkedList<Customer>();
        lines.add(line);
        
        Worker worker0 = new Worker();
        workers.add(worker0);
        Worker worker1 = new Worker();
        workers.add(worker1);
    }
    
    public LinkedList stallClock()
    {
        int nextDeparture  = 0;
        int currentLine = 0;
        LinkedList tmpLine = lines.get(0);
        Customer tmpCustomer = tmpLine.get(0);

        for(int time = 0; ; time++){
            if(tmpCustomer.getServeTime() < lines.get(1).get(0).getServeTime()){
                nextDeparture += lines.get(0).get(0).getServeTime();
                currentLine = 0;
            } else {
                nextDeparture += lines.get(1).get(0).getServeTime();
                currentLine = 1;
            }
            
            if(time == nextDeparture){
                serveCustomer(lines.get(currentLine).get(0));
                lines.get(currentLine).remove(0);
            }
        }
        
        return lines;
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
            Line<Customer> line = new Line<Customer>();
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
    
    public void serveCustomer(Customer c)
    {
        Line currentLine;
        for(int i = 0; i < lines.size(); i++){
            currentLine = lines.get(i);
            for(int j = 0; j < currentLine.size(); j++){
                if(currentLine.get(j) == c){
                    c.fulfillNeed(type);
                    c.moveLines(c.nextShortest());
                    currentLine.remove(c);
                }
            }
        }
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