package MapObjects.Traversable;

import MapObjects.Node;
import handin2.Settings.Setting;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoadTest {
    Road testroad1;
    Road testroad2;

    @BeforeEach
    public void setup(){

        Node node1 = new Node(0.0f, 0.0f);
        Node node2 = new Node(0.0f, 10.0f);
        Node node3 = new Node(1.0f, 0.0f);
        Node node4 = new Node(1.0f, 1.0f);
        Node node5 = new Node(3.0f, 1.0f);

        List<Node> road1 = new ArrayList<>();
        List<Node> road2 = new ArrayList<>();

        road1.add(node1); road1.add(node2);
        road2.add(node3); road2.add(node4); road2.add(node5);

       testroad1 = new Road(road1,"motorway", 120, "", false);
       testroad2 = new Road(road2,"primary", 80, "", false);

    }



    @Test
    public void LengthsShouldNotbeEqual() {
        assertNotEquals(testroad1.getLength(),testroad2.getLength());
    }

    @Test
    public void ExactLength() {
        assertEquals(1111949.2664455872d, testroad1.getLength(), 0.05d);
    }


    @Test
    public void getYCoords() {
        float[] yCoordsExample = {-0.0f, -10.0f};
        assertArrayEquals(yCoordsExample,testroad1.getYCoords());
    }

    @Test
    public void getXCoords() {
        float[] xCoordsExample = {0.0f*0.56f, 0.0f*0.56f};
        assertArrayEquals(xCoordsExample,testroad1.getXCoords());
    }

    @Test
    public void setLayer() {
        assertNotEquals(testroad1.getLayer(), testroad2.getLayer());
    }

}