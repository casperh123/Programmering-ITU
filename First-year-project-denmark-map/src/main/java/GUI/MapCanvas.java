package GUI;

import Exceptions.NoRouteFoundException;
import Exceptions.NoSuchAddressException;
import Interfaces.iDrawable;
import Interfaces.iTraversable;
import MapObjects.Markers.Address;
import MapObjects.Markers.MapMarker;
import PathFinding.DijkstraGraph;
import PathFinding.RouteAggregater;
import PathFinding.TurnDirection;
import handin2.*;
import handin2.Settings.DrawSettings;
import handin2.Settings.PathfindingSettings;
import handin2.Settings.Setting;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;
import org.controlsfx.control.tableview2.filter.filtereditor.SouthFilter;

import java.util.Comparator;
import java.util.List;

public class MapCanvas extends Pane {

    private static MapCanvas Instance;

    private MainViewController mvc;
    private MapCanvasController controller;
    private Canvas canvas;
    private GraphicsContext gc;
    private Affine transform;
    private Boundaries boundaries;
    private KDTree<iDrawable> kdTreeNonTraversibles;
    private KDTree<iTraversable> kdTreeTraversibles;
    private KDTree<iTraversable> kdTreeRoute;
    private DijkstraGraph dijkstraGraph;
    private TernTree tst;
    public float zoomLevel;
    public MapMarker mapMarker;

    public float zoomPercent;
    public boolean mapMarkerMode = false;

    public static MapCanvas getInstance() {
        return Instance;
    }



    public MapCanvas() {

        if (MapCanvas.Instance == null) {
            MapCanvas.Instance = this;
        }

        this.kdTreeNonTraversibles = DataSingleton.getNonTraversableTree();
        this.kdTreeTraversibles = DataSingleton.getTraversibleTree();
        this.dijkstraGraph = DataSingleton.getRouteGraph();
        this.boundaries = DataSingleton.getBoundaries();
        this.kdTreeRoute = null;
        this.tst = DataSingleton.getTST();

        this.canvas = new Canvas(800, 600);
        this.gc = canvas.getGraphicsContext2D();
        this.getChildren().add(canvas);

        controller = new MapCanvasController(this);


        reloadMap();
    }

    public void reloadMap() {

        this.transform = new Affine();
        zoomLevel = 0;

        this.kdTreeNonTraversibles = DataSingleton.getNonTraversableTree();
        this.kdTreeTraversibles = DataSingleton.getTraversibleTree();
        this.dijkstraGraph = DataSingleton.getRouteGraph();
        this.boundaries = DataSingleton.getBoundaries();
        this.kdTreeRoute = null;
        this.tst = DataSingleton.getTST();

        zoomPan((float) (-0.56*14.6503 + 8), boundaries.getMaxLat() + 8);
        zoomInit(0, 0, 50);

        redraw();
    }

    private void redraw() {

        gc.setTransform(new Affine());

        // Colors the ocean black when in debug mode
        if(Setting.debugMode) {
            gc.setFill(Color.BLACK);
        } else {
            if (DrawSettings.darkTheme) {
                gc.setFill(Color.web("#b4d0d0").grayscale().darker().darker());
            } else {
                gc.setFill(Color.web("#b4d0d0"));
            }
        }


        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setTransform(transform);

        double determinant = gc.getTransform().determinant();

        gc.setLineWidth(1/Math.sqrt(determinant));

        // Utilizes kd-tree
        int offset = 10;
        if (Setting.debugMode) offset = -150;
        Rect rect = getCanvasRect(offset);

        List<iDrawable> nonTraversables = kdTreeNonTraversibles.searchTree(rect);
        List<iTraversable> traversables = kdTreeTraversibles.searchTree(rect);

        nonTraversables.sort(Comparator.comparingDouble(iDrawable::getLayer));
        traversables.sort(Comparator.comparingDouble(iDrawable::getLayer));

        nonTraversables.forEach(element -> element.draw(gc, determinant));
        traversables.forEach(element -> element.draw(gc, determinant));

        if(kdTreeRoute != null) {
            kdTreeRoute.searchTree(rect).forEach(traversable -> traversable.draw(gc, determinant));
        }
        if (mapMarker != null) {
            mapMarker.draw(gc, determinant);
        }

        // Visualizes the load zone
        if (Setting.debugMode) {

            float x1 = rect.getMinX();
            float y1 = rect.getMinY();
            float width = Math.abs(rect.getMaxX() - x1);
            float height = Math.abs(rect.getMaxY() - y1);

            gc.setLineWidth(1/Math.sqrt(determinant));
            gc.setStroke(Paint.valueOf("RED"));
            gc.strokeRect(x1, y1, width, height);

        }
    }

    @Override
    public void resize(double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
        canvas.setWidth(width);
        canvas.setHeight(height);
        redraw();
    }

    public iTraversable getNearestRoad(Address address) {
        return kdTreeTraversibles.closestElementToPointTraversable(new Point2D(address.getX(), address.getY()), 0.003f, 0.01f, PathfindingSettings.pathFindingMode());
    }

    public List<TurnDirection> setRoute(int source, int destination, int mode) throws NoRouteFoundException {

        long start = System.nanoTime();
        List<iTraversable> path = dijkstraGraph.getPathTo(source, destination, mode);
        kdTreeRoute = new KDTree<>(path, 2);

        System.out.println((System.nanoTime() - start) / 1000000);

        redraw();

        return RouteAggregater.getDirections(path);

    }

    public void resetRoute() {
        kdTreeRoute = null;
        redraw();
    }

    public void pan(double dx, double dy) {
        transform.prependTranslation(dx, dy);
        redraw();
    }

    private void zoomPan(double dx, double dy) {
        transform.prependTranslation(dx, dy);
    }

    public void zoom(double dx, double dy, double amount) {
        float innerZoomBound = Setting.innerZoomBound;
        float outerZoomBound = 0;


        // Tjekker om det næste zoom step vil overtræde grænserne.
        if (zoomLevel + amount > innerZoomBound) amount = innerZoomBound - zoomLevel;
        if (zoomLevel + amount < outerZoomBound) amount = outerZoomBound - zoomLevel;


        zoomLevel += amount;
        zoomPercent = convertToPercent(outerZoomBound, innerZoomBound, zoomLevel);

        double factor = Math.pow(1.01, amount);

        // Opdatere zoom tekst
        mvc.zoomLevelLabel.setText(Math.round(getZoomPercent()) + "%");

        zoomPan(-dx, -dy);
        transform.prependScale(factor, factor);
        zoomPan(dx, dy);
        redraw();
    }

    public void zoomInit(double dx, double dy, double factor) {
        transform.prependScale(factor, factor);
        transform.prependTranslation(-(getWidth() / 2), -(getHeight()/2));
    }

    public Point2D mouseToModel(double lastX, double lastY) {
        try {
            return transform.inverseTransform(lastX, lastY);
        } catch (NonInvertibleTransformException e) {
            throw new RuntimeException(e);
        }
    }

    public float convertToPercent(float lowerBound, float upperBound, float value) {
        return ((value - lowerBound) / (upperBound - lowerBound)) * 100;
    }

    public Rect getCanvasRect(float scaleModifier) {
        float x1 = 0;
        float y1 = 0;
        float x2 = (float) canvas.getWidth();
        float y2 = (float) canvas.getHeight();

        x1 -= scaleModifier;
        y1 -= scaleModifier;
        x2 += scaleModifier;
        y2 += scaleModifier;

        Point2D p1 = mouseToModel(x1, y1);
        Point2D p2 = mouseToModel(x2, y2);

        return new Rect((float) p1.getX(),
                (float) p1.getY(),
                (float) (p1.getX() + (p2.getX() - p1.getX())),
                (float) (p1.getY() + (p2.getY() - p1.getY())),
                zoomLevel);

    }

    public GraphicsContext getGraphicsContext() { return gc; }
    public float getZoomPercent() {
        return zoomPercent;
    }

    public void debugRedraw() {
        redraw();
    }

    public Boundaries getBoundaries() {
        return boundaries;
    }

    public void debugDrawMaxNeighborArea(float x, float y) {
        redraw();
        Point2D point = mouseToModel(x, y);
        float x1 = (float) point.getX() - 0.005f;
        float y1 = (float) point.getY() - 0.005f;
        float width = Math.abs((float) point.getX() + 0.005f - x1);
        float height = Math.abs((float) point.getY() + 0.005f - y1);

        gc.setStroke(Paint.valueOf("BLUE"));
        gc.strokeRect(x1, y1, width, height);
    }

    public List<Address> getTST(String prefix) {
        tst.findSuffixes(prefix);
        return tst.getMatches();
    }

    public Address getTSTFinalAddress(String prefix) throws NoSuchAddressException {
        Address address = tst.getFinalAddress(prefix);
        if (address != null) {
            return address;
        }
        throw new NoSuchAddressException(prefix);
    }

    public void toggleMapMarkerMode() {
        mapMarkerMode = !mapMarkerMode;
        mvc.updateColorOnMarkerButton();

    }
    public void placeMarker(float x, float y) {
        Point2D point = mouseToModel(x, y);
        placeMarkerNoToggle(point);
        toggleMapMarkerMode();
    }

    public void placeMarkerNoToggle(Point2D point) {
        String nearestRoadName = "";
        try {
            iTraversable element = kdTreeTraversibles.closestElementToPoint(point, 0.0001f, 0.005f);
            nearestRoadName = element.getName();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
        mapMarker = new MapMarker((float)point.getX(), (float)point.getY(), nearestRoadName);
        redraw();
    }

    public void setMainViewController(MainViewController mvc) { this.mvc = mvc; }

}