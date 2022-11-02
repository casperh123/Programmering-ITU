import java.util.List;
import java.util.ArrayList;

public class Cocktail
{
    private String name;
    private List<Ingredient> ingredients;
    
    public Cocktail(String name) {
        this.name = name;
        ingredients = new ArrayList<>();
    }
    
    void add(Ingredient ingredient) {
        ingredients.add(ingredient);
    }
    
    void doubleUp() {
        for(Ingredient ingredient : ingredients) {
            ingredient.doubleUp();
        }
    }
    
    int totalEnergy() {
        
        int totalEnergy = 0;
        
        for(Ingredient ingredient : ingredients) {
            totalEnergy += ingredient.energy();
        }
        
        return totalEnergy;
    }
    
    void line(char c) {
        
        int nameLength = name.length();
        
        for(int i = 0; i < nameLength; i++) {
            System.out.print(c);
        }
    }
    
    void recipe() {
        
        System.out.println(name);
        
        line('=');
        
        System.out.println();
        
        for(Ingredient ingredient : ingredients) {
            System.out.println(ingredient);
        }
        
        line('-');
        
        System.out.println();
        System.out.println(totalEnergy() + " kJ in TOTAL");
        
        if(safe4Kids()) {
            System.out.println("Safe-4-Kids: Yes");
        } else {
            System.out.println("Safe-4-Kids: No!");
        }
    
    }
    
    boolean safe4Kids() {
        
        for(Ingredient ingredient : ingredients) {
            
            if(ingredient instanceof AlcoholicIngredient) {
                
                AlcoholicIngredient alcoholicIngredient = (AlcoholicIngredient) ingredient;
            
                if(alcoholicIngredient.isAlcoholic()) {
                    return false;
                }
                
            }
            
            
        }
        
        return false;
    }
}
