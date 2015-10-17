import java.util.*;

/**
 * Write a description of class Market here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Market
{
    GlobalTime time                       = new GlobalTime();
    LinkedList<Stall> marketStalls        = new LinkedList<Stall>();
    LinkedList<Customer> marketCustomers = new LinkedList<Customer>();
    ProcessedCustomers satisfied          = new ProcessedCustomers();
    
    private final int ARRTIMEMEAN       = 61;
    private final int ARRTIMESTDV       = 31;
    
    private final int BAKERYTIME        = 29;
    private final int BAKERYTIMESTDV    = 13;
    
    private final int BEVERAGETIME      = 19;
    private final int BEVERAGTETIMESTDV = 7;
    
    private final int DAIRYTIME         = 59;
    private final int DAIRYTIMESTDV     = 23;
    
    private final int FRUITTIME         = 83;
    private final int FRUITTIMESTDV     = 31;
    
    private final int MEATTIME          = 101;
    private final int MEATTIMESTDV      = 41;
    
    private final int VEGETABLETIME     = 119;
    private final int VEGETABLETIMESTDV = 29;
    
    RandomGaussian arrivalTime          = new RandomGaussian();
    
    public static void main(String args[]){
        
    }
    
    public List FatherTime()
    {
        int currentID    = 0;
        int nextArrival  = 0;
        addAll(1, 1, 1, 1, 1, 1, 1);
        nextArrival += (int) arrivalTime.getGaussian(ARRTIMEMEAN, ARRTIMESTDV);
        while(time.getTime() < 12600){
            if(time.getTime() == nextArrival){
                Customer customer = new Customer(currentID, marketStalls);
                customer.info();
                marketCustomers.add(customer);
                currentID++;
                nextArrival += arrivalTime.getGaussian(ARRTIMEMEAN, ARRTIMESTDV);
            }
            
            
            for(int i = 0; i < marketCustomers.size(); i++){
                if(marketCustomers.get(i).listComplete() == true){
                    satisfied.addOne();
                }
            }
            time.addTime();
        }
        //System.out.println(marketCustomers.size());
        System.out.println(satisfied.processed);
        return marketCustomers;
    }
    
    public List addAll(int bake, int bev, int dairy, int fruit, int meat, int vegi, int stalls)
    {
        System.out.println("Adding all stalls");
        for( int i = 0; i < bake; i++){
            marketStalls.add(addStall("bakery", BAKERYTIME, BAKERYTIMESTDV, stalls));
        }
        
        for( int j = 0; j < bev; j++){
            marketStalls.add(addStall("beverage", BEVERAGETIME, BEVERAGTETIMESTDV, stalls));
        }
        
        for( int k = 0; k < bake; k++){
            marketStalls.add(addStall("dairy", DAIRYTIME, DAIRYTIMESTDV, stalls));
        }
        
        for( int l = 0; l < bake; l++){
            marketStalls.add(addStall("fruit", FRUITTIME, FRUITTIMESTDV, stalls));
        }
        
        for( int m = 0; m < bake; m++){
            marketStalls.add(addStall("meat", MEATTIME, MEATTIMESTDV, stalls));
        }
        
        for( int n = 0; n < bake; n++){
            marketStalls.add(addStall("vegetables", VEGETABLETIME, VEGETABLETIMESTDV, stalls));
        }
        //for(int o = 0; o < marketStalls.size(); o++){
        //    System.out.println(marketStalls.get(o).type);
        //}
        return marketStalls;
    }
    
    public Customer addCustomer(int ID)
    {
        Customer customer = new Customer(ID, marketStalls);
        customer.listGen(marketStalls);
        
        customer.info();
        return customer;
    }
    
    /**
     * Constructor for objects of class Market
     */
    public Stall addStall(String type, int s, int d, int i)
    {
        Stall stall = new Stall(type, s, d, i);
        return stall;
    }
}