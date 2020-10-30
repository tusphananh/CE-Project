package Main.Models;

import Main.Enums.Type;

import java.util.ArrayList;

// Main.Main.Models.Room Class
public class Room implements Identifier {
    private String id;
    public long capacity;
    public double price;
    public double sale;
    public int hot;
    public Type type;
    public ArrayList<Reservation> reservations = new ArrayList<>();
    public ArrayList<Service> services = new ArrayList<>();
    public String images;

    public Room(String id, long capacity, int price, Type type) {
        this.id = id;
        this.capacity = capacity;
        setPrice(price);
        this.type = type;
    }

    public Room(String id, long capacity, int price, Type type, String images) {
        this.id = id;
        this.capacity = capacity;
        setPrice(price);
        this.type = type;
        this.images = images;
    }

    public Room(String id, long capacity, int price, Type type, String images, double sale, int hot) {
        this.id = id;
        this.capacity = capacity;
        setPrice(price);
        this.type = type;
        this.images = images;
        this.hot = hot;
        this.sale = sale;
    }

    public void setPrice(double price) {
        this.price = price - price * sale;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImages() {
        return images;
    }

    // toString here


    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                ", sale=" + sale +
                ", hot=" + hot +
                ", type=" + type +
                ", images='" + images + '\'' +
                '}';
    }

    @Override
    public String getID() {
        return id;
    }


    public double getPrice() {
        return price;
    }

    public long getCapacity() {
        return capacity;
    }


    public String getType() {
        return type.getValue();
    }

    public void addService(Service service){
        this.services.add(service);
    }

    public void dropService(Service service){
        this.services.remove(service);
    }
}
