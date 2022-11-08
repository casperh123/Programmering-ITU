public class Container {
    private int coloumn;
    private int row;
    private boolean booked;

    public Container(int coloumn, int row) {
        this.coloumn = coloumn;
        this.row = row;
        booked = false;
    }

    public boolean getBooked() { return booked; }

    public void setBooked() { booked = true; }
}
