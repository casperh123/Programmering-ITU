public class Account {
    
    double balance;
    
    Account() {
        balance = 0;
    }
    
    void deposit(double amount) {
        balance = balance + amount;
    }
}