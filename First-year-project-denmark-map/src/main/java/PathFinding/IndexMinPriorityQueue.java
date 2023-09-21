package PathFinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class IndexMinPriorityQueue {

    private ResizingArray vertexIDArray;
    private Map<Integer, Double> weights;
    private int size = 0;

    public IndexMinPriorityQueue() {
        vertexIDArray = new ResizingArray();
        weights = new HashMap<>();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void insert(int vertex, double weight) {
        size++;
        vertexIDArray.addAtIndex(size, vertex);
        weights.put(vertex, weight);
        swim(size);
    }

    public int delMin() {
        if(!isEmpty()) {
            int minID = vertexIDArray.get(1);
            weights.remove(minID);
            vertexIDArray.exchange(1, size--);
            vertexIDArray.removeLast();
            sink(1);
            return minID;
        }
        throw new NoSuchElementException("The queue is empty");
    }

    public boolean contains(int ID) {
        return vertexIDArray.contains(ID);
    }

    private boolean less(int parentIndex, int childIndex) {

        int parentID = vertexIDArray.get(parentIndex);
        int childID = vertexIDArray.get(childIndex);

        return weights.get(parentID) > weights.get(childID);
    }

    private void swim(int k) { //Remember that less(k) compares the value of the weights associated with k/2 and k
        while(k > 1 && less(k/2, k)) {
            vertexIDArray.exchange(k/2, k);
            k = k/2;
        }
    }

    private void sink(int parentIndex) {

        while(2*parentIndex <= size) {

            int childIndex = 2*parentIndex;

            if (childIndex < size && less(childIndex, childIndex + 1)) {
                childIndex++;
            }
            if(less(childIndex, parentIndex)) break;

            vertexIDArray.exchange(parentIndex, childIndex);
            parentIndex = childIndex;

        }
    }

    public void changeWeight(int vertex, double weight) {

        weights.put(vertex, weight);
        swim(vertex);
        
    }

    public List<Integer> toList() {
        return vertexIDArray.toHeapList();
    }
}
