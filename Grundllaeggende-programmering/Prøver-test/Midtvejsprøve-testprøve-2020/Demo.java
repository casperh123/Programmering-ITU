
public class Demo
{
    public static void demo() {
        
        Cocktail espressoMartini = new Cocktail("Espresso Martini");
        
        Ingredient coffeBeans = new EnergeticIngredient("coffe beans", "x", 2, 0, 0, 0);
        Ingredient espresso = new EnergeticIngredient("espresso", "cl", 10, 4, 54, 56);
        Ingredient vodka = new AlcoholicIngredient("vodka", "cl", 8, 0, 0, 0, 8);
        Ingredient kahlua = new AlcoholicIngredient("kahlua", "cl", 8, 0, 30, 0, 4);
        
        espressoMartini.add(coffeBeans);
        espressoMartini.add(espresso);
        espressoMartini.add(vodka);
        espressoMartini.add(kahlua);
        
        espressoMartini.recipe();
    }
}
