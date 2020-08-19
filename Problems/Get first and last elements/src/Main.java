import java.util.*;

public class Main {

    private static int[] getFirstAndLast(int[] array) {
        int[] result = new int[2];
        result[0] = array[0];
        result[1] = array[array.length-1];
        return result;

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] result = getFirstAndLast(array);
        Arrays.stream(result).forEach(e -> System.out.print(e + " "));
    }
}

class NonGenericClass {

    private Object val;

    public NonGenericClass(Object val) {
        this.val = val;
    }

    public Object get() {
        return val;
    }

    public static void main(String[] args) {
        NonGenericClass instance = new NonGenericClass("Hello");
        Object obj = instance.get();

        Integer num = (Integer) instance.get();

        String str = (String) instance.get();

        //String str1 = instance.get();
    }
}