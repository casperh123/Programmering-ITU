public class Transaction
{
    private String recipient;
    private String sender;
    private int amount;
    private boolean isComplete;
    private String bank;
    
    public Transaction(String sender, String recipient, int amount) {
        if(sender == null || recipient == null) {throw new IllegalArgumentException("*** Afsender eller modtager mangler!");}
        this.sender = sender;
        this.recipient = recipient;
        this.amount = amount;
        this.isComplete = false;
    }
    
    public void completeViaBank(String bank) {
        if(bank.equals("Danske bank") || bank.equals("Sydbank") || bank.equals("Nordea")) {
            this.bank = bank;
            isComplete = true;
        } else {
            throw new IllegalBankException(bank);
        }
    }
    
    public void notifyRecipient() throws IncompleteTransactionException {
        if(isComplete == true) {
            System.out.println("Informerer " + recipient + 
            " om transaktionen fra "
            + sender + " på " + amount + "kr (via " + bank + ")");
        } else {
            throw new IncompleteTransactionException();
        }
    }
    
    public void handleTransaction() {
        System.out.println("Vi håndterer nu din transaktion...");
        try {
            notifyRecipient();
        } catch(IncompleteTransactionException e) {
            System.out.println(e.getMessage());
        }
    }
}
