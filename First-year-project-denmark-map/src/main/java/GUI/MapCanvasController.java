package GUI;

public class MapCanvasController {

    private MapCanvas mapCanvas;

    private float lastX;
    private float lastY;

    public MapCanvasController(MapCanvas mapCanvas) {

        this.mapCanvas = mapCanvas;


        mapCanvas.setOnMousePressed(e -> {
            lastX = (float) e.getX();
            lastY = (float) e.getY();
        });

        mapCanvas.setOnMouseClicked(e -> {
            if (mapCanvas.mapMarkerMode) {
                mapCanvas.placeMarker((float) e.getX(), (float) e.getY());
            }
        });


        mapCanvas.setOnMouseDragged(e -> {

            mapCanvas.pan(e.getX() - lastX, e.getY() - lastY);



            lastX = (float) e.getX();
            lastY = (float) e.getY();
        });

        mapCanvas.setOnScroll(e -> {
            double amount = e.getDeltaY();
            mapCanvas.zoom(e.getX(), e.getY(), amount);
        });

        mapCanvas.setOnMouseMoved(e -> {
            /*if (!Setting.debugMode || !mapCanvas.mapMarkerMode) return;
            mapCanvas.debugDrawMaxNeighborArea((float) e.getX(), (float) e.getY());*/
        });
    }

    public void reset() {
        lastX = 0;
        lastY = 0;
    }



}