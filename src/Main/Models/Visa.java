package Main.Models;

public class Visa extends PaymentMethod {
    String cvv, validFrom, validTo;

    Visa(String id, String cvv, String validFrom, String validTo) {
        super(id);
        this.cvv = cvv;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.name = "Visa";
    }
}
