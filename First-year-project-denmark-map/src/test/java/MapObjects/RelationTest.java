package MapObjects;

import Exceptions.UnsupportedFormatException;
import MapObjects.Traversable.Road;
import handin2.XMLParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RelationTest {
    Road testroad1;
    Road testroad2;
    Relation testRel;
    Way way1;
    Way way2;
    Way reverse1;
    Way reverse2;
    @BeforeEach
    public void setup() throws UnsupportedFormatException, XMLStreamException, IOException {

        //testRel = (Relation) XMLParser.parseXML("data/osmTestFiles/RelationWayNode.osm").get(2);
        Node node1 = new Node(0.0f, 0.0f);
        Node node2 = new Node(0.0f, 1.0f);
        Node node3 = new Node(1.0f, 0.0f);
        Node node4 = new Node(1.0f, 1.0f);

        List<Node> wayList1 = new ArrayList<>();
        List<Node> wayList2 = new ArrayList<>();
        List<Node> reverseList1 = new ArrayList<>();
        List<Node> reverseList2 = new ArrayList<>();

        wayList1.add(node1); wayList1.add(node2);wayList1.add(node4);
        wayList2.add(node4); wayList2.add(node3);wayList2.add(node1);
        reverseList1.add(node4); reverseList1.add(node2);reverseList1.add(node1);
        reverseList2.add(node1); reverseList2.add(node3); reverseList2.add(node4);

        way1 = new Road(wayList1, "coastline", 0, "NoName", false);
        way2 = new Road(wayList2, "coastline", 0, "NoName", false);
        reverse1 = new Road(reverseList1, "coastline", 0, "NoName", false);
        reverse2 = new Road(reverseList2, "coastline", 0, "NoName", false);

        List<Way> roads = new ArrayList<>();
        roads.add(way1); roads.add(way2);
        testRel = new Relation(roads, "island");

    }

}