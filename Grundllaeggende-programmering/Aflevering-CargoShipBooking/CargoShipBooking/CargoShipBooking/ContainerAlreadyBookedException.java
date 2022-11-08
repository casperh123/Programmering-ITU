public class ContainerAlreadyBookedException extends Exception
{
    private int column;
    private int row;
    
    public ContainerAlreadyBookedException(String message, int requestedColumn, int requestedRow) {
            super(message);
            this.column = requestedColumn;
            this.row = requestedRow;
    }
    
    public String requestedContainer() {
        return "Requested container: Column " + column + " row " + row;
    }
}
