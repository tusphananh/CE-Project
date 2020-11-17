package Main.Models;

import java.util.ArrayList;

public class BanquetBooking extends Reservation {
    public String from;
    public BanquetBooking(String id, String from, Owner owner, ArrayList<Room> rooms) throws Exception {
        super(id, owner, rooms);
        this.from = from;
    }
}
