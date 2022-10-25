import java.util.HashMap;

public class EnergeticIngredient extends Ingredient
{
    protected int protein;
    protected int carbs;
    protected int fat;
    
    public EnergeticIngredient(String name, String unit, int amount, int protein, int carbs, int fat) {
        super(name, unit, amount);
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }
    
    void doubleUp() {
        super.doubleUp();
        protein = protein * 2;
        carbs = carbs * 2;
        fat = fat * 2;
    }
    
    int energy() {
        return protein * 17 + carbs * 17 + fat * 37;
    }
    
    public String toString() {
        
        String output = " " + "(" + this.energy() + "kJ)";
                        
        if(protein > 0) {
            output += " " + "[protein:" + this.protein + "g" + "]";    
        }
        if(carbs > 0) {
            output += " " + "[carbs:" + this.carbs + "g" + "]";  
        }
        if(fat > 0) {
            output += " " + "[fat:" + this.fat + "g" + "]";
        }
        
        return super.toString() + output;
    }
}
