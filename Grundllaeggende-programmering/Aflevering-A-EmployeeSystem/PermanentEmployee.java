import java.util.ArrayList;

public class PermanentEmployee extends Employee
{
    protected ArrayList<String> projects;
    
    public PermanentEmployee(String name, double salary) {
        super(name, salary);
        projects = new ArrayList<>();
    }
    
    public void display() {
        
        super.display();
        
        if (projects.size() > 0) {
            System.out.println("Current projects: ");
            for(String p : projects) {
                System.out.println("- " + p);
            }
        }
    }
    
    public void addProject(String projectName) {
        projects.add(projectName);
    }
    
}
