import java.util.ArrayList;

public class CargoShip {
    private String model;
    private Container[][] containers;
    private ArrayList<Booking> bookings;

    public CargoShip(String model, int coloumns, int rowsPerColoumn) {
        this.model = model;
        containers = buildCargoShip(coloumns, rowsPerColoumn);
        bookings = new ArrayList<>();
    }

    /* Tilføjer en ny booking til listen bookings. */
    public void addBooking(String bookingId, int kg, boolean flammableGoods, int requestedColoumn, int requestedRowNo) {
        try {
            Container requestedContainer = containers[requestedColoumn][requestedRowNo];
            try {
                bookings.add(new Booking(bookingId, kg, flammableGoods, requestedContainer));
            } catch (InvalidKgException e) {
                System.out.println(e.getMessage());   
            }
            requestedContainer.setBooked();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Den ønskede container findes ikke");
        }
    }

    /* Udregner det gennemsnitlige antal kilo per booking. */
    public double avgKg() {
        int sum = 0;
        for (Booking b : bookings) {
            sum += b.getKgs();
        }
        
        try {
            return sum / bookings.size();   
        } catch (ArithmeticException e) {
            System.out.println("Der er ingen bookinger i systemet");
        }
        
        return 0;
    }

    /* Hjælpemetode til constructoren.
       Opretter Container-objekter, som den tilføjer til containers. */
    private Container[][] buildCargoShip(int coloumns, int rowsPerColoumn) {
        Container[][] cargoShip = new Container[coloumns][rowsPerColoumn];
        for (int i = 0; i < cargoShip.length; i++) {
            for (int j = 0; j < cargoShip[i].length; j++) {
                cargoShip[i][j] = new Container(i, j);
            }
        }
        return cargoShip;
    }

    /** SKRIV DIN KODE HERUNDER */
    
    public void checkAddBooking (String bookingId, int kg, boolean flammableGoods, int requestedColumn, int requestedRow) throws ContainerAlreadyBookedException{
        if(containers[requestedColumn][requestedRow].getBooked()) {
            throw new ContainerAlreadyBookedException("Containeren er allerede booket.", requestedColumn, requestedRow);    
        } else {
            
            Container requestedContainer = containers[requestedColumn][requestedRow];
            
            try {
                bookings.add(new Booking(bookingId, kg, flammableGoods, requestedContainer));
            } catch (InvalidKgException e) {
                System.out.println(e.getMessage());
            }
            
            requestedContainer.setBooked();
        }
        
    }
    
    public void showCargoShip() {
        for(Container[] containerColumn : containers) {
            for(Container container : containerColumn) {
                if(container.getBooked()) {
                    System.out.print("[X]");
                } else {
                    System.out.print("[ ]");
                }
            }
            System.out.println();
        }
    }
    
    public double flammableGoodsFactor() {
        
        int flammableContainers = 0;
        int containerTotal = containers[0].length * containers.length;
        
        
        for(Booking booking : bookings) {
            if(booking.getFlammableGoodsIncluded()) {
                flammableContainers++;
            }
        }
        
        return (double) flammableContainers / containerTotal;
    }
}
