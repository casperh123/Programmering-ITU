package handin2;

import Exceptions.NoBoundariesFoundInFileException;
import Exceptions.UnsupportedFormatException;
import Interfaces.iDrawable;
import Interfaces.iTraversable;
import MapObjects.Markers.Address;
import MapObjects.Traversable.Road;
import PathFinding.DijkstraGraph;
import PathFinding.DirectedEdge;
import handin2.Settings.Setting;

import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataSingleton implements Serializable {

    private static KDTree<iDrawable> nonTraversableTree;
    private static KDTree<iTraversable> traversableTree;
    private static TernTree addressTree;
    private static List<iDrawable> drawables;
    private static List<Address> addressList;
    private static Boundaries boundaries;
    private static DijkstraGraph routeGraph;


    static {
        reloadData();
    }

    public static void reloadData() {

        System.gc();

        try {
            drawables = parseXML();
            addressList = setAddressList();
            boundaries = parseBoundaries();
        } catch (UnsupportedFormatException e) {
            try {
                Setting.setFileSource("data/bornholm.osm.zip");
                drawables = parseXML();
                addressList = setAddressList();
                boundaries = parseBoundaries();
            } catch (UnsupportedFormatException ex) {
                throw new RuntimeException(ex);
            }
        }
        nonTraversableTree = new KDTree<>(getNonTraversables(), 3);
        traversableTree = new KDTree<>(getRoadSegments(), 3);
        routeGraph = generateRouteGraph(traversableTree);
        addressTree = new TernTree();
        addressTree.insert(addressList);
        drawables = null;
        addressList = null;
    }

    private static List<iDrawable> parseXML() throws UnsupportedFormatException{

        List<iDrawable> drawables;

        if(!Setting.debugMode) {

            File f = new File(Setting.getFileSource().split("\\.")[0] + ".obj");

            if(f.isFile()) {
                try {
                    drawables = (List<iDrawable>) FileManager.loadFile(f);
                    System.out.println("Read .obj");
                    return drawables;
                } catch (IOException e) {
                    System.out.println("Fallback XML parse. " + e.getMessage());
                }
            }
        }

        try {

            drawables = XMLParser.parseXML(Setting.getFileSource());

            FileManager.save(Setting.getFileSource().split("\\.")[0] + ".obj", drawables);

            return drawables;

        } catch (IOException | XMLStreamException e) {
            throw new UnsupportedFormatException(e.getMessage());
        }
    }

    public static Boundaries getBoundaries() {
        return boundaries;
    }

    private static Boundaries parseBoundaries() throws UnsupportedFormatException {

        if(Setting.debugMode) System.out.println("Parsing boundaries");

        Boundaries boundaries;

        try {
            boundaries = XMLParser.getBoundaries(Setting.getFileSource());
        } catch (NoBoundariesFoundInFileException e) {
            boundaries = new Boundaries(0, 0, 0, 0);
        } catch (IOException | XMLStreamException e) {
            throw new UnsupportedFormatException("The markup in the document following the root element must be well-formed");
        }
        return boundaries;
    }

    public static KDTree<iDrawable> getNonTraversableTree() {
        return nonTraversableTree;
    }

    public static KDTree<iTraversable> getTraversibleTree() {
        return traversableTree;
    }

    public static DijkstraGraph getRouteGraph() {
        return routeGraph;
    }

    private static DijkstraGraph generateRouteGraph(KDTree<iTraversable> segmentedTraversables) {

        EdgeGenerater edgeGenerater = new EdgeGenerater(segmentedTraversables);
        List<DirectedEdge> edges = edgeGenerater.getEdges();

        DijkstraGraph graph = new DijkstraGraph(edges.size());

        for(DirectedEdge edge : edges) {
            graph.addEdge(edge);
        }

        return graph;

    }

    private static List<iDrawable> getNonTraversables() {

        List<iDrawable> nonTraversables = new ArrayList<>();

        for(iDrawable drawable : drawables) {
            if(!(drawable instanceof Address) && !(drawable instanceof iTraversable)) {
                nonTraversables.add(drawable);
            }
        }

        return new ArrayList<>(nonTraversables);

    }

    private static List<iTraversable> getRoadSegments() {

        RoadSegmenter roadSegmenter = new RoadSegmenter(getRoads());

        return roadSegmenter.getRoadSegments();
    }

    private static List<iTraversable> getRoads() {

        List<iTraversable> roads = new ArrayList<>();

        for(iDrawable drawable : drawables) {
            if(drawable instanceof Road) {
                roads.add((iTraversable) drawable);
            }
        }

        return roads;
    }

    private static List<Address> setAddressList() {

        List<Address> addresses = new ArrayList<>();
        for (iDrawable drawable : drawables) {
            if (drawable instanceof Address) {
                addresses.add((Address) drawable);
            }
        }
        return addresses;
    }

    public static TernTree getTST() {
        return addressTree;
    }
}