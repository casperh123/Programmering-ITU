public class Product
{
    private String name;
    private int price;
    
    Product(String name, int price) {
        this.name = name;
        this.price = price;
    }
    
    int getPrice() {
        return this.price;
    }
    
    void setPrice(int newPrice) {
        this.price = newPrice;
    }
    
    void display() {
        System.out.print(this.name + ": ");
        System.out.print(this.price + " kr");
    }
}
