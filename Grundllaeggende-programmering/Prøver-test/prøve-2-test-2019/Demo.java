public class Demo
{
    public static void test() {
        
        Painting p1 = new Painting("Dette er ikke en pibe", "Rene Magritte", 1928, 12.3, "surrealism");
        Sculpture s1 = new Sculpture("David", "Michelangelo", 1504, 98.7, 5.17);
    
        Gallery louvreGallery = new Gallery("Louvre");
        
        louvreGallery.add(p1);
        louvreGallery.add(s1);
        
        louvreGallery.show();
        
        louvreGallery.sale();
        
        louvreGallery.show();
    }
}
