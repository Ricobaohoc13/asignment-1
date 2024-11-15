package rental;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Load sample data from files for each role
        Person.loadFromFile("Host.dat");
        Person.loadFromFile("Owner.dat");
        Person.loadFromFile("Tenant.dat");

        int choice;
        do {
            System.out.println("\n--- Person Management System ---");
            System.out.println("1. Add Host/Tenant/Owner");
            System.out.println("2. Update Host/Tenant/Owner");
            System.out.println("3. Delete Host/Tenant/Owner");
            System.out.println("4. Display Host/Tenant/Owner");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addPerson();
                    break;
                case 2:
                    updatePerson();
                    break;
                case 3:
                    deletePerson();
                    break;
                case 4:
                    displayPeople();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private static void addPerson() {
        System.out.println("\nChoose Role to Add:");
        System.out.println("1. Host");
        System.out.println("2. Owner");
        System.out.println("3. Tenant");
        System.out.print("Enter your choice: ");
        int roleChoice = Integer.parseInt(scanner.nextLine());

        String role = null;
        String fileName = null;
        switch (roleChoice) {
            case 1:
                role = "Host";
                fileName = "Host.dat";
                break;
            case 2:
                role = "Owner";
                fileName = "Owner.dat";
                break;
            case 3:
                role = "Tenant";
                fileName = "Tenant.dat";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter Name: ");
            String fullName = scanner.nextLine();
            System.out.print("Enter Contact Info: ");
            String contactInfo = scanner.nextLine();

            System.out.print("Enter Birthday (yyyy-MM-dd): ");
            String birthdayString = scanner.nextLine();

            // Parse the date
            if (birthdayString.isEmpty()) {
                System.out.println("Birthday cannot be empty.");
                return;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = dateFormat.parse(birthdayString);

            // Add new person to the correct list and save to the file
            Person person = new Person(id, fullName, contactInfo, birthday, role);
            Person.addPerson(person, fileName);
            System.out.println(role + " added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding person: " + e.getMessage());
        }
    }

    private static void updatePerson() {
        System.out.println("\nChoose Role to Update:");
        System.out.println("1. Host");
        System.out.println("2. Owner");
        System.out.println("3. Tenant");
        System.out.print("Enter your choice: ");
        int roleChoice = Integer.parseInt(scanner.nextLine());

        String role = null;
        String fileName = null;
        switch (roleChoice) {
            case 1:
                role = "Host";
                fileName = "Host.dat";
                break;
            case 2:
                role = "Owner";
                fileName = "Owner.dat";
                break;
            case 3:
                role = "Tenant";
                fileName = "Tenant.dat";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.print("Enter the ID of the " + role + " to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new Name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new Contact Info: ");
        String newContact = scanner.nextLine();

        // Update person in the specified role file
        Person.updatePerson(id, newName, newContact, fileName);
        System.out.println(role + " updated successfully.");
    }

    private static void deletePerson() {
        System.out.println("\nChoose Role to Delete:");
        System.out.println("1. Host");
        System.out.println("2. Owner");
        System.out.println("3. Tenant");
        System.out.print("Enter your choice: ");
        int roleChoice = Integer.parseInt(scanner.nextLine());

        String role = null;
        String fileName = null;
        switch (roleChoice) {
            case 1:
                role = "Host";
                fileName = "Host.dat";
                break;
            case 2:
                role = "Owner";
                fileName = "Owner.dat";
                break;
            case 3:
                role = "Tenant";
                fileName = "Tenant.dat";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.print("Enter the ID of the " + role + " to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        // Delete person in the specified role file
        Person.deletePerson(id, fileName);
        System.out.println(role + " deleted successfully.");
    }

    private static void displayPeople() {
        System.out.println("\nChoose Role to Display:");
        System.out.println("1. Host");
        System.out.println("2. Owner");
        System.out.println("3. Tenant");
        System.out.print("Enter your choice: ");
        int roleChoice = Integer.parseInt(scanner.nextLine());

        String role = null;
        String fileName = null;
        switch (roleChoice) {
            case 1:
                role = "Host";
                fileName = "Host.dat";
                break;
            case 2:
                role = "Owner";
                fileName = "Owner.dat";
                break;
            case 3:
                role = "Tenant";
                fileName = "Tenant.dat";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("\n--- Displaying All " + role + "s ---");
        Person.displayPeople(fileName);
    }
}
