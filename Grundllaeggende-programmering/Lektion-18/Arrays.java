public class Arrays
{
    public Arrays() {
    }
    
    public void arrayTest() {
        
        int[] numbers = new int[3];
              numbers = new int[] {4,5,6};
        
        int numberAtIndex = numbers[0]; //number = 4
        numbers[2] = 10; //Index 2 = 10
        
        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = numbers[i] + 1;
        }
        
        for(int number : numbers) {
            System.out.println(number);
        }
        
    }
}
