package PathFinding;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnDirectionTest {

    @Test
    public void toStringTest(){
        TurnDirection td = new TurnDirection("Skt. Hansgade", 200, "turn right");
        assertEquals("In 200 meters, turn right to Skt. Hansgade", td.toString());
    }
}