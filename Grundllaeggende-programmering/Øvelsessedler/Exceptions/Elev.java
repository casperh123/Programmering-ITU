import java.util.List;
import java.util.ArrayList;

public class Elev
{
    private String navn;
    private List<Integer> tabellerLaert; 
    
    public Elev(String navn) {
        this.navn = navn;
        tabellerLaert = new ArrayList<>();
    }
    
    public void laerTabel(int n) {
        tabellerLaert.add(n);
    }
    
    public void sigTabel(int n) throws Exception {
        
        if(tabellerLaert.contains(n)) {
            for(int i = 0; i <= 10; i++) {
            System.out.println(n * i);
            }
        } else {
            throw new Exception("Eleven kan ikke denne tabel");
        }
    }
}
