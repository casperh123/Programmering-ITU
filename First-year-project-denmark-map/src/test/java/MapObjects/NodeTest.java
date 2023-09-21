package MapObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {

    @Test
    public void latCoords() {
        float lon = 1.0f;
        float lat = 2.0f;
        Node node = new Node(lon, lat);

        assertEquals(-lat, node.getY());
    }
    @Test
    public void lonCoords() {
        float lon = 1.0f;
        float lat = 2.0f;
        Node node = new Node(lon, lon);

        assertTrue(lon==node.getX());
    }

    @Test
    public void nodesShouldBeEqual(){
        Node node1 = new Node(0.0f, 0.0f);
        Node node2 = new Node(0.0f, 0.0f);
        assertTrue(node2.equals(node1));
    }

    @Test
    public void nodesShouldNotBeEqual(){
        Node node1 = new Node(0.0f, 0.0f);
        Node node2 = new Node(0.0f, 0.2f);
        assertFalse(node2.equals(node1));
    }

    @Test
    public void shouldBeFalseWhenObjectIsNotNode(){
        Way way = new Way("motorway");
        Node node1 = new Node(0.0f, 0.0f);
        assertFalse(node1.equals(way));
    }
    @Test
    public void theSameRoadShouldAlsoBeEqualItself(){
        Node node1 = new Node(0.0f, 0.0f);
        assertTrue(node1.equals(node1));
    }
}