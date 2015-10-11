import java.util.*;

/**
 * Write a description of class Customer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Customer {   
    private int customerID;
    private int arrivalTime;
    
    private final int BAKERYMEAN        = 37;
    private final int BAKERYSTDV        = 17;
    private final int BAKERYTIME        = 29;
    private final int BAKERYTIMESTDV    = 13;
    
    private final int BEVERAGEMEAN      = 43;
    private final int BEVERAGESTDV      = 11;
    private final int BEVERAGETIME      = 19;
    private final int BEVERAGTETIMESTDV = 7;
    
    private final int DAIRYMEAN         = 59;
    private final int DAIRYSTDV         = 19;
    private final int DAIRYTIME         = 59;
    private final int DAIRYTIMESTDV     = 23;
    
    private final int FRUITMEAN         = 47;
    private final int FRUITSTDV         = 13;
    private final int FRUITTIME         = 83;
    private final int FRUITTIMESTDV     = 31;
    
    private final int MEATMEAN          = 53;
    private final int MEATSTDV          = 13;
    private final int MEATTIME          = 101;
    private final int MEATTIMESTDV      = 41;
    
    private final int VEGETABLEMEAN     = 71;
    private final int VEGETABLESTDV     = 29;
    private final int VEGETABLETIME     = 119;
    private final int VEGETABLETIMESTDV = 29;
    
    RandomGaussian needGen = new RandomGaussian();
    ArrayList shoppingList = new ArrayList();
    ArrayList fullNeeds = new ArrayList();
    
    public Customer(int ID)
    {
        customerID = ID;
    }
    
    public void listGen( ){
        if(needGen.getGaussian(BAKERYMEAN, BAKERYSTDV) > 50){
            shoppingList.add("Bakery");
            fullNeeds.add(true);
        } else {
            fullNeeds.add(false);
        }
        
        if(needGen.getGaussian(BEVERAGEMEAN, BEVERAGESTDV) > 50){
            shoppingList.add("Beverage");
            fullNeeds.add(true);
        } else {
            fullNeeds.add(false);
        }
        
        if(needGen.getGaussian(DAIRYMEAN, DAIRYSTDV) > 50){
            shoppingList.add("Dairy");
            fullNeeds.add(true);
        } else {
            fullNeeds.add(false);
        }
        
        if(needGen.getGaussian(FRUITMEAN, FRUITSTDV) > 50){
            shoppingList.add("Fruit");
            fullNeeds.add(true);
        } else {
            fullNeeds.add(false);
        }
        
        if(needGen.getGaussian(MEATMEAN, MEATSTDV) > 50){
            shoppingList.add("Meat");
            fullNeeds.add(true);
        } else {
            fullNeeds.add(false);
        }
        
        if(needGen.getGaussian(VEGETABLEMEAN, VEGETABLESTDV) > 50){
            shoppingList.add("Vegetables");
            fullNeeds.add(true);
        } else {
            fullNeeds.add(false);
        }
        
        System.out.println("Customer: " + customerID);
        System.out.println("    Specific Needs: " + shoppingList.toString());
        
        System.out.println("    Overall Needs: " + fullNeeds.toString());
    }
}
