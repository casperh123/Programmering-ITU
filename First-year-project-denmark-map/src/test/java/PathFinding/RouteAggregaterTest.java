package PathFinding;

import Interfaces.iTraversable;
import MapObjects.Node;
import MapObjects.Traversable.RoadSegment;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RouteAggregaterTest {
    iTraversable source;
    iTraversable destination;

    @Test
    public void getDirectionsForangleInBetween45andMinus45() {
        Node n1 = new Node(0f,-7f);
        Node n2 = new Node(0f, 0f);
        Node n3 = new Node(0f,0f);
        Node n4 = new Node(2f, 4f);
        List<Node> nodes1 = new ArrayList<>();
        nodes1.add(n1); nodes1.add(n2);
        List<Node> nodes2 = new ArrayList<>();
        nodes2.add(n3); nodes2.add(n4);

        source = new RoadSegment(nodes1,"primary", 55, 44, "Christianborgsvej", true );
        destination = new RoadSegment(nodes2,"primary", 55, 42, "Frederikborgsvej", true );
        String directions = new RouteAggregater().direction(source, destination);
        assertEquals("continue down", directions);
    }
    @Test
    public void getDirectionsForAngleBigger45(){
        Node n2 = new Node(0f,-7f);
        Node n1 = new Node(0f, 0f);
        Node n3 = new Node(0f,0f);
        Node n4 = new Node(4f, 2f);
        List<Node> nodes1 = new ArrayList<>();
        nodes1.add(n1); nodes1.add(n2);
        List<Node> nodes2 = new ArrayList<>();
        nodes2.add(n3); nodes2.add(n4);

        source = new RoadSegment(nodes1,"primary", 55, 44, "Christianborgsvej", true );
        destination = new RoadSegment(nodes2,"primary", 55, 42, "Frederikborgsvej", true );
        String directions = new RouteAggregater().direction(source, destination);
        assertEquals("turn right", directions);
    }
    @Test
    public void getDirectionsForAngleSmallerThanMinus45(){
        Node n1 = new Node(0f,-7f);
        Node n2 = new Node(0f, 0f);
        Node n4 = new Node(0f,0f);
        Node n3 = new Node(-4f, 2f);
        List<Node> nodes1 = new ArrayList<>();
        nodes1.add(n1); nodes1.add(n2);
        List<Node> nodes2 = new ArrayList<>();
        nodes2.add(n3); nodes2.add(n4);

        source = new RoadSegment(nodes1,"primary", 55, 44, "Christianborgsvej", true );
        destination = new RoadSegment(nodes2,"primary", 55, 42, "Frederikborgsvej", true );
        String directions = new RouteAggregater().direction(source, destination);
        assertEquals("turn left", directions);
    }
    /*@Test
    public void comPareLastCoordWithLastCoord(){
        Node n2 = new Node(0f,-7f);
        Node n1 = new Node(0f, 0f);
        Node n4 = new Node(0f,0f);
        Node n3 = new Node(-4f, 2f);
        List<Node> nodes1 = new ArrayList<>();
        nodes1.add(n1); nodes1.add(n2);
        List<Node> nodes2 = new ArrayList<>();
        nodes2.add(n3); nodes2.add(n4);

        source = new RoadSegment(nodes2,"primary", 55, 44, "Christianborgsvej", true );
        destination = new RoadSegment(nodes1,"primary", 55, 42, "Frederikborgsvej", true );
        String directions = new RouteAggregater().direction(source, destination);
        assertEquals("Turn right", directions);
    }*/

    @Test
    public void OneStraightOneRightAndOneLeft(){
        Node n1 = new Node(1f,0f);
        Node n2 = new Node(1f, 3f);

        Node n3 = new Node(1f,3f);
        Node n4 = new Node(2f, 5f);

        Node n5 = new Node(2f,5f);
        Node n6 = new Node(4f, 3f);

        Node n7 = new Node(4f,3f);
        Node n8 = new Node(5f, 6f);

        List<Node> nodes1 = new ArrayList<>();
        nodes1.add(n1); nodes1.add(n2);
        List<Node> nodes2 = new ArrayList<>();
        nodes2.add(n3); nodes2.add(n4);
        List<Node> nodes3 = new ArrayList<>();
        nodes3.add(n5); nodes3.add(n6);
        List<Node> nodes4 = new ArrayList<>();
        nodes4.add(n7); nodes4.add(n8);


        RoadSegment road1 = new RoadSegment(nodes1,"primary", 55, 44, "Mikkelsvej", true );
        RoadSegment road2 = new RoadSegment(nodes2,"primary", 55, 43, "Magnusvej", true );
        RoadSegment road3 = new RoadSegment(nodes3,"primary", 55, 42, "Madsvej", true );
        RoadSegment road4 = new RoadSegment(nodes4,"primary", 55, 41, "Caspersvej", true );

        List<iTraversable> roadsegmentList = new ArrayList<>();
        roadsegmentList.add(road1);roadsegmentList.add(road2);
        roadsegmentList.add(road3);roadsegmentList.add(road4);

        List<TurnDirection> directions;

        directions = new RouteAggregater().getDirections(roadsegmentList);
        List<String> directionString = new ArrayList<>();


        List<TurnDirection> finalDirections = directions;
        assertAll(
                "Test on output of directions",
                () -> assertEquals(3, finalDirections.size(), "There should be 3 directions to give"),
                () -> assertEquals("For "+ (int)road1.getLength() + " meters, continue down " + road2.getName(), finalDirections.get(0).toString(), "First direction should be 'Continue down'"),
                () -> assertEquals("In "+ (int)road2.getLength() + " meters, turn right to " + road3.getName(), finalDirections.get(1).toString(), "First direction should be 'Turn right'"),
                () -> assertEquals("In "+ (int)road3.getLength()+ " meters, turn left to " + road4.getName(), finalDirections.get(2).toString(), "First direction should be 'Turn left'")
        );

    }

}