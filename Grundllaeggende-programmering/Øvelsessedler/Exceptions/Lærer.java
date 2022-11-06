public class Lærer
{
    private String navn;
    
    public Lærer(String navn) {
        this.navn = navn;
    }
    
    public void hvadErNTabellen(int n, Elev elev) {
         try {
             elev.sigTabel(n);
         } catch (Exception e){
             elev.laerTabel(n);
         }
         
         try {
             elev.sigTabel(n);
         } catch (Exception e) {
             elev.laerTabel(n);
         }
    }
}
