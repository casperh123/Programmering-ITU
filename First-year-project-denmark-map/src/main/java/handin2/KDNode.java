package handin2;

import Interfaces.Bounds;

import java.io.Serializable;

public class KDNode<T extends Bounds> implements Serializable {

    KDNode<T> leftChild, rightChild;
    short depth;
    boolean isLeafNode;

    float minX, minY, maxX, maxY, priorityZ;

    T[] list;

    public KDNode(float minX, float minY, float maxX, float maxY, float priorityZ) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.priorityZ =  priorityZ;
    }

    public void setList(T[] list) {
        this.list = list;
    }

    public T[] getList() {
        return list;
    }
    public boolean getIsLeafNode() { return isLeafNode; }
    public boolean setIsLeafNode(boolean state) {
        isLeafNode = state;
        return isLeafNode;
    }

    public float getMinX() {
        return this.minX;
    }
    public float getMinY() {
        return this.minY;
    }
    public float getMaxX() {
        return this.maxX;
    }
    public float getMaxY() {
        return this.maxY;
    }
    public float getPriorityZ() { return this.priorityZ; }

    public void setLeftChild(KDNode<T> node) { leftChild = node; }
    public void setRightChild(KDNode<T> node) { rightChild = node; }

    public KDNode<T> getLeftChild() { return leftChild; }
    public KDNode<T> getRightChild() { return rightChild; }

    public short getDepth() { return depth; }
    public short setDepth(short depth) {
        this.depth = depth;
        return this.depth;
    }


}
