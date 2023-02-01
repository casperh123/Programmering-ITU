import java.util.Scanner;

public class Disjointsets {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        String[] firstInputString = input.nextLine().split(" ");

        int[] ids = new int[Integer.parseInt(firstInputString[0])];
        int[] size = new int[Integer.parseInt(firstInputString[1])];

        for(int i = 0; i < Integer.parseInt(firstInputString[0]); i++) {
            ids[i] = i;
            size[i] = 1;
        }

        for(int i = 0; i < Integer.parseInt(firstInputString[1]); i++) {
            
            String[] inputString = input.nextLine().split(" ");

            int query = Integer.parseInt(inputString[0]);
            int elementS = Integer.parseInt(inputString[1]);
            int elementT = Integer.parseInt(inputString[2]);

            if(query == 0) {
                if(connected(elementS, elementT, ids)) {
                    System.out.println(1);
                } else {
                    System.out.println(0);
                }
            }
            if(query == 1) {
                union(elementS, elementT, ids, size);
            } 
            if(query == 2) {
                move(elementS, elementT, ids, size);
            }
        }
    }

    public static boolean connected(int s, int t, int[] ids) {
        return find(s, ids) == find(t, ids);
    }

    public static int find(int s, int[] ids) {
        while(s != ids[s]) {
            s = ids[s];
        }
        return s;
    }

    public static void union(int s, int t, int[] ids, int[] size) {

        int elementSRoot = find(s, ids);
        int elementTRoot = find(t, ids);

        if(elementSRoot == elementTRoot) {
            return;
        }

        if(size[elementSRoot] < size[elementTRoot]) {
            ids[s] = elementTRoot;
            size[elementTRoot] += size[elementSRoot];
        }  else {
            ids[t] = elementSRoot;
            size[elementSRoot] += size[elementTRoot];
        }
    }

    public static void move(int s, int t, int[] ids, int[] size) {
       
        if(ids[s] == ids[t]) {
            return;
        }
        
        ids[s] = ids[t];
        
        for(int i = 0; i < ids.length; i++) {
            if(ids[i] == s) {
                ids[i] = i;
                break;
            }
        }
    }
}
