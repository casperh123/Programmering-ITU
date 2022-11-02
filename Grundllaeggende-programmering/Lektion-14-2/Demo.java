import java.util.Random;

public class Demo
{
    static void Demo() {
        
        Recipe r;
        
        Random rand = new Random();
        boolean randomNumber = rand.nextBoolean();
        
        if(randomNumber) {
            r = new Lasagne();
            r.make();
            
            Eatable x = (Eatable) r;
            x.eat();
        } else {
            r = new IkeaTable();
            r.make();
        }
    }
}
