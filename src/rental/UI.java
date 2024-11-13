package rental;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Iterator;

public class UI {
    private List<Person> people = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UI manager = new UI();
        manager.run();
    }

    public void run() {
        // Load existing data from file before the main menu
        loadFromFile();

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
                System.out.println("5. Exit the program");
                System.out.println("0. Back to Role Selection");
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        addPerson(roleString);
                        break;
                    case 2:
                        deletePerson();
                        break;
                    case 3:
                        updatePerson();
                        break;
                    case 4:
                        viewAllPeople();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    case 0:
                        System.out.println("Returning to role selection...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
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

        // Save immediately after contact info is entered
        people.add(person);
        saveToFile();
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

                // Save immediately after updating contact info
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
        try (FileWriter writer = new FileWriter(new File("information.dat"), false)) {  // Overwrite file each time
            for (Person person : people) {
                writer.write(person.toString() + "\n");  // Write each person's info to the file
            }
            System.out.println("Data saved to file.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving to file.");
            e.printStackTrace();
        }
    }

    private void loadFromFile() {
        File file = new File("information.dat");
        if (!file.exists()) {
            System.out.println("No data file found.");
            return;  // No data to load, just return
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Parse each line into a Person object and add to the people list
                Person person = parsePersonFromFile(line);
                people.add(person);
            }
            System.out.println("Data loaded from file.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading from file.");
            e.printStackTrace();
        }
    }

    private Person parsePersonFromFile(String line) {
        // A simple parsing method. You may need to adjust based on your file format.
        String[] parts = line.split(", ");  // Assuming your format is like: "ID: 1, Full Name: John Doe, Role: Host, ..."

        if (parts.length == 5) {
            int id = Integer.parseInt(parts[0].split(": ")[1]);
            String fullName = parts[1].split(": ")[1];
            String role = parts[2].split(": ")[1];
            String birthdayStr = parts[3].split(": ")[1];
            String contactInfo = parts[4].split(": ")[1];

            // Convert the birthday string to a Date object
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date birthday = null;
            try {
                birthday = dateFormat.parse(birthdayStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return new Person(id, fullName, contactInfo, birthday, role);
        }

        return null;  // Return null if parsing fails
    }
}
