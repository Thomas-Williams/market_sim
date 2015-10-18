import java.util.*;

/**
 * The stall is responsible for almost all of the customer manipulation within the program. From determining
 * when a customer get to the stall to how long it will take for the stall to fulfill their needs as well as
 * telling a customer to find the next stall on their list once their needs from a given stall has been filled.
 * 
 * @author Thomas Williams
 * @version v1.0
 */
public class Stall
{
    //The 4 variables that define the crucial characteristics of a stall
    public String type;
    public int SERVETIMEMEAN;
    public int SERVETIMESTDV;
    public int numberOfLines;
    
    //This lists that store customers and workers
    public LinkedList<Line> lines = new LinkedList<Line>();
    LinkedList workers = new LinkedList();
    
    //Instnace of the random number generator used throughout this project
    RandomGaussian timeGen = new RandomGaussian();

    /**
     * The default constructor for a stall. Takes a type, number of lines, MEAN and STDV as inputs in order to
     * make a stall.
     * 
     * @param t  a strong that represents what type of stall this will be
     * @param n  the number of lines this stall will contain.
     * @param s  the MEAN serve time for a stall of this type, used in the RNG for customrs wait time
     * @param d the STDV of the serve time for a stall of this type, used in the RNG for customrs wait time
     */
    public Stall(String t, int n, int s, int d)
    {
        //Sets the stall defining values to the inputs
        type = t;
        numberOfLines = n;
        SERVETIMEMEAN = s;
        SERVETIMESTDV = d;
        
        //Creates a single line since all stalls must have at least one
        Line line = new Line();
        lines.add(line);
        
        //Creates 2 workers because all stalls start with two and gain more with more lines
        Worker worker0 = new Worker();
        workers.add(worker0);
        Worker worker1 = new Worker();
        workers.add(worker1);
        
        //Adds more lines and subsequent workers if n is greater than 1
        if(numberOfLines > 1){
            addLines(numberOfLines);
        }
    }
    
    
    /**
     * The main functional method of a stall, checks the front of any lines within the stall after making sure
     * they are not empty, and then checks
     * 
     * @param t  a string that represents what type of stall this will be
     */
    public void checkFront(int t)
    {
        int nextDeparture  = 0;
        int currentLine = 0;
        int multiLineCheck = 0;
        
        //System.out.println("Checking the lines in the " + type + " stall");
        
        //Checks if there is more than one line in this stall
        if(lines.size() < 1){
            multiLineCheck = 1;
        }
        
        //Iterates through the lines in the stall checking the front of each for the customer with the next
        //departure time
        for(int i = 0; i < lines.size(); i++){
            //Checks if the current line is empty
            if(lines.get(i).emptyLine() == true){
                ;
                
            //If there is more than one line compares the customers at the front of each and set the
            //currentLine to the one with the next customer to leave
            } else if(multiLineCheck == 1){
                if(lines.get(0).getCustomer(0).getServeTime() < lines.get(1).getCustomer(0).getServeTime()){
                    nextDeparture += lines.get(0).getCustomer(0).getServeTime();
                    currentLine = 0;
                } else {
                    nextDeparture += lines.get(1).getCustomer(0).getServeTime();
                    currentLine = 1;
                }
                
            //If there is only one line sets the current line to that
            } else {
                nextDeparture += lines.get(0).getCustomer(0).getServeTime();
                currentLine = 0;
            }
            
            //Checks if the current time matches the next daparture time of the customer at the front of the
            //current line.
            if(t == nextDeparture && lines.get(i).emptyLine() != true){
                serveCustomer(lines.get(currentLine).getCustomer(0));
            }
        }

    }
    
    /**
     * The main functional method of a stall, checks the front of any lines within the stall after making sure
     * they are not empty, and then checks
     * 
     * @param t  a strong that represents what type of stall this will be
     */
    public void serveCustomer(Customer c)
    {
        Line currentLine;
        for(int i = 0; i < lines.size(); i++){
            currentLine = lines.get(i);
            for(int j = 0; j < currentLine.getLength(); j++){
                if(currentLine.getCustomer(j).equals(c)){
                    c.fulfillNeed(type);
                    c.nextShortest();
                }
            }
        }
    }
   
    /**
     * Adds more lines to the stall based on a given amount, as well as the workers necessary for those lines
     * 
     * @param l  the number of line to add
     */
    public void addLines(int l)
    {
        //Iterates through making lines up to the value l
        for(int lineCount = 0; lineCount < l; lineCount++){
            Line line = new Line();
            Worker worker = new Worker();
            lines.add(line);
            workers.add(worker);
        }
    }
    
    /**
     * Customers always join the shortest line, so when a customer arrives this method directs that customer to
     * the shortest line within the stall
     * 
     * @param c  the customer that is 'arriving' and will be placed in a line
     */
    public void customerArrives(Customer c)
    {
        findShortest().addCustomer(c);
    }
    
    /**
     * Sets the the time it will take a given customer to be served once he/she is at the front of the line,
     * using the MEAN and STDV value for this stall and the RNG used throughout the project
     * 
     * @param c  the customer whose serveTime time is being set
     */
    public void serveTime(Customer c)
    {
        c.setServeTime((int) timeGen.getGaussian(SERVETIMEMEAN, SERVETIMESTDV));
    }
    
    /**
     * Finds the shortest line within the stall
     * 
     * @return  the line which has the shortest length in the stall
     */
    public Line findShortest()
    {
        //Assumes the shortest line is the first one
        Line shortestLine = lines.get(0);
        
        //Checks if there is only one line, in which case the first will also be the shortest
        if(lines.size() == 1){
            ;
        } else {
            
            //Checks and compares all the lines to find the overall shortest
            for(int i = 1; i < lines.size(); i++){
                if(lines.get(i).getLength() < shortestLine.getLength()){
                    shortestLine = lines.get(i);
                }
            }
        }
        return shortestLine;
    }
}