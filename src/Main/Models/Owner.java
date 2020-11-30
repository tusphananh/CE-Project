package Main.Models;

// Main.Main.Models.Owner Class
public class Owner {
    private int id;
    private String name, phone,membership,identifier;
    private int coins;

    public Owner(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    // toString here
    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
