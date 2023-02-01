package bfst22.roman;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RomanTest {
    @Test
    void fromRomanIX() {
        assertEquals(9, Roman.fromRoman("IX"), "fromRoman(\"IX\") should return 9");
    }

    @Test
    void fromRomanAllCharacters() {
        assertEquals(1666, Roman.fromRoman("IVXLCDM"));
    }

    @Test
    void fromIItoSixty() {
        assertEquals(2, Roman.fromRoman("II"));
    }

    @Test void fromInvalidTo() {
        assertEquals(0, Roman.fromRoman("aaaaazzz"));
    }
}
