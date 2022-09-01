import java.util.Scanner;

public class TemperaturOmregner {
    static void celciusFahrenheit() {
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Indtast temperaturen i Celcius");
        
        double temperatureCelcius = input.nextDouble();
        double temperatureFahrenheit = temperatureCelcius * 9 / 5 + 32;
        
        System.out.println("Temperaturen er " + temperatureFahrenheit + " Fahrenheit");
        
        input.close();
        
    }
    
    static void fahrenheitCelcius() {
        
        Scanner input = new Scanner(System.in);
        
        System.out.println("Indtast temperaturen i Fahrenheit");
        
        double temperatureFahrenheit = input.nextDouble();
        double temperatureCelcius = (temperatureFahrenheit - 32) * 0.555555555;
        
        System.out.println("Temperaturen er " + temperatureCelcius + " Celcius");
        
        input.close();
    }
}