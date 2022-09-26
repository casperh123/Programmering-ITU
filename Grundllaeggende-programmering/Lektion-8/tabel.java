import java.util.*;

public class tabel {
    private static int[] tabel(int number) {
        int[] tabel = new int[10];

        for(int i = 0; i < 10; i++) {
            tabel[i] = number * (i + 1);
        }

        return tabel;
    }

    private static String[] toArray(List<String> names)
    {

        String[] namesArray = new String[names.size()]; 

        for(int i = 0; i < names.size(); i++) {
            namesArray[i] = names.get(i);
        }

        return namesArray;

    }

    private static int[] removeEven(int[] numbers) {

        int arraySize = numbers.length;
        int newArrayLength = arraySize;

        for (int number : numbers) {
            if(number % 2 == 0) {
                newArrayLength--;
            }
        }

        int[] unevenArray = new int[newArrayLength];
        int unevenIndex = 0;

        for (int i = 0; i < arraySize; i++ ) {
            if(numbers[i] % 2 != 0) {
                unevenArray[unevenIndex] = numbers[i]; 
                unevenIndex++;
            }
        }

        return unevenArray;
    }

    private static List<Integer> evenRemover (List<Integer> numbers) {

        for(int i = 0; i < numbers.size(); i++) {
            if(numbers.get(i) % 2 == 0) {
                numbers.remove(i);
                i--;
            }
        }

        return numbers;
    }
    
    private static void removeLecturers(List<String> list) {
        
    }

    static void printUneven() {

        Scanner input = new Scanner(System.in);

        System.out.println("Typen af input: ");
        String inputType = input.nextLine();

        System.out.println("Mængde af input: ");
        int testCases = input.nextInt();

        if(inputType.equals("Array")) {
            
            int[] numberArray = new int[testCases];

            for(int i = 0; i < testCases; i++) {
                numberArray[i] = input.nextInt();
            }

            int[] unevenArray = removeEven(numberArray);

            for(int number : unevenArray) {
                System.out.println(number);
            }  
        } else {
            
            System.out.println("Arraylist Chosen");
            
            List<Integer> numberArray = new ArrayList<>();

            for(int i = 0; i < testCases; i++) {
                numberArray.add(input.nextInt());
            }

            numberArray = evenRemover(numberArray);

            for(int number : numberArray) {
                System.out.println(number);
            }  
            
        }
    }

    static void printArray()
    {

        Scanner input = new Scanner(System.in);

        List<String> names = new ArrayList<>();
        int testCases = input.nextInt();

        for (int i = 0; i < testCases; i++) {
            names.add(input.next());
        }

        String[] nameArray = toArray(names);

        for (String name : nameArray) {
            System.out.println(name);
        }
    }

    static void printTabel() {
        Scanner input = new Scanner(System.in);
        int[] syvTabel = tabel(input.nextInt());
        for (int tal : syvTabel) {
            System.out.println(tal);
        }

        input.close();
    }

}
