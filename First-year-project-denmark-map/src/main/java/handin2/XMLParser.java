package handin2;

import Exceptions.NoBoundariesFoundInFileException;
import Exceptions.UnsupportedFormatException;
import Interfaces.iDrawable;
import MapObjects.Markers.Address;
import MapObjects.Node;
import MapObjects.NonTraversable.Area;
import MapObjects.Relation;
import MapObjects.Traversable.Road;
import MapObjects.Way;
import handin2.Settings.DrawSettings;
import handin2.Settings.PathfindingSettings;
import handin2.Settings.Setting;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

public class XMLParser {

    public static List<iDrawable> parseXML(String filename) throws IOException, XMLStreamException, UnsupportedFormatException {

        XMLStreamReader input = load(filename);

        List<iDrawable> drawables = new ArrayList<>();
        Map<Long, Node> id2node = new HashMap<>();
        Map<Long, Way> id2way = new HashMap<>();

        boolean isAtStartElement = false;

        while (input.hasNext()) {

            if(!isAtStartElement) {
                advanceXMLPointer(input);
                isAtStartElement = false;
            }

            String tagName = input.getLocalName();

            if(tagName.equals("node")) {

                readNode(input, isAtStartElement, drawables, id2node);

            } else if(tagName.equals("way")) {

                readWay(input, isAtStartElement, drawables, id2node, id2way);

            } else if(tagName.equals("relation")) {

                readRelation(input, isAtStartElement, id2way, drawables);

            } else {
                advanceXMLPointer(input);
                isAtStartElement = true;
            }
        }
        System.out.println("                                                                                                              Ben Webster");
        System.out.println("XML has been parsed");

        return drawables;
    }

    private static void advanceXMLPointer(XMLStreamReader input) throws XMLStreamException {

        int tagKind = 0;

        while(tagKind != XMLStreamConstants.START_ELEMENT && input.hasNext()) {
            tagKind = input.next();
        }
    }

    protected static XMLStreamReader load(String filename) throws IOException, XMLStreamException, UnsupportedFormatException {

        if (filename.endsWith(".osm.zip")) {

            ZipInputStream zipStream = new ZipInputStream(new FileInputStream(filename));

            zipStream.getNextEntry();

            return XMLInputFactory
                    .newInstance()
                    .createXMLStreamReader(zipStream);

        } else if (filename.endsWith(".osm")) {

            InputStreamReader osmStream = new InputStreamReader(new FileInputStream(filename));

            return XMLInputFactory
                    .newInstance()
                    .createXMLStreamReader(osmStream);
        }
        throw new UnsupportedFormatException(
                "File '" + filename + "' not supported. \n Supported formats are: '.osm' and '.osm.zip'");
    }

    public static Boundaries getBoundaries(String filename) throws XMLStreamException, IOException, UnsupportedFormatException, NoBoundariesFoundInFileException {

        XMLStreamReader input = load(filename);

        while (input.hasNext()) {
            if (input.next() == XMLStreamConstants.START_ELEMENT) {
                String tagName = input.getLocalName();
                if (tagName.equals("bounds")) {
                    float minY = Float.parseFloat(input.getAttributeValue(null, "minlat"));
                    float maxY = Float.parseFloat(input.getAttributeValue(null, "maxlat"));
                    float minX = Float.parseFloat(input.getAttributeValue(null, "minlon"));
                    float maxX = Float.parseFloat(input.getAttributeValue(null, "maxlon"));
                    return new Boundaries(maxX, maxY, minX, minY);
                }
            }
        }
        throw new NoBoundariesFoundInFileException("File didn't include any boundaries");
    }

    private static Node createNode(float x, float y, Map<String, String> tags) {

        if (tags.keySet().containsAll(DrawSettings.getAddressKeys())) {
            return new Address(x, y,
                    tags.get("addr:street"),
                    tags.get("addr:housenumber"),
                    tags.get("addr:postcode"),
                    tags.get("addr:city")
            );
        }
        return new Node(x, y);
    }

    private static void readNode(XMLStreamReader input, boolean isAtStartElement, List<iDrawable> drawables, Map<Long, Node> id2node) throws XMLStreamException {

        Map<String, String> tags = new HashMap<>();

        long id = Long.parseLong(input.getAttributeValue(null, "id"));
        float x = Float.parseFloat(input.getAttributeValue(null, "lon"));
        float y = Float.parseFloat(input.getAttributeValue(null, "lat"));

        advanceXMLPointer(input);
        isAtStartElement = true;

        while (input.hasNext() && input.getLocalName().equals("tag")) {

            tags.put(input.getAttributeValue(null, "k"),
                    input.getAttributeValue(null, "v"));

            advanceXMLPointer(input);
        }

        Node addedNode = createNode(x, y, tags);

        if(addedNode instanceof Address) {
            drawables.add((Address) addedNode);
        } else {
            id2node.put(id, addedNode);
        }
    }

    private static void readWay(XMLStreamReader input, boolean isAtStartElement, List<iDrawable> drawables, Map<Long, Node> id2node, Map<Long, Way> id2way) throws XMLStreamException {

        long id = Long.parseLong(input.getAttributeValue(null, "id"));

        Map<String, String> tags = new HashMap<>();
        List<Node> nodes = new ArrayList<>();

        advanceXMLPointer(input);
        isAtStartElement = true;

        while(input.hasNext() && input.getLocalName().equals("nd")) {

            long wayId = Long.parseLong(input.getAttributeValue(null, "ref"));

            if(id2node.containsKey(wayId)) {
                nodes.add(id2node.get(wayId));
            }

            advanceXMLPointer(input);
        }

        while(input.hasNext() && input.getLocalName().equals("tag")) {
            tags.put(input.getAttributeValue(null, "k"), input.getAttributeValue(null, "v"));
            advanceXMLPointer(input);
        }

        createWay(nodes, tags, drawables, id2way, id);
    }

    private static void createWay(List<Node> ways, Map<String, String> tags, List<iDrawable> drawables, Map<Long, Way> id2way, long id) {

        Way addedObject = null;

        if(tags.size() == 0) {
            id2way.put(id, new Way(ways));
            return;
        }
        if (ways.size() == 0){
            return;
        }

        for(String tagKey : tags.keySet()) {
            if (tagKey.equals("building")) {
                addedObject = new Area(ways, "building");
                drawables.add(addedObject);
                break;
            } else if ((tags.get(tagKey).equals("coastline") || tags.containsKey("border_type") || tagKey.equals("note") || tags.isEmpty() || tags.containsKey("boundary")) && !Way.isClosedWay(ways)) {

                addedObject = new Way(ways, tags.get(tagKey));
                break;

            } else if (DrawSettings.isWay(tagKey, tags.get(tagKey))) {

                addedObject = new Way(ways, tags.get(tagKey));
                drawables.add(addedObject);
                break;

            } else if(DrawSettings.isRoad(tagKey, tags.get(tagKey))) {

                int speed = Setting.defaultSpeedTertiaryRoad;

                if(tags.containsKey("maxspeed")) {
                    try {
                        speed = Integer.parseInt(tags.get("maxspeed"));
                    } catch (NumberFormatException e) {
                        speed = PathfindingSettings.defaultSpeeds(tags.get("highway"));
                    }
                }
                String roadName;
                boolean oneWayStatus = determineOneWay(tags);
                try {
                    roadName = tags.get("name").intern();
                } catch (NullPointerException e) {
                    roadName = "Unnamed road";
                }
                addedObject = new Road(ways, tags.get(tagKey), speed, roadName, oneWayStatus);
                drawables.add(addedObject);
                break;

            } else if (DrawSettings.isArea(tagKey, tags.get(tagKey)) && Way.isClosedWay(ways)) {

                addedObject = new Area(ways, tags.get(tagKey));
                drawables.add(addedObject);
                break;
            }
        }

        if(addedObject != null) {
            id2way.put(id, addedObject);
        }
    }

    private static void readRelation(XMLStreamReader input, boolean isAtStartElement, Map<Long, Way> id2way, List<iDrawable> drawables) throws XMLStreamException {

        Map<String, String> tags = new HashMap<>();
        List<Way> relationBounds = new ArrayList<>();

        advanceXMLPointer(input);
        isAtStartElement = true;

        while(input.hasNext() && input.getLocalName().equals("member")){

            String role = input.getAttributeValue(null,"role");

            if(role.equals("outer")) {

                Long wayId = Long.parseLong(input.getAttributeValue(null,"ref"));

                if(id2way.containsKey(wayId)) {
                    Way tempWay = id2way.get(wayId);
                    relationBounds.add(tempWay);
                }
            }
            advanceXMLPointer(input);
        }

        while(input.hasNext() && input.getLocalName().equals("tag")){
            tags.put(input.getAttributeValue(null, "k"),
                    input.getAttributeValue(null, "v"));
            advanceXMLPointer(input);
        }
        if (tags.containsKey("building")) {
            tags.put("building", "building");
        }
        for(String tag : tags.keySet()){
            if(DrawSettings.isRelation(tag, tags.get(tag)) && relationBounds.size() > 0){

                Relation currRelation = new Relation(relationBounds, tags.get(tag));
                drawables.add(currRelation);
                break;
            }
        }
    }
    protected static boolean determineOneWay(Map<String, String> tags) {
        boolean status = false;
        if (tags.containsKey("oneway")) {
            status = true;
        } else if (tags.containsKey("junction") && tags.get("junction").equals("roundabout")) {
            status = true;
        } else if (tags.containsKey("highway") && tags.get("highway").equals("motorway")) {
            status = true;
        }
        return status;
    }

}