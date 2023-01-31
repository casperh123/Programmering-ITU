package bfst22.roman;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RomanTest {
    @Test
    void toRoman4() {
        assertEquals(Roman.toRoman(4), "IV", "toRoman(4) should return \"IV\"");
    }
}
