import java.util.*;

/**
 * Write a description of class Market here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Market
{
    GlobalTime time                     = new GlobalTime();
    LinkedList<Stall> marketStalls      = new LinkedList<Stall>();
    LinkedList marketCustomers          = new LinkedList();
    ProcessedCustomers satisfied        = new ProcessedCustomers();
    
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
        int lastArrival  = 0;
        int nextArrival  = 0;
        int currentIndex = 0;
        addAll(1, 1, 1, 1, 1, 1, 1);
        nextArrival += arrivalTime.getGaussian(61, 31);
        while(time.getTime() < 1000){
            if(time.getTime() == nextArrival){
                Customer customer = new Customer(currentID);
                marketCustomers.add(customer);
                currentID++;
                nextArrival += arrivalTime.getGaussian(61, 31);
                time.addTime();
            }
            
            
            for(int i = 0; i < marketStalls.size(); i++){
                marketStalls.get(i).checkFront(time.getTime());
                for(int j = 0; j < marketStalls.get(i).lines.size(); j++){
                    for(int k = 0; k < marketStalls.get(j).lines.size(); k++){
                        if(marketStalls.get(i).lines.get(j).getCustomer(k).listComplete()
                        if(marketStalls.get(i).lines.get(j).getCustomer(k).listComplete() == true){
                            satisfied.addOne();
                            marketStalls.get(i).lines.get(j).removeCustomer(marketStalls.get(i).lines.get(j).getCustomer(k));
                        }
                    }
                }
            }
            
            
            time.addTime();
        }
        
        return marketCustomers;
    }
    
    public List addAll(int bake, int bev, int dairy, int fruit, int meat, int vegi, int stalls)
    {
        for( int i = 0; i < bake; i++){
            marketStalls.add(addStall("bakery", BAKERYTIME, BAKERYTIMESTDV, stalls));
        }
        
        for( int i = 0; i < bev; i++){
            marketStalls.add(addStall("beverage", BEVERAGETIME, BEVERAGTETIMESTDV, stalls));
        }
        
        for( int i = 0; i < bake; i++){
            marketStalls.add(addStall("dairy", DAIRYTIME, DAIRYTIMESTDV, stalls));
        }
        
        for( int i = 0; i < bake; i++){
            marketStalls.add(addStall("fruit", FRUITTIME, FRUITTIMESTDV, stalls));
        }
        
        for( int i = 0; i < bake; i++){
            marketStalls.add(addStall("meat", MEATTIME, MEATTIMESTDV, stalls));
        }
        
        for( int i = 0; i < bake; i++){
            marketStalls.add(addStall("vegetables", VEGETABLETIME, VEGETABLETIMESTDV, stalls));
        }
        
        return marketStalls;
    }
    
    public Customer addCustomer(int ID)
    {
        Customer customer = new Customer(ID);
        customer.listGen(marketStalls);
        
        //customer.info();
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