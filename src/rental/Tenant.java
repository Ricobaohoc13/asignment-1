package rental;

import java.util.Date;

public class Tenant extends Person{
    public Tenant(int id, String fullName, String contactInfo, Date birthday) {
        super(id, fullName, contactInfo, birthday);
    }
}
