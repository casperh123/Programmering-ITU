package handin2;

import Interfaces.Bounds;
import Interfaces.iTraversable;
import org.junit.jupiter.api.Test;

import javax.security.auth.callback.TextInputCallback;
import java.lang.reflect.Type;

import static org.junit.jupiter.api.Assertions.*;

class KDNodeTest {
    private KDNode<iTraversable> node = new KDNode<>(0.0f, -10.0f, 10.0f, 20.0f, 0);

    @Test
    public void TestofGetters(){
        node.setDepth((short) 2);

        assertAll(
                "This a combined test of the getters of a KDNode",
                () -> assertEquals(0.0f, node.getMinX()),
                () -> assertEquals(-10.0f, node.getMinY()),
                () -> assertEquals(10.0f, node.getMaxX()),
                () -> assertEquals(20.0f, node.getMaxY()),
                () -> assertEquals(0f, node.getPriorityZ()),
                () -> assertEquals((short) 2, node.getDepth())
        );
    }


}