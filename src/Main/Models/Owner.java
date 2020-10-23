package Main.Models;

// Main.Main.Models.Owner Class
public class Owner {
    String name, phone, id;

    public Owner(String name, String phone, String id) {
        this.name = name;
        this.phone = phone;
        this.id = id;
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
