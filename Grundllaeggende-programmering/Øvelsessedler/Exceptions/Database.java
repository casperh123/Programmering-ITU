import java.util.List;
import java.util.ArrayList;

public class Database
{
    private List<PersonData> personData;
    public Database() {
        personData = new ArrayList<>();
    }
    
    public void tilfoejData(String navn, int alder, int hoejde) {
        try {
            personData.add(new PersonData(navn, alder, hoejde));   
        } catch(HoejdeUgyldigException e) {
            if(hoejde < 80 && hoejde> 40) {
                
                double hoejdeICentimeter = hoejde * 2.54;
                
                try {
                    personData.add(new PersonData(navn, alder, hoejde));
                } catch (HoejdeUgyldigException otherException) {
                    System.out.println(otherException.getMessage() + "Umenneskelig højde");
                }
            } else {
                System.out.println(e.getMessage() + "Umenneskelig højde");
            }
        }
    }
}
