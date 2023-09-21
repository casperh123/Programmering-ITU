package handin2;

import java.io.Serializable;

public class Boundaries implements Serializable {

    private final float maxLon, maxLat, minLon, minLat;

    public Boundaries(float maxLon, float maxLat, float minLon, float minLat) {
        this.maxLon = maxLon;
        this.maxLat = maxLat;
        this.minLon = minLon;
        this.minLat = minLat;
    }

    public float getMaxLon() {
        return maxLon;
    }

    public float getMaxLat() {
        return maxLat;
    }

    public float getMinLon() {
        return minLon;
    }

    public float getMinLat() {
        return minLat;
    }

}
