package Interfaces;

public interface Bounds {

    float getMinX();

    float getMinY();

    float getMaxX();

    float getMaxY();

    float getPriorityZ();

    void setBounds(float minX, float minY, float maxX, float maxY, float priorityZ);

    default float[] getXCoords() {return null;}

    default float[] getYCoords() {return null;}
}