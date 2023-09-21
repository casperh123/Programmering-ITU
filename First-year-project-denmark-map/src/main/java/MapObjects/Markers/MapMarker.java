package MapObjects.Markers;

import Interfaces.iDrawable;
import MapObjects.Node;
import handin2.Settings.DrawSettings;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.controlsfx.glyphfont.Glyph;


public class MapMarker extends Node implements iDrawable {

    static Glyph markerGlyph = Glyph.create("FontAwesome|MAP_MARKER");
    String roadName;

    public MapMarker(float xPos, float yPos, String roadName) {
        super(xPos, -yPos);
        this.roadName = roadName;
    }

    @Override
    public void draw(GraphicsContext gc, double determinant) {
        Font font = new Font("FontAwesome", 30 * (1/Math.sqrt(determinant)));
        gc.setFont(font);
        gc.setTextAlign(TextAlignment.CENTER);
        gc.setTextBaseline(VPos.BASELINE);
        gc.setFill(Color.RED);
        gc.fillText(markerGlyph.getText(), x, y);

        font = new Font("FontAwesome", 15 * (1/Math.sqrt(determinant)));
        gc.setFont(font);
        gc.setTextBaseline(VPos.TOP);
        if (DrawSettings.darkTheme) {
            gc.setFill(Color.RED);
        } else {
            gc.setFill(Color.BLACK);
        }
        gc.fillText(roadName, x, y);
    }
    @Override
    public float[] getXCoords() {
        return new float[] {getX()};
    }
    @Override
    public float[] getYCoords() {
        return new float[] {getY()};
    }
    @Override
    public void setLayer(float layer) {

    }
    @Override
    public float getMinX() { return getX(); }
    @Override
    public float getMinY() { return getY(); }
    @Override
    public float getMaxX() { return getX(); }
    @Override
    public float getMaxY() { return getY(); }
    @Override
    public float getPriorityZ() { return 0; }
    @Override
    public void setBounds(float minX, float minY, float maxX, float maxY, float priorityZ) {

    }
}
