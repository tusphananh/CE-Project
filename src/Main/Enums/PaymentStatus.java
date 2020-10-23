package Main.Enums;

public enum PaymentStatus {
    pending("Pending"), success("Success");
    private final String values;

    PaymentStatus(String values) {
        this.values = values;
    }

    public String getValues() {
        return values;
    }
}
