import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;

public class Disjointsets {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int initialSingletons = input.nextInt();
        int iterations = input.nextInt();

        HashMap<Integer, Set<Integer>> sets = new HashMap<>(); 

        for(int i = 0; i < initialSingletons; i++) {
            HashSet<Integer> singletonSet = new HashSet<>();

            singletonSet.add(i);

            sets.put(i, singletonSet);
        }

        for(int i = 0; i < iterations; i++) {

            int query = input.nextInt();
            int elementS = input.nextInt();
            int elementT = input.nextInt();

            if(query == 0) {

                Set<Integer> comparatorSet = sets.get(elementS);

                    if(comparatorSet.contains(elementS)) {
                        System.out.println(1);
                    } else {
                        System.out.println(0);
                    }

            } else if (query == 1 && elementS != elementT) {
                
                Set<Integer> elementSSet = sets.get(elementS);
                Set<Integer> elementTSet = sets.get(elementT);
    
                elementSSet.addAll(elementTSet);

                elementTSet.forEach(value -> sets.merge(value, elementSSet, (a, b) -> b));

            } else if (query == 2 && elementS != elementT) {
                
                Set<Integer> movingToSet = sets.get(elementT);
                
                movingToSet.add(elementS);
                
                sets.get(elementS).remove(elementS);

                sets.merge(elementS, movingToSet, (a, b) -> b);
            }
        }
    }
}
