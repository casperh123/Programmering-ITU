import java.util.Scanner;
import java.util.ArrayList;

public class BookingARoom {
    public static void main(String[] args) {
    
    Scanner input = new Scanner(System.in);

    int rooms = input.nextInt();
    int bookings = input.nextInt();

    ArrayList<Boolean> roomsBooked = new ArrayList<>();

    if(rooms == bookings) {
        System.out.println("too late");
    } else {

        for(int i = 0; i < rooms; i++) {
            roomsBooked.add(false);
        }

        for(int i = 0; i < bookings; i++) {

            int booking = input.nextInt() - 1;

            roomsBooked.set(booking, true);
        }

        int index = 0;

        for(int i = 0; i < rooms; i++) {
            if(roomsBooked.get(i) == false) {
                System.out.println(i + 1);
                break;
            } else {
                continue;
            }
        }
    }
    
}
}
