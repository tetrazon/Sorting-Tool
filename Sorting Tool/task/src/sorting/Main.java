package sorting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

enum SortingType {NATURAL, BY_COUNT}
enum DataType {WORD, LINE, LONG}

public class Main {
    public static void main(final String[] args) throws Exception {
        SortingType sortingType = null;
        DataType dataType = null;
        Scanner scanner = new Scanner(System.in);
        FileWriter fileWriter = null;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-inputFile":
                    scanner = new Scanner(args[i + 1]);
                    break;
                case "-outputFile":
                    fileWriter = new FileWriter(new File(args[i + 1]));

            }
        }

        for (int i = 0; i < args.length; i++) {

            switch (args[i]) {
                case "-sortingType":
                    if (i == args.length -1) {
                        if (fileWriter != null) {
                            fileWriter.write("No sorting type defined!\n");
                        } else {
                            System.out.println("No sorting type defined!");
                        }
                        System.exit(0);
                    }
                    switch (args[i + 1]) {
                        case "natural":
                            sortingType = SortingType.NATURAL;
                            break;
                        case "byCount":
                            sortingType = SortingType.BY_COUNT;
                            break;
                        default:
                            if (fileWriter != null) {
                                fileWriter.write("No sorting type defined!\n");
                            } else {
                                System.out.println("No sorting type defined!");
                            }
                            System.exit(0);
                    }
                    break;
                case "-dataType":
                    if (i == args.length -1) {
                        if (fileWriter != null) {

                            fileWriter.write("No data type defined!\n");
                        } else {
                            System.out.println("No data type defined!");
                        }
                        System.exit(0);
                    }
                    switch (args[i + 1]) {
                        case "long":
                            dataType = DataType.LONG;
                            break;
                        case "word":
                            dataType = DataType.WORD;
                            break;
                        case "line":
                            dataType = DataType.LINE;
                            break;
                        default:
                            if (fileWriter != null) {

                                fileWriter.write("No data type defined!\n");
                            } else {
                                System.out.println("No data type defined!");
                            }
                            System.exit(0);
                    }
                    break;
                default:
                    if (args[i].startsWith("-")) {
                        if (fileWriter != null) {
                            fileWriter.write("\"" + args[i] + "\"" + " isn't a valid parameter. It's skipped.\n");
                        } else {
                            System.out.println("\"" + args[i] + "\"" + " isn't a valid parameter. It's skipped.");
                        }
                    }

            }


        }

        if (dataType == null) {
            dataType = DataType.WORD;
        }
        if (sortingType == null) {
            sortingType = SortingType.NATURAL;
        }

       /* SortingType sortingType = Arrays.stream(args).noneMatch(a -> a.equals("-sortingType")) ? SortingType.NATURAL
                : args[1].equals("natural") ? SortingType.NATURAL
                : args[1].equals("byCount") ? SortingType.BY_COUNT
                : args.length == 4 ? args[3].equals("byCount") ? SortingType.BY_COUNT : SortingType.NATURAL
                : SortingType.NATURAL;

        DataType dataType = args[1].equals("word") || args[args.length-1].equals("word") ? DataType.WORD
                : args[1].equals("line") || args[args.length-1].equals("line") ? DataType.LINE
                : args[1].equals("long") || args[args.length-1].equals("long") ? DataType.LONG
                : DataType.WORD;*/

        switch (dataType) {
            case LINE:
                sortLine(sortingType, scanner);
                break;
            case WORD:
                sortWord(sortingType, scanner);
                break;
            case LONG:
                sortLong(sortingType, scanner, fileWriter);
                break;

        }

    }

    private static void sortWord(SortingType sortingType, Scanner scanner) {
        List<String> stringList = new ArrayList<>();
        while (scanner.hasNext()) {
            stringList.add(scanner.next().trim());
        }
        switch (sortingType) {
            case NATURAL:
                stringList.sort(String::compareTo);
                System.out.println("Total words: " + stringList.size());
                System.out.print("Sorted data:");
                stringList.forEach(s -> System.out.print(" " + s));
                break;
            case BY_COUNT:
                Map<String, Long> stringLongMap = stringList
                        .stream()
                        .collect(Collectors.groupingBy(aLong -> aLong, Collectors.counting()));

                System.out.println("Total words: " + stringList.size() + ".");

                stringLongMap
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.<String, Long>comparingByValue()
                                .thenComparing(Map.Entry.comparingByKey()))
                        .forEach(e -> System.out.println(e.getKey()
                                + ": " + e.getValue() + " time(s), "
                                + e.getValue() * 100 / stringList.size() + "%"));
                break;
        }
    }

    private static void sortLine(SortingType sortingType, Scanner scanner) {
        List<String> stringList = new ArrayList<>();
        while (scanner.hasNext()) {
            stringList.add(scanner.nextLine());
        }
        switch (sortingType) {
            case NATURAL:
                stringList.sort(String::compareTo);
                System.out.println("Total lines: " + stringList.size());
                System.out.println("Sorted data:");
                stringList.forEach(System.out::println);
                break;
            case BY_COUNT:
                Map<String, Long> stringLongMap = stringList
                        .stream()
                        .collect(Collectors.groupingBy(aLong -> aLong, Collectors.counting()));

                System.out.println("Total lines: " + stringList.size());

                stringLongMap
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.<String, Long>comparingByValue()
                                .thenComparing(Map.Entry.comparingByKey()))
                        .forEach(e -> System.out.println(e.getKey()
                                + ": " + e.getValue() + " time(s), "
                                + e.getValue() * 100 / stringList.size() + "%"));
                break;
        }
    }

    private static void sortLong(SortingType sortingType, Scanner scanner, FileWriter fileWriter) throws IOException {
        List<Long> longList = new ArrayList<>();
        String tempString;
        while (scanner.hasNext()) {
            tempString = scanner.next().trim();
            try {
                long number = Long.parseLong(tempString);
                longList.add(number);
            } catch (NumberFormatException e) {
                if (fileWriter != null) {
                    fileWriter.write("\"" + tempString + "\"" + " isn't a long. It's skipped.\n");
                } else {
                    System.out.println("\"" + tempString + "\"" + " isn't a long. It's skipped.");
                }
            }

        }
        switch (sortingType) {
            case NATURAL:
                System.out.println("Total numbers: " + longList.size());
                longList.sort(Long::compareTo);
                System.out.print("Sorted data:");
                longList.forEach(a -> System.out.print(" " + a));
                break;
            case BY_COUNT:
                Map<Long, Long> longIntegerMap = longList
                        .stream()
                        .collect(Collectors.groupingBy(aLong -> aLong, Collectors.counting()));

                System.out.println("Total numbers: " + longList.size() + ".");

                longIntegerMap
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue())
                        .forEach(e -> System.out.println(e.getKey()
                                        + ": " + e.getValue() + " time(s), "
                                        + e.getValue() * 100 / longList.size() + "%"));
                break;
        }

    }

}
