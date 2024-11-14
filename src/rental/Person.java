package rental;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Person {
    private static final List<Person> people = new ArrayList<>();
    private static final String FILE_NAME = "information.dat";
    private int id;
    private String fullName;
    private Date birthday;
    private String contactInfo;
    private String role;

    public Person(int id, String fullName, String contactInfo, Date birthday, String role) {
        this.id = id;
        this.fullName = fullName;
        this.contactInfo = contactInfo;
        this.birthday = birthday;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public String getRole() {
        return role;
    }

    public void inputId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID: ");
        this.id = Integer.parseInt(scanner.nextLine());
    }

    public void inputFullName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Full Name: ");
        this.fullName = scanner.nextLine();
    }

    public void inputBirthday() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Birthday (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();
        try {
            this.birthday = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter in yyyy-MM-dd format.");
        }
    }

    public void inputContactInfo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Contact Info: ");
        this.contactInfo = scanner.nextLine();
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "ID: " + id + ", Full Name: " + fullName + ", Role: " + role +
                ", Birthday: " + dateFormat.format(birthday) + ", Contact Info: " + contactInfo;
    }

    public static void addPerson(String role) {
        Person person = new Person(0, "", "", new Date(), role);
        person.inputId();
        person.inputFullName();
        person.inputBirthday();
        person.inputContactInfo();

        people.add(person);
        saveToFile();
        System.out.println(role + " added successfully.");
    }

    public static void deletePerson() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the person to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean found = people.removeIf(person -> person.getId() == id);

        if (found) {
            saveToFile();
            System.out.println("Person deleted successfully.");
        } else {
            System.out.println("Person not found.");
        }
    }

    public static void updatePerson() {
        Scanner scanner = new Scanner(System.in);
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

    public static void viewAllPeople() {
        if (people.isEmpty()) {
            System.out.println("No people to display.");
        } else {
            people.forEach(System.out::println);
        }
    }

    private static void saveToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
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

    public static void loadFromFile() {
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
                    people.add(person);
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

    private static Person parsePersonFromFile(String line) {
        String[] parts = line.split(", ");
        if (parts.length == 5) {
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
        return null;
    }

    public static void displayFile() {
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
