import java.util.List;
import java.util.ArrayList;

public class Gallery
{
    private String name;
    private List<Displayable> artworks;
    
    public Gallery(String name) {
        this.name = name;
        artworks = new ArrayList<>();
    }
    
    public void add(Displayable a) {
        artworks.add(a);
    }
    
    public void show() {
        
        System.out.println("<" + name + ">");
        
        int galleryNameLenght = name.length() + 2;
        
        for(int i = 0; i < galleryNameLenght; i++) {
            System.out.print("=");
        }
        
        System.out.println();
        
        for(Displayable a : artworks) {
            a.display();
        }
    }
    
    public void sale() {
        for(Displayable a : artworks) {
            if(a instanceof Painting) {
                
                Painting painting = (Painting) a;
                
                if(painting.getGenre().equals("surrealism")) {
                    painting.setPrice(painting.getPrice() * 0.9);
                }
            }
        }
    }
}
