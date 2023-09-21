package PathFinding;

import Exceptions.UnsupportedFormatException;
import Interfaces.iDrawable;
import Interfaces.iTraversable;
import handin2.XMLParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectedEdgeTest {
    iTraversable source;
    iTraversable destination;
    List<iDrawable> tempList;
    List<iTraversable> roadList = new ArrayList<>();

    @BeforeEach
    public void setup() throws UnsupportedFormatException, XMLStreamException, IOException {
        tempList = XMLParser.parseXML("data/osmTestFiles/DIrectedEdgeTest.osm");

       for(int i = 0; i < tempList.size() ; i++){
           if(tempList.get(i) instanceof iTraversable){
                roadList.add((iTraversable) tempList.get(i));
           }
       }
        /*
        roadList ways:
        Index 0: residential
              1: tertiary
              2: primary
              3: primary
         */

    }

    @Test
    public void allVehiclesShouldBeAbleToTraverseResidentialTertiary(){
        DirectedEdge DE = new DirectedEdge(roadList.get(0), roadList.get(1));
        assertAll(
                "Testing constructor with drivable, walkable and cyclable roads from kbh.osm, \n according to our pathfinding settings",
                () -> assertTrue(DE.isCyclable(), "Should be cyclable"),
                () -> assertTrue(DE.isDrivable(), "Should be driable"),
                () -> assertTrue(DE.isWalkable(), "Should be walkable")
        );
    }
    @Test
    public void primaryShouldNotBeWalkable(){
        DirectedEdge DE = new DirectedEdge(roadList.get(2), roadList.get(3)); //Primary roads
        assertAll(
                "Testing for vehicle restrictions on primary road",
                () -> assertTrue(DE.isDrivable(), "Should be drivable"),
                () -> assertTrue(DE.isCyclable(), "should be cyclable"),
                () -> assertFalse(DE.isWalkable(), "Should not be walkable")
        );
    }
    @Test
    public void footPathandCyclewayShouldNotBeDrivable(){
        DirectedEdge DE = new DirectedEdge(roadList.get(4), roadList.get(5)); //Cyclepath
        DirectedEdge DE1 = new DirectedEdge(roadList.get(6), roadList.get(7)); //footway

        assertAll(
                "Testing for vehicle restrictions on cyclepath and footway",
                () -> assertFalse(DE.isDrivable()),
                () -> assertTrue(DE.isCyclable()),
                () -> assertFalse(DE.isWalkable()),
                () -> assertFalse(DE1.isDrivable()),
                () -> assertFalse(DE1.isCyclable()),
                () -> assertTrue(DE1.isWalkable())
        );
    }

   @Test
    void getWeight() {
        /*
       It is not relevant to test if for example a car can drive on a cyclepath, as there is a clause in 'DijkstraGraph',
       that checks the status bools, of wether the given edge is indeed drivable upon relaxing the graph.
       So the weight of the edge won't be transformed with respect to the other modes in a meaningful manner.
        */

       DirectedEdge DE = new DirectedEdge(roadList.get(4), roadList.get(5)); //Cyclepath
       DirectedEdge DE1 = new DirectedEdge(roadList.get(6), roadList.get(7)); //footway
       DirectedEdge DE2 = new DirectedEdge(roadList.get(2), roadList.get(3)); //Primary roads
       DirectedEdge DE3 = new DirectedEdge(roadList.get(0), roadList.get(1)); //Residential and tertiary

       double expectedWalkWeight= DE1.getSource().getLength();

       double expectedCycleWeight= DE.getSource().getLength();

       double driveLength = DE2.getSource().getLength();
       double driveSpeed = DE2.getSource().getSpeedLimit();
       double expectedDriveWeight = driveLength/driveSpeed;

       //Modes: 1 = drive, 2 = cycle, 3 = walk
       assertAll(
               () -> assertEquals(expectedWalkWeight, DE1.getWeight(3), "walk weight"),
               () -> assertEquals(expectedCycleWeight, DE.getWeight(2), "Cycle weight"),
               () -> assertEquals(expectedDriveWeight, DE2.getWeight(1), "Drive weight"),
               () -> assertNotEquals(DE3.getWeight(1), DE2.getWeight(1), "Weight shouldn't be the same of two different types of drivable roads")
       );

    }
}