import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Siblings {

    public static boolean areSiblings(File f1, File f2) {
        return f1.getParent().equals(f2.getParent());
    }

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("F:\\Users\\Айвен\\Downloads\\numb.txt");
        Scanner scanner = new Scanner(file);
        int i = scanner.nextInt();
        int counter = 0;
        while (scanner.hasNext() && i != 0) {
            if (i % 2 == 0) {
                counter++;
            }
            i = scanner.nextInt();
        }
        System.out.println(counter);
    }
}