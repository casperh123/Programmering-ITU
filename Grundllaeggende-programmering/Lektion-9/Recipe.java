import java.util.*;

public class Recipe {
    
    String title;
    List<Ingredient> ingredient;
    
    Recipe(String title) {
        this.title = title;
        this.ingredient = new ArrayList<>();
    }
    
    void addIngredient(Ingredient ingredient) {
        this.ingredient.add(ingredient);
    }
    
    void show() {
        System.out.println(this.title);
        
        for(Ingredient index : this.ingredient) {
            index.display();
        }
    }
    
}