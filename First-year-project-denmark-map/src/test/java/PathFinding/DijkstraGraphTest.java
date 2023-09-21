package PathFinding;

import Exceptions.NoRouteFoundException;
import Exceptions.NoSuchAddressException;
import Interfaces.iTraversable;
import MapObjects.Markers.Address;
import MapObjects.Traversable.RoadSegment;
import handin2.Settings.Setting;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DijkstraGraphTest {

    DijkstraGraph graph;
    float[] yCoords;
    float[] xCoords;
    Address address1 = new Address("Oesterbrogade", "91", "2100", "Koebenhavn");
    Address address2 = new Address("Yesgade", "1", "2000", "Roskilde");
    RoadSegment segment1;
    RoadSegment segment2;
    RoadSegment segment3;
    RoadSegment segment4;
    DirectedEdge edge1;
    DirectedEdge edge2;
    DirectedEdge edge3;
    DirectedEdge edge4;

    @BeforeEach
    void setUp() {

        yCoords = new float[] {1};
        xCoords = new float[] {1};

        graph = new DijkstraGraph(4);
    }

    @AfterEach
    void tearDown() {
        yCoords = null;
        xCoords = null;
        segment1 = null;
        segment2 = null;
        segment3 = null;
        segment4 = null;
        graph = null;
        edge1 = null;
        edge2 = null;
        edge3 = null;
        edge4 = null;
    }

    @Test
    void addEdge() {

        segment1 = new RoadSegment(xCoords, yCoords, 1, 1, 5.0, "motorway", "");
        segment2 = new RoadSegment(xCoords, yCoords, 2, 1, 2.0, "motorway", "");

        edge1 = new DirectedEdge(segment1, segment2);

        graph.addEdge(edge1);

        Iterable<DirectedEdge> it = graph.getAllEdges();
        Set<DirectedEdge> edges = new HashSet<>();

        for (DirectedEdge directedEdge : it) {
            edges.add(directedEdge);
        }
        assert(edges.contains(edge1));
    }

    @Test
    void getAllEdges() {

        segment1 = new RoadSegment(xCoords, yCoords, 1, 1, 5.0, "motorway", "");
        segment2 = new RoadSegment(xCoords, yCoords, 2, 1, 2.0, "motorway", "");
        segment3 = new RoadSegment(xCoords, yCoords, 3, 1, 10.0, "motorway", "");
        segment4 = new RoadSegment(xCoords, yCoords, 4, 1, 4.0, "motorway", "");

        edge1 = new DirectedEdge(segment1, segment2);
        edge2 = new DirectedEdge(segment1, segment3);
        edge3 = new DirectedEdge(segment2, segment4);
        edge4 = new DirectedEdge(segment3, segment4);

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        Iterable<DirectedEdge> it = graph.getAllEdges();
        Set<DirectedEdge> edges = new HashSet<>();
        for (DirectedEdge directedEdge : it) {
            edges.add(directedEdge);
        }
        assert(edges.contains(edge1) && edges.contains(edge2) && edges.contains(edge3) && edges.contains(edge4));
    }

    @Test
    void noPathFoundExceptionForNoPath() {

        segment1 = new RoadSegment(xCoords, yCoords, 1, 5.0, 1, "motorway", new HashSet<>(Arrays.asList(address2)));
        segment2 = new RoadSegment(xCoords, yCoords, 2, 1, 2.0, "motorway", "");
        segment3 = new RoadSegment(xCoords, yCoords, 3, 1, 10.0, "motorway", "");
        segment4 = new RoadSegment(xCoords, yCoords, 4, 4.0, 1, "motorway", new HashSet<>(Arrays.asList(address1)));

        edge1 = new DirectedEdge(segment1, segment2);
        //edge2 = new DirectedEdge(segment1, segment3);
        //edge3 = new DirectedEdge(segment2, segment4);
        edge4 = new DirectedEdge(segment3, segment4);

        graph.addEdge(edge1);
        //graph.addEdge(edge2);
        //graph.addEdge(edge3);
        graph.addEdge(edge4);

        assertThrows(NoRouteFoundException.class, ()-> graph.getPathTo(segment1.getID(), segment4.getID(), 0));

    }

    @Test
    void getPathToFindsPath() throws NoRouteFoundException, NoSuchAddressException {

        segment1 = new RoadSegment(xCoords, yCoords, 1, 5.0, 1, "motorway",new HashSet<>(Arrays.asList(address1)));
        segment2 = new RoadSegment(xCoords, yCoords, 2, 1, 2.0, "motorway", "");
        segment3 = new RoadSegment(xCoords, yCoords, 3, 1, 10.0, "motorway", "");
        segment4 = new RoadSegment(xCoords, yCoords, 4, 4.0, 1,"motorway", new HashSet<>(Arrays.asList(address2)));

        edge1 = new DirectedEdge(segment1, segment2);
        edge2 = new DirectedEdge(segment1, segment3);
        edge3 = new DirectedEdge(segment2, segment4);
        edge4 = new DirectedEdge(segment3, segment4);

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);

        List<iTraversable> path = graph.getPathTo(segment1.getID(), segment4.getID(), 0);

        assert(path.contains(segment1));
        assert(path.contains(segment4));

    }

    @Test
    void getPathToFindsShortestPath() throws NoRouteFoundException, NoSuchAddressException {

        segment1 = new RoadSegment(xCoords, yCoords, 1, 5.0, 1, "motorway", new HashSet<>(Arrays.asList(address1)));
        segment2 = new RoadSegment(xCoords, yCoords, 2, 1, 10.0, "motorway", "");
        segment3 = new RoadSegment(xCoords, yCoords, 3, 1, 2.0, "motorway", "");
        segment4 = new RoadSegment(xCoords, yCoords, 4, 4.0, 1, "motorway", new HashSet<>(Arrays.asList(address2)));

        edge1 = new DirectedEdge(segment1, segment2);
        edge2 = new DirectedEdge(segment1, segment3);
        edge3 = new DirectedEdge(segment2, segment4);
        edge4 = new DirectedEdge(segment3, segment4);

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);

        List<iTraversable> path = graph.getPathTo(segment1.getID(), segment4.getID(), 0);

        assert(path.get(0).equals(segment1));
        assert(path.get(1).equals(segment3));
        assert(path.get(2).equals(segment4));

    }

}