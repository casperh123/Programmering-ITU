public class NoVotesYetException extends Exception
{
    public NoVotesYetException() {
        super("*** no votes yet!");
    }
}
