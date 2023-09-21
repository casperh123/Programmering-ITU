package handin2;

import Exceptions.UnsupportedFormatException;
import Interfaces.iDrawable;
import Interfaces.iTraversable;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KDTreeTest {
    List<iDrawable> list = XMLParser.parseXML("data/osmTestFiles/KDTreeTestXML.osm");
    List<iTraversable> traversables = new ArrayList<>();
    List<iDrawable> drawables = new ArrayList<>();
    private KDTree<iTraversable> kdTree;


 KDTreeTest() throws UnsupportedFormatException, XMLStreamException, IOException {
 }
 @BeforeEach
 public void setup(){
    for(iDrawable item : list){
       if(item instanceof iTraversable){
          traversables.add((iTraversable)item);
       } else{
          drawables.add(item);
       }
    }
    kdTree = new KDTree<>(traversables, 3);

 }



 @Test
    public void TestThatTheConstructorActsAsExpected(){
        assertAll(
              "This is a combined test of the constructors' functionality, tested on the dataset of Bornholm.osm",
                () -> assertEquals(1, kdTree.maxDepth, "")

        );
    }

}