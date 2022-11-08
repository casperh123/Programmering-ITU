public class Election
{
    private String question;
    protected int yes;
    protected int no;
    
    
    public Election(String question) {
        if(question == null) {throw new IllegalArgumentException("*** question missing!");}
        this.question = question;
        this.yes = 0;
        this.no = 0;
    }
    
    public void vote(char v) {
        if(v == 'y') {
            yes++;
        } else if(v == 'n') {
            no++;
        } else if(v == ' '){
        } else {
            throw new IllegalVoteException(v);
        }
    }
    
    public int percentage(int fraction, int total) throws NoVotesYetException{
        
        if(total == 0) {
            throw new NoVotesYetException();
        }
        return fraction*100/total;
    }
    
    public String visualize(int fraction, int total) throws NoVotesYetException {
        return fraction  + "/" + total + " (" + percentage(fraction, total) + ")";
    }
    
    public void display() {
        
        int total = yes + no;
        
        try {
            System.out.println(question);
            System.out.println("* " + visualize(yes, total) + " yes");
            System.out.println("* " + visualize(no, total) + " no");
        
        } catch (NoVotesYetException e) {
            System.out.println(e.getMessage());
        }
      }
}
