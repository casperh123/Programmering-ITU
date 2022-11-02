class Account {
    
    String owner;
    int balance;

    Account(String owner) {
        if(owner == null) {
            throw new NullPointerException("La' vær");
        }
        this.owner = owner;
        balance = 0;
    }

    void deposit(int amount) throws Exception {
        
        if(amount < 0) {
            throw new Exception("Negativt tal");
        }
        balance = balance + amount;
    }

    void withdraw(int amount) throws InsufficientFundsException{
        if(amount > balance) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        balance = balance - amount;
    }

    void inspect() {
        System.out.print("balance: ");
        System.out.println(balance);
    }
}