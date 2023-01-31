package bfst22.roman;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RomanTest {
    @Test
    void fromRomanIX() {
        assertEquals(Roman.fromRoman("IX"), 9, "fromRoman(\"IX\") should return 9");
    }
}
