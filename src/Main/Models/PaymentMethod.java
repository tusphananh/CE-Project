package Main.Models;

public abstract class PaymentMethod {
    String id, name;

    PaymentMethod(String id) {
        this.id = id;
    }
}
