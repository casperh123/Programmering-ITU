public class Lasagne implements Recipe, Eatable
{
    boolean cooked;
    boolean eaten;
    
    public Lasagne() {
        cooked = false;
        eaten = false;
    }
    
    public void make() {
        cooked = true;
        System.out.println("Lasagnen er færdig");
    }
    
    public void eat() {
        if(cooked) {
            eaten = true;
            System.out.println("Lasagnen er spist");
        } else {
            System.out.println("Lasagnen kunne ikke spises");   
        }
    }
}
