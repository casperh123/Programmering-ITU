package handin2;

import Interfaces.iTraversable;
import MapObjects.Node;
import MapObjects.Traversable.RoadSegment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoadSegmenter {

    private List<iTraversable> roads;
    private List<iTraversable> roadSegments;
    private int currentID;
    private Map<Node, List<iTraversable>> intersectingSegments;

    RoadSegmenter(List<iTraversable> roads) {

        this.roads = roads;
        this.roadSegments = new ArrayList<>();
        this.currentID = 0;
        this.intersectingSegments = new HashMap<>();

        KDTree<iTraversable> roadSearchTree = new KDTree<>(this.roads, 2);

        for(iTraversable road : roads) {

            Rect roadSearchArea = new Rect(road.getMinX(), road.getMinY(), road.getMaxX(), road.getMaxY(), 1000000.0F);
            List<iTraversable> adjacentRoads = roadSearchTree.searchTree(roadSearchArea);

            List<Node> segmentNodes = new ArrayList<>();

            float[] xCoords = road.getXCoords();
            float[] yCoords = road.getYCoords();

            Node firstNode =  new Node(1.78571F * xCoords[0], -yCoords[0]);
            segmentNodes.add(firstNode);

            for(int i = 1; i < xCoords.length; i++) {

                segmentNodes.add(new Node(1.78571F * xCoords[i], -yCoords[i]));

                for(iTraversable adjacentRoad : adjacentRoads) {

                    if(road == adjacentRoad || i == xCoords.length - 1) {
                        continue;
                    }

                    float[] adjacentXCoords = adjacentRoad.getXCoords();
                    float[] adjacentYCoords = adjacentRoad.getYCoords();

                    if(xCoords[i] == adjacentXCoords[0] && yCoords[i] == adjacentYCoords[0] ||
                            xCoords[i] == adjacentXCoords[adjacentXCoords.length - 1] && yCoords[i] == adjacentYCoords[adjacentXCoords.length - 1]) {

                        RoadSegment newSegment = new RoadSegment(segmentNodes, road.getType(), road.getSpeedLimit(), ++currentID, road.getName(), road.isOneWay());

                        roadSegments.add(newSegment);

                        Node lastNode = segmentNodes.get(segmentNodes.size() - 1);

                        segmentNodes.clear();
                        segmentNodes.add(lastNode);
                        break;
                    }
                }
            }
            roadSegments.add(new RoadSegment(segmentNodes, road.getType(), road.getSpeedLimit(), ++currentID, road.getName(), road.isOneWay()));
            segmentNodes.clear();
        }
    }

    public Map<Node, List<iTraversable>> getIntersectingSegments() {
        return intersectingSegments;
    }

    public List<iTraversable> getRoadSegments() {
        return roadSegments;
    }
}