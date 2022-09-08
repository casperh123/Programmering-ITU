import java.util.Scanner;

public class TreTabeller {
    static void fireTabel() {
        
        int tabel = 4;
        
        for(int i = 1; i <= 10; i++) {
            System.out.println(tabel*i);
        }
    }
    
    static void syvTabel() {
        
        int tabel = 7;
        
        for(int i = 1; i <= 10; i++) {
            System.out.println(tabel*i);
        }
    }
    
    static void niTabel() {
        
        int tabel = 9;
        
        for(int i = 1; i <= 10; i++) {
            System.out.println(tabel*i);
        }
    }
    
    static void tabeller() {
        
        fireTabel();
        
        syvTabel();
        
        niTabel();
        
    }
    
    static void outputTabel(int tabel, int antalGentagelser) {
        
        for (int i = 1; i <= antalGentagelser; i++) {
            System.out.println(tabel*i);
        }
        
    }
}