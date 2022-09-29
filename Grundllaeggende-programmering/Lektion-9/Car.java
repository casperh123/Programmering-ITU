public class Car{
    
    private int speed = 0;
    
    Car(int initialSpeed) {
        speed = initialSpeed;
    }
    
    int accellerate() {
        speed = speed + 5;
        System.out.println("Din hastighed er nu: " + speed);
        return speed;
    }
}