public class IncompleteTransactionException extends Exception
{
    public IncompleteTransactionException() {
        super("*** transaktionen er ikke gennemført");
    }
}
