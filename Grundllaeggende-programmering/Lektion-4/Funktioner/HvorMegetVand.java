public class HvorMegetVand {
    static double water(double weight, double extraModifier) {
        
        double amountOfWater = 35.0;
        
        double result = weight * amountOfWater * extraModifier;
        double resultLiters = result / 1000;
        
        return resultLiters;
    }
    
    static void kaldWater() {
        
        double førsteVægt = 65;
        double andenVægt = 10;
        
        if(førsteVægt < andenVægt) {
            System.out.println(andenVægt);
        } else {
            System.out.println(førsteVægt);
        }
    }
}