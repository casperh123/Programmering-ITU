class Car {
    int speed;
    String color;
 
    public Car(String color) {
        this.speed = 0;
        this.color = color;
    }
 
    void accelerate() {
        speed = speed + 5;
    }
    
    void changeColor(String color) {
        this.color = color;
    }
 }
