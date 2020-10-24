package Main.Models;

public class Service {
    private String id, name, description;
    double price;

    public Service(String id, String name , double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
