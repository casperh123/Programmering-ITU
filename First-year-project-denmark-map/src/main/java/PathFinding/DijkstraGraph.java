package PathFinding;

import Exceptions.NoRouteFoundException;
import Interfaces.iTraversable;
import MapObjects.Traversable.RoadSegment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DijkstraGraph {

    private final int vertices;
    private int edges;
    private Set<DirectedEdge>[] adjacentEdges;
    private DirectedEdge[] edgeTo;
    private Double[] distTo;
    private IndexMinPriorityQueue priorityQueue;

    public DijkstraGraph(int vertices) {

        this.vertices = vertices + 1;
        this.edges = 0;
        this.adjacentEdges = (HashSet<DirectedEdge>[]) new HashSet[this.vertices];

        for(int i = 0; i < this.vertices; i++) {
            adjacentEdges[i] = new HashSet<>();
        }
    }

    public void addEdge(DirectedEdge newEdge) {

        adjacentEdges[newEdge.from()].add(newEdge);

        edges++;
    }

    public Iterable<DirectedEdge> getAllEdges() {

        Set<DirectedEdge> edges = new HashSet<>();

        for (int i = 0; i < vertices; i++) {
            edges.addAll(adjacentEdges[i]);
        }

        return edges;
    }

    public List<iTraversable> getPathTo(int source, int destination, int mode) throws NoRouteFoundException {
        return searchGraph(source, destination, mode);
    }

    public int getVertices() {
        return vertices;
    }

    public int getEdgeAmount() {
        return edges;
    }

    private List<iTraversable> searchGraph(int source, int destination, int mode) throws NoRouteFoundException {

        edgeTo = new DirectedEdge[vertices];
        distTo = new Double[vertices];
        priorityQueue = new IndexMinPriorityQueue();

        for(int i = 0; i < vertices; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }

        distTo[source] = 0.0;

        priorityQueue.insert(source, 0.0);

        int currentVertex = source;

        while(!priorityQueue.isEmpty() && !(currentVertex == destination)) {

            currentVertex = priorityQueue.delMin();

            relax(currentVertex, mode);
        }

        if(edgeTo[destination] == null) {
            throw new NoRouteFoundException();
        }

        return collectSegments(edgeTo, source, destination);
    }

    private void relax(int currentVertex, int mode) {

        for(DirectedEdge edge : adjacentEdges[currentVertex]) {

            int nextVertex = edge.to();

            if(mode == 1 && !edge.isDrivable()) {
                continue;
            } else if(mode == 2 && !edge.isCyclable()) {
                continue;
            } else if(mode == 3 && !edge.isWalkable()) {
                continue;
            }

            if(distTo[nextVertex] > distTo[currentVertex] + edge.getWeight(mode)) {
                distTo[nextVertex] = distTo[currentVertex] + edge.getWeight(mode);
                edgeTo[nextVertex] = edge;

                if(priorityQueue.contains(nextVertex)) {
                    priorityQueue.changeWeight(nextVertex, distTo[nextVertex]);
                } else {
                    priorityQueue.insert(nextVertex, distTo[nextVertex]);
                }
            }
        }
    }

    private List<iTraversable> collectSegments(DirectedEdge[] edgeTo, int source, int destination) {

        List<iTraversable> reversePath = new ArrayList<>();

        int currentRoad = destination;

        reversePath.add(edgeTo[currentRoad].getDestination());

        while(currentRoad != source) {

            iTraversable roadTo = edgeTo[currentRoad].getSource();

            reversePath.add(roadTo);
            currentRoad = edgeTo[currentRoad].from();
        }

        List<iTraversable> path = new ArrayList<>();

        for(int i = reversePath.size() - 1; i >= 0; i--) {
            path.add(reversePath.get(i));
        }

        List<iTraversable> finalPath = new ArrayList<>();

        path.forEach(segment -> finalPath.add(new RoadSegment(segment.getXCoords(), segment.getYCoords(), segment.getID(), 0, segment.getLength(), "routePath", segment.getName())));

        return finalPath;

    }

}