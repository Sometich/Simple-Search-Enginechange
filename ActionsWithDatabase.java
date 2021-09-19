package search;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ActionsWithDatabase {
    private static final List<String> dataBase = new ArrayList<>();
    private static final HashMap<String, List<Integer>> indexArray = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);


    public static void initDatabase(String path) {
        File file = new File(path);
        try (Scanner scannerFile = new Scanner(file)) {
            while (scannerFile.hasNext()) {
                dataBase.add(scannerFile.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        makeIndexArray();
    }

    public static void makeIndexArray() {
        for (int i = 0; i < dataBase.size(); i++) {
            String[] oneTimeArray = dataBase.get(i).toLowerCase(Locale.ROOT).split(" ");
            for (String oneTimeElements : oneTimeArray) {
                if (!indexArray.containsKey(oneTimeElements)) {
                    List<Integer> oneTimeListArray = new ArrayList<>();
                    oneTimeListArray.add(i);
                    indexArray.put(oneTimeElements, oneTimeListArray);
                } else {
                    indexArray.get(oneTimeElements).add(i);
                }
            }
        }
    }

    public static void method_1(String choice) {
        Pattern pattern = Pattern.compile(choice);
        List<String> localBase = new ArrayList<>();

        for (String element : dataBase) {
            Matcher matcher = pattern.matcher(element);
            if (matcher.find()) {
                localBase.add(element);
            }
        }

        if (localBase.size() > 0) {
            System.out.println(localBase.size() + " persons found:");
            localBase.forEach(System.out::println);
        } else {
            System.out.println("No matching people found.");
        }
    }

    public static void method_2(String choice) {
        Set<String> newSet = new HashSet<>();
        String[] array = choice.split(" ");
        for (String element : array) {
            newSet.addAll(findMemberOfDataBase(element));
        }
        if (newSet.size() > 0) {
            System.out.println(newSet.size() + " persons found:");
            newSet.forEach(System.out::println);
        } else {
            System.out.println("No matching people found.");
        }
    }

    public static void method_3(String choice) {
        Set<String> mainSet = new HashSet<>(dataBase);
        Set<String> exClude = new HashSet<>();
        String[] array = choice.split(" ");
        for (String element : array) {
            exClude.addAll(findMemberOfDataBase(element));
        }
        mainSet.removeAll(exClude);
        if (exClude.size() > 0) {
            System.out.println(mainSet.size() + " persons found:");
            mainSet.forEach(System.out::println);
        } else {
            System.out.println("No matching people found.");
        }
    }

    public static Set<String> findMemberOfDataBase(String choice) {
        Set<String> localSet = new HashSet<>();
        if (indexArray.containsKey(choice)) {
            System.out.println(indexArray.get(choice).size() + " persons found:");
            indexArray.get(choice).forEach(a -> localSet.add(dataBase.get(a)));
        }
        return localSet;
    }

    public static void findPerson() {
        System.out.println("Select a matching strategy: ALL, ANY, NONE");
        String choiceVariable = scanner.nextLine();
        System.out.println("Enter a name or email to search all suitable people.");
        String value = scanner.nextLine().toLowerCase(Locale.ROOT);

        switch (choiceVariable) {
            case "ALL":
                method_1(value);
                break;
            case "ANY":
                method_2(value);
                break;
            case "NONE":
                method_3(value);
                break;
        }
    }

    public static void printAllPerson() {
        dataBase.forEach(System.out::println);
    }
}
