import java.util.ArrayList;

public class EmployeeOverview {
    private ArrayList<Employee> employees;

    public EmployeeOverview() {
        employees = new ArrayList<>();
    }

    /* Show employee info */
    public void display() {
        for (Employee e : employees) {
            e.display();
            System.out.println();
        }
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }
}
