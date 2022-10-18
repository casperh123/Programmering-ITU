public class Student {
    private int age;
    private String name;
    private String programme;
    
    public Student(int age, String name, String programme) {
        this.age = age;
        this.name = name;
        this.programme = programme;
    }
 
    public int getAge() {
        return age;
    }
    
    public void birthday() {
        age = age + 1;
    }
    
    public String getName() {
        return name;
    }
    
    public String getProgramme() {
        return programme;
    }
    
    public void display() {
        System.out.println(name);
        System.out.println("(" + age + "year(s) old)");
        System.out.println(programme + " student");
    }
}