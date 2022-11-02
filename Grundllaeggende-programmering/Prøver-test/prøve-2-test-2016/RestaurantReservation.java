public class RestaurantReservation extends Reservation
{
    private String restaurant;
    private int persons;
    
    public RestaurantReservation(int phone, String name, String restaurant, int persons) {
        super(phone, name);
        this.restaurant = restaurant;
        this.persons = persons;
    }
    
    public String toString() {
        
        String output = super.toString();
        
        output += ": table for " + persons + " at " + restaurant;
        
        return output;
    }
    
    public String kind() {
        return "RESTAURANT";
    }
}
