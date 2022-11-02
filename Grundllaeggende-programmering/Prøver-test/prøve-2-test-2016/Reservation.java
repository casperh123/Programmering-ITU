public abstract class Reservation
{
    private int phone;
    private String name;
    
    public Reservation(int phone, String name) {
        this.phone = phone;
        this.name = name;
    }
    
    public int getPhone() {
        return phone;
    }
    
    public String toString() {
        
        String output = name + " (" + phone + ")";
        
        return output;
    }
    
    public abstract String kind();
}
