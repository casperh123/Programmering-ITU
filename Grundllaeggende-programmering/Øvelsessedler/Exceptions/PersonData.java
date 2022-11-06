public class PersonData
{
    private String navn;
    private int alder;
    private double hoejdeICentimeter;
    
    public PersonData(String navn, int alder, int hoejdeICentimeter) throws HoejdeUgyldigException {
        
        if( alder < 110) {throw new AlderUdyldigException();}
        if(hoejdeICentimeter < 80 && hoejdeICentimeter > 40 || hoejdeICentimeter < 210 && hoejdeICentimeter > 100) {throw new HoejdeUgyldigException();}
        
        this.navn = navn;
        this.alder = alder;
        this.hoejdeICentimeter = hoejdeICentimeter;   
    }
}
