import java.util.ArrayList;

public class MonthlySalaryEmployee extends PermanentEmployee {
    
    private int projectCounter;

    public MonthlySalaryEmployee(String name, double salary) {
        super(name, salary);
        projectCounter = 0;
    }
    
    public void addProject(String project) {
        super.addProject(project);
        
        projectCounter++;
        
        if(projectCounter % 3 == 0) {
            salary += 1000;
        }
    }
}
