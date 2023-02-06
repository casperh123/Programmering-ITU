import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Disjointsets {
    public static void main(String[] args) throws IOException {
        
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));

        String[] firstInputString = input.readLine().split(" ");

        int[] ids = new int[Integer.parseInt(firstInputString[0])];
        int[] size = new int[Integer.parseInt(firstInputString[0])];
        int iterations = Integer.parseInt(firstInputString[1]);

        for(int i = 0; i < ids.length; i++) {
            ids[i] = i;
            size[i] = 1;
        }

        for(int i = 0; i < iterations; i++) {
            
            String[] inputString = input.readLine().split(" ");

            String query = inputString[0];
            int elementS = Integer.parseInt(inputString[1]);
            int elementT = Integer.parseInt(inputString[2]);

            if(query.equals("?")) {
                if(find(elementS, ids) == find(elementS, ids)) {
                    out.print("yes");
                } else {
                    out.print("no");
                }
            } else {
                union(elementS, elementT, ids, size);
            } 
        }
    }

    public static int find(int s, int[] ids) {
        while(s != ids[s]) {
            ids[s] = ids[ids[s]];
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
            ids[elementSRoot] = elementTRoot;
            size[elementTRoot] += size[elementSRoot];
        }  else {
            ids[elementTRoot] = elementSRoot;
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
