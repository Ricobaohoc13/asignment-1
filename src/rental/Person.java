package rental;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Person {
    private int id;
    private String name;
    private String contactInfo;
    private Date birthday;
    private String role;

    // Constructor
    public Person(int id, String name, String contactInfo, Date birthday, String role) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
        this.birthday = birthday;
        this.role = role;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getRole() {
        return role;
    }

    // Static method to load persons from file based on role
    public static List<Person> loadFromFile(String fileName) {
        List<Person> people = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String contactInfo = data[2];
                Date birthday = dateFormat.parse(data[3]);
                String role = data[4];
                people.add(new Person(id, name, contactInfo, birthday, role));
            }
        } catch (Exception e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        }
        return people;
    }

    // Static method to add a person to a file
    public static void addPerson(Person person, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String personData = person.getId() + "," + person.getName() + "," + person.getContactInfo() + ","
                    + dateFormat.format(person.getBirthday()) + "," + person.getRole();
            writer.write(personData);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving person to file: " + e.getMessage());
        }
    }

    // Static method to update a person's details in the file
    public static void updatePerson(int id, String newName, String newContact, String fileName) {
        List<Person> people = loadFromFile(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            boolean updated = false;
            for (Person person : people) {
                if (person.getId() == id) {
                    person.name = newName;
                    person.contactInfo = newContact;
                    updated = true;
                }
                // Write the updated or unchanged person data back to the file
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                writer.write(person.getId() + "," + person.getName() + "," + person.getContactInfo() + ","
                        + dateFormat.format(person.getBirthday()) + "," + person.getRole());
                writer.newLine();
            }
            if (!updated) {
                System.out.println("Person with ID " + id + " not found.");
            }
        } catch (IOException e) {
            System.out.println("Error updating person in file: " + e.getMessage());
        }
    }

    // Static method to delete a person by ID from the file
    public static void deletePerson(int id, String fileName) {
        List<Person> people = loadFromFile(fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            boolean deleted = false;
            for (Person person : people) {
                if (person.getId() != id) {
                    // Write the remaining persons back to the file
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    writer.write(person.getId() + "," + person.getName() + "," + person.getContactInfo() + ","
                            + dateFormat.format(person.getBirthday()) + "," + person.getRole());
                    writer.newLine();
                } else {
                    deleted = true;
                }
            }
            if (!deleted) {
                System.out.println("Person with ID " + id + " not found.");
            }
        } catch (IOException e) {
            System.out.println("Error deleting person from file: " + e.getMessage());
        }
    }

    // Static method to display all people in the specified file
    public static void displayPeople(String fileName) {
        List<Person> people = loadFromFile(fileName);
        if (people.isEmpty()) {
            System.out.println("No people found in " + fileName);
        } else {
            for (Person person : people) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println("ID: " + person.getId() + ", Name: " + person.getName() + ", Contact Info: "
                        + person.getContactInfo() + ", Birthday: " + dateFormat.format(person.getBirthday())
                        + ", Role: " + person.getRole());
            }
        }
    }
}
