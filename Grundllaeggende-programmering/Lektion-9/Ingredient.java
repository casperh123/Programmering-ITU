public class Ingredient {
    
    int quantity;
    String unit;
    String name;
    
    Ingredient (int quantity, String unit, String name) {
        this.quantity = quantity;
        this.unit = unit;
        this.name = name;
    }
    
    void display() {
        System.out.print(this.quantity + " ");
        System.out.print(this.unit + " ");
        System.out.print(this.name);
        System.out.println();
    }
}