public class Binary {
    
    static String bin(int n) {
        if(n < 2) return Integer.toString(n);
        return bin(n/2) + n % 2;
    }

    public static void main(String[] args) {
        System.out.println(bin(2000000));
    }
}
