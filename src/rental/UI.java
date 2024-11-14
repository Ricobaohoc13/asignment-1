package rental;

import java.util.Scanner;

public class UI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UI manager = new UI();
        manager.run();
    }

    public void run() {
        Person.loadFromFile(); // Load data at startup

        int choice;
        do {
            System.out.println("\n--- Person Management System ---");
            System.out.println("Select role:");
            System.out.println("1. Host");
            System.out.println("2. Tenant");
            System.out.println("3. Owner");
            System.out.print("Enter your role (1, 2, or 3): ");
            int role = Integer.parseInt(scanner.nextLine());
            String roleString = getRoleString(role);

            if (roleString == null) {
                System.out.println("Invalid role selected. Returning to main menu.");
                continue;
            }

            do {
                System.out.println("\n--- " + roleString + " Menu ---");
                System.out.println("1. Add Person");
                System.out.println("2. Delete Person");
                System.out.println("3. Update Person");
                System.out.println("4. View All People");
                System.out.println("5. Display File Contents");
                System.out.println("6. Exit the program");
                System.out.println("0. Back to Role Selection");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1 -> Person.addPerson(roleString);
                    case 2 -> Person.deletePerson();
                    case 3 -> Person.updatePerson();
                    case 4 -> Person.viewAllPeople();
                    case 5 -> Person.displayFile();
                    case 6 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    case 0 -> System.out.println("Returning to role selection...");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 0);
        } while (true);
    }

    private String getRoleString(int role) {
        return switch (role) {
            case 1 -> "Host";
            case 2 -> "Tenant";
            case 3 -> "Owner";
            default -> null;
        };
    }
}
