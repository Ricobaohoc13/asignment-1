package rental;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private int id;
    private String fullName;
    private String contactInfo;
    private Date birthday;
    private String role;

    // This will hold people of the current role being processed (e.g., Host, Owner, Tenant)
    private static List<Person> people = new ArrayList<>();

    public Person(int id, String fullName, String contactInfo, Date birthday, String role) {
        this.id = id;
        this.fullName = fullName;
        this.contactInfo = contactInfo;
        this.birthday = birthday;
        this.role = role;
    }

    // Load people from the file, but first clear the existing people list to prevent mixing roles
    public static void loadFromFile(String fileName, String role) {
        people.clear();  // Clear the list to avoid mixing data between roles
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int id = Integer.parseInt(parts[0].trim());
                    String fullName = parts[1].trim();
                    String contactInfo = parts[2].trim();
                    Date birthday = dateFormat.parse(parts[3].trim());
                    String fileRole = parts[4].trim();
                    // Only add people with the matching role
                    if (fileRole.equalsIgnoreCase(role)) {
                        people.add(new Person(id, fullName, contactInfo, birthday, fileRole));
                    }
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
            System.out.println(role + " data loaded successfully from " + fileName);
        } catch (IOException | ParseException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        }
    }

    // Add person, save data only for the correct role
    public static void addPerson(Person person, String role, String fileName) {
        if (!person.role.equalsIgnoreCase(role)) {
            System.out.println("Role mismatch. Unable to add person.");
            return;
        }
        people.add(person);
        saveToFile(fileName);  // Save only for the current file
    }

    // Update person, saving only the updated list of people for the correct role
    public static void updatePerson(int id, String newName, String newContact, String role, String fileName) {
        for (Person person : people) {
            if (person.id == id && person.role.equalsIgnoreCase(role)) {
                person.fullName = newName;
                person.contactInfo = newContact;
                System.out.println("Updated " + role + ": " + person);
                saveToFile(fileName);  // Save the list back to the file after update
                return;
            }
        }
        System.out.println(role + " with ID " + id + " not found.");
    }

    // Delete person, removing the person from the list and saving back the correct data
    public static void deletePerson(int id, String role, String fileName) {
        boolean removed = people.removeIf(person -> person.id == id && person.role.equalsIgnoreCase(role));
        if (removed) {
            System.out.println(role + " with ID " + id + " deleted successfully.");
            saveToFile(fileName);  // Save after deletion
        } else {
            System.out.println(role + " with ID " + id + " not found.");
        }
    }

    // Save the list of people to the specified file
    private static void saveToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Person person : people) {
                writer.write(person.id + "," + person.fullName + "," + person.contactInfo + "," +
                        dateFormat.format(person.birthday) + "," + person.role);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + fullName + ", Contact: " + contactInfo +
                ", Birthday: " + dateFormat.format(birthday) + ", Role: " + role;
    }

}
