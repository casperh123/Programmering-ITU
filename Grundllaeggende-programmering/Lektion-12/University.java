import java.util.List;
import java.util.ArrayList;

public class University {
    List<Student> students;
    List<Teacher> teachers;
    
    public University() {
        students = new ArrayList<Student>();
        teachers = new ArrayList<Teacher>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }
    
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void show() {
        for (Student student : students) {
            student.display();
            System.out.println();
        }
        for (Teacher teacher : teachers) {
            teacher.display();
            System.out.println();
        }
    }
}