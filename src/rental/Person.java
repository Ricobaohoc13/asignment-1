package rental;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Person {
    private int id;
    private String fullName;
    private Date birthday;
    private String contactInfo;

    public Person(int id, String fullName, String contactInfo, Date birthday) {
        this.id = id;
        this.fullName = fullName;
        this.contactInfo = contactInfo;
        this.birthday = birthday;
    }

    // Getters
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

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // Methods for console input
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
        return "ID: " + id + ", Full Name: " + fullName + ", Birthday: " + dateFormat.format(birthday) + ", Contact Info: " + contactInfo;
    }

    // Method to save information to a file
    public void saveInfo() {
        File file = new File("information.dat");
        try (FileWriter writer = new FileWriter(file, true)) { // 'true' for appending to the file
            writer.write(this.toString() + "\n"); // Write the string representation of the object to the file
            System.out.println("Information saved to " + file.getName());
        } catch (IOException e) {
            System.out.println("An error occurred while saving the information: " + e.getMessage());
        }
    }
}