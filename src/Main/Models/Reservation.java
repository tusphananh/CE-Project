package Main.Models;

import Main.Enums.ReservedStatus;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Main.Main.Models.Reservation Class
public class Reservation implements Identifier {
    private static int transID = 0;
    private String id;
    long duration;
    ReservedStatus reservedStatus;
    String from, to;
    Owner owner;
    Room room;
    double totalPrice;

    public static int getTransID() {
        return transID;
    }

    public Reservation(String id, String from, String to, Owner owner, Room room) throws Exception {
        Reservation.transID += 1;
        this.id = id;
        this.owner = owner;
        this.room = room;
        this.from = from + " 14";
        this.to = to + " 12";
        this.reservedStatus = ReservedStatus.pending;
        setDuration();
        setTotalPrice();
        room.reservations.add(this);
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
        this.totalPrice = (int) duration * room.price;
    }

    // toString here
    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", duration=" + duration +
                ", reservedStatus=" + reservedStatus +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", owner=" + owner.name +
                ", room=" + room +
                ", totalPrice=" + totalPrice +
                '}';
    }

    @Override
    public String getID() {
        return id;
    }
}
