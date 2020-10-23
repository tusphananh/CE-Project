package Main.Enums;

public enum ReservedStatus {
    pending("Pending"), success("Success"), fail("Fail");

    private final String values;

    ReservedStatus(String values) {
        this.values = values;
    }

    public String getValues() {
        return values;
    }
}
