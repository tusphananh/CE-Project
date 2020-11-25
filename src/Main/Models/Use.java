package Main.Models;

import java.util.ArrayList;

public class Use {
    private Service service;
    private long amount;
    private Room room;

    public Use(Service service,long amount,Room room){
        this.service = service;
        this.amount = amount;
        this.room = room;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public long getAmount() {
        return amount;
    }

    public Service getService() {
        return service;
    }

    public double getTotalPrice() {
        return service.getPrice()*amount;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Use{" +
                "service=" + service +
                ", amount=" + amount +
                ", totalPrice=" + getTotalPrice() +
                ", room=" + room.getID() +
                '}';
    }
}
