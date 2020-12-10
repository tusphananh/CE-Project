package Main.Models;

import java.util.ArrayList;

public class BanquetBooking extends Reservation {
    private String from,hour;
    private ArrayList<Use> uses;

    public BanquetBooking(String first,int id, Owner owner, User user, String status, String paymentStatus, double totalPrice, String from,String hour,ArrayList<Use> uses, String note) throws Exception {
        super(first,id,owner,user,status,paymentStatus,totalPrice,note);
        this.from = from;
        this.uses = uses;
        this.hour = hour;
    }

    public String getHour() {
        return hour;
    }

    public String getFrom() {
        return from;
    }

    public ArrayList<Use> getUses() {
        return uses;
    }
}
