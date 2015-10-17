import java.util.*;

/**
 * This class contains all the functions applicable to customers. It can generate a list of random needs based
 * on the six pairs of variables that are defined on the class level. Customers are also capable of finding the
 * next available shortest line within their list of needs, move themselves from line to line when a need is
 * fulfilled, and return various information about themselves.
 * 
 * @author Thomas Williams 
 * @version v1.0
 */
public class Customer {
    //The information directly associated with a given customer
    private int customerID;
    public int serveTime;
    public int arrivalTime;
    private Stall currentStall;
    
    //The means and standard deviations for the liklyhood of a particulat stall needing to be visited
    private final int BAKERYMEAN    = 37;
    private final int BAKERYSTDV    = 17;
    
    private final int BEVERAGEMEAN  = 43;
    private final int BEVERAGESTDV  = 11;
    
    private final int DAIRYMEAN     = 59;
    private final int DAIRYSTDV     = 19;
    
    private final int FRUITMEAN     = 47;
    private final int FRUITSTDV     = 13;
    
    private final int MEATMEAN      = 53;
    private final int MEATSTDV      = 13;
    
    private final int VEGETABLEMEAN = 71;
    private final int VEGETABLESTDV = 29;
    
    //Instnace of the random number generator used throughout this project
    RandomGaussian needGen          = new RandomGaussian();
    
    /*The various ways needs are stored
     * shoppingList is simply strings of the names of the stalls that need visiting
     * fullNeeds is a boolean associated with every stall, true for needed, false for not
     * stallsToVisit stored stall objects that are of the same type as the needs of the customer
     */
    ArrayList shoppingList          = new ArrayList();
    ArrayList fullNeeds             = new ArrayList();
    ArrayList<Stall> stallsToVisit  = new ArrayList<Stall>();
    
    /**
     * Default constructor for the Customer class
     * 
     * @param ID  the unique identification number for an instance of the Customer class
     * @param s  the full list of stalla created at the market level.
     */
    public Customer(int ID, int t, LinkedList<Stall> s)
    {
        customerID = ID;
        arrivalTime = t;
        listGen(s);
        nextShortest();
    }
    
    /**
     * Generates the list of needs based on the constants defined above.
     * 
     * @param s  the list of all stall generated at the market level used so they can be added to the customer's
     *  list of all the ones it needs to visit
     */
    public void listGen(LinkedList<Stall> s){
        //System.out.println("Generating list.");
        
        //Uses the results of a random number to check if a particular stall will be needed by this customer
        if(needGen.getGaussian(BAKERYMEAN, BAKERYSTDV) > 50){
            shoppingList.add("Bakery");
            fullNeeds.add(true);
            
            //Iterates through the list of all stalls to find the ones of the matching type and adds them to
            //the customers personal list.
            for(int i = 0; i < s.size(); i++){
                if(s.get(i).type.equals("bakery")){
                    stallsToVisit.add(s.get(i));
                }
            }
        } else {
            fullNeeds.add(false);
        }
        
        //Uses the results of a random number to check if a particular stall will be needed by this customer
        if(needGen.getGaussian(BEVERAGEMEAN, BEVERAGESTDV) > 50){
            shoppingList.add("Beverage");
            fullNeeds.add(true);
            
            //Iterates through the list of all stalls to find the ones of the matching type and adds them to
            //the customers personal list.
            for(int i = 0; i < s.size(); i++){
                if(s.get(i).type.equals("beverage")){
                    stallsToVisit.add(s.get(i));
                }
            }
        } else {
            fullNeeds.add(false);
        }
        
        //Uses the results of a random number to check if a particular stall will be needed by this customer
        if(needGen.getGaussian(DAIRYMEAN, DAIRYSTDV) > 50){
            shoppingList.add("Dairy");
            fullNeeds.add(true);
            
            //Iterates through the list of all stalls to find the ones of the matching type and adds them to
            //the customers personal list.
            for(int i = 0; i < s.size(); i++){
                if(s.get(i).type.equals("dairy")){
                    stallsToVisit.add(s.get(i));
                }
            }
        } else {
            fullNeeds.add(false);
        }
        
        //Uses the results of a random number to check if a particular stall will be needed by this customer
        if(needGen.getGaussian(FRUITMEAN, FRUITSTDV) > 50){
            shoppingList.add("Fruit");
            fullNeeds.add(true);
            
            //Iterates through the list of all stalls to find the ones of the matching type and adds them to
            //the customers personal list.
            for(int i = 0; i < s.size(); i++){
                if(s.get(i).type.equals("fruit")){
                    stallsToVisit.add(s.get(i));
                }
            }
        } else {
            fullNeeds.add(false);
        }
        
        //Uses the results of a random number to check if a particular stall will be needed by this customer
        if(needGen.getGaussian(MEATMEAN, MEATSTDV) > 50){
            shoppingList.add("Meat");
            fullNeeds.add(true);
            
            //Iterates through the list of all stalls to find the ones of the matching type and adds them to
            //the customers personal list.
            for(int i = 0; i < s.size(); i++){
                if(s.get(i).type.equals("meat")){
                    stallsToVisit.add(s.get(i));
                }
            }
        } else {
            fullNeeds.add(false);
        }
        
        //Uses the results of a random number to check if a particular stall will be needed by this customer
        if(needGen.getGaussian(VEGETABLEMEAN, VEGETABLESTDV) > 50){
            shoppingList.add("Vegetables");
            fullNeeds.add(true);
            
            //Iterates through the list of all stalls to find the ones of the matching type and adds them to
            //the customers personal list.
            for(int i = 0; i < s.size(); i++){
                if(s.get(i).type.equals("vegetables")){
                    stallsToVisit.add(s.get(i));
                }
            }
        } else {
            fullNeeds.add(false);
        }
    }
    
    /**
     * Finds the next shortest line out of all the lines in all the stall currently in THIS customer's list
     * 
     * @return the next shortest available line or null if no more lines are available
     */
    public Line nextShortest()
    {
        Stall nextStall;
        Line shortestLine;
        //System.out.println("Finding the next shortest line");
        
        //Checks that there is in fact a stall left to visit
        if(stallsToVisit.isEmpty() != true){
            shortestLine = stallsToVisit.get(0).findShortest();
            nextStall = stallsToVisit.get(0);
            
            //Iterates through the stall left in THIS customers list and compares them to find the shortest
            for(int i = 0; i < stallsToVisit.size(); i++){
                if(stallsToVisit.get(i).findShortest().getLength() < shortestLine.getLength()){
                    shortestLine = stallsToVisit.get(i).findShortest();
                    nextStall = stallsToVisit.get(i);
                }
            }
            moveStall(nextStall);
            return shortestLine;
        } else if(stallsToVisit.isEmpty() == true){
            return null;
        }
        return null;
    }
    
    /**
     * Checks if THIS customer has completed its list of needs
     * 
     * @return  a boolean value of it the needs have been filled or not
     */
    public boolean listComplete()
    {
        return stallsToVisit.isEmpty();
    }

    
    /**
     * Compares a given string to the types of stalls in a customer's list to find which one matches and 
     * remove it to "fulfill" that particular need.
     * 
     * @param n  the type of the stall that a need has been fulfilled from.
     */
    public void fulfillNeed(String n)
    {
        //System.out.println("Fulfilling a need");
        
        //Checks if the input string matches this need
        if(n.equals("bakery")){
            shoppingList.remove("Bakery");
            fullNeeds.set(0, false);
            
            //Iterates through the stalls THIS customer needs to visit, finding the one that matches and
            //removes it from the list and tells the customer to move to the next line
            for(int i = 0; i < stallsToVisit.size(); i++){
                if(stallsToVisit.get(i).type.equals("bakery")){
                    this.moveLines(nextShortest());
                    stallsToVisit.remove(i);
                }
            }
            

           
        //Checks if the input string matches this need
        } else if(n.equals("beverage")){
            shoppingList.remove("Beverage");
            fullNeeds.set(1, false);
            
            //Iterates through the stalls THIS customer needs to visit, finding the one that matches and
            //removes it from the list and tells the customer to move to the next line
            for(int i = 0; i < stallsToVisit.size(); i++){
                if(stallsToVisit.get(i).type.equals("beverage")){

                    this.moveLines(nextShortest());
                    stallsToVisit.remove(i);
                }
            }
            
        //Checks if the input string matches this need
        } else if(n.equals("dairy")){
            shoppingList.remove("Dairy");
            fullNeeds.set(2, false);
            
            //Iterates through the stalls THIS customer needs to visit, finding the one that matches and
            //removes it from the list and tells the customer to move to the next line
            for(int i = 0; i < stallsToVisit.size(); i++){
                if(stallsToVisit.get(i).type.equals("dairy")){
                    this.moveLines(nextShortest());
                    stallsToVisit.remove(i);
                }
            }
            
        //Checks if the input string matches this need
        } else if(n.equals("fruit")){
            shoppingList.remove("Fruit");
            fullNeeds.set(3, false);
            
            //Iterates through the stalls THIS customer needs to visit, finding the one that matches and
            //removes it from the list and tells the customer to move to the next line
            for(int i = 0; i < stallsToVisit.size(); i++){
                if(stallsToVisit.get(i).type.equals("fruit")){
                    this.moveLines(nextShortest());
                    stallsToVisit.remove(i);
                }
            }
            
        //Checks if the input string matches this need
        } else if(n.equals("meat")){
            shoppingList.remove("Meat");
            fullNeeds.set(4, false);
            
            //Iterates through the stalls THIS customer needs to visit, finding the one that matches and
            //removes it from the list and tells the customer to move to the next line
            for(int i = 0; i < stallsToVisit.size(); i++){
                if(stallsToVisit.get(i).type.equals("meat")){
                    this.moveLines(nextShortest());
                    stallsToVisit.remove(i);
                }
            }
            
        //Checks if the input string matches this need
        } else if(n.equals("vegetables")){
            shoppingList.remove("Vegetables");
            fullNeeds.set(5, false);
            
            //Iterates through the stalls THIS customer needs to visit, finding the one that matches and
            //removes it from the list and tells the customer to move to the next line
            for(int i = 0; i < stallsToVisit.size(); i++){
                if(stallsToVisit.get(i).type.equals("vegetables")){
                    this.moveLines(nextShortest());
                    stallsToVisit.remove(i);
                }
            }
        }
    }
    
    /**
     * Simply set the serveTime of THIS customer to a randomly generated time for how long it will take for it to
     *  be served at any given stall.
     * 
     * @param t  an integer passed in from the a stall that is set as the new serveTime
     * @return an integer that stores the value of how long it will take to serve THIS customer 
     */
    public int setServeTime(int t)
    {
        serveTime = t;
        return serveTime;
    }
    
    /**
     * Simply provides access to THIS customer's randomly generated time for how long it will take for it to be
     *  served at any given stall.
     * 
     * @return  an integer that stores the value of how long it will take to serve THIS customer
     */
    public int getServeTime()
    {
        return serveTime;
    }
        
    /**
     * Takes a line as input and moves THIS customer to it, returing the new line.
     * 
     * @param l the line that THIS customer will be moved to
     * @return  the line that THIS customer was moved to 
     */
    public Line moveLines(Line l)
    {
        //System.out.println("Moving lines");
        l.removeCustomer(this);
        nextShortest().addCustomer(this);
        return nextShortest();
    }
    
    /**
     * Takes a stall object as input and "moves" THIS customer to it, then return the stall it was moved to.
     * 
     * @param stall  the stall that THIS customer will be moved to
     * @raturn  the stall that THIS customer was moved to
     */
    public Stall moveStall(Stall stall)
    {
        //System.out.println("Moving to the next stall");
        currentStall = stall;
        currentStall.customerArrives(this);
        return currentStall;
    }
    
    /**
     * Simply compiles informaiton about a customer in order to make it usable as output to the console.
     * 
     * @return the single string of all the infomration
     */
    public String info()
    {
        String info =  "Customer: "           + customerID              + "\n";
               info += "    Specific Needs: " + shoppingList.toString() + "\n";
               info += "    Overall  Needs: " + fullNeeds.toString();
               
        System.out.println(info);
        
        return info;
    }
}