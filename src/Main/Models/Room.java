package Main.Models;

// Main.Main.Models.Room Class
public class Room implements Identifier {
    private int id;
    private String name;
    public long capacity;
    public double price;
    public double sale;
    public String type;
    public String images;
    private String status;

    public Room(int id,String name,String status, String type, long capacity, double price, double sale,String images) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        setPrice(price);
        this.type = type;
        this.images = images + ".png";
    }

    public String getName() {
        return name;
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
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                ", sale=" + sale +
                ", type=" + type +
                ", images='" + images + '\'' +
                '}';
    }

    @Override
    public String getID() {
        return String.valueOf(id);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public double getPrice() {
        return price;
    }

    public long getCapacity() {
        return capacity;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

}
