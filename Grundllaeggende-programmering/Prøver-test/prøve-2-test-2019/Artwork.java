public class Artwork implements Displayable
{
    private String title;
    private String artist;
    private int year;   
    private double price;
    
    public Artwork(String title, String artist, int year, double price) {
        this.title = title;
        this.artist = artist;
        this.year = year;
        this.price = price;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public void display() {
        System.out.print("* '" + title + "',");
        System.out.print(artist + " (" + year + ") ");
        System.out.print("[" + price + " EUR]" );
        System.out.println();
    }
}
