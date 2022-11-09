public class StateViolationException extends RuntimeException
{
    protected int number;
    
    public StateViolationException(int number) {
        super("*** state violation!");
        this.number = number;
    }
    
    public int getNumber() {
        return number;
    }
}
