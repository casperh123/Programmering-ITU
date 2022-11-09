public class Demo
{
    public static void test21() throws IllegalArgumentException {
        Turnstyle turnstyle = new Turnstyle("Analog", 500);
        
        turnstyle.enter(-100);
    }
    
    public static void test22() {
        try {
            test21();
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void test31() {
        Turnstyle turnstyle = new Turnstyle("Analog", 500);
        turnstyle.exit(100);
    }
    
    public static void test32() {
        try{
            test31();
        } catch(StateViolationException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getNumber() + " too many");
        }
    }
    
    public static void test41() throws CapacityExceededException {
        Turnstyle turnstyle = new Turnstyle("Analog", 5);
        
        turnstyle.in(6);
    }
    
    public static void test42() {
        try {
            test41();
        } catch(CapacityExceededException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getExcess() + " too many");
        }
    }
}
