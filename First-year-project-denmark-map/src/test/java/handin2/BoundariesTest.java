package handin2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoundariesTest {

    @Test
    public void gettersTest(){
        Boundaries b = new Boundaries(400f,200f, 100f, 150f);
        assertAll(
                "Consistent getters..",
                () -> assertEquals(400f, b.getMaxLon()),
                () -> assertEquals(200f, b.getMaxLat()),
                () -> assertEquals(100f, b.getMinLon()),
                () -> assertEquals(150f, b.getMinLat())
        );
    }
}