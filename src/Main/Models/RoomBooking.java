package Main.Models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RoomBooking extends Reservation {
    public ArrayList<Room> rooms;
    public ArrayList<Use> uses;
    long duration;
    public String from,to;
    public RoomBooking(String id, String from,String to, Owner owner, ArrayList<Room> rooms) throws Exception {
        super(id, owner, rooms);
        this.from = from + "14";
        this.to = to + " 12";
        this.rooms  = rooms;
        setDuration();
        setTotalPrice();
    }

    @Override
    public String getID() {
        return super.getID();
    }

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

    public String getTo() {
        return to;
    }
}
