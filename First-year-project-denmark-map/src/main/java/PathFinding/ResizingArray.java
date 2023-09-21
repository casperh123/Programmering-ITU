package PathFinding;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ResizingArray {

    private Integer INIT_CAPACITY = 8;
    private int[] array;
    private int numberOfElements;
    private HashSet<Integer> elements;

    public ResizingArray() {
        array = new int[INIT_CAPACITY];
        numberOfElements = 0;
        elements = new HashSet<>();
    }

    public int getLength(){
        //Returns the total amount of spaces in the array
        return array.length;
    }
    public int get(int index) {
        return array[index];
    }

    public boolean contains(Object o) {
        return elements.contains(o);
    }

    private void resize(int max) {
        array = java.util.Arrays.copyOf(array, max);
    }

    public void add(int value) {
        addAtIndex(numberOfElements, value);
        elements.add(value);
    }

    public void addAtIndex(int index, int value) {
        if (array.length - 1 < index) {
            resize(2 * array.length);
            addAtIndex(index, value);
        } else {
            array[index] = value;
            numberOfElements++;
            elements.add(value);
        }
    }

    public int size() {
        //Returns the amount of occupied spaces in the array, I.E. the amount of elements.

        return numberOfElements;
    }

    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    public int indexOf(int element) {
        for(int i = 0; i < array.length; i++) {
            if(array[i] == element) {
                return i;
            }
        }
        return -1;
    }

    public void exchange(int indexA, int indexB) {
        int aux = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = aux;
    }

    public void removeLast() {
        elements.remove(array[numberOfElements]);
        numberOfElements--;
        if (array.length > 0 && numberOfElements == (array.length / 4)) {
            resize(array.length / 2);
        }
    }

    public List<Integer> toHeapList() {

        List<Integer> convertedArray = new ArrayList<>();

        for(int i = 1; i <= numberOfElements; i++) {
            convertedArray.add(array[i]);
        }

        return convertedArray;
    }

    public List<Integer> toList() {

        List<Integer> convertedArray = new ArrayList<>();

        for(int i = 0; i < numberOfElements; i++) {
            convertedArray.add(array[i]);
        }

        return convertedArray;
    }


}
