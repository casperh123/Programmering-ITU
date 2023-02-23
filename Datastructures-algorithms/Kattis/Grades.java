import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Merge;

public class Grades {
    public static void main(String[] args) {

        int iterations = Integer.parseInt(StdIn.readLine());
        String[] studentGrades = new String[iterations];

        for (int i = 0; i < iterations; i++) {
            
            String[] inputString = StdIn.readLine().split(" ");
            String gradeString = inputString[1];
            StringBuilder sortableGradeString = new StringBuilder();
            int grade = 0;

            if(gradeString.length() >= 2 && gradeString.contains("FX")) {
                grade =6000;
            } else {
                switch(gradeString.charAt(0)) {
                    case 'A':
                        grade = 1000;
                        break;
                    case 'B':
                        grade = 2000;
                        break;
                    case 'C':
                        grade = 3000;
                        break;
                    case 'D':
                        grade = 4000;
                        break;
                    case 'E':
                        grade = 5000;
                        break;
                    case 'F':
                        grade = 7000;
                        break;
                }
            }

            if(gradeString.length() > 2) {
                grade = gradeModifier(grade, gradeString);
            }

            sortableGradeString.append(grade);
            sortableGradeString.append(" ");
            sortableGradeString.append(inputString[0]);

            studentGrades[i] = sortableGradeString.toString();
        }

        Merge.sort(studentGrades);

        for(String studentGrade : studentGrades) {
            int spacePosition = 0;
            for(int i = 0; i < studentGrade.length(); i++) {
                if(studentGrade.charAt(i) == ' ') {
                    spacePosition = i + 1;
                }
            }
            System.out.println(studentGrade.substring(spacePosition, studentGrade.length()));
        }
        
    }

    public static int gradeModifier(int unmodifiedGrade, String gradeString) {

        int grade = unmodifiedGrade;

        for(int i = 0; i < gradeString.length(); i++) {
            if(gradeString.charAt(i) == '+') {
                grade -= 1;
            } else if(gradeString.charAt(i) == '-') {
                grade += 1;
            }
        }

        return grade;
    }
}
