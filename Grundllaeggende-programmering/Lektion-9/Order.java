import java.util.*;

public class Order
{
    String name;
    List<Product> products; 
    
    Order(String name) {
        this.name = name;
        products = new ArrayList<>();
    }
    
    void addProduct (Product product) {
        this.products.add(product);
    }
    
    int totalPrice(List<Product> products) {
        
        int sum = 0;
        
        for(Product index : products) {
            sum += index.getPrice();
        }
        
        return sum;
    }
    
    void print() {
        for(Product product : products) {
            product.display();
        }
    }
}
