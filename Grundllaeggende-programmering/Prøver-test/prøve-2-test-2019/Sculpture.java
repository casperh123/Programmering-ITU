public class Sculpture extends Artwork
{
    protected double height;
    
    public Sculpture(String title, String artist, int year, double price, double height) {
        super(title, artist, year, price);
        this.height = height;
    }
    
    public void display() {
        System.out.println("SCULPTURE (" + height + "m):");
        super.display();
    }
}
