package Main.Models;

// Main.Main.Models.Room Class
public class Room implements Identifier {
    private int id;
    private String name;
    public long capacity;
    public double price,salePrice;
    public long sale;
    public String type;
    public String images;
    private String status;

    public Room(int id, String name, String status, String type, long capacity, double price, long sale, String images) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.type = type;
        this.images = images;
        this.status = status;
        this.sale = sale;
        this.salePrice = price - price*50/100;
    }

    public String getName() {
        return name;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImages() {
        return images;
    }

    public double getSalePrice() {
        return salePrice;
    }

    // toString here


    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                ", salePrice=" + salePrice +
                ", sale=" + sale +
                ", type='" + type + '\'' +
                ", images='" + images + '\'' +
                ", status='" + status + '\'' +
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

    public long getSale() {
        return sale;
    }
}
