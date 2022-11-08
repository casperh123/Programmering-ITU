public class IllegalVoteException extends RuntimeException
{
    protected char illegal_vote;
    
    public IllegalVoteException(char illegal_vote) {
        super("*** illegal vote");
        this.illegal_vote = illegal_vote;
    }
    
    public char getIllegalVote() {
        return illegal_vote;
    }
}
