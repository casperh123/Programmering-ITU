package PathFinding;

public class TurnDirection {

    String roadName;
    int roadLength;
    String turnDirection;

    public TurnDirection(String roadName, int roadLength, String turnDirection) {
        this.roadName = roadName;
        this.roadLength = roadLength;
        this.turnDirection = turnDirection;
    }

    public String toString() {
        if(turnDirection.equals("continue down")) {
            return "For " + roadLength + " meters, " + turnDirection + " " + roadName;
        }
        return "In " + roadLength + " meters, " + turnDirection + " to " + roadName;
    }
}
