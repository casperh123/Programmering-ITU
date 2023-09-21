package MapObjects;

import Interfaces.iDrawable;
import MapObjects.NonTraversable.Area;
import handin2.Settings.DrawSettings;
import handin2.Settings.Setting;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;


public class Relation extends Way implements iDrawable {

    List<Area> areas;



    public Relation(List<Way> boundaries, String type) {

        super(type);

        // Orders list boundaries to make a congruent relation.
        areas = getOrderedBoundaries(boundaries);
        setBounds(Setting.innerZoomBound - DrawSettings.getRelationKDLayer(type), areas);
        setLayer(DrawSettings.getRelationLayer(type));
    }

    @Override
    public void draw(GraphicsContext gc, double determinant) {

        /*
        When drawing ways, we want to check whether the next way is pointing in the right direction
        Therefor we check whether the first or the last point in the "new" way is closest to the last point
        of the just drawn way.
         */

        for (var way : areas) {
            way.draw(gc, determinant);
        }
    }

    private List<Area> getOrderedBoundaries(List<Way> list) {
        List<Area> closed = new ArrayList<>();
        boolean[] marked = new boolean[list.size()];

        float firstX = 0f;
        float firstY = 0f;
        float lastX = 1f;
        float lastY = 1f;

        int markedCount = 0;
        List<Way> group = new ArrayList<>();
        Way firstOpen = findFirstOpenWay(list, group, marked);
        if (firstOpen != null) {
            markedCount++;
            firstX = firstOpen.getXCoords()[0];
            firstY = firstOpen.getYCoords()[0];
            lastX = firstOpen.getXCoords()[firstOpen.getXCoords().length - 1];
            lastY = firstOpen.getYCoords()[firstOpen.getYCoords().length - 1];
        }

        // Debugging
        int count = 0;
        int size = group.size();

        // Order ways
        while (list.size() > markedCount) {
            for (int i = 0; i < list.size(); i++) {
                if (!marked[i] && !list.get(i).isClosedWay()) {

                    // Checks the first coordinates in the way for match with last coordinates in group
                    if (list.get(i).getXCoords()[0] == lastX &&
                        list.get(i).getYCoords()[0] == lastY)   {
                        Way way = list.get(i);
                        group.add(way);
                        marked[i] = true;
                        markedCount++;
                        //Update last coords
                        lastX = way.getXCoords()[way.getXCoords().length - 1];
                        lastY = way.getYCoords()[way.getYCoords().length - 1];
                    }
                    // Checks the last coordinates in the way for match with last coordinates in group
                    else if (list.get(i).getXCoords()[list.get(i).getXCoords().length - 1] == lastX &&
                             list.get(i).getYCoords()[list.get(i).getYCoords().length - 1] == lastY)   {
                        Way way = list.get(i).reverseWay();
                        group.add(way);
                        marked[i] = true;
                        markedCount++;
                        //Update first coords
                        lastX = way.getXCoords()[way.getXCoords().length - 1];
                        lastY = way.getYCoords()[way.getYCoords().length - 1];
                    }
                    // Checks the first coordinates in the way for match with first coordinates in group
                    else if (list.get(i).getXCoords()[0] == firstX &&
                             list.get(i).getYCoords()[0] == firstY)   {
                        Way way = list.get(i).reverseWay();
                        group.add(0, way);
                        marked[i] = true;
                        markedCount++;
                        //Update first coords
                        firstX = way.getXCoords()[0];
                        firstY = way.getYCoords()[0];
                    }
                    // Checks the last coordinates in the way for match with first coordinates in group
                    else if (list.get(i).getXCoords()[list.get(i).getXCoords().length - 1] == firstX &&
                             list.get(i).getYCoords()[list.get(i).getYCoords().length - 1] == firstY)   {
                        Way way = list.get(i);
                        group.add(0, way);
                        marked[i] = true;
                        markedCount++;
                        //Update last coords
                        firstX = way.getXCoords()[0];
                        firstY = way.getYCoords()[0];
                    }

                // Adds and marks closed ways
                } else if (!marked[i] && list.get(i).isClosedWay()) {
                    closed.add(new Area(list.get(i), type));
                    marked[i] = true;
                    markedCount++;
                }
            }

            if (lastX == firstX && lastY == firstY) {
                // Conditional breakpoint - IGNORE
                if (group.size() == 0) {
                    count = count;
                }


                // closes current group
                closed.add(new Area(type, group));
                group.clear();
                // Start the next group
                firstOpen = findFirstOpenWay(list, group, marked);
                if (firstOpen != null) {
                    markedCount++;
                    firstX = firstOpen.getXCoords()[0];
                    firstY = firstOpen.getYCoords()[0];
                    lastX = firstOpen.getXCoords()[firstOpen.getXCoords().length - 1];
                    lastY = firstOpen.getYCoords()[firstOpen.getYCoords().length - 1];
                }
            }

            // debugging
            if (size == group.size()) count++;
            else size = group.size();
            if (count >= 3) {
                System.out.println("stuck " + type + ", group size = " + group.size() );
                count = 0;
                closed.add(new Area(type, group));
                group.clear();
                // Start the next group
                firstOpen = findFirstOpenWay(list, group, marked);
                if (firstOpen != null) {
                    markedCount++;
                    firstX = firstOpen.getXCoords()[0];
                    firstY = firstOpen.getYCoords()[0];
                    lastX = firstOpen.getXCoords()[firstOpen.getXCoords().length - 1];
                    lastY = firstOpen.getYCoords()[firstOpen.getYCoords().length - 1];
                }
            }
        }

        //ordered.addAll(closed);
        return closed;
    }

    private Way findFirstOpenWay(List<Way> list, List<Way> group, boolean[] marked) {
        // Finds the first way which is not closed
        for(int i = 0; i < list.size(); i++) {
            if (!list.get(i).isClosedWay() && !marked[i]) {
                group.add(list.get(i));
                marked[i] = true;
                return list.get(i);
            }
        }
        return null;
    }

    public void setBounds(float layer, List<Area> ways1) {

        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX = -Float.MAX_VALUE;
        float maxY = -Float.MAX_VALUE;

        for (int i = 0; i < ways1.size(); i++) {
            if (ways1.get(i).getMinX() < minX) {
                minX = ways1.get(i).getMinX();
            }
            if (ways1.get(i).getMinY() < minY) {
                minY = ways1.get(i).getMinY();
            }
            if (ways1.get(i).getMaxX() > maxX) {
                maxX = ways1.get(i).getMaxX();
            }
            if (ways1.get(i).getMaxY() > maxY) {
                maxY = ways1.get(i).getMaxY();
            }
        }

        // Sets the bounds for super class rectElement
        setBounds(minX, minY, maxX, maxY, layer);
    }
    public List<Area> getAreas() {return areas;}
}
