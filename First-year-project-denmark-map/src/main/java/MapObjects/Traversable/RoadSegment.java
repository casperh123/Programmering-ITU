package MapObjects.Traversable;

import MapObjects.Markers.Address;
import MapObjects.Node;
import handin2.Settings.DrawSettings;
import handin2.Settings.Setting;

import java.util.*;

public class RoadSegment extends Road {

    private final int ID;
    Set<Address> addresses;

    public RoadSegment(List<Node> nodes, String type, int speedLimit, int ID, Set<Address> addresses, String roadName, boolean oneWay) {
        super(nodes, type, speedLimit, roadName, oneWay);
        this.ID = ID;
        this.addresses = addresses;
    }

    public RoadSegment(List<Node> nodes, String type, int speedlimit, int ID, String roadName, boolean oneWay) {
        super(nodes, type, speedlimit, roadName, oneWay);
        this.ID = ID;
        this.addresses = new HashSet<>();
        setBounds(Setting.innerZoomBound - DrawSettings.getRoadKDLayer(type));
        setLayer(DrawSettings.getRoadLayer(type));
    }

    //DEBUG
    public RoadSegment(float[] xCoords, float[] yCoords, int ID, int speedLimit, double debugLength, String type, String name) {
        super(debugLength, type);
        this.xCoords = xCoords;
        this.yCoords = yCoords;
        this.name = name;
        this.type = type;
        this.speedLimit = speedLimit;
        this.ID = ID;
        this.addresses = new HashSet<>();
        setBounds(Setting.innerZoomBound - DrawSettings.getRoadKDLayer(type));
        setLayer(DrawSettings.getRoadLayer(type));
    }

    //DEBUG
    public RoadSegment(float[] xCoords, float[] yCoords, int ID, double debugLength, int speedLimit, String type, Set<Address> addresses) {
        super(debugLength, type);
        this.xCoords = xCoords;
        this.yCoords = yCoords;
        this.speedLimit = speedLimit;
        this.type = type;
        this.ID = ID;
        this.addresses = addresses;
        setBounds(Setting.innerZoomBound - DrawSettings.getRoadKDLayer(type));
        setLayer(DrawSettings.getRoadLayer(type));
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public int getID() {
        return ID;
    }

    public boolean equals(Object o) {

        if (!(o instanceof RoadSegment comparingRoad)) {
            return false;
        }

        if (this == o) {
            return true;
        }
        return this.ID == comparingRoad.getID();
    }

    @Override
    public Collection<Address> getAddresses() {
        return addresses;
    }

    @Override
    public String toString() {
        return "RoadSegment{" +
                "ID=" + ID +
                ", length=" + length +
                ", speed=" + speedLimit +
                ", name='" + name + '\'' +
                ", oneWay=" + oneWay +
                ", xCoords=" + Arrays.toString(xCoords) +
                ", yCoords=" + Arrays.toString(yCoords) +
                ", type='" + type + '\'' +
                '}';
    }
}