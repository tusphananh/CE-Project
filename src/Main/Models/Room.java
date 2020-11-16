package Main.Models;

import java.util.ArrayList;

// Main.Main.Models.Room Class
public class Room implements Identifier {
    private String id;
    public long capacity;
    public double price;
    public double sale;
    public int hot;
    public String type;
    public ArrayList<Reservation> reservations = new ArrayList<>();
    public ArrayList<Use> uses = new ArrayList<>();
    public String images;
    private String status;

    public Room(String id, long capacity, int price, String type) {
        this.id = id;
        this.capacity = capacity;
        setPrice(price);
        this.type = type;
    }


    public Room(String id, long capacity, int price, String type, String images) {
        this.id = id;
        this.capacity = capacity;
        setPrice(price);
        this.type = type;
        this.images = images;
    }

    public Room(String id, long capacity, int price, String type, String images, double sale, int hot) {
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


    public String getType() {
        return type;
    }

    public void updateUses(Use use){
        if (!this.uses.isEmpty()) {
            for (Use u : this.uses
            ) {
                if (u.getService().equals(use.getService()) && use.getAmount() != 0) {
                    this.uses.remove(u);
                    break;
                }
                else {
                    this.uses.remove(u);
                    return ;
                }
            }
        }

        this.uses.add(use);
    }

    public void setUses(ArrayList<Use> uses) {
        this.uses = uses;
    }

    public ArrayList<Use> getUses() {
        return uses;
    }
}
