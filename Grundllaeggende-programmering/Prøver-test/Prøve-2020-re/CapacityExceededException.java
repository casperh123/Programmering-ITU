public class CapacityExceededException extends Exception
{
    protected int excess;
    
    public CapacityExceededException(int excess) {
        super("*** capacity exceeded!");
        this.excess = excess;
    }
    
    public int getExcess() {
        return excess;
    }
}
