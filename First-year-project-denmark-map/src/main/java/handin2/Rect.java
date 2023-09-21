package handin2;

import Interfaces.Bounds;
import javafx.geometry.Point2D;

public class Rect implements Bounds {

    float minX, minY, maxX, maxY, priorityZ;

    public Rect(Point2D topLeft, Point2D bottomRight, float priorityZ) {
        setBounds((float) topLeft.getX(), (float) topLeft.getY(),(float) bottomRight.getX(),(float) bottomRight.getY(),priorityZ);
    }
    public Rect(float minX, float minY, float maxX, float maxY, float priorityZ) {
        setBounds(minX, minY, maxX, maxY, priorityZ);

    }

    public Rect(Bounds element) {
        setBounds(element.getMinX(), element.getMinY(), element.getMaxX(), element.getMaxY(), 1000000);
    }

    public boolean overlaps(float otherMinX, float otherMinY, float otherMaxX, float otherMaxY, float otherPriorityZ) {

        //Check if other box is inside this box
        if (otherMinX >= minX && otherMaxX <= maxX && otherMinY >= minY && otherMaxY <= maxY && otherPriorityZ <= priorityZ) {
            return true;

            //Check if this box is inside other box
        } else if (otherMinX <= minX && otherMaxX >= maxX && otherMinY <= minY && otherMaxY >= maxY && otherPriorityZ <= priorityZ) {
            return true;

            //Check if other min/max x is inside this box
        } else if (((otherMinX >= minX && otherMinX <= maxX) || (otherMaxX >= minX && otherMaxX <= maxX)) && otherPriorityZ <= priorityZ) {
            //Check if other min/max y is inside this box
            if (((otherMinY >= minY && otherMinY <= maxY) || (otherMaxY >= minY && otherMaxY <= maxY)) && otherPriorityZ <= priorityZ) {
                return true;

            } else if ((otherMinY <= minY && otherMaxY >= maxY) && otherPriorityZ <= priorityZ) {
                return true;
            }
        }
        if (((otherMinY >= minY && otherMinY <= maxY) || (otherMaxY >= minY && otherMaxY <= maxY)) && otherPriorityZ <= priorityZ) {
            return otherMinX <= minX && otherMaxX >= maxX;
        }
        return false;
    }


    public float getMinX() {
        return minX;
    }
    public float getMinY() {
        return minY;
    }
    public float getMaxX() {
        return maxX;
    }
    public float getMaxY() {
        return maxY;
    }
    public float getPriorityZ() {
        return priorityZ;
    }

    @Override
    public void setBounds(float minX, float minY, float maxX, float maxY, float priorityZ) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.priorityZ = priorityZ;
    }
}
