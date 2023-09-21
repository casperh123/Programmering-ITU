package handin2.Settings;

import java.util.HashMap;
import java.util.Map;

public class PathfindingSettings {

    private static boolean cycleMode;
    private static boolean footMode;

    public static int pathFindingMode() {
        if(cycleMode) {
            return 2;
        } else if(footMode) {
            return 3;
        }
        return 1;
    }

    public static int defaultSpeeds(String type) {
        return speedLimits.getOrDefault(type, 50);
    }

    public static boolean isDriveable(String type) {
        return driveables.getOrDefault(type, false);
    }

    public static boolean isCycleable(String type) {
        return cycleables.getOrDefault(type, false);
    }

    public static boolean isWalkable(String type) {
        return walkables.getOrDefault(type, false);
    }

    private static final Map<String, Integer> speedLimits = new HashMap<>() {{
        put("motorway", 120);
        put("motorway_link",120);
        put("primary", 90);
        put("primary_link", 90);
        put("trunk", 80);
        put("trunk_link", 80);
        put("secondary", 80);
        put("secondary_link", 80);
        put("tertiary", 80);
        put("tertiary_link", 80);
    }};


    private static final Map<String, Boolean> driveables = new HashMap<>() {{
        put("motorway", true);
        put("motorway_link", true);
        put("primary", true);
        put("primary_link", true);
        put("trunk", true);
        put("trunk_link", true);
        put("secondary", true);
        put("secondary_link", true);
        put("tertiary", true);
        put("tertiary_link", true);
        put("unclassified", true);
        put("residential", true);
        put("living_street", true);
        put("service", true);
        put("road", true);
    }};

    private static final Map<String, Boolean> cycleables = new HashMap<>() {{
        put("primary", true);
        put("primary_link", true);
        put("secondary", true);
        put("secondary_link", true);
        put("tertiary", true);
        put("tertiary_link", true);
        put("unclassified", true);
        put("residential", true);
        put("living_street", true);
        put("service", true);
        put("road", true);
        put("bridleway", true);
        put("path", true);
        put("crossing", true);
        put("cycleway", true);
    }};

    private static final Map<String, Boolean> walkables= new HashMap<>() {{
        put("secondary", true);
        put("secondary_link", true);
        put("tertiary", true);
        put("tertiary_link", true);
        put("unclassified", true);
        put("residential", true);
        put("living_street", true);
        put("service", true);
        put("road", true);
        put("bridleway", true);
        put("path", true);
        put("crossing", true);
        put("pedestrian", true);
        put("steps", true);
        put("sidewalk", true);
        put("footway", true);
    }};

    public static void setCycleMode() {
        footMode = false;
        cycleMode = true;
    }

    public static void setFootMode() {
        cycleMode = false;
        footMode = true;
    }

    public static void setCarMode() {
        cycleMode = false;
        footMode = false;
    }
}
