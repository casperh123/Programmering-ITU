package handin2;

import Interfaces.iTraversable;
import PathFinding.DirectedEdge;

import java.util.ArrayList;
import java.util.List;

public class EdgeGenerater {

    List<DirectedEdge> edges;
    KDTree<iTraversable> traversables;

    public EdgeGenerater(KDTree<iTraversable> traversables) {
        this.traversables = traversables;
        this.edges = generateEdges();
    }

    public List<DirectedEdge> getEdges() {
        return edges;
    }

    protected List<DirectedEdge> generateEdges() {

        List<DirectedEdge> edges = new ArrayList<>();

        for(iTraversable roadSegment : traversables.getAllElements()) {

            List<iTraversable> intersectingSegments = traversables.searchTree(new Rect(roadSegment));

            for(iTraversable intersectingSegment : intersectingSegments) {

                if(roadSegment == intersectingSegment) {
                    continue;
                }
                if(traversableIsConnected(roadSegment, intersectingSegment, roadSegment.isOneWay(), intersectingSegment.isOneWay())) {
                    edges.add(new DirectedEdge(roadSegment, intersectingSegment));
                }
            }
        }
        return edges;
    }

    protected boolean traversableIsConnected(iTraversable road, iTraversable checkingRoad, boolean roadOneWay, boolean checkingRoadOneWay) {

        float[] roadX = road.getXCoords();
        float[] roadY = road.getYCoords();

        float[] checkingRoadX = checkingRoad.getXCoords();
        float[] checkingRoadY = checkingRoad.getYCoords();

        if(!roadOneWay && !checkingRoadOneWay) {

            //Returns true if the two ways intersect in one of the ends of the road
            return coordinateIntersects(roadX[0], roadY[0], checkingRoadX[checkingRoadX.length - 1], checkingRoadY[checkingRoadY.length - 1]) ||
                    coordinateIntersects(roadX[0], roadY[0], checkingRoadX[0], checkingRoadY[0]) ||
                    coordinateIntersects(roadX[roadX.length - 1], roadY[roadY.length - 1], checkingRoadX[0], checkingRoadY[0]) ||
                    coordinateIntersects(roadX[roadX.length - 1], roadY[roadY.length - 1], checkingRoadX[checkingRoadX.length - 1], checkingRoadY[checkingRoadY.length - 1]);
        } else if (!checkingRoadOneWay){
            //Returns true if any end of the first road intersects with the starting end of the oneway road
            return coordinateIntersects(roadX[roadX.length - 1], roadY[roadY.length - 1], checkingRoadX[0], checkingRoadY[0]) ||
                    coordinateIntersects(roadX[roadX.length - 1], roadY[roadY.length - 1], checkingRoadX[checkingRoadX.length - 1], checkingRoadY[checkingRoadY.length - 1]);
        } else if (!roadOneWay){
            return coordinateIntersects(roadX[roadX.length - 1], roadY[roadY.length - 1], checkingRoadX[0], checkingRoadY[0]) ||
                    coordinateIntersects(roadX[0], roadY[0], checkingRoadX[0], checkingRoadY[0]);
        } else {
            return coordinateIntersects(roadX[roadX.length - 1], roadY[roadY.length - 1], checkingRoadX[0], checkingRoadY[0]);
        }
    }

    private boolean coordinateIntersects(float x1, float y1, float x2, float y2) {
        return x1 == x2 && y1 == y2;
    }

}
