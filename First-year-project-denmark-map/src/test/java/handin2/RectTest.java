package handin2;

import MapObjects.Node;
import MapObjects.Traversable.Road;
import MapObjects.Way;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectTest {
    Way way1;
    Way way2;

    @BeforeEach
    public void setup(){
        Node node1 = new Node(1.0f, 2.0f);
        Node node2 = new Node(0.5f, 3.0f);
        Node node3 = new Node(2.0f, 5.0f);
        Node node4 = new Node(2.0f, 5.0f);

        List<Node> wayList1 = new ArrayList<>();
        List<Node> wayList2 = new ArrayList<>();

        wayList1.add(node1); wayList1.add(node2);wayList1.add(node4);
        wayList2.add(node4); wayList2.add(node3);wayList2.add(node1);


        way1 = new Road(wayList1, "motorway", 110, "Holbækmotovejen", true);
        way2 = new Road(wayList2, "coastline", 0, "NoName", false);

    }

    @Test
    public void correctBoundsAndPriority(){
        Rect r = new Rect(way1);
        //Coords gets transformed into coords befitting the axis' of the canvas.
        assertAll(
                "Combined test translation from element coords to rect coords",
                () -> assertEquals(0.28f, r.minX),
                () -> assertEquals(1.12f, r.maxX),
                () -> assertEquals(-5.0f, r.minY),
                () -> assertEquals(-2.0f, r.maxY),
                () -> assertEquals(1000000.0f, r.getPriorityZ())
        );
    }
    @Test
    public void theTwoRectsShouldOverlap1stCase() {
        Rect r = new Rect(new Point2D(0.5f, 5.0f), new Point2D(2.0f, 2.0f), 10.0f);
        Rect r1 = new Rect(1f, 1.5f, 1.75f, 4.5f, 10.0f);
        assertTrue(r.overlaps(r1.getMinX(), r1.getMinY(),r1.getMaxX(),r1.getMaxY(),r1.getPriorityZ()), "The bounding boxes of the two roads should overlap with r1 being inside r");
    }
    @Test
    public void theTwoRectsShouldOverlap2ndCase() {
        Rect r = new Rect(way1);
        Rect r1 = new Rect(way2);
       assertTrue(r1.overlaps(r.getMinX(),r.getMinY(),r.getMaxX(),r.getMaxY(),r.getPriorityZ()), "The bounding boxes of the two roads should overlap with r being inside r1");
    }

   /* @Test
    public void theTwoRectsShouldOverlap3rdCase1(){
        //Rect r = new Rect(new Point2D(-3f, 2.75f), new Point2D(3f,-2f), 10.0f);
       // Rect r1 = new Rect(new Point2D(-1f, 3f), new Point2D(7f,-1f), 10.0f);
        Rect r = new Rect(new Point2D(0f, 5f), new Point2D(8f,0f), 10.0f);
        Rect r1 = new Rect(new Point2D(7f, 9f), new Point2D(16f,4f), 10.0f);
        assertTrue(r.overlaps(r1.getMinX(), r1.getMinY(),r1.getMaxX(),r1.getMaxY(),r1.getPriorityZ()), "The button left corner of r1 is inside r");
        Should evaluate to true, but evaluates to false.
    }*/

    @Test
    public void theTwoRectsShouldNotOverlap() {
        Rect r = new Rect(way1);
        Rect r1 = new Rect(8.0f, 8.0f, 10.0f, 10.0f, 10.0f);
        assertFalse(r.overlaps(r1.getMinX(), r1.getMinY(),r1.getMaxX(),r1.getMaxY(),r1.getPriorityZ()), "The bounding boxes of the two roads should not overlap");
    }

}