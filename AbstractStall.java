/**
 * Write a description of class Stall here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class AbstractStall
{
    AbstractStall
    
    public int size() {
        return data.size();
    }
    
    public String get(int index) {
        return data.get(index);
    }
    
    public AbstractList addElement(String value){
        data.add( value );
        quickSort(data, 0, data.size() - 1);
        return data;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
