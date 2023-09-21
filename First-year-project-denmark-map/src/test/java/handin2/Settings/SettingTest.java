package handin2.Settings;

import Exceptions.UnsupportedFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SettingTest {
    Setting s = new Setting();
    @BeforeEach
    public void setup() throws UnsupportedFormatException {
        s = new Setting();
        s.setFileSource("data/bornholm.osm.zip");
    }
    @Test
    public void fileSource(){
        assertEquals("data/bornholm.osm.zip", s.getFileSource(), "Check default path to filesource");
    }
    @Test
    public void changeFileSource() throws UnsupportedFormatException {
        s.setFileSource("data/newFile.osm");
        assertEquals("data/newFile.osm", s.getFileSource(), "Change path to filesource ");
    }
}