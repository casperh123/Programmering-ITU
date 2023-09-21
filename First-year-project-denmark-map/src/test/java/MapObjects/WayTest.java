package MapObjects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WayTest {
    Way way;
    @BeforeEach
    public void setup(){
        Node node1 = new Node(0.0f, 0.0f);
        Node node2 = new Node(0.0f, 10.0f);
        Node node3 = new Node(0.0f, 30.0f);
        List<Node> road = new ArrayList<>();
        road.add(node1); road.add(node2); road.add(node3);

        way = new Way(road);
    }

    @Test
    public void checkIfBoundsAreCorrect(){

        //Corrected coords: minX 0.0, maxX 0.0, minY -30.0, maxY -0.0

        float[] expectedBounds = {0.0f, 0.0f, -30.0f, -0.0f};

        float[] bounds = new float[4];

        bounds[0] = way.getMinX();
        bounds[1] = way.getMaxX();
        bounds[2] = way.getMinY();
        bounds[3] = way.getMaxY();
        assertArrayEquals(expectedBounds, bounds);
    }

    @Test
    public void checkLayer(){
        assertEquals(150f,way.getPriorityZ(), 0.01f);
    }
    @Test
    public void wayIsNotClosed(){
        assertFalse(way.isClosedWay());
    }
    @Test
    public void shouldBeClosed(){
        List<Node> closedRoad = new ArrayList<>();
        closedRoad.add(new Node(0.0f, 0.0f));
        closedRoad.add(new Node(1.0f, 0.0f));
        closedRoad.add(new Node(1.0f, 1.0f));
        closedRoad.add(new Node(0.0f, 1.0f));
        closedRoad.add(new Node(0.0f, 0.0f));
        assertTrue(new Way(closedRoad).isClosedWay());
    }
    @Test
    public void shouldNotbeclosed(){
        List<Node> falseClosedRoad = new ArrayList<>();
        falseClosedRoad.add(new Node(0.0f, 0.0f));
        falseClosedRoad.add(new Node(0.0f, 0.0f));
        assertFalse(new Way(falseClosedRoad).isClosedWay());
    }
    @Test
    public void ListIsClosed(){
        List<Node> list = new ArrayList<>();
        list.add(new Node(0.03f, 10.2f));
        list.add(new Node(0.2f, 11f));
        list.add(new Node(0.32f, 14.2f));
        list.add(new Node(0.43f, 11f));
        list.add(new Node(0.03f, 10.2f));
        assertTrue(way.isClosedWay(list));

    }
    @Test
    public void listShouldNotBeClosed(){
        List<Node> falseClosedRoad = new ArrayList<>();
        falseClosedRoad.add(new Node(0.0f, 0.0f));
        falseClosedRoad.add(new Node(0.0f, 0.0f));
        assertFalse(way.isClosedWay(falseClosedRoad));
    }
    @Test
    public void reversedWayShouldreverseX(){
        Way reversedWay = way.reverseWay();
        float[] expectedX = {0.0f, 0.0f, 0.0f};
        assertArrayEquals(expectedX, reversedWay.xCoords, 0.01f);
    }
    @Test
    public void reversedWayShouldreverseY(){
        Way reversedWay = way.reverseWay();
        float[] expectedY = {-30.0f, -10.0f, -0.0f};
        assertArrayEquals(expectedY, reversedWay.yCoords, 0.01f);
    }




}