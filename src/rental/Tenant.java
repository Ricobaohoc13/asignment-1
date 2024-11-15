package rental;

import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

public class Tenant extends Person {
    private List<String> rentalAgreements;

    // Constructor
    public Tenant(int id, String fullName, Date dateOfBirth, String contactInfo) {
        super(id, fullName, contactInfo, dateOfBirth, contactInfo);
        this.rentalAgreements = new ArrayList<>();
    }

    // Getter and Setter for rental agreements
    public List<String> getRentalAgreements() {
        return rentalAgreements;
    }

    public void addRentalAgreement(String agreement) {
        rentalAgreements.add(agreement);
    }

    @Override
    public String toString() {
        return super.toString() + ", Rental Agreements=" + rentalAgreements;
    }
}
