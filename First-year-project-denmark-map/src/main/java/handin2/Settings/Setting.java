package handin2.Settings;


import Exceptions.UnsupportedFormatException;
import GUI.MapCanvas;

import java.io.Serializable;

public class Setting implements Serializable {

    private static String fileSource;

    static {
        fileSource = "data/denmark-latest.osm";
    }

    public static final float innerZoomBound = 1000f;

    public static boolean debugMode = false;

    public static int defaultSpeedTertiaryRoad = 50;

    public static void setFileSource(String filePath) throws UnsupportedFormatException {
        fileSource = filePath;
    }

    public static String getFileSource() {
        return fileSource;
    }

    public static void toggleDebugMode() {
        debugMode = !debugMode;
        MapCanvas.getInstance().debugRedraw();
    }

    public static void setDebugMode(boolean debug) {
        debugMode = debug;
    }
}
