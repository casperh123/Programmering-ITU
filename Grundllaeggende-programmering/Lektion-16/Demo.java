public class Demo
{
    static void makeAccount() {
        new Account(null);        
    }
    
    static void test() {
        try {
            makeAccount();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
    
    static void makeDeposit(Account account, int amount) throws Exception {
        account.deposit(amount);
    }
    
    static void test2() {
        
        Account acc = new Account("Yeah man");
        
        try {
            makeDeposit(acc, -100);   
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    static void makeWithdrawal() throws InsufficientFundsException {
        
        Account acc = new Account("Man");
        
        acc.withdraw(10000);
        
    }
    
    static void test3() {
        try {
            makeWithdrawal();
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }
}
