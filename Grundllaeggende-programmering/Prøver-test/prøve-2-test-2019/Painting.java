public class Painting extends Artwork
{
    protected String genre;

    public Painting(String title, String artist, int year, double price, String genre) {
        super(title, artist, year, price);
        this.genre = genre;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void display() {
        System.out.println("PAINTING (" + genre + "):");
        super.display();
    }
}
