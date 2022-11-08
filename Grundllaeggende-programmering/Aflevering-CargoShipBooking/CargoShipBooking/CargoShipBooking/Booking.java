public class Booking {
    private String bookingId;
    private int kg;
    private boolean flammableGoodsIncluded;
    private Container container;

    public Booking(String bookingId, int kg, boolean flammableGoodsIncluded, Container container) throws InvalidKgException {
        if(kg == 0) {throw new InvalidKgException("Vægten skal være større end 0");}
        this.bookingId = bookingId;
        this.kg = kg;
        this.flammableGoodsIncluded = flammableGoodsIncluded;
        this.container = container;
    }

    public int getKgs() { return kg; }

    public boolean getFlammableGoodsIncluded() { return flammableGoodsIncluded; }
}
