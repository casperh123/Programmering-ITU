package MapObjects;

import java.io.Serializable;

public class Node implements Serializable {

    protected float x;
    protected float y;

    public Node(float x, float y) {
        this.x = x;
        this.y = -y;
    }

    public boolean equals(Object o) {

        if(!(o instanceof Node comparingNode)) {
            return false;
        }

        return this.y == comparingNode.y
                && this.x == comparingNode.x;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }
}