import java.util.ArrayList;

public class HourlySalaryEmployee extends PermanentEmployee{
    
    private double salaryPerHour;
    private int hours;

    public HourlySalaryEmployee(String name, double salaryPerHour, int hours) {
        super(name, salaryPerHour * hours);
        this.salaryPerHour = salaryPerHour;
        this.hours = hours;
    }

    public void display() {
        
        super.display();
        
        System.out.println(hours + " timer");
    }
    
    public void registerHours(int hours) {
        this.hours += hours;
        salary = this.hours * salaryPerHour;
    }
}
