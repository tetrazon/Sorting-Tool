import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int z = 'z';
        char mystery = (char) (z - 10);
        System.out.println(mystery);
        Scanner scanner = new Scanner(System.in);
        double firstDouble = scanner.nextDouble();
        double secondDouble = scanner.nextDouble();
        System.out.println(secondDouble - firstDouble);
    }
}