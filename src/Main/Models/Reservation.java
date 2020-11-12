package Main.Models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Main.Main.Models.Reservation Class
public class Reservation implements Identifier {
    private static int transID = 0;
    private String id;
    long duration;
    public String status;
    public String from, to;
    public Owner owner;
    public ArrayList<Room> rooms = new ArrayList<>();
    double totalPrice;

    public static int getTransID() {
        return transID;
    }

    public Reservation(String id, String from, String to, Owner owner, ArrayList<Room> rooms) throws Exception {
        Reservation.transID += 1;
        this.id = id;
        this.owner = owner;
        this.rooms  = rooms;
        this.from = from + " 14";
        this.to = to + " 12";
        this.status = "pending";
        setDuration();
        setTotalPrice();
        for (Room room : rooms
                ) {
            room.reservations.add(this);
        }
    }


    // The duration to calculate the different between 2 days
    public void setDuration() throws ParseException {
        Date date1 = HotelManagement.getFormat().parse(from);
        Date date2 = HotelManagement.getFormat().parse(to);
        // Different between 2 days in MiliSec
        long diffInMillies = Math.abs(date2.getTime() - date1.getTime());
        // Cuz the duration from 14PM to 12AM so it's not fully 24 hours so we need +1 after this
        duration = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS) + 1;
    }

    public void setTotalPrice() {
        double totalprice = 0;
        for (Room room : rooms
             ) {
            totalprice = totalprice + (int) duration * room.price;
        }
        this.totalPrice = totalprice;
    }

    // toString here
    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", duration=" + duration +
                ", status=" + status +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", owner=" + owner.name +
                ", room=" + rooms +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public String getID() {
        return id;
    }
}
