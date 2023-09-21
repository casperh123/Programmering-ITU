package MapObjects.Markers;

import Interfaces.Bounds;
import Interfaces.iDrawable;
import MapObjects.Node;
import javafx.scene.canvas.GraphicsContext;

public class Address extends Node implements Bounds, iDrawable {

    private String streetName;
    private String postalCode;
    private String city;
    private String houseNumber;


    public Address(float x, float y, String streetName, String houseNumber, String postalCode, String city) {
        super(0.56f * x, y);
        this.streetName = streetName.intern();
        this.houseNumber = houseNumber; // Doesn't pay off.
        this.postalCode = postalCode.intern();
        this.city = city.intern();

    }



    public Address(String streetName, String houseNumber, String postalCode, String city) {
        super(0, 0);
        this.streetName = streetName.intern();
        this.houseNumber = houseNumber; // Dosn't pay off.
        this.postalCode = postalCode.intern();
        this.city = city.intern();

        /*
        The constructor below was used for debugging purposes, now it's only present in tests.
            */
    }



    public String getStreetName() {
        return streetName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getCity() {
        return city;
    }

    public int hashCode() {
        return streetName.hashCode() + postalCode.hashCode() + houseNumber.hashCode() + city.hashCode();
    }

    public boolean equals(Object o) {

        if(this == o) {
            return true;
        }

        if(!(o instanceof Address addressComparator)) {
            return false;
        }

        return this.streetName.equals(addressComparator.streetName) &&
                this.postalCode.equals(addressComparator.postalCode) &&
                this.houseNumber.equals(addressComparator.houseNumber) &&
                this.city.equals(addressComparator.city);
    }

    @Override
    public String toString() {
        return streetName + " " + houseNumber + ", " + postalCode + " " + city;
    }

    public String addrInfo() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                '}';
    }

    @Override
    public float getMinX() {
        return x;
    }

    @Override
    public float getMinY() {
        return y;
    }

    @Override
    public float getMaxX() {
        return x;
    }

    @Override
    public float getMaxY() {
        return y;
    }

    @Override
    public float getPriorityZ() {
        return 0;
    }

    @Override
    public void setBounds(float minX, float minY, float maxX, float maxY, float priorityZ) {
    }

    @Override
    public void draw(GraphicsContext gc, double determinant) {
    }

    @Override
    public float[] getXCoords() {
        return new float[] {getX()};
    }
    @Override
    public float[] getYCoords() {
        return new float[] {getY()};
    }

    @Override
    public float getLayer() {
        return iDrawable.super.getLayer();
    }

    @Override
    public void setLayer(float layer) {

    }

}
