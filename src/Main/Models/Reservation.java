package Main.Models;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Main.Main.Models.Reservation Class
public class Reservation implements Identifier {
    private String id;
    public String status;
    public Owner owner;
    public ArrayList<Use> uses;
    double totalPrice;

    public Reservation(String id, Owner owner, ArrayList<Room> rooms) throws Exception {
        this.id = id;
        this.owner = owner;
        this.status = "pending";
    }

    @Override
    public String getID() {
        return id;
    }
}
