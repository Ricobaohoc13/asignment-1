package rental;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class UI {
    private List<Person> people = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "information.dat";

    public static void main(String[] args) {
        UI manager = new UI();
        manager.run();
    }

    public void run() {
        loadFromFile();  // Load data at startup

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
                    case 1 -> addPerson(roleString);
                    case 2 -> deletePerson();
                    case 3 -> updatePerson();
                    case 4 -> viewAllPeople();
                    case 5 -> displayFile(); // New option to display file contents
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

    private void addPerson(String role) {
        Person person = new Person(0, "", "", new Date(), role);
        person.inputId();
        person.inputFullName();
        person.inputBirthday();
        person.inputContactInfo();

        people.add(person);  // Add to the list
        saveToFile();        // Save immediately to file
        System.out.println(role + " added successfully.");
    }

    private void deletePerson() {
        System.out.print("Enter the ID of the person to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        Iterator<Person> iterator = people.iterator();
        boolean found = false;
        while (iterator.hasNext()) {
            Person person = iterator.next();
            if (person.getId() == id) {
                iterator.remove();
                found = true;
                break;
            }
        }
        if (found) {
            saveToFile();
            System.out.println("Person deleted successfully.");
        } else {
            System.out.println("Person not found.");
        }
    }

    private void updatePerson() {
        System.out.print("Enter the ID of the person to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        for (Person person : people) {
            if (person.getId() == id) {
                System.out.println("Updating person: " + person);
                person.inputFullName();
                person.inputBirthday();
                person.inputContactInfo();

                saveToFile();
                System.out.println("Person updated successfully.");
                return;
            }
        }
        System.out.println("Person not found.");
    }

    private void viewAllPeople() {
        if (people.isEmpty()) {
            System.out.println("No people to display.");
        } else {
            for (Person person : people) {
                System.out.println(person);
            }
        }
    }

    private void saveToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {  // Overwrite file each time
            for (Person person : people) {
                writer.write(person.getId() + ", " + person.getFullName() + ", " + person.getRole() + ", "
                        + new SimpleDateFormat("yyyy-MM-dd").format(person.getBirthday()) + ", " + person.getContactInfo() + "\n");
            }
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file.");
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No data file found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Person person = parsePersonFromFile(line);
                if (person != null) {
                    people.add(person);  // Add only if parsing was successful
                } else {
                    System.out.println("Failed to parse line: " + line);
                }
            }
            System.out.println("Data loaded from file.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading from file.");
            e.printStackTrace();
        }
    }

    private Person parsePersonFromFile(String line) {
        String[] parts = line.split(", ");
        if (parts.length == 5) {  // Verify correct format with all parts present
            try {
                int id = Integer.parseInt(parts[0].trim());
                String fullName = parts[1].trim();
                String role = parts[2].trim();
                Date birthday = new SimpleDateFormat("yyyy-MM-dd").parse(parts[3].trim());
                String contactInfo = parts[4].trim();

                return new Person(id, fullName, contactInfo, birthday, role);
            } catch (NumberFormatException | ParseException e) {
                System.out.println("Error parsing line: " + line);
                e.printStackTrace();
            }
        }
        System.out.println("Invalid line format: " + line);
        return null;  // Return null if parsing fails
    }

    // Method to display the file contents directly
    private static void displayFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        try {
            System.out.println("\nContents of " + FILE_NAME + ":");
            Files.lines(Paths.get(FILE_NAME)).forEach(System.out::println);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }
    }
}
