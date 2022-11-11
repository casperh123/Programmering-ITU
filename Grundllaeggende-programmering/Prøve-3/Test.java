public class Test
{
    public static void test1() {
        Transaction transaction = new Transaction("Claus", "Sebastian", 100);
        transaction.completeViaBank("Nordea");
        transaction.handleTransaction();
    }
    
    public static void test21() throws IllegalArgumentException {
        Transaction transaction = new Transaction(null, null, 100);
    }
    
    public static void test22() {
        try {
            test21();
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void test3() {
        Transaction transaction = new Transaction("Claus", "Sebastian", 100);
        transaction.handleTransaction();
    }
    
    public static void test41() {
        Transaction transaction = new Transaction("Claus", "Sebastian", 100);
        transaction.completeViaBank("Lån & Spar");
    }
    
    public static void test42() {
        try {
            test41();
        } catch(IllegalBankException e) {
            System.out.println(e.getMessage() + ": '" + e.getIllegalBank() + "'");
        }
    }
}
