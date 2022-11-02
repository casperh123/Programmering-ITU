public class InternEmployee extends Employee
{
    private int contractedHours;
    private String project;
    
    public InternEmployee(String name, int contractedHours, String project) {
        super(name, (contractedHours * 150));
        this.contractedHours = contractedHours;
        this.project = project;
    }
    
    
    public void display() {
        
        super.display();
        
        System.out.println("Contracted hours: " + contractedHours);
        System.out.println("Project: " + project);
        
    }
}
