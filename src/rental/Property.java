package rental;

import java.util.List;

public class Property {
    private int propertyId;
    private String address;
    private double pricing;
    private String status;
    private Person owner;
    private List<Host> hosts;

    // Constructor
    public Property(int propertyId, String address, double pricing, String status, Person owner, List<Host> hosts) {
        this.propertyId = propertyId;
        this.address = address;
        this.pricing = pricing;
        this.status = status;
        this.owner = owner;
        this.hosts = hosts;
    }

    // Getters
    public int getPropertyId() {
        return propertyId;
    }

    public String getAddress() {
        return address;
    }

    public double getPricing() {
        return pricing;
    }

    public String getStatus() {
        return status;
    }

    public Person getOwner() {
        return owner;
    }

    public List<Host> getHosts() {
        return hosts;
    }

    // Setters
    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPricing(double pricing) {
        this.pricing = pricing;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public void setHosts(List<Host> hosts) {
        this.hosts = hosts;
    }

    // Other shared methods
    @Override
    public String toString() {
        return "Property{" +
                "propertyId=" + propertyId +
                ", address='" + address + '\'' +
                ", pricing=" + pricing +
                ", status='" + status + '\'' +
                ", owner=" + owner +
                ", hosts=" + hosts +
                '}';
    }
}
