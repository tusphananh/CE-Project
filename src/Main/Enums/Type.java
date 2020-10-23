package Main.Enums;

public enum Type {
    vip("V.I.P"), normal("Normal");
    private String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
