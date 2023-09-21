package PathFinding;

import Interfaces.iTraversable;

import java.util.ArrayList;
import java.util.List;

public class RouteAggregater {

    public static List<TurnDirection> getDirections(List<iTraversable> traversables) {

        List<TurnDirection> turnDirections = new ArrayList<>();

        int roadLength = 0;

        TurnDirection turnDirection;

        for(int i = 0; i < traversables.size() - 1; i++) {


            iTraversable source = traversables.get(i);
            roadLength = (int) source.getLength();
            iTraversable destination = traversables.get(i + 1);

            if(source.getName().equals(destination.getName())) {
                roadLength += source.getLength();
                continue;
            } else {
                turnDirection = new TurnDirection(destination.getName(), roadLength, direction(source, destination));
                roadLength = (int) source.getLength();
                turnDirections.add(turnDirection);
            }
        }

        return turnDirections;
    }

    public static String direction(iTraversable source, iTraversable destination) {

        float[] xCoordsSource = source.getXCoords();
        float[] yCoordsSource = source.getYCoords();

        float[] xCoordsDestination = destination.getXCoords();
        float[] yCoordsDestination = destination.getYCoords();

        float[] vSource = new float[2];
        float[] vDestination = new float[2];

        // Compare first and first
        if (xCoordsSource[0] == xCoordsDestination[0] &&
            yCoordsSource[0] == yCoordsDestination[0]) {
            vSource[0] = xCoordsSource[0] - xCoordsSource[1];
            vSource[1] = yCoordsSource[0] - yCoordsSource[1];
            vDestination[0] = xCoordsDestination[1] - xCoordsDestination[0];
            vDestination[1] = yCoordsDestination[1] - yCoordsDestination[0];

        // Compare last and first
        } else if (xCoordsSource[xCoordsSource.length -1] == xCoordsDestination[0] &&
                   yCoordsSource[yCoordsSource.length -1] == yCoordsDestination[0]) {
            vSource[0] = xCoordsSource[xCoordsSource.length -1] - xCoordsSource[xCoordsSource.length -2];
            vSource[1] = yCoordsSource[yCoordsSource.length -1] - yCoordsSource[yCoordsSource.length -2];
            vDestination[0] = xCoordsDestination[1] - xCoordsDestination[0];
            vDestination[1] = yCoordsDestination[1] - yCoordsDestination[0];

        // Compare first and last
        } else if (xCoordsSource[0] == xCoordsDestination[xCoordsDestination.length -1] &&
                   yCoordsSource[0] == yCoordsDestination[yCoordsDestination.length -1]) {
            vSource[0] = xCoordsSource[0] - xCoordsSource[1];
            vSource[1] = yCoordsSource[0] - yCoordsSource[1];
            vDestination[0] = xCoordsDestination[xCoordsDestination.length -2] - xCoordsDestination[xCoordsDestination.length -1];
            vDestination[1] = yCoordsDestination[xCoordsDestination.length -2] - yCoordsDestination[xCoordsDestination.length -1];

        // Compare last and last
        } else if (xCoordsSource[xCoordsSource.length -1] == xCoordsDestination[xCoordsDestination.length -1] &&
                   yCoordsSource[yCoordsSource.length -1] == yCoordsDestination[yCoordsDestination.length -1]) {
            vSource[0] = xCoordsSource[xCoordsSource.length -1] - xCoordsSource[xCoordsSource.length -2];
            vSource[1] = yCoordsSource[yCoordsSource.length -1] - yCoordsSource[yCoordsSource.length -2];
            vDestination[0] = xCoordsDestination[xCoordsDestination.length -2] - xCoordsDestination[xCoordsDestination.length -1];
            vDestination[1] = yCoordsDestination[xCoordsDestination.length -2] - yCoordsDestination[xCoordsDestination.length -1];

        }

        double deltaAng = degreesBetweenVectors(vSource, vDestination);

        if (Math.abs(deltaAng) <= 45) {
            return "continue down";
        }
        if (deltaAng < -45) {
            return "turn left";
        }
        if (deltaAng > 45) {
            return "turn right";
        }

        return "";
    }

    private static double degreesBetweenVectors(float[] v1, float[] v2) {
        double dot = (v1[0] * v2[0]) + (v1[1] * v2[1]);
        double v1Magnitude = Math.sqrt(Math.pow(v1[0], 2) + Math.pow(v1[1], 2));
        double v2Magnitude = Math.sqrt(Math.pow(v2[0], 2) + Math.pow(v2[1], 2));
        double cos = dot / (v1Magnitude * v2Magnitude);
        return Math.toDegrees(Math.acos(cos)) * getSign(v1, v2);
    }

    private static int getSign(float[] v1, float[] v2) {
        double dir = v1[0]*v2[1] - v1[1]*v2[0];
        if (dir > 0) {
            return 1;
        } else if (dir < 0) {
            return -1;
        } else {
            return 0;
        }
    }

}
