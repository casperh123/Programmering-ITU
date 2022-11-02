public class TravelReservation extends Reservation
{
    private String departure;
    private String arrival;
    
    public TravelReservation(int phone, String name, String departure, String arrival) {
        super(phone, name);
        this.departure = departure;
        this.arrival = arrival;
    }
    
    public String toString() {
        
        String output = super.toString();
        
        output += ": " + departure + " --> " + arrival;
        
        return output;
        
    }
    
    public String kind() {
        return "TRAVEL";
    }
}
