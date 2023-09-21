package PathFinding;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.testng.AssertJUnit.*;

class ResizingArrayTest {
    ResizingArray array1;
    @BeforeEach
    public void setup(){
        array1 = new ResizingArray();
    }
    @AfterEach
    public void teardown(){
        array1 = new ResizingArray();
    }

    @Test
    public void valuesAtNewInstance(){
        assertTrue(array1.isEmpty());
        assertEquals(0, array1.size());
        assertEquals(8, array1.getLength());

    }
    @Test
    public void adding9ElementsShouldTriggerResize(){
        array1.add(0);array1.add(1); array1.add(2);array1.add(3);array1.add(4);
        array1.add(5);array1.add(6); array1.add(7);array1.add(8);
        assertFalse(array1.isEmpty());
        assertEquals(9, array1.size());
        assertEquals(16, array1.getLength());
        assertEquals(6, array1.indexOf(6));
        assertEquals(-1, array1.indexOf(10));

        //5x remove last to force resizing
        array1.removeLast();array1.removeLast();array1.removeLast();array1.removeLast();array1.removeLast();
        assertEquals(8, array1.getLength());
        assertEquals(4, array1.size());
    }
    @Test
    public void exchange2Elements(){
        array1.add(0); array1.add(1);
        array1.exchange(0, 1);

        assertEquals(1, array1.get(0));
    }

    @Test
    public void containsNumber(){
        array1.add(1);
        assertTrue(array1.contains(1));
    }
    @Test
    public void indexOfNonExistantElement(){
        array1.add(1);
        assertEquals(-1, array1.indexOf(10));
    }
    @Test
    public void checkThatTheGeneratedListContainsAllElements(){
        array1.add(0);array1.add(1); array1.add(2);array1.add(3);array1.add(4);
        array1.add(5);array1.add(6); array1.add(7);array1.add(8);
        List<Integer> ints = new ArrayList<>();
        ints.add(0);ints.add(1); ints.add(2);ints.add(3);ints.add(4);
        ints.add(5);ints.add(6); ints.add(7);ints.add(8);

        assertTrue(array1.toList().containsAll(ints));
    }
}