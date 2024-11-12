package rental;

import java.util.List;

public class CommercialProperty extends Property{
    public CommercialProperty(int propertyId, String address, double pricing, String status, Person owner, List<Host> hosts) {
        super(propertyId, address, pricing, status, owner, hosts);

    }
}
