package Main;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

public class HotelManagement {
    static ArrayList<Room> rooms = new ArrayList<>();
    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH");
    static ArrayList<Reservation> reservations = new ArrayList<>();

    public void addReservation(Room room, String from, String to, Owner owner) throws Exception {
        if (this.checkingReservation(room,from,this.getAvailableTime(to))){
            reservations.add(new Reservation(String.valueOf(Reservation.getTransID()),from,to,owner,room));
        }
    }

    public Room findRoom(String id){
        return Finder.search(rooms,id);
    }
    public Reservation findReservations(String id){
        return Finder.search(reservations,id);
    }

    public boolean checkingReservation(Room room,String from1, String to1) throws Exception {
        Date f1 = format.parse(from1);
        Date t1 = format.parse(to1);
        if (room.reservations.isEmpty()) {
            System.out.println("Success");
            return true;
        } else {
            for (Reservation r : room.reservations
            ) {
                Date f2 = format.parse(r.from);
                Date t2 = format.parse(r.to);

                if ((t1.after(f2) && (t1.before(t2) || t1.equals(t2))) || (f1.before(t2) && (f1.after(f2) || f1.equals(f2)))){
                    System.out.println("Fail");
                    return false;
                }
            }
        }
        System.out.println("Success");
        return true;
    }

    public String getAvailableTime(String to) throws ParseException {
        Date date = format.parse(to);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 2);
        return format.format(calendar.getTime());
    }

}




// Main.Main.Reservation Class
class Reservation implements Identifier {
    private static int transID = 0;
    private String id;
    public String availableTime;
    ReservedStatus reservedStatus;
    String from, to;
    Owner owner;
    Room room;
    int totalPrice;
    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH");

    public static int getTransID() {
        return transID;
    }

    public Reservation(String id, String from, String to, Owner owner, Room room) throws Exception {
        Reservation.transID += 1;
        this.id = id;
        this.owner = owner;
        this.room = room;
        this.from = from;
        this.to = to;
        this.reservedStatus = ReservedStatus.pending;
        setAvailableTime(this.to);
        setTotalTime(from, to);
        setTotalPrice();
        room.reservations.add(this);
    }

    public void setTotalTime(String from, String to) throws Exception {
        Date d1 = format.parse(from);
        Date d2 = format.parse(to);
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cgual1.setTime(d1);
        cal2.setTime(d2);
        this.isOverDay = !(cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR));
        long dayInHour = ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24)) % 365;
        long hour = ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60)) % 24;
        this.totalTime = dayInHour * 24 + hour;
    }

    public void setAvailableTime(String to) throws ParseException {
        Date date = format.parse(to);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR, 2);
        availableTime = format.format(calendar.getTime());
    }

    public void setTotalPrice() {
        if (isOverDay) totalPrice = (int) (room.dayPrice * (totalTime + 2) / 24);
        else if (totalTime > 2) totalPrice = (int) (room.hourPrice + (totalTime - 2) * room.hourPrice * 0.07);
        else totalPrice = (int) (room.hourPrice * totalTime);
    }

    // toString here
    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", reservedStatus=" + reservedStatus.getValues() +
                ", availableTime=" + availableTime +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", owner=" + owner.name +
                ", room=" + room +
                ", totalTime=" + totalTime +
                ", totalPrice=" + totalPrice +
                ", isOverDay=" + isOverDay +
                '}';
    }

    @Override
    public String getID() {
        return id;
    }
}

class ReservationOnline extends Reservation {
    PaymentStatus paymentStatus;
    PaymentMethod paymentMethod;

    public ReservationOnline(String id, String from, String to, PaymentMethod paymentMethod, Owner owner, Room room) throws Exception {
        super(id, from, to, owner, room);
        this.paymentStatus = PaymentStatus.pending;
        this.paymentMethod = paymentMethod;
    }

    // toString here
    @Override
    public String toString() {
        return "ReservationOnline{" +
                "id='" + getID() + '\'' +
                ", reservedStatus=" + reservedStatus.getValues() +
                ", paymentMethod=" + paymentMethod.name +
                ", paymentStatus=" + paymentStatus.getValues() +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", owner=" + owner.name +
                ", room=" + room +
                ", totalTime=" + totalTime +
                ", totalPrice=" + totalPrice +
                ", isOverDay=" + isOverDay +
                '}';
    }
}


// Main.Main.Owner Class
class Owner {
    String name, phone, id;

    public Owner(String name, String phone, String id) {
        this.name = name;
        this.phone = phone;
        this.id = id;
    }

    // toString here
    @Override
    public String toString() {
        return "Owner{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}


// Main.Main.Room Class
class Room implements Identifier{
    private String id;
    long bedAmount;
    int hourPrice, dayPrice;
    ArrayList<Reservation> reservations = new ArrayList<>();

    public Room(String id, long bedAmount, int hourPrice, int dayPrice) {
        this.id = id;
        this.bedAmount = bedAmount;
        this.hourPrice = hourPrice;
        this.dayPrice = dayPrice;
    }


    // toString here
    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", bedAmount=" + bedAmount +
                ", hourPrice=" + hourPrice +
                ", dayPrice=" + dayPrice +
                '}';
    }

    @Override
    public String getID() {
        return id;
    }
}

abstract class PaymentMethod {
    String id, name;

    PaymentMethod(String id) {
        this.id = id;
    }
}

class Visa extends PaymentMethod {
    String cvv, validFrom, validTo;

    Visa(String id, String cvv, String validFrom, String validTo) {
        super(id);
        this.cvv = cvv;
        this.validFrom = validFrom;
        this.validTo = validTo;
        this.name = "Visa";
    }
}

class ATM extends PaymentMethod {
    String owner;

    ATM(String id, String owner) {
        super(id);
        this.owner = owner;
        this.name = "ATM";
    }
}


enum ReservedStatus {
    pending("Pending"), success("Success"), fail("Fail");

    private final String values;

    ReservedStatus(String values) {
        this.values = values;
    }

    public String getValues() {
        return values;
    }
}

enum PaymentStatus {
    pending("Pending"), success("Success");
    private final String values;

    PaymentStatus(String values) {
        this.values = values;
    }

    public String getValues() {
        return values;
    }
}

interface Identifier{
    String getID();
}

class Finder{

    public static <E extends Identifier> E search(Collection<E> collection, String key)
    {
        for(E e: collection)
        {
            if( e.getID().equals(key) )
            {
                return e;
            }
        }
        return null;
    }

}
