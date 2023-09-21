package handin2;

import Interfaces.Bounds;
import Interfaces.iTraversable;
import handin2.Settings.Setting;
import javafx.geometry.Point2D;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KDTree<T extends Bounds> implements Serializable {

    KDNode<T> root;

    short depth;
    int maxDepth;
    private final int dimensions;

    public KDTree(List<T> list, int dimensions) {

        List<T> elementList = new ArrayList<>(list);

        depth = 0;
        this.dimensions = dimensions;

        if (elementList.size() > 3) {

            elementList.sort(Comparator.comparingDouble(T::getMaxX));
            int middle = elementList.size() / 2;

            List<T> leftSubDivision = new ArrayList<>(elementList.subList(0, middle));
            List<T> rightSubDivision = new ArrayList<>(elementList.subList(middle, elementList.size()));

            float maxX = leftSubDivision.get(middle - 1).getMaxX();
            float minX = Float.MAX_VALUE;

            for (T element : rightSubDivision) {
                if (element.getMinX() < minX) {
                    minX = element.getMinX();
                }
            }

            root = new KDNode<>(minX, Float.MIN_VALUE, maxX, Float.MAX_VALUE, 0);

            depth++;
            addChild(root, leftSubDivision, false);
            addChild(root, rightSubDivision, true);
            depth--;
            if (Setting.debugMode) System.out.println("Built Tree - Max depth: " + maxDepth);
        }
    }

    private void addChild(KDNode<T> currentElement, List<T> list, boolean right) {

        // Updates max depth of the tree
        if (depth > maxDepth) {
            maxDepth = depth;
        }

        // Determines which axis to sort the list by
        int sortBy = depth % dimensions;

        // Sorts the list by the given axis
        sortBy(list, sortBy);


        int middle = list.size() / 2;

        // Divide list of elements into 2 lists, where the middle element is contained in the right list.
        List<T> leftSubDivision = new ArrayList<>(list.subList(0, middle));
        List<T> rightSubDivision = new ArrayList<>(list.subList(middle, list.size()));

        boolean createLeafNode = list.size() <= 3;

        KDNode<T> middleNode = getMiddleNode(sortBy, list, leftSubDivision, rightSubDivision);
        middleNode.setDepth(depth);

        if (right)  currentElement.setRightChild(middleNode);
        else        currentElement.setLeftChild(middleNode);

        if (createLeafNode) {

            T[] ways = (T[]) new Bounds[list.size()];

            for(int i = 0; i < ways.length; i++) {
                ways[i] = list.get(i);
            }

            middleNode.setList(ways);
            middleNode.setIsLeafNode(true);

        } else {
            depth++;
            addChild(middleNode, leftSubDivision, false);
            addChild(middleNode, rightSubDivision, true);
            depth--;
        }

    }

    private KDNode<T> getMiddleNode(int sortBy , List<T> elements, List<T> leftSubDivision, List<T> rightSubDivision) {

        int middle = elements.size() / 2;
        boolean createLeafNode = elements.size() <= 3;
        if (!createLeafNode) {

            float maxX = -Float.MAX_VALUE;
            float maxY = -Float.MAX_VALUE;
            float minX = Float.MAX_VALUE;
            float minY = Float.MAX_VALUE;
            float priorityZ = Float.MAX_VALUE;

            switch (sortBy) {
                case 0 -> {

                    maxX = leftSubDivision.get(middle - 1).getMaxX();
                    for (T element : rightSubDivision) {
                        if (element.getMinX() < minX) {
                            minX = element.getMinX();
                        }
                    }
                }
                case 1 -> {

                    maxY = leftSubDivision.get(middle - 1).getMaxY();
                    for (T element : rightSubDivision) {
                        if (element.getMinY() < minY) {
                            minY = element.getMinY();
                        }
                    }
                }
                case 2 -> {
                    priorityZ = rightSubDivision.get(0).getPriorityZ();
                }
            }

            return new KDNode<>(minX, minY, maxX, maxY, priorityZ);

        } else {
            T middleElement = elements.get(middle);
            float minX = middleElement.getMinX();
            float maxX = middleElement.getMaxX();
            float minY = middleElement.getMinY();
            float maxY = middleElement.getMaxY();
            float priorityZ = middleElement.getPriorityZ();

            return new KDNode<>(minX, minY, maxX, maxY, priorityZ);
        }
    }

    public List<T> getAllElements() {
        List<T> list  = new ArrayList<>();
        getAllElements(list, root);
        return list;
    }
    private void getAllElements(List<T> list, KDNode<T> currentPoint) {

        if (currentPoint.getList() != null) {
            list.addAll(List.of(currentPoint.getList()));
        }
        if (currentPoint.getLeftChild() != null) getAllElements(list, currentPoint.getLeftChild());
        if (currentPoint.getRightChild() != null) getAllElements(list, currentPoint.getRightChild());
    }

    private void sortBy(List<T> list, int sortBy) {
        switch (sortBy) {
            case 0 -> list.sort(Comparator.comparingDouble(T::getMaxX));
            case 1 -> list.sort(Comparator.comparingDouble(T::getMaxY));
            case 2 -> list.sort(Comparator.comparingDouble(T::getPriorityZ));
        }
    }

    // Returns a list of every node within the search rect
    public List<T> searchTree(Rect searchRect) {
        List<T> list = new ArrayList<>();
        searchTree(root, searchRect, list);
        return list;
    }

    private void searchTree(KDNode<T> kdNode, Rect rect, List<T> list) {

        if (kdNode != null) {

            if (kdNode.getList() != null) { //Check if KdNode is a leaf

                for (T element : kdNode.getList()) {

                    float minX = element.getMinX();
                    float maxX = element.getMaxX();
                    float minY = element.getMinY();
                    float maxY = element.getMaxY();
                    float priorityZ = element.getPriorityZ();

                    if (rect.overlaps(minX, minY, maxX, maxY, priorityZ)) {
                        list.add(element);
                    }
                }
            }

            boolean checkRight = false;
            boolean checkLeft = false;

            switch (kdNode.getDepth() % dimensions) {
                case 0 -> {
                    float nodeMaxX = kdNode.getMaxX();
                    float nodeMinX = kdNode.getMinX();
                    float maxX = rect.getMaxX();
                    float minX = rect.getMinX();

                    if (nodeMaxX >= minX) {
                        checkLeft = true;
                    }
                    if (nodeMinX <= maxX) {
                        checkRight = true;
                    }
                }
                case 1 -> {
                    float nodeMaxY = kdNode.getMaxY();
                    float nodeMinY = kdNode.getMinY();
                    float maxY = rect.getMaxY();
                    float minY = rect.getMinY();

                    if (nodeMaxY >= minY) {
                        checkLeft = true;
                    }
                    if (nodeMinY <= maxY) {
                        checkRight = true;
                    }
                }
                case 2 -> {
                    checkLeft = true; // Always goes downwards on Z axis.
                    float nodePriorityZ = kdNode.getPriorityZ();
                    if (nodePriorityZ <= rect.getPriorityZ()) {
                        checkRight = true;
                    }
                }
            }

            if (checkRight) {
                searchTree(kdNode.getRightChild(), rect, list);
            }
            if (checkLeft) {
                searchTree(kdNode.getLeftChild(), rect, list);
            }
        }
    }

    public T closestElementToPoint(Point2D point, float sizeOffset, float maxRadius) {

        if (sizeOffset > maxRadius) return null;

        Rect searchRect = new Rect( (float) point.getX() - sizeOffset,
                (float) point.getY() - sizeOffset,
                (float) point.getX() + sizeOffset,
                (float) point.getY() + sizeOffset,
                10000);


        List<T> candidates = searchTree(searchRect);

        // If nothing is found, search with a bigger area.
        if (candidates.size() == 0) return closestElementToPoint(point, sizeOffset + sizeOffset, maxRadius);

        float smallestDist = Float.MAX_VALUE;
        T closestElement = candidates.get(0);
        for (T t : candidates) {
            for (int i = 0; i < t.getXCoords().length; i++) {

                double deltaX = point.getX() - t.getXCoords()[i];
                double deltaY = point.getY() - t.getYCoords()[i];
                double c2 = Math.pow(deltaX, 2) + Math.pow(deltaY, 2);

                if (c2 < smallestDist) {
                    smallestDist = (float) c2;
                    closestElement = t;
                }
            }
        }

        return closestElement;
    }

    public iTraversable closestElementToPointTraversable(Point2D point, float sizeOffset, float maxRadius, int mode) {

        if (sizeOffset > maxRadius) return null;

        Rect searchRect = new Rect( (float) point.getX() - sizeOffset,
                (float) point.getY() - sizeOffset,
                (float) point.getX() + sizeOffset,
                (float) point.getY() + sizeOffset,
                10000);


        List<iTraversable> candidates = (ArrayList<iTraversable>) searchTree(searchRect);
        candidates.removeIf(i -> mode == 0 && !i.isDriveable() ||
                                 mode == 1 && !i.isCycleable() ||
                                 mode == 2 && !i.isWalkable());

        // If nothing is found, search with a bigger area.
        if (candidates.size() == 0) return closestElementToPointTraversable(point, sizeOffset + sizeOffset, maxRadius, mode);

        float smallestDist = Float.MAX_VALUE;
        iTraversable closestElement = candidates.get(0);
        for (iTraversable t : candidates) {
            for (int i = 0; i < t.getXCoords().length; i++) {

                double deltaX = point.getX() - t.getXCoords()[i];
                double deltaY = point.getY() - t.getYCoords()[i];
                double c2 = Math.pow(deltaX, 2) + Math.pow(deltaY, 2);

                if (c2 < smallestDist) {
                    smallestDist = (float) c2;
                    closestElement = t;
                }
            }
        }

        return closestElement;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
}