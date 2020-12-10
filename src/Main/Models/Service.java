package Main.Models;

public class Service implements Identifier {
    private String name,permission;
    double price;
    private int id;

    public Service(int id, String name , double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Service(int id, String name , double price,String permission) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getID() {
        return String.valueOf(id);
    }
}
