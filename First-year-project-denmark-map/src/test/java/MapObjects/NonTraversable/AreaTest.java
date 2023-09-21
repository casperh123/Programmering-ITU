package MapObjects.NonTraversable;

import MapObjects.Node;
import MapObjects.Traversable.Road;
import MapObjects.Way;
import handin2.Settings.DrawSettings;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AreaTest {
    Area square;
    @BeforeEach
    public void setup(){
        //Simple square area
        Node one = new Node(0.0f, 0.0f);
        Node two = new Node(0.0f, 1.0f);
        Node three = new Node(1.0f, 0.0f);
        Node four = new Node(1.0f, 1.0f);

        List<Node> oneList = new ArrayList<>();
        oneList.add(one); oneList.add(two); oneList.add(three); oneList.add(four);

        square = new Area(oneList, "water");
    }

    @Test
    public void checkingIfItGetsTheRightColorWhenDrawing() {
        //Checking if the fillcolor is right according to type
        assertEquals(DrawSettings.getAreaColor(square.getType()), Color.web("#b4d0d0"));
    }


    @Test
    public void theLayerCorrectForWateris3561f() {

        assertEquals(square.getLayer(),3.561f, 0.1f);
    }

}