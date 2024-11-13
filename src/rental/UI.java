package rental;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    private List<Person> people = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        UI manager = new UI();
        manager.run();
    }

    public void run() {
        int choice;

        do {
            System.out.println("\n--- Person Management System ---");
            System.out.println("1. Add Person");
            System.out.println("2. Delete Person");
            System.out.println("3. Update Person");
            System.out.println("4. View All People");
            System.out.println("5. Save All People to File");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    addPerson();
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
                    saveAllPeople();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    private void addPerson() {
        Person person = new Person(0, "", "", new Date());
        person.inputId();
        person.inputFullName();
        person.inputBirthday();
        person.inputContactInfo();
        people.add(person);
        System.out.println("Person added successfully.");
    }

    private void deletePerson() {
        System.out.print("Enter the ID of the person to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean removed = people.removeIf(person -> person.getId() == id);
        if (removed) {
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

    private void saveAllPeople() {
        for (Person person : people) {
            person.saveInfo();
        }
        System.out.println("All people saved to file.");
    }
}