package search;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ActionsWithDatabase.initDatabase(args[1]);

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("=== Menu ===\n" +
                    "1. Find a person\n" +
                    "2. Print all people\n" +
                    "0. Exit");

            int selectMenu = Integer.parseInt(scanner.nextLine());
            switch (selectMenu) {
                case 0:
                    flag = false;
                    break;
                case 1:
                    ActionsWithDatabase.findPerson();
                    break;
                case 2:
                    ActionsWithDatabase.printAllPerson();
                    break;
                default:
                    System.out.println("Incorrect option! Try again.");
                    break;
            }
        }
        System.out.println("Bye!");
        scanner.close();
    }
}