public class FruitTree extends Tree
{
    protected int fruits;
    
    public FruitTree(String type) {
        super(type);
        fruits = 0;
    }
    
    public void grow() {
        
        super.grow();
        
        if(fruits == 0) {
            fruits = fruits + 1;
        } else {
            fruits = fruits * 2;
        }
    }
    
    public String toString() {
        
        String output = super.toString();
        
        output += " med " + fruits + " frugt(er)";
        
        return output;
    }
}
