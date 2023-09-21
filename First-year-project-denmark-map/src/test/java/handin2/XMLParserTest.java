package handin2;

import Exceptions.NoBoundariesFoundInFileException;
import Exceptions.UnsupportedFormatException;
import Interfaces.iDrawable;
import MapObjects.Markers.Address;
import MapObjects.NonTraversable.Area;
import MapObjects.Relation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class XMLParserTest {



    @Test
    void parseDataCorrectly() throws UnsupportedFormatException, XMLStreamException, IOException {
        String filename = "data/osmTestFiles/RelationWayNodeTest.osm";
        List<iDrawable> drawables = handin2.XMLParser.parseXML(filename);
        Area testArea = ((Area)handin2.XMLParser.parseXML(filename).get(0));
        Relation testRelation = ((Relation) handin2.XMLParser.parseXML(filename).get(1));

        assertAll(
                "Group Assertations of returned drawable list from a single parsed relation and it's related elements.",
                () -> assertEquals(2, drawables.size(), "Drawables should include all members. "), //Area+Relation
                () -> assertInstanceOf(Area.class,handin2.XMLParser.parseXML(filename).get(0),"First element of list should be an area." ),
                () -> assertInstanceOf(Relation.class, handin2.XMLParser.parseXML(filename).get(1), "Second element of list should be a Relation"),

                //Tests on parsed Area Data.
                () -> assertEquals("forest", testArea.getType(), "testArea should be of type 'forest'"),
                () -> assertEquals(5 , testArea.getXCoords().length, "testArea should have 5 xCoords."),

                //Tests on parsed Relation Data.
                () -> assertEquals(1, testRelation.getAreas().size(), "There is only one boundary to this Relation"),
                () -> assertEquals(8, testRelation.getAreas().get(0).getXCoords().length, "Boundary should have 8 coords"),
                () -> assertEquals("forest", testRelation.getType(), "Relation type should be forest")

        );
    }


    @Test
    public void parseZipFileCorrecly() throws UnsupportedFormatException, XMLStreamException, IOException{
        String filename = "data/osmTestFiles/RelationWayNodeTest.osm.zip";
        List<iDrawable> drawables = handin2.XMLParser.parseXML(filename);
        Area testArea = ((Area)handin2.XMLParser.parseXML(filename).get(0));
        Relation testRelation = ((Relation) handin2.XMLParser.parseXML(filename).get(1));

        assertAll(
                "Group Assertations of returned drawable list from a single parsed relation and it's related elements. from zip-file. ",
                ()-> assertInstanceOf(XMLStreamReader.class, handin2.XMLParser.load(filename), "Checks if a reader is returned"),
                () -> assertEquals(2, drawables.size(), "Drawables should include all members. "), //Area+Relation
                () -> assertInstanceOf(Area.class,handin2.XMLParser.parseXML(filename).get(0),"First element of list should be an area." ),
                () -> assertInstanceOf(Relation.class, handin2.XMLParser.parseXML(filename).get(1), "Second element of list should be a Relation"),

                //Tests on parsed Area Data.
                () -> assertEquals("forest", testArea.getType(), "testArea should be of type 'forest'"),
                () -> assertEquals(5 , testArea.getXCoords().length, "testArea should have 5 xCoords."),

                //Tests on parsed Relation Data.
                () -> assertEquals(1, testRelation.getAreas().size(), "There is only one boundary to this Relation"),
                () -> assertEquals(8, testRelation.getAreas().get(0).getXCoords().length, "Boundary should have 8 coords"),
                () -> assertEquals("forest", testRelation.getType(), "Relation type should be forest")

        );
    }
    @Test
    public void ShouldThrowUnsupportedFormatException(){
        String filename = "data/osmTestFiles/UnsupportedFormat.pdf";
        Exception thrown = Assertions.assertThrows(UnsupportedFormatException.class,
                () -> {handin2.XMLParser.getBoundaries(filename);
                });
        Assertions.assertEquals(
                "File 'data/osmTestFiles/UnsupportedFormat.pdf' not supported. \n Supported formats are: '.osm' and '.osm.zip'", thrown.getMessage()
        );
    }

    @Test
    void correctlyExtractsBoundsFromOSMfile() throws NoBoundariesFoundInFileException, UnsupportedFormatException, XMLStreamException, IOException {

        float[] expectedBoundaries = { 55.6810000f,  12.5702000f,  12.6235000f, 55.6537000f};
        float[] actualBoundaries = new float[4];
        String filename = "data/osmTestFiles/HasBound.osm";
        actualBoundaries[0] = handin2.XMLParser.getBoundaries(filename).getMaxLat();
        actualBoundaries[1] = handin2.XMLParser.getBoundaries(filename).getMinLon();
        actualBoundaries[2] = handin2.XMLParser.getBoundaries(filename).getMaxLon();
        actualBoundaries[3] = handin2.XMLParser.getBoundaries(filename).getMinLat();
        assertArrayEquals(expectedBoundaries,actualBoundaries);

    }
    @Test
    void throwsNoBoundariesFoundInFileExceptionWhenNoBoundsFound() throws NoBoundariesFoundInFileException, UnsupportedFormatException, XMLStreamException, IOException {
        String filename = "data/osmTestFiles/NoBound.osm";
        Exception thrown = Assertions.assertThrows(NoBoundariesFoundInFileException.class,
                () -> {handin2.XMLParser.getBoundaries(filename);
                });
        Assertions.assertEquals(
                "File didn't include any boundaries", thrown.getMessage()
        );


    }
    @Test
    void shouldThrowXMLStreamExceptionWhenXMLStructureIsCorrupted()throws NoBoundariesFoundInFileException, UnsupportedFormatException, XMLStreamException, IOException{
        String filename = "data/osmTestFiles/XMLStreamExceptionTest.osm";
        Exception thrown = Assertions.assertThrows(XMLStreamException.class,
                () -> {handin2.XMLParser.parseXML(filename);
                });
        Assertions.assertEquals(
                "ParseError at [row,col]:[8,6]\n" +
                        "Message: The content of elements must consist of well-formed character data or markup.", thrown.getMessage()
        );
    }

    @Test
    public void extractAdressCorrectly() throws UnsupportedFormatException, XMLStreamException, IOException {
        String filename = "data/osmTestFiles/AdressTest.osm";
        Address testAddress = (Address) XMLParser.parseXML(filename).get(0);
        assertAll(
                "Group assertations of parsed data from singular address node.",
                () -> assertEquals("17", testAddress.getHouseNumber(), "Checks housenumber"),
                () -> assertEquals("1066", testAddress.getPostalCode(), "Checks postalcode"),
                () -> assertEquals("Admiralgade", testAddress.getStreetName(), "checks streetname")
        );
    }
    @Test
    public void isNotOneWay(){
        Map<String, String> tags = new HashMap<>();
        tags.put("highway","primary");
        assertFalse(XMLParser.determineOneWay(tags));
    }

    @Test
    public void isOneWay(){
        Map<String, String> tags = new HashMap<>();
        tags.put("oneway","Hello There. General Kenobi?");

        Map<String, String> tags1 = new HashMap<>();
        tags1.put("highway","motorway");

        Map<String, String> tags2 = new HashMap<>();
        tags2.put("junction","roundabout");

        assertAll(
                "Combined test of branches in determineOneWay method of XMLParser",
                () -> assertTrue(XMLParser.determineOneWay(tags), "Should be true for 'oneway'"),
                () -> assertTrue(XMLParser.determineOneWay(tags1), "Should be true for 'motorway'"),
                () -> assertTrue(XMLParser.determineOneWay(tags2), "Should be true for 'roundabout'")

        );
    }


}