import java.util.Random;

public class Demo
{
     static void test() {
         
         Animal a;
         
         Random rand = new Random();
         
         boolean randomBool = rand.nextBoolean();

         if(randomBool) {
             a = new Dog();
         } else {
             a = new Cat();
         }
         
         a.speak();
     }
}
