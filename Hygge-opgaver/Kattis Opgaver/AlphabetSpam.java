import java.util.*;


public class AlphabetSpam {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        String inputString = input.next();
        int stringLength = inputString.length();


        Map<String, Integer> ratioData = new HashMap<>();

        ratioData.put("whiteSpaces", 0);
        ratioData.put("lowerCase", 0);
        ratioData.put("upperCase", 0);
        ratioData.put("symbols", 0);

        for(char index : inputString.toCharArray()) {
            
            if (index == '_') {

                ratioData.merge("whiteSpaces", 1, (a, b) -> a + b);
            } else if(Character.isLowerCase(index)) {
            
                ratioData.merge("lowerCase", 1, (a, b) -> a + b);
            
            } else if (Character.isUpperCase(index)) {
                
                ratioData.merge("upperCase", 1, (a,b) -> a + b);
            
            } else {
                ratioData.merge("symbols", 1, (a, b) -> a + b);
            }
        }

        System.out.println((double) ratioData.get("whiteSpaces") / stringLength);
        System.out.println((double) ratioData.get("lowerCase") / stringLength);
        System.out.println((double) ratioData.get("upperCase") / stringLength);
        System.out.println((double) ratioData.get("symbols") / stringLength);
    }
}