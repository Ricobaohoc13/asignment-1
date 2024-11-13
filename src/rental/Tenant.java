package rental;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tenant extends Person{
    public Tenant(int id, String fullName, String contactInfo, Date birthday, String role) {
        super(id, fullName, contactInfo, birthday, role);
    }
    private List<RentalAgreement> rentalAgreements = new ArrayList<>();
    private List<Payment> paymentTransactions = new ArrayList<>();

    public void addRentalAgreement(RentalAgreement agreement) {
        rentalAgreements.add(agreement);
    }

    public void addPayment(Payment payment) {
        paymentTransactions.add(payment);
    }

}
