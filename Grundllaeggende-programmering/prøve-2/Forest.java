import java.util.List;
import java.util.ArrayList;

public class Forest
{
    private String name;
    private List<Tree> trees;
    
    public Forest(String name) {
        this.name = name;
        trees = new ArrayList<>();
    }
    
    public void plant(Tree t) {
        trees.add(t);
    }
    
    public void grow(){
        for(Tree t : trees){
            t.grow();
        }
    }
    
    public void display() {
        
        System.out.println("'" + name + "' indeholder:");
        
        for(Tree t : trees) {
            System.out.println(t);
        }
    }
    
    public Tree tallest() {
        
        Tree tallestTree = null;
        
        double maxHeight = 0;
        
        for(Tree t : trees) {
            if(t.getHeight() >= maxHeight) {
                maxHeight = t.getHeight();
                tallestTree = t;
            }
        }
        
        return tallestTree;
    }
    
    public void display(String type) {
        
        System.out.println("'" + name + "' indeholder følgende " + type + "træ(er):");
        
        for(Tree t : trees) {
            if(t.type.equals(type)) {
                System.out.println(t);
            }
        }
    }
}
