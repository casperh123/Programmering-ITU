public class Tree
{
    protected String type;
    protected double height;
    
    public Tree(String type) {
        this.type = type;
        height = 0.0;
    }
    
    public void grow() {
        height += 0.5;
    }
    
    public double getHeight() {
        return height;
    }
    
    public String toString() {
        
        String output = "- Et " + height + "m  højt " + type + "træ";
        
        return output;
    }
    
    public String getType() {
        return type;
    }
}
