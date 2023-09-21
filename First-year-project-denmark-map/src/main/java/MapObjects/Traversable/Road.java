package MapObjects.Traversable;

import Interfaces.iTraversable;
import MapObjects.Markers.Address;
import MapObjects.Node;
import MapObjects.Way;
import handin2.Settings.DrawSettings;
import handin2.Settings.Setting;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Road extends Way implements iTraversable {

    protected int speedLimit;
    private double debugLength;
    protected double length;
    protected String name;
    protected boolean oneWay;

    public Road(List<Node> way, String type, int speedlimit, String roadName, boolean oneWay) {
        super(way, type);
        this.speedLimit = speedlimit;
        this.length = calcLength();
        this.name = roadName.intern();
        this.oneWay = oneWay;
        setBounds(Setting.innerZoomBound - DrawSettings.getRoadKDLayer(type));
        setLayer(DrawSettings.getRoadLayer(type));
    }
    //Debug
    public Road(double debugLength, String type) {
        super(type);
        this.length = debugLength;
        this.speedLimit = 0;
        this.name = "null";
        setLayer(DrawSettings.getRoadLayer(type));
    }
    public double getLength() {
        return length;
    }
    @Override
    public int getSpeedLimit() {
        return this.speedLimit;
    }
    @Override
    public int getID() {
        return 0;
    }
    @Override
    public Collection<Address> getAddresses() {
        return new HashSet<>();
    }
    @Override
    public float[] getXCoords() {
        return this.xCoords;
    }
    @Override
    public float[] getYCoords() {
        return this.yCoords;
    }
    private double calcLength() {
        double sum = 0;
        double radius = 6371000;
        float[] xCoords = getXCoords();
        float[] yCoords = getYCoords();


        for (int i = 0; i < xCoords.length - 1; i++) {
            double y1Rad = yCoords[i] * Math.PI / 180;
            double y2Rad = yCoords[i + 1] * Math.PI / 180;
            double deltY = (Math.abs(yCoords[i + 1]) - Math.abs(yCoords[i])) * Math.PI / 180;
            double deltX = (Math.abs(xCoords[i + 1]) - Math.abs(xCoords[i])) * Math.PI / 180;

            double a =  Math.sin(deltY / 2) * Math.sin(deltY / 2) +
                    Math.cos(y1Rad) * Math.cos(y2Rad) *
                            Math.sin(deltX/2) * Math.sin(deltX/2);

            double c = (2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a)));

            sum += radius * c;
        }
        return sum;
    }
    @Override
    protected void graphicsSetter(GraphicsContext gc, double determinant){

        double lineWidth = DrawSettings.getRoadLineWidth(type);

        gc.setLineWidth((1/Math.sqrt(determinant)) * lineWidth);

        Color color;
        if (Setting.debugMode && isOneWay()) {
            color = DrawSettings.getRoadColor("debugoneway");
        } else {
            color = DrawSettings.getRoadColor(type);
        }


        if(!color.equals(gc.getStroke())) {
            gc.setStroke(color);
        }
        gc.setLineDashes(DrawSettings.getRoadDash(type));
    }
    public String getName() { return name;}
    public boolean isOneWay() {return oneWay;}
}