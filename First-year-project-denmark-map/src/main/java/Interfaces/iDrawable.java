package Interfaces;

import javafx.scene.canvas.GraphicsContext;

public interface iDrawable extends Bounds {
    void draw(GraphicsContext gc, double determinant);

    float layer = 0;

    float[] getXCoords();
    float[] getYCoords();

    default float getLayer() { return layer; }
    void setLayer(float layer);

}
