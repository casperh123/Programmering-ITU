public class loop {
    public static void loopTing() {
        
        int tabel = 0;
        
        while(tabel < 45) {
            tabel += 5;
            System.out.println("While Loop: " + tabel);
        }
        
        tabel = 0;
        
        for (int i = 0; i <= 45; i = i + 5) {
            System.out.println("For Loop " + i);
        }
    }
}