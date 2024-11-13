
package rental;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Host extends Person {
    private List<Property> managedProperties = new ArrayList<>();
    private List<Person> cooperatingOwners = new ArrayList<>();
    private List<RentalAgreement> rentalAgreements = new ArrayList<>();

    public Host(int id, String fullName, String contactInfo, Date birthday, String role) {
        super(id, fullName, contactInfo, birthday, role);
    }

    // Add a property to the managed properties list
    public void addProperty(Property property) {
        managedProperties.add(property);
    }

    // Add a rental agreement to the rental agreements list
    public void addRentalAgreement(RentalAgreement agreement) {
        rentalAgreements.add(agreement);
    }

    // Add a cooperating owner to the cooperating owners list
    public void addCooperatingOwner(Person owner) {
        cooperatingOwners.add(owner);
    }

    // Getters
    public List<Property> getManagedProperties() {
        return managedProperties;
    }

    public List<Person> getCooperatingOwners() {
        return cooperatingOwners;
    }

    public List<RentalAgreement> getRentalAgreements() {
        return rentalAgreements;
    }

    // Setters
    public void setManagedProperties(List<Property> managedProperties) {
        this.managedProperties = managedProperties;
    }

    public void setCooperatingOwners(List<Person> cooperatingOwners) {
        this.cooperatingOwners = cooperatingOwners;
    }

    public void setRentalAgreements(List<RentalAgreement> rentalAgreements) {
        this.rentalAgreements = rentalAgreements;
    }

    // Other host-specific methods
    @Override
    public String toString() {
        return "Host{" +
                "managedProperties=" + managedProperties +
                ", cooperatingOwners=" + cooperatingOwners +
                ", rentalAgreements=" + rentalAgreements +
                '}';
    }
}
