package rental;

import java.util.List;

public class ResidentialProperty extends Property{
    private int numberOfBedrooms;
    private boolean hasGarden;
    private boolean petFriendly;
    public ResidentialProperty(int propertyId, String address, double pricing, String status, Person owner, List<Host> hosts)
    {
        super(propertyId, address , pricing, status, owner, hosts);
    }
}
