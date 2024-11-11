package rental;

import java.util.Date;

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
}
