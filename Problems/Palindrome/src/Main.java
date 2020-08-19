
import java.io.File;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        System.out.println(File.separator);
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                System.out.println("no");
                return;
            }
        }
        System.out.println("yes");
    }
}