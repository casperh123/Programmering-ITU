public class Turnstyle implements Passable
{
    private String name;
    private int guests;
    private int capacity;
    
    public Turnstyle(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
    
    public void enter(int people) {
        if(people < 0) {
            throw new IllegalArgumentException("*** invalid entry (" + people + " people)!");
        } else if(guests + people > capacity) {
            System.out.println("*** " + name + " is full!");
        } else {
            guests += people;
        }
    }
    
    public void exit(int people) {
        if(people > guests) {
            throw new StateViolationException(people - guests);
        } else {
            guests -= people;    
        }
    }
    
    public void status() {
        System.out.println("===" + name + "===");
        System.out.println("#guests: " + guests);
        System.out.println("(capacity: " + capacity + " )");
    }
    
    public void in(int people) throws CapacityExceededException {
        
        int leftoverCapacity = capacity - guests;
        
        if(leftoverCapacity < people) {
            throw new CapacityExceededException(people - leftoverCapacity);
        } else {
            enter(people);
        }
    }
}
