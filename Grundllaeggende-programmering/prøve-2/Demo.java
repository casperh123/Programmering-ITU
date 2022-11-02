public class Demo
{
    public static void test() {
        
        Forest amagerFaelled = new Forest("Amager Fælled");
        
        FruitTree aeble = new FruitTree("æble");
        
        amagerFaelled.plant(aeble);
        amagerFaelled.grow();
        
        Tree gran1 = new Tree("gran");
        Tree gran2 = new Tree("gran");
        
        amagerFaelled.plant(gran1);
        amagerFaelled.plant(gran2);
        amagerFaelled.grow();
        amagerFaelled.display();
        amagerFaelled.display("gran");
        
        System.out.println("Skovens højeste træ er: ");
        System.out.println(amagerFaelled.tallest());
    }
}
