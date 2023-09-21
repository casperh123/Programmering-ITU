package handin2;

import Interfaces.iTraversable;
import MapObjects.Node;
import MapObjects.Traversable.RoadSegment;
import PathFinding.DirectedEdge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EdgeGeneraterTest {
    EdgeGenerater EG;
    @BeforeEach
    public void setup(){
        EG = new EdgeGenerater(DataSingleton.getTraversibleTree());

    }
    @Test
    public void testGenerateEdge(){
        int EdgeListSize = EG.getEdges().size();
        List<DirectedEdge> oldList = EG.getEdges();
        List<DirectedEdge> newList = EG.generateEdges();

        assertFalse(oldList.isEmpty());
        assertEquals(EdgeListSize, newList.size(), "The two generated lists of edges should be of equal size as they're given the same dataset.");
    }

    //We've found that when two roadsegments intersect they share a node,
    //in the spot where they intersect.
    @Test
    public void RoadsThatIntersectInTheEnd(){
        Node n1 = new Node(10f,20f);
        Node n2 = new Node(20f, 10f);
        Node n3 = new Node(10f,20f);
        Node n4 = new Node(35f, 55f);
        List<Node> nodes1 = new ArrayList<>();
        nodes1.add(n1); nodes1.add(n2);
        List<Node> nodes2 = new ArrayList<>();
        nodes2.add(n3); nodes2.add(n4);

        iTraversable road = new RoadSegment(nodes1,"primary", 55, 44, "Christianborgsvej", false );
        iTraversable checkingRoad = new RoadSegment(nodes2,"primary", 55, 44, "Frederikborgsvej", false );
        assertTrue(EG.traversableIsConnected(road, checkingRoad, false, false));

    }
    @Test
    public void roadsThatDoesNotIntersect(){
        Node n1 = new Node(10f,20f);
        Node n2 = new Node(20f, 10f);
        Node n3 = new Node(20f,20f);
        Node n4 = new Node(35f, 55f);
        List<Node> nodes1 = new ArrayList<>();
        nodes1.add(n1); nodes1.add(n2);
        List<Node> nodes2 = new ArrayList<>();
        nodes2.add(n3); nodes2.add(n4);

        iTraversable road = new RoadSegment(nodes1,"primary", 55, 44, "Christianborgsvej", false );
        iTraversable checkingRoad = new RoadSegment(nodes2,"primary", 55, 44, "Frederikborgsvej", false );
        assertFalse(EG.traversableIsConnected(road, checkingRoad, false, false));

    }
    @Test
    public void OneWayThatIntersectsCorrectly(){
        Node n1 = new Node(10f,20f);
        Node n2 = new Node(20f, 10f);
        Node n3 = new Node(20f,10f);
        Node n4 = new Node(35f, 55f);
        List<Node> nodes1 = new ArrayList<>();
        nodes1.add(n1); nodes1.add(n2);
        List<Node> nodes2 = new ArrayList<>();
        nodes2.add(n3); nodes2.add(n4);

        iTraversable road = new RoadSegment(nodes1,"primary", 55, 44, "Christianborgsvej", true );
        iTraversable checkingRoad = new RoadSegment(nodes2,"primary", 55, 44, "Frederikborgsvej", true );
        assertTrue(EG.traversableIsConnected(road, checkingRoad, road.isOneWay(), checkingRoad.isOneWay()));
    }
    @Test
    public void OneWayThatDoesNotIntersect(){
        Node n1 = new Node(10f,20f);
        Node n2 = new Node(20f, 10f);
        Node n3 = new Node(20f,10f);
        Node n4 = new Node(35f, 55f);
        List<Node> nodes1 = new ArrayList<>();
        nodes1.add(n1); nodes1.add(n2);
        List<Node> nodes2 = new ArrayList<>();
        nodes2.add(n4); nodes2.add(n3);

        iTraversable road = new RoadSegment(nodes1,"primary", 55, 44, "Christianborgsvej", true );
        iTraversable checkingRoad = new RoadSegment(nodes2,"primary", 55, 44, "Frederikborgsvej", true );
        assertFalse(EG.traversableIsConnected(road, checkingRoad, road.isOneWay(), checkingRoad.isOneWay()));
    }




}