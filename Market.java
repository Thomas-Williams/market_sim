import java.util.*;

/**
 * This is the main control for a single instance of the market simulation. This regulates the global time and
 * calls all methods in order to get functionality out of the stalls and customers.
 * 
 * @author Thomas Williams 
 * @version v1.0
 */
public class Market
{
    //Instances of the 4 global variables used in the project
    GlobalTime           time            = new GlobalTime();
    CsvWriter            output          = new CsvWriter();
    Space                space           = new Space();
    ProcessedCustomers   satisfied       = new ProcessedCustomers();
    
    //The different LinkedLists that store stalls and customers
    LinkedList<Stall>    marketStalls    = new LinkedList<Stall>();
    LinkedList<Customer> marketCustomers = new LinkedList<Customer>();
    
    //The mean and standard deviations for the service time of the different stalls
    private final int ARRTIMEMEAN        = 61;
    private final int ARRTIMESTDV        = 31;
    
    private final int BAKERYTIME         = 29;
    private final int BAKERYTIMESTDV     = 13;
    
    private final int BEVERAGETIME       = 19;
    private final int BEVERAGTETIMESTDV  = 7;
    
    private final int DAIRYTIME          = 59;
    private final int DAIRYTIMESTDV      = 23;
    
    private final int FRUITTIME          = 83;
    private final int FRUITTIMESTDV      = 31;
    
    private final int MEATTIME           = 101;
    private final int MEATTIMESTDV       = 41;
    
    private final int VEGETABLETIME      = 119;
    private final int VEGETABLETIMESTDV  = 29;
    
    //Instnace of the random number generator used throughout this project
    RandomGaussian arrivalTime           = new RandomGaussian();
    
    /**
     * The main method for the whoel market. This is a big loop that counts up to the closing time of the 
     * market and calls the main functional methods of the stall and customer classes to actually 'run' the
     * market
     * 
     * @param bake  the number of bakery stalls
     * @param bakeL  the number of line for each bakery stall
     * @param bev  the number of beverage stalls
     * @param bevL  the number of line for each beverage stall
     * @param dairy  the number of dairy stalls
     * @param dairyL  the number of line for each dairy stall
     * @param fruit  the number of fruit stalls
     * @param fruitL  the number of line for each fruit stall
     * @param meat  the number of meat stalls
     * @param meatL  the number of line for each meat stall
     * @param vegi  the number of vegetable stalls
     * @param vegiL  the number of line for each vegetable stall
     * @return  Returns a list of all the customers left in the market at closing time.
     */
    public List runSim(int b, int bL, int be, int beL, int d, int dL, int f, int fL, int m, int mL, int v, int vL)
    {
        int currentID    = 0;
        int nextArrival  = 0;
        addAll(b, bL, be, beL, d, dL, f, fL, m, mL, v, vL);
        nextArrival += (int) arrivalTime.getGaussian(ARRTIMEMEAN, ARRTIMESTDV);
        
        //The master loop that acts as the 'clock' for the market
        while(time.getTime() < 12600){
            //Checks is the current time matches that of the next customer arrival and if so add a customer
            if(time.getTime() == nextArrival){
                Customer customer = new Customer(currentID, time.getTime(), marketStalls);
                //customer.info();
                marketCustomers.add(customer);
                space.addCustomer();
                currentID++;
                nextArrival += arrivalTime.getGaussian(ARRTIMEMEAN, ARRTIMESTDV);
            }
            
            //Iterates through every stall and executes the main functional method of each
            for(int i = 0; i < marketStalls.size(); i++){
                marketStalls.get(i).checkFront(time.getTime());
            }
            
            //Iterates through all the customers and checks if all their needs have been met, if so removes
            //them from the market and adds one to the global satisfaction counter and logs their time in
            //that merket to a CSV file.
            for(int j = 0; j < marketCustomers.size(); j++){
                if(marketCustomers.get(j).listComplete() == true){
                    System.out.println("A customer is leaving");
                    int timeInMarket = time.getTime() - marketCustomers.get(j).arrivalTime;
                    marketCustomers.remove(marketCustomers.get(j));
                    satisfied.addOne();
                    output.writeCsvFile(time.getTime(), 1);
                }
            }
            time.addTime();
        }
        System.out.println(satisfied.processed);
        return marketCustomers;
    }
    
    /**
     * Adds all the stalls for the whole market based on 
     * 
     * @param bake  the number of bakery stalls
     * @param bakeL  the number of line for each bakery stall
     * @param bev  the number of beverage stalls
     * @param bevL  the number of line for each beverage stall
     * @param dairy  the number of dairy stalls
     * @param dairyL  the number of line for each dairy stall
     * @param fruit  the number of fruit stalls
     * @param fruitL  the number of line for each fruit stall
     * @param meat  the number of meat stalls
     * @param meatL  the number of line for each meat stall
     * @param vegi  the number of vegetable stalls
     * @param vegiL  the number of line for each vegetable stall
     * @return  Returns a list of all the stalls
     */
    public List addAll(int bake, int bakeL, int bev, int bevL, int dairy, int dairyL, int fruit, int fruitL,
                       int meat, int meatL, int vegi, int vegiL)
    {
        //Add the desired number of stalls of this type
        for( int i = 0; i < bake; i++){
            marketStalls.add(addStall("bakery", bakeL, BAKERYTIME, BAKERYTIMESTDV));
            space.addStall();
        }
        
        //Add the desired number of stalls of this type
        for( int j = 0; j < bev; j++){
            marketStalls.add(addStall("beverage", bevL, BEVERAGETIME, BEVERAGTETIMESTDV));
            space.addStall();
        }
        
        //Add the desired number of stalls of this type
        for( int k = 0; k < bake; k++){
            marketStalls.add(addStall("dairy", dairyL, DAIRYTIME, DAIRYTIMESTDV));
            space.addStall();
        }
        
        //Add the desired number of stalls of this type
        for( int l = 0; l < bake; l++){
            marketStalls.add(addStall("fruit", fruitL, FRUITTIME, FRUITTIMESTDV));
            space.addStall();
        }
        
        //Add the desired number of stalls of this type
        for( int m = 0; m < bake; m++){
            marketStalls.add(addStall("meat", meatL, MEATTIME, MEATTIMESTDV));
            space.addStall();
        }
        
        //Add the desired number of stalls of this type
        for( int n = 0; n < bake; n++){
            marketStalls.add(addStall("vegetables", vegiL, VEGETABLETIME, VEGETABLETIMESTDV));
            space.addStall();
        }
        return marketStalls;
    }
    
    /**
     * Creates a new instance of the customer calss and passes in the necessary parameters
     * 
     * @param ID  is a value that becomes the instances unique ID
     * @return  Returns the customer that was just created
     */
    public Customer addCustomer(int ID)
    {
        Customer customer = new Customer(ID, time.getTime(), marketStalls);
        customer.listGen(marketStalls);
        
        customer.info();
        return customer;
    }
    
    /**
     * Creates a new instance of the stall calss and passes in the necessary parameters
     * 
     * @param type  is a string that defines the type the instance of the stall class will be
     * @param s  this value get used as the mean service time for the particular instance of the stall class
     * @param d  this value get used as the stdv service time for the particular instance of the stall class
     * @return  Returns the stall that was just created
     */
    public Stall addStall(String type, int n, int s, int d)
    {
        Stall stall = new Stall(type, n, s, d);
        for(int i = 0; i < n; i++){
            space.addWorker();
        }
        return stall;
    }
}