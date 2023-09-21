package MapObjects.Markers;

import MapObjects.Node;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {
    Address addr1;
    Address addr2;
    @BeforeEach
    public void setup(){
        addr1 = new Address(10.0f, 10.0f, "Skovgården", "32", "4100", "Ringsted");
        addr2 = new Address("Skovgården", "32", "4100", "Ringsted");

    }

    @Test
    public void ConstructorTest(){
       assertAll(
               "Combined test of the constructor of 'Adress'",
               () -> assertEquals("Skovgården", addr1.getStreetName()),
               () -> assertEquals("Skovgården", addr2.getStreetName()),
               () -> assertEquals("32", addr1.getHouseNumber()),
               () -> assertEquals("32", addr2.getHouseNumber()),
               () -> assertEquals("4100", addr1.getPostalCode()),
               () -> assertEquals("4100", addr2.getPostalCode()),
               () -> assertEquals("Ringsted", addr1.getCity()),
               () -> assertEquals("Ringsted", addr2.getCity())
       );
    }
    @Test
    public void addressesShouldBeEquals(){
        assertTrue(addr1.equals(addr2));
    }
    @Test
    public void addressesShouldNotBeEqual(){
        Address addr3 =  new Address("Skovgården", "3", "4100", "Ringsted");
        assertFalse(addr1.equals(addr3));
    }
    @Test
    public void CoverageTestEqual(){
        assertTrue(addr1.equals(addr1));
    }
    @Test
    public void NotAnAddress(){
        Node node = new Node(0f, 0f);
        assertFalse(addr1.equals(node));
    }
    @Test
    public void toStringtest(){
        assertEquals("Skovgården 32, 4100 Ringsted", addr1.toString());
    }
    @Test
    public void coordTest(){
        assertAll(
                "Combined tests of coordinate-related methods",
                () -> assertEquals(0f,addr2.getMaxX(), "Max X"),
                () -> assertEquals(-0f,addr2.getMaxY(), "MaxY"),
                () -> assertEquals(5.6f,addr1.getMinX(),"Min X"),
                () -> assertEquals(-10f,addr1.getMinY(), "Min Y"),
                () -> assertEquals(0f,addr2.getPriorityZ(), "Priority Z"),
                () -> assertEquals(0f,addr2.getXCoords()[0], "XCoords"),
                () -> assertEquals(-10.0f,addr1.getYCoords()[0], "YCoords"),
                () -> assertEquals(0f,addr2.getLayer(), "Layer")

        );
    }
    @Test
    public void hashCodeOfAddress(){
        assertEquals(268238980, addr1.hashCode());
    }
    @Test
    public void hashCodeOfAddress1(){
        Address addr3 =  new Address("Skovgården", "32", "4000", "Roskilde");
        assertEquals(1444827020, addr3.hashCode());
    }



}