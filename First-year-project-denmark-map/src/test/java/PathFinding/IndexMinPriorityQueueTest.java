package PathFinding;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

class IndexMinPriorityQueueTest {

    IndexMinPriorityQueue priorityQueue;

    @BeforeEach
    public void setup() {
        priorityQueue = new IndexMinPriorityQueue();
    }

    @AfterEach
    public void tearDown() {
        priorityQueue = null;
    }

    @Test
    void isEmpty() {

        assert(priorityQueue.isEmpty());

        priorityQueue.insert(1, 1.0);

        assert(!priorityQueue.isEmpty());

    }

    @Test
    void contains() {

        priorityQueue.insert(10, 5.0);

        assert(priorityQueue.contains(10));
    }

    @Test
    void size() {

        assert(priorityQueue.size() == 0);

        for(int i = 1; i < 10000; i++) {
            priorityQueue.insert(i, 1.0 + i);
        }

        priorityQueue.insert(1, 1.0);

        assert(priorityQueue.size() == 10000);
    }

    @Test
    void delMin() {

        priorityQueue.insert(10, 50.0);
        priorityQueue.insert(2, 11.0);
        priorityQueue.insert(5, 10.0);
        priorityQueue.insert(8, 1.0);
        priorityQueue.insert(9, 2.0);
        priorityQueue.insert(3, 5.0);

        List<Integer> expectedOrder = Arrays.asList(8, 9, 3, 10, 5, 2);
        List<Integer> realOrder  = priorityQueue.toList();


        for(int i = 0; i < expectedOrder.size(); i++) {
            assert(expectedOrder.get(i) == realOrder.get(i));
        }

        assert(priorityQueue.delMin() == 8);
        assert(priorityQueue.delMin() == 9);
        assert(priorityQueue.delMin() == 3);
        assert(priorityQueue.delMin() == 5);
        assert(priorityQueue.delMin() == 2);
        assert(priorityQueue.delMin() == 10);
    }

    @Test
    void delMinWithDuplicatesValues() {

        priorityQueue.insert(10, 10.0);
        priorityQueue.insert(9, 9.0);
        priorityQueue.insert(5, 10.0);

        assert(priorityQueue.delMin() == 9);
        assert(priorityQueue.delMin() == 10);
        assert(priorityQueue.delMin() == 5);
    }

    @Test
    void maintainsInesertionOrderWithDuplcateValues() {
        priorityQueue.insert(9, 10.0);
        priorityQueue.insert(10, 10.0);
        priorityQueue.insert(5, 10.0);

        assert(priorityQueue.delMin() == 9);
        assert(priorityQueue.delMin() == 10);
        assert(priorityQueue.delMin() == 5);
    }

    @Test
    void delMinThrowsException() {

        priorityQueue.insert(1, 1.0);

        priorityQueue.delMin();

        assertThrows(NoSuchElementException.class, () -> priorityQueue.delMin());
    }

    @Test
    void insert() {
    }

    @Test
    void changeWeightHigherValue() {
        priorityQueue.insert(9, 10.0);
        priorityQueue.insert(10, 7.0);
        priorityQueue.insert(5, 11.0);

        priorityQueue.changeWeight(10, 15.0);

        assert(priorityQueue.delMin() == 9);
        assert(priorityQueue.delMin() == 5);
        assert(priorityQueue.delMin() == 10);
    }

    @Test
    void changeWeightLowerValue() {
        priorityQueue.insert(9, 10.0);
        priorityQueue.insert(10, 7.0);
        priorityQueue.insert(5, 11.0);

        priorityQueue.changeWeight(5, 5.0);

        assert(priorityQueue.delMin() == 5);
        assert(priorityQueue.delMin() == 10);
        assert(priorityQueue.delMin() == 9);
    }
}