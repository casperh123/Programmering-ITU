import java.util.*;

class ListDemo {
    static int N = 200000;
        
    static void test() {
        List<Integer> list;
        list = new ArrayList<Integer>();
        System.out.println("ArrayList: (N=" + N + ")");
        System.out.println("- insertion: " + insertion(list) + "ms");
        System.out.println("- traversal: " + traversal(list) + "ms");
        System.out.println();
        list = new LinkedList<Integer>();
        System.out.println("LinkedList: (N=" + N + ")");
        System.out.println("- insertion: " + insertion(list) + "ms");
        System.out.println("- traversal: " + traversal(list) + "ms");
        System.out.println();
    }

    

    static long insertion(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.add(i, i); // insert at index 0
        }
        long stop = System.currentTimeMillis();
        return (stop - start); // time elapsed (in ms)
    }

    static long traversal(List<Integer> list) {
        long start = System.currentTimeMillis();
        int test = 0;
        for (int i : list) {
            
        }
        long stop = System.currentTimeMillis();
        return (stop - start); // time elapsed (in ms)
    }

}