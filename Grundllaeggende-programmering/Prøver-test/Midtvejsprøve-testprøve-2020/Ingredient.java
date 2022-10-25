public abstract class Ingredient
{
    protected String name;
    protected String unit;
    protected int amount;
    
    public Ingredient(String name, String unit, int amount) {
        this.name = name;
        this.unit = unit;
        this.amount = amount;
    }
    
    void doubleUp() {
        amount = amount * 2;
    }
    
    abstract int energy();
    
    public String toString() {
        
        String output = this.amount + this.unit + " " + this.name;
        
        return output;
    }
}
