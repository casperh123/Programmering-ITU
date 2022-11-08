 

/** OBS: Du skal ikke ændre koden i denne klasse, andet end at udkommentere metoder undervejs. */


public class Demo {
    static void opg1() {
        CargoShip c = new CargoShip("MørskT13", 4, 3);
        c.avgKg();
        c.addBooking("18273", 7, true, 2, 2);
        c.addBooking("29384", 2, false, 1, 2);
        c.addBooking("39278", 3, false, 3, 0);
        c.addBooking("41723", 2, true, 3, 1);
        c.addBooking("58362", 2, true, 4, 1);
    }

    static void opg2() {
        CargoShip c = new CargoShip("MørskT13", 4, 3);
        c.addBooking("62938", 0, true, 2, 1);
        try {
            c.checkAddBooking("89647", 2, true, 3 ,0);    
        } catch (ContainerAlreadyBookedException e) {
            System.out.println(e.getMessage() + " " + e.requestedContainer());
        }
    }

    static void opg4() {
        CargoShip c = new CargoShip("MørskT13", 4, 3);
        c.addBooking("18273", 7, true, 2, 2);
        c.addBooking("29384", 2, false, 1, 2);
        c.addBooking("39278", 3, false, 3, 0);
        c.addBooking("41723", 2, true, 3, 1);
        c.showCargoShip();
        System.out.println(c.flammableGoodsFactor());
    }
}
