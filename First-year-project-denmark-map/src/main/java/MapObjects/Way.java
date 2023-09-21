package MapObjects;


import GUI.MapCanvas;
import Interfaces.Bounds;
import Interfaces.iDrawable;
import handin2.Rect;
import handin2.Settings.DrawSettings;
import handin2.Settings.Setting;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.List;

public class Way implements Serializable, Bounds, iDrawable {

    protected float[] xCoords;
    protected float[] yCoords;
    protected String type;
    protected float layer;
    protected float minX, minY, maxX, maxY, priorityZ;

    public Way(List<Node> way, String type) {
        this.type = type.intern();
        this.layer = DrawSettings.getRoadLayer(this.type);
        initialize(way);
    }

    public Way(List<Node> way) {
        this.type = "undefined";
        initialize(way);
    }

    public Way(Way way) {
        this.type = way.getType();
        this.xCoords = way.getXCoords();
        this.yCoords = way.getYCoords();
        this.layer = way.getLayer();
        setBounds(way.getMinX(), way.getMinY(), way.getMaxX(), way.getMaxY(), way.getPriorityZ());
    }

    public Way(String type) {
        this.type = type.intern();
    }


    private void initialize(List<Node> way) {

        this.xCoords = new float[way.size()];
        this.yCoords = new float[way.size()];

        for (int i = 0 ; i < way.size() ; i++) {
            Node node = way.get(i);
            xCoords[i] = 0.56f * node.getX();
            yCoords[i] = node.getY();
        }

        setLayer(DrawSettings.getWayLayer(type));
        setBounds(DrawSettings.getWayKDLayer(type));
    }

    public void setBounds(float layer) {

        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX = -Float.MAX_VALUE;
        float maxY = -Float.MAX_VALUE;


        for (int i = 0; i < xCoords.length; i++) {
            if (xCoords[i] < minX) {
                minX = xCoords[i];
            }
            if (xCoords[i] > maxX) {
                maxX = xCoords[i];
            }
            if (yCoords[i] < minY) {
                minY = yCoords[i];
            }
            if (yCoords[i] > maxY) {
                maxY = yCoords[i];
            }
        }
        setBounds(minX, minY, maxX, maxY, layer);
    }

    //@Override
    public void draw(GraphicsContext gc, double determinant) {
        graphicsSetter(gc, determinant);
        for (int i = 0; i < (xCoords.length - 1) ; i ++) {
            gc.strokeLine(xCoords[i], yCoords[i], xCoords[i+1], yCoords[i+1]);
        }
    };

    protected void graphicsSetter(GraphicsContext gc, double determinant){

        double lineWidth = DrawSettings.getWayLineWidth(type);

        gc.setLineWidth((1/Math.sqrt(determinant)) * lineWidth);

        Color color = DrawSettings.getWayColor(type);

        if(!color.equals(gc.getStroke())) {
            gc.setStroke(color);
        }
    }

    public Way reverseWay(){
        Way way = new Way(this);

        float[] reverseX = new float[way.xCoords.length];
        float[] reverseY = new float[way.yCoords.length];

        int reversePointer = way.xCoords.length-1;

        for(int i = 0 ; i < way.xCoords.length; i++){
            reverseX[reversePointer] = way.xCoords[i];
            reverseY[reversePointer] = way.yCoords[i];
            reversePointer -= 1;
        }

        way.xCoords = reverseX;
        way.yCoords = reverseY;

        return way;
    }

    @Override
    public float[] getXCoords() {
        return xCoords;
    }
    @Override
    public float[] getYCoords() {
        return yCoords;
    }

    public String getType() {
        return type;
    }

    public boolean isClosedWay() {
        if(xCoords.length<=2){
            return false;
        }

        float firstX = xCoords[0];
        float firstY = yCoords[0];

        float lastX = xCoords[yCoords.length - 1];
        float lastY = yCoords[yCoords.length - 1];

        return firstX == lastX && firstY == lastY;
    }

    public static boolean isClosedWay(List<Node> list) {
        if(list.size()<=2){
            return false;
        }
        float firstX = list.get(0).getX();
        float firstY = list.get(0).getY();

        float lastX = list.get(list.size() - 1).getX();
        float lastY = list.get(list.size() - 1).getY();

        return firstX == lastX && firstY == lastY;
    }

    @Override
    public void setBounds(float minX, float minY, float maxX, float maxY, float priorityZ) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.priorityZ = priorityZ;
    }

    public int getLOD() {
        int advance = 1;
        float zoom = Setting.innerZoomBound - MapCanvas.getInstance().zoomLevel;
        if (zoom > 800) advance = 12;
        else if (zoom > 640) advance = 9;
        else if (zoom > 560) advance = 6;
        else if (zoom > 380) advance = 2;
        return advance;
    }

    @Override
    public float getMinX() {
        return minX;
    }

    @Override
    public float getMinY() {
        return minY;
    }

    @Override
    public float getMaxX() {
        return maxX;
    }

    @Override
    public float getMaxY() {
        return maxY;
    }

    @Override
    public float getPriorityZ() {
        return priorityZ;
    }

    @Override
    public float getLayer() { return layer; }
    @Override
    public void setLayer(float layer) {
        this.layer = layer;
    }

}