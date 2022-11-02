public class Demo {
 public static void main(String[] args) {
 ReservationSystem r = new MyReservationSystem();
 r.add(new TravelReservation(12345678, "Obama", "JFK", "CPH"));
 r.add(new RestaurantReservation(12345678, "Obama", "Noma", 2));
 r.display();
 }
}
