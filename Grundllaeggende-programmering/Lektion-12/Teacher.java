public class Teacher {
    private int age;
    private String name;
    private String teaching;
    
    public Teacher(int age, String name, String teaching) {
        this.age = age;
        this.name = name;
        this.teaching = teaching;
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
    
    public String getTeaching() {
        return teaching;
    }
    
    public void display() {
        System.out.println(name);
        System.out.println("(" + age + "year(s) old)");
        System.out.println("teaching: " + teaching);
    }
}