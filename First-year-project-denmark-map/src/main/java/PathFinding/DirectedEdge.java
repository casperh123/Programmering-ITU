package PathFinding;

import Interfaces.iTraversable;

public class DirectedEdge {

    //Fields containing the road-segments. Used for accessing members and returning drawable object.
    private final iTraversable source;
    private final iTraversable destination;

    //ID's used for vertex indexed arrays.
    private final int from;
    private final int to;

    private boolean drivable;
    private boolean walkable;
    private boolean cyclable;

    public DirectedEdge(iTraversable source, iTraversable destination) {
        this.source = source;
        this.destination = destination;
        this.from = source.getID();
        this.to = destination.getID();
        this.drivable = source.isDriveable();
        this.cyclable = source.isCycleable();
        this.walkable = source.isWalkable();
    }

    public double getWeight(int mode) {
        if(!(mode == 2 || mode == 3)) {
            return source.getLength() / source.getSpeedLimit();
        }
        return source.getLength();
    }

    public int to() {
        return to;
    }

    public int from() {
        return from;
    }

    public boolean isDrivable() {
        return drivable;
    }
    public boolean isWalkable() {
        return walkable;
    }
    public boolean isCyclable() {
        return cyclable;
    }

    public iTraversable getSource() {
        return source;
    }

    public iTraversable getDestination() {
        return destination;
    }
}
