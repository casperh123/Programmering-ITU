public class IllegalBankException extends RuntimeException
{
    protected String illegal_bank;
    
    public IllegalBankException(String illegal_bank) {
        super("*** ulovlig bank");
        this.illegal_bank = illegal_bank;
    }
    
    public String getIllegalBank() {
        return illegal_bank;
    }
}
