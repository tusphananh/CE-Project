package Main.Models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Main.Main.Models.Reservation Class
public class Reservation implements Identifier {
    private String id,from,to;
    public String status;
    public String paymentStatus;
    public Owner owner;
    public ArrayList<Use> uses;
    public ArrayList<Room> rooms;
    double totalPrice;

    public Reservation(String id,String from,String to, Owner owner, ArrayList<Room> rooms) throws Exception {
        this.id = id;
        this.from = from + " 14";
        this.to = to + " 12";
        this.owner = owner;
        this.rooms = rooms;
        this.status = "pending";
        this.paymentStatus = "pending";
    }

    @Override
    public String getID() {
        return id;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }
}
