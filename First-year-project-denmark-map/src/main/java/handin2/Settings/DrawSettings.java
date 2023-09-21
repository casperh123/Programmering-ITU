package handin2.Settings;

import GUI.MapCanvas;
import javafx.scene.paint.Color;

import java.util.*;

public class DrawSettings {


    // Toggle theme
    public static boolean darkTheme = false;
    public static void toggleDarkThemeMode() {
        darkTheme = !darkTheme;
        MapCanvas.getInstance().debugRedraw();
    }

    // Draw layers
    public static float getAreaLayer(String type) {
        return area2Layer.getOrDefault(type, 7.0f);
    }
    public static float getWayLayer(String type) { return way2Layer.getOrDefault(type, 8.0f); }
    public static float getRoadLayer(String type) {
        return road2Layer.getOrDefault(type, 12.0f);
    }
    public static float getRelationLayer(String type) {
        return relation2Layer.getOrDefault(type, 0.6f);
    }

    // KD layers
    public static float getAreaKDLayer(String type) { return areaKDLayer.getOrDefault(type, 150f); }
    public static float getWayKDLayer(String type) { return wayKDLayer.getOrDefault(type, 150f); }
    public static float getRoadKDLayer(String type) { return roadKDLayer.getOrDefault(type, 150f); }
    public static float getRelationKDLayer(String type) { return relationKDLayer.getOrDefault(type, 0f); }

    // Compare keys and values
    public static boolean isArea(String key, String value) {
        return getAreaKeys().contains(key) && getAreaValues().contains(value);
    }
    public static boolean isWay(String key, String value) {
        return getWayKeys().contains(key) && getWayValues().contains(value);
    }
    public static boolean isRoad(String key, String value) {
        return getRoadKeys().contains(key) && getRoadValues().contains(value);
    }
    public static boolean isRelation(String key, String value) {
        return getRelationKeys().contains(key) && getRelationValues().contains(value);
    }

    // Colors
    public static Color getAreaColor(String wayType) {
        return areaColors.getOrDefault(wayType, Color.web("rgba(0, 0, 0, 1)"));
    }
    public static Color getWayColor(String wayType) {
        return wayColors.getOrDefault(wayType, Color.web("rgba(0, 0, 0, 1)"));
    }
    public static Color getRoadColor(String wayType) {
        return roadColors.getOrDefault(wayType, Color.web("rgba(0, 0, 0, 1)"));
    }
    public static Color getRelationColor(String Type) {
        return relationColors.getOrDefault(Type, Color.web("rgba(0, 0, 0, 1)"));
    }

    // Additional draw settings
    public static double getWayLineWidth(String roadtype) {
        return wayTypeWidth.getOrDefault(roadtype, 1.0d);
    }
    public static double getRoadLineWidth(String roadtype) {
        return roadTypeWidth.getOrDefault(roadtype, 1.0d);
    }
    public static double[] getRoadDash(String roadtype) {
        return roadTypeDash.getOrDefault(roadtype, new double[]{1d, 0d});
    }

    //
    //
    // Address
    public static Collection<String> getAddressKeys() {

        return new ArrayList<>(
                Arrays.asList(
                        "addr:city",
                        "addr:country",
                        "addr:housenumber",
                        "addr:street",
                        "addr:postcode")
        );
    }

    //
    //
    //Area properties
    private static Set<String> getAreaKeys() {
        return new HashSet<>(
                Arrays.asList(
                        "landuse",
                        "man_made",
                        "natural",
                        "building",
                        "waterway",
                        "aeroway",
                        "amenity",
                        "leisure"

                ));
    }
    private static Set<String> getAreaValues() {
        return new HashSet<>(
                Arrays.asList(
                        "peninsula",
                        "building",
                        "coastline",
                        "breakwater",
                        "commercial",
                        "industrial",
                        "residential",
                        "retail",
                        "farmland",
                        "farmyard",
                        "forest",
                        "greenhouse_horticulture",
                        "meadow",
                        "orchard",
                        "plant_nursery",
                        "vineyard",
                        "basin",
                        "salt_pond",
                        "yes",
                        "dock",
                        "grassland",
                        "grass",
                        "heath",
                        "scrub",
                        "wood",
                        "beach",
                        "shoal",
                        "water",
                        "sand",
                        "mud",
                        "pier",
                        "aerodrome",
                        "apron",
                        "parking",
                        "quarry",
                        "sports_centre",
                        "playground",
                        "park",
                        "allotments",
                        "school",
                        "grave_yard",
                        "hospital",
                        "kindergarten",
                        "helipad",
                        "cemetery"
                ));
    }
    private static final Map<String, Color> areaColors = new HashMap<>() {{
        put("water", Color.web("#b4d0d0"));
        put("residential", Color.web("#e0dfdf"));
        put("industrial", Color.web("#eadae8"));
        put("coastline", Color.web("#F3EFE8"));
        put("island", Color.web("#F3EFE8"));
        put("4", Color.web("#F3EFE8"));
        put("beach", Color.web("#fff1ba"));
        put("breakwater", Color.web("#abaaab"));
        put("bridge", Color.web("#b9b8b9"));
        put("pier", Color.web("#f3eee8"));
        put("wastewater_plant", Color.web("#e7d1e2"));
        put("grassland", Color.web("#cfedb1"));
        put("grass", Color.web("#cdebb0"));
        put("heath", Color.web("#d6d99f"));
        put("scrub", Color.web("#c9d8ad"));
        put("wood", Color.web("#9ccb8a"));
        put("mud", Color.web("#e6dcd2"));
        put("shoal", Color.web("#fff0ba"));
        put("sand", Color.web("#f5eac7"));
        put("commercial", Color.web("#efcecf"));
        put("retail", Color.web("#fecac5"));
        put("farmland", Color.web("#eff1d7"));
        put("farmyard", Color.web("#eacda5"));
        put("greenhouse_horticulture", Color.web("#eff1d7"));
        put("meadow", Color.web("#9fdd90"));
        put("orchard", Color.web("#9fdd90"));
        put("plant_nursery", Color.web("#aee1a4"));
        put("vineyard", Color.web("#9fdd90"));
        put("basin", Color.web("#abd5e1"));
        put("salt_pond", Color.web("#abd5e1"));
        put("building", Color.web("#d9d1ca"));
        put("forest", Color.web("#add09f"));
        put("unclassified", Color.web("#fefefe"));
        put("aerodrome", Color.web("#e8e7e1"));
        put("apron", Color.web("dadadf"));
        put("parking", Color.web("eeeeee"));
        put("runway", Color.web("#bbbbcb"));
        put("quarry", Color.web("#c4c3c3"));
        put("sports_centre", Color.web("#dffce2"));
        put("playground", Color.web("#dffce2"));
        put("park", Color.web("#c8facc"));
        put("allotments", Color.web("#c9e1bf"));
        put("school", Color.web("#ffffe5"));
        put("hospital", Color.web("#ffffe5"));
        put("kindergarten", Color.web("#ffffe5"));
        put("grave_yard", Color.web("#aacbaf"));
        put("helipad", Color.web("#bbbbcc"));
        put("DK-81", Color.web("#F3EFE8"));
        put("DK-82", Color.web("#F3EFE8"));
        put("DK-83", Color.web("#F3EFE8"));
        put("DK-84", Color.web("#F3EFE8"));
        put("DK-85", Color.web("#F3EFE8"));
        put("lake", Color.web("#b4d0d0"));
        put("cemetery", Color.web("#aacbaf"));
    }};
    private static final Map<String, Float> area2Layer = new HashMap<>() {{
        put("coastline", 0.234f);
        put("DK-81", 0.234f);
        put("DK-82", 0.234f);
        put("DK-83", 0.234f);
        put("DK-84", 0.234f);
        put("DK-85", 0.234f);
        put("breakwater", 3.2f);
        put("commercial", 3.15f);
        put("industrial", 3.14f);
        put("residential", 3.13f);
        put("retail", 3.14f);
        put("helipad", 4.56f);
        put("farmland", 3.21f);
        put("farmyard", 3.22f);
        put("forest", 3.21f);
        put("greenhouse_horticulture", 3.23f);
        put("meadow", 3.24f);
        put("orchard", 3.25f);
        put("plant_nursery", 3.26f);
        put("vineyard", 3.27f);
        put("basin", 3.28f);
        put("salt_pond", 3.29f);
        put("yes", 4.01f);
        put("school", 3.91f);
        put("hospital", 3.92f);
        put("kindergarten", 3.93f);
        put("building", 4.01f);
        put("dock", 3.15f);
        put("grassland", 3.292f);
        put("grass", 3.295f);
        put("heath", 3.293f);
        put("scrub", 3.294f);
        put("wood", 2.111f);
        put("pier", 2.151f);
        put("beach", 2.15f);
        put("shoal", 2.151f);
        put("water", 3.561f);
        put("sand", 2.11f);
        put("mud", 2.13f);
        put("aerodrome", 0.241f);
        put("apron", 3.021f);
        put("parking", 4.002f);
        put("runway", 2.5111f);
        put("sports_centre", 3.5f);
        put("playground", 3.51f);
        put("park", 3.49f);
        put("allotments", 3.52f);
        put("grave_yard", 3.53f);
        put("cemetery", 3.53f);
    }};
    private static final Map<String, Float> areaKDLayer = new HashMap<>() {{
        put("coastline", 1000000f);
        put("DK-81", 1000000f);
        put("DK-82", 1000000f);
        put("DK-83", 1000000f);
        put("DK-84", 1000000f);
        put("DK-85", 1000000f);
        put("breakwater", 475f);
        put("building", 375f);
        put("commercial", 500f);
        put("industrial", 500f);
        put("residential", 575f);
        put("retail", 400f);
        put("farmland", 500f);
        put("farmyard", 450f);
        put("forest", 680f);
        put("greenhouse_horticulture", 200f);
        put("meadow", 400f);
        put("orchard", 400f);
        put("plant_nursery", 300f);
        put("vineyard", 300f);
        put("basin", 300f);
        put("salt_pond", 150f);
        put("yes", 375f);
        put("dock", 250f);
        put("grassland", 600f);
        put("grass", 500f);
        put("heath", 500f);
        put("scrub", 600f);
        put("wood", 500f);
        put("pier", 200f);
        put("beach", 600f);
        put("shoal", 300f);
        put("parking", 450f);
        put("water", 500f);
        put("sand", 300f);
        put("mud", 300f);
        put("school", 450f);
        put("hospital", 450f);
        put("kindergarten", 450f);
        put("helipad", 375f);
        put("sports_centre", 500f);
        put("playground", 500f);
        put("park", 500f);
        put("allotments", 500f);
        put("grave_yard", 500f);
        put("cemetery", 500f);

    }};

    //
    //
    // Way properties
    private static Set<String> getWayKeys() {
        return new HashSet<>(
                Arrays.asList(
                        "waterway"
                ));
    }
    private static Set<String> getWayValues() {

        return new HashSet<>(
                Arrays.asList(
                        "river",
                        "stream",
                        "tidal_channel",
                        "canal",
                        "drain",
                        "ditch"
                ));
    }
    private static final Map<String, Color> wayColors = new HashMap<>() {{

        put("stream", Color.web("#abd5e1"));
        put("river", Color.web("#abd5e1"));
        put("tidal_channel", Color.web("#abd5e1"));
        put("canal", Color.web("#abd5e1"));
        put("drain", Color.web("#abd5e1"));
        put("ditch", Color.web("#abd5e1"));

    }};
    private static final Map<String, Float> way2Layer = new HashMap<>() {{

        put("stream", 8.0f);
        put("river", 8.0f);
        put("tidal_channel", 8.0f);
        put("canal", 8.0f);
        put("drain", 8.0f);
        put("ditch", 8.0f);

    }};
    private static final Map<String, Float> wayKDLayer = new HashMap<>() {{

        put("stream", 640f);
        put("river", 640f);
        put("tidal_channel", 600f);
        put("canal", 600f);
        put("drain", 600f);
        put("ditch", 600f);

    }};
    private static final Map<String, Double> wayTypeWidth = new HashMap<>() {{
        put("stream", 3.5d);
        put("river", 3.5d);
        put("tidal_channel", 3.5d);
        put("canal", 3.5d);
        put("drain", 3.5d);
        put("ditch", 3.5d);
        put("water", 3.5d);

    }};


    //
    //
    //Road properties
    private static Set<String> getRoadKeys() {
        return new HashSet<>(
                Arrays.asList(
                        "highway",
                        "natural",
                        "waterway",
                        "aeroway"
                ));
    }
    private static Set<String> getRoadValues() {

        return new HashSet<>(
                Arrays.asList(
                        "motorway",
                        "motorway_link",
                        "trunk",
                        "trunk_link",
                        "primary",
                        "primary_link",
                        "secondary",
                        "secondary_link",
                        "tertiary",
                        "tertiary_link",
                        "unclassified",
                        "residential",
                        "living_street",
                        "turning_circle",
                        "service",
                        "pedestrian",
                        "track",
                        "bus_guideway",
                        "raceway",
                        "road",
                        "footway",
                        "bridleway",
                        "steps",
                        "path",
                        "sidewalk",
                        "crossing",
                        "cycleway",
                        "track",
                        "runway"
                ));
    }
    private static final Map<String, Color> roadColors = new HashMap<>() {{

        put("motorway", Color.web("#e990a0"));
        put("motorway_link", Color.web("#e891a1"));
        put("motorway_junction", Color.LIGHTCORAL);
        put("trunk", Color.web("#fbc1ad"));
        put("trunk_link", Color.web("#fbc1ad"));
        put("service", Color.web("#fff"));
        put("primary", Color.web("#fcd6a1"));
        put("primary_link", Color.web("#fdddb3"));
        put("secondary", Color.web("#f7faba"));
        put("residential", Color.web("#f7faba"));
        put("road", Color.web("#f7faba"));
        put("unclassified", Color.web("#fefefe"));
        put("secondary_link", Color.web("#f9fbcb"));
        put("highway", Color.DARKGRAY);
        put("tertiary", Color.web("#fefefe"));
        put("tertiary_link", Color.web("#fefefe"));
        put("turning_circle", Color.ANTIQUEWHITE);
        put("living_street", Color.web("#edecec"));
        put("pedestrian", Color.web("#dcdce8"));
        put("bus_guideway", Color.web("#7071fc")); //STRIPED
        put("raceway", Color.PLUM);
        put("footway", Color.web("#f9b1a7")); //DOTTED
        put("bridleway", Color.web("#6ab369")); //DOTTED
        put("steps", Color.web("#fa8d7e")); //DOTTED
        put("path", Color.web("#f8b3a9"));
        put("cycleway", Color.web("#9191f7")); //DOTTED
        put("track", Color.web("#dfd3ab"));
        put("debugoneway", Color.web("#00FF00"));
        put("routePath", Color.CRIMSON);
    }};
    private static final Map<String, Float> road2Layer = new HashMap<>() {{
        put("coastline", 0f);
        put("motorway", 5.51f);
        put("motorway_link", 5.5f);
        put("trunk", 5.11f);
        put("trunk_link", 5.1f);
        put("primary", 5.41f);
        put("primary_link", 5.4f);
        put("secondary", 5.31f);
        put("secondary_link", 5.3f);
        put("tertiary", 5.21f);
        put("tertiary_link", 5.2f);
        put("unclassified", 2.01f);
        put("residential", 3.5f);
        put("living_street", 3.4f);
        put("turning_circle", 5.23f);
        put("service", 3.24f);
        put("pedestrian", 0f);
        put("track", 4.50f);
        put("bus_guideway", 5.01f);
        put("raceway", 4.501f);
        put("road", 4.201f);
        put("footway", 2.1f);
        put("bridleway", 4023f);
        put("steps", 2.01f);
        put("path", 2.021f);
        put("sidewalk", 2.022f);
        put("crossing", 2.02001f);
        put("cycleway", 2.0032f);
        put("routePath", 8.0f);

    }};
    private static final Map<String, Float> roadKDLayer = new HashMap<>() {{
        put("motorway", 800f);
        put("coastline", -10000f); // Never want this shown. Even if it is unnecessary.
        put("motorway_link", 600f);
        put("primary", 600f);
        put("primary_link", 500f);
        put("secondary", 500f);
        put("secondary_link", 480f);
        put("tertiary", 480f);
        put("tertiary_link", 440f);
        put("unclassified", 440f);
        put("residential", 440f);
        put("living_street", 300f);
        put("turning_circle", 400f);
        put("service", 400f);
        put("pedestrian", 400f);
        put("track", 450f);
        put("bus_guideway", 400f);
        put("raceway", 400f);
        put("road", 500f);
        put("footway", 300f);
        put("bridleway", 400f);
        put("steps", 400f);
        put("path", 400f);
        put("sidewalk", 300f);
        put("crossing", 400f);
        put("cycleway", 300f);
        put("routePath", 100000.0f);

    }};
    private static final Map<String, Double> roadTypeWidth = new HashMap<>() {{
        put("motorway", 1.2d);
        put("motorway_link", 1.2d);
        put("motorway_junction", 1.2d);
        put("toll_gantry", 0.5d);
        put("emergency_bay", 0.5d);
        put("rest_area", 1.0d);
        put("trunk", 1.1d);
        put("trunk_link", 1.1d);
        put("primary", 5d);
        put("primary_link", 4d);
        put("secondary", 3d);
        put("secondary_link", 3d);
        put("highway", 1.2d);
        put("tertiary", 3d);
        put("tertiary_link", 3d);
        put("turning_circle", 0.8d);
        put("turning_loop", 0.8d);
        put("residential", 1.0d);
        put("living_street", 0.6d);
        put("service", 1.2d);
        put("pedestrian", 2d);
        put("crossing", 1.5d);
        put("track", 1.5d); //DOTTED
        put("bus_guideway", 0.6d); //STRIPED
        put("busway", 0.6d); //STRIPED
        put("bus_stop", 0.6d);
        put("platform", 0.6d);
        put("escape", 0.6d);
        put("raceway", 1d);
        put("emergency_access_point", 1.6d);
        put("footway", 1.5d); //DOTTED
        put("bridleway", 1.5d); //DOTTED
        put("steps", 1.5d); //DOTTED
        put("cycleway", 1.5d); //DOTTED
        put("construction", 1.5d); //DOTTED
        put("runway", 1.2d);
    }};
    private static final Map<String, double[]> roadTypeDash = new HashMap<>() {{
        put("track", new double[]{1d, 0.5d}); //DOTTED
        put("bus_guideway", new double[]{1d, 0.5d}); //STRIPED
        put("busway", new double[]{1d, 0.5d}); //STRIPED
        put("footway", new double[]{1d, 1d}); //DOTTED
        put("bridleway", new double[]{1d, 1d}); //DOTTED
        put("steps", new double[]{1d, 1d}); //DOTTED
        put("cycleway", new double[]{1d, 1d}); //DOTTED
        put("construction", new double[]{1d, 1d}); //DOTTED
    }};


    //
    //
    //
    //Relation properties
    private static Set<String> getRelationKeys() {
        return new HashSet<>(
                Arrays.asList(
                        "highway",
                        "natural",
                        "landuse",
                        "place",
                        "water",
                        "building",
                        "ISO3166-2"
                ));
    }
    private static Set<String> getRelationValues() {

        return new HashSet<>(

                Arrays.asList(
                        "forest",
                        //"island",
                        "water",
                        "sand",
                        "beach",
                        "farmland",
                        //"peninsula",
                        "lake",
                        "building",
                        "residential",
                        "DK-81",
                        "DK-82",
                        "DK-83",
                        "DK-84",
                        "DK-85"
                        ));
    }
    private static final Map<String, Color> relationColors = new HashMap<>() {{

        //put("island", Color.web("#F3EFE8"));
        put("forest", Color.web("#add09f"));
        put("water", Color.web("#b4d0d0"));
        put("sand", Color.web("#f5eac7"));
        put("beach", Color.web("#fff1ba"));
        //put("peninsula", Color.web("#F3EFE8"));
        put("farmland", Color.web("#eff1d7"));
        put("lake", Color.web("#b4d0d0"));
        put("unclassified", Color.web("#fefefe"));

    }};
    private static final Map<String, Float> relation2Layer = new HashMap<>() {{
        put("forest", 0.3f);
        put("DK-81", 0.1f);
        put("DK-82", 0.1f);
        put("DK-83", 0.1f);
        put("DK-84", 0.1f);
        put("DK-85", 0.1f);
        //put("island", 0.1f);
        put("water", 0.01f);
        put("sand", 0.7f);
        put("beach", 0.99f);
        put("farmland", 0.35f);
        put("building", 4.01f);
    }};
    private static final Map<String, Float> relationKDLayer = new HashMap<>() {{
        put("forest", 800f);
        //put("island", 1000f);
        put("DK-81", 1000f);
        put("DK-82", 1000f);
        put("DK-83", 1000f);
        put("DK-84", 1000f);
        put("DK-85", 1000f);
        put("water", 1000f);
        put("sand", 1000f);
        put("beach", 1000f);
        put("farmland", 500f);
        //put("peninsula", 1000f);
        put("building", 375f);
    }};
}
