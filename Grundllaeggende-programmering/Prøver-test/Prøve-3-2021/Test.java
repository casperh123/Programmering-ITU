public class Test
{
    public static void test() {
        Election election = new Election("Are fries better with remoulade than ketchup?");
    }
    
    public static void test21() throws IllegalArgumentException {
        Election election = new Election(null);
    }
    
    public static void test22() {
        try {
            test21();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void test3() {
        Election election = new Election("Is McDonals better than Burger King");
        
        election.display();
    
    }
    
    public static void test41() {
        Election election = new Election("Mega fedt emne");
        
        election.vote('x');
    }
    
    public static void test42() {
        try {
            test41();
        } catch (IllegalVoteException e) {
            System.out.println(e.getMessage() + ": " + e.getIllegalVote());
        }
    }
}
