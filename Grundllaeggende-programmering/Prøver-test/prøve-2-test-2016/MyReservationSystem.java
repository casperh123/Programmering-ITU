import java.util.List;
import java.util.ArrayList;

public class MyReservationSystem implements ReservationSystem
{
    private List<Reservation> reservations;
    
    public MyReservationSystem() {
        reservations = new ArrayList<>();
    }
    
    public void add(Reservation r) {
        reservations.add(r);
    }
    public void display() { 
        for (Reservation r : reservations) {
            
            System.out.println(r.kind() + ":");
            System.out.println(r);
        }
    }
}
