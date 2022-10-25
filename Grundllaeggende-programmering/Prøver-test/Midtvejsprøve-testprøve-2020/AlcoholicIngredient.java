public class AlcoholicIngredient extends EnergeticIngredient
{
    
    protected int alcohol;
    
    AlcoholicIngredient(String name, String unit, int amount, int protein, int carbs, int fat, int alcohol) {
        super(name, unit, amount, protein, carbs, fat);
        this.alcohol = alcohol;
    }
    
    boolean isAlcoholic() {
        
        if(alcohol > 0) {
            return true;
        }
        
        return false;
    }
    
    void doubleUp() {
        
        super.doubleUp();
        
        alcohol = alcohol * 2;
    }
    
    int energy() {
        return super.energy() + alcohol * 29;
    }
    
    public String toString() {
        
        String output = super.toString();
        
        if(isAlcoholic()) {
            output += " " + "[alcohol:" + alcohol + "g]";
        }
        
        return output;
    }
}
