public class Main {
public static void main ( String [] args ){
Employee e1 = new Employee ( " Leslie " , 10000) ;
Employee e2 = new InternEmployee (" Alex " , 300 , " Evaluering af arbejdsmiljo ") ;
Employee e3 = new HourlySalaryEmployee (" Taylor " , 150 , 30) ;
EmployeeOverview eo = new EmployeeOverview () ;
eo . addEmployee ( e1 );
eo . addEmployee ( e2 );
eo . addEmployee ( e3 );
eo . display () ;
}
}