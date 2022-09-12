import java.util.Scanner;

public class LaptopSticker {
    public static void main(String[] args) {
        
        Scanner input = new Scanner(System.in);

        int laptopWidth = input.nextInt();
        int laptopHeight = input.nextInt();
        int stickerWidth = input.nextInt();
        int stickerHeight = input.nextInt();

        if (laptopWidth - stickerWidth >= 2 && laptopHeight - stickerHeight >= 2) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}
