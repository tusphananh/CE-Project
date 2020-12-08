package Main.Models;

import java.util.ArrayList;

public class RoomBooking extends Reservation {
    private String from,to;
    private ArrayList<Use> uses;
    private ArrayList<Room> rooms;


    public RoomBooking(int id, Owner owner, User user, String status, String paymentStatus, double totalPrice, String from, String to, ArrayList<Room> rooms, ArrayList<Use> uses, String note) throws Exception {
        super(id,owner,user,status,paymentStatus,totalPrice,note);
        this.from = from;
        this.to = to;
        this.from = from;
        this.to = to;
        this.rooms = rooms;
        this.uses = uses;
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


    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }


    public ArrayList<Use> getUses() {
        return uses;
    }

}
