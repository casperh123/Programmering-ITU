package MapObjects.NonTraversable;

import Interfaces.iNonTraversable;
import MapObjects.Node;
import MapObjects.Way;
import handin2.Settings.DrawSettings;
import handin2.Settings.Setting;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class Area extends Way implements iNonTraversable {


    public Area(List<Node> way, String type) {
        super(way, type);
        setBounds(Setting.innerZoomBound - DrawSettings.getAreaKDLayer(type));
        setLayer(DrawSettings.getAreaLayer(type));
        /*
            This constructor is used by the XMLParser inorder to make buildings as well
             as other areas from ways. The super class is "Way", which is in charge of assigning
             the layer appropriate to the given type, as well as transforming the coordinates
             from raw coordinates to coordinates that fits our canvas.

         */
    }

    public Area(Way way, String type) {
        super(type);
        this.type = type;
        this.xCoords = way.getXCoords();
        this.yCoords = way.getYCoords();

        setBounds(Setting.innerZoomBound - DrawSettings.getAreaKDLayer(type));
        setLayer(DrawSettings.getAreaLayer(type));
        /*
            This constructor is used by the Relation class, inorder to make the areas that makeup
            the relations.
         */
    }


    public Area(String type, List<Way> ways) {
        super(type);
        int length = 0;
        for (Way way : ways) {
            length += way.getXCoords().length;
        }

        this.xCoords = new float[length];
        this.yCoords = new float[length];

        int size = 0;
        for (int i = 0; i < ways.size(); i++) {
            for (int j = 0; j < ways.get(i).getXCoords().length; j++) {
                this.xCoords[j + size] = ways.get(i).getXCoords()[j];
                this.yCoords[j + size] = ways.get(i).getYCoords()[j];
            }
            size += ways.get(i).getXCoords().length;
        }
        setBounds(Setting.innerZoomBound - DrawSettings.getAreaKDLayer(type));
        setLayer(DrawSettings.getAreaLayer(type));
        /*
        This constructor is used by the method getOrderedBoundaries in the Relation Class.
         */
    }

    @Override
    public void draw(GraphicsContext gc, double determinant) {

        gc.beginPath();
        int advance = getLOD();

        if (xCoords.length >= 5) {

            gc.lineTo(xCoords[0], yCoords[0]);
            gc.lineTo(xCoords[1], yCoords[1]);

            for (int i = 2; i < xCoords.length - 1; i += advance) {
                gc.lineTo(xCoords[i], yCoords[i]);
            }

            gc.lineTo(xCoords[xCoords.length - 2], yCoords[xCoords.length - 2]);
            gc.lineTo(xCoords[xCoords.length - 1], yCoords[xCoords.length - 1]);

        } else {
            for (int i = 0; i < xCoords.length; i++) {
                gc.lineTo(xCoords[i], yCoords[i]);
            }
        }

        //Closes the path to create a polygon which can be filled with a color.
        gc.closePath();


        Color color = DrawSettings.darkTheme ? DrawSettings.getAreaColor(type).grayscale().darker().darker().darker() : DrawSettings.getAreaColor(type);

        if(!color.equals(gc.getFill())) {
            gc.setFill(color);
        }
        gc.fill();
    }

}
