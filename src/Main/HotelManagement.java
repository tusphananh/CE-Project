package Main;

import javafx.scene.control.Alert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class HotelManagement {
    static ArrayList<Room> rooms = new ArrayList<>();
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static ArrayList<Service> services = new ArrayList<Service>();

    // This func will check and add the reservation
    public void addReservation(Room room, String from, String to, Owner owner) throws Exception {
        if (this.checkingReservation(room,from,to)){
            reservations.add(new Reservation(String.valueOf(Reservation.getTransID()),from,to,owner,room));
        }
    }

    public static SimpleDateFormat getFormat() {
        return format;
    }

    // We will use Finder to find room by room ID
    public Room findRoom(String id){
        return Finder.search(rooms,id);
    }
    // We will use Finder to find reservation by ID
    public Reservation findReservations(String id){
        return Finder.search(reservations,id);
    }

    public static boolean checkingReservation(Room room,String from1, String to1) throws Exception {
        // Cuz most of hotel now will be reserved from 14PM reserved day to 12AM the day after
        String from = from1 + " 14";
        String to = to1 + " 12";
        System.out.println(from);
        System.out.println(to);
        Date f1 = format.parse(from);
        Date t1 = format.parse(to);
        if (f1.after(t1)){
            showAlertInformation("Something wrong","Why start day after end day");
            throw new Exception("Time compare");
        }
        if (room.reservations.isEmpty()) {
            System.out.println("True");
            return true;
        } else {
            for (Reservation r : room.reservations
            ) {
                Date f2 = format.parse(r.from);
                Date t2 = format.parse(r.to);
                //  If from1 in other reservation's duration ( from1 >= from2 or from1 < to2) or ( to1 > from2 or to1 <= to2)
                // We will throw fail
                if ((t1.after(f2) && (t1.before(t2) || t1.equals(t2))) || (f1.before(t2) && (f1.after(f2) || f1.equals(f2)))){
                    System.out.println("False");
                    return false;
                }
            }
        }
        System.out.println("Success");
        return true;
    }

    public static void showAlertInformation(String title, String body){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(body);
        alert.show();
    }

}


// Main.Main.Reservation Class
class Reservation implements Identifier {
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
        this.totalPrice = (int) duration*room.price;
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
                "duration=" + duration +
                ", reservedStatus=" + reservedStatus +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", owner=" + owner.name +
                ", room=" + room +
                ", totalPrice=" + totalPrice +
                ", paymentStatus=" + paymentStatus +
                ", paymentMethod=" + paymentMethod.getClass() +
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
    long capacity;
    double price;
    double sale;
    int hot;
    Type type;
    ArrayList<Reservation> reservations = new ArrayList<>();
    String images;

    public Room(String id, long capacity, int price, Type type) {
        this.id = id;
        this.capacity = capacity ;
        setPrice(price);
        this.type = type;
    }
    public Room(String id, long capacity, int price, Type type,String images) {
        this.id = id;
        this.capacity = capacity;
        setPrice(price);
        this.type = type;
        this.images = images;
    }
    public Room(String id, long capacity, int price, Type type,String images,double sale,int hot) {
        this.id = id;
        this.capacity = capacity;
        setPrice(price);
        this.type = type;
        this.images = images;
        this.hot = hot;
        this.sale = sale;
    }

    public void setPrice(double price) {
        this.price = price - price*sale;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getImages() {
        return images;
    }

    // toString here


    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                ", sale=" + sale +
                ", hot=" + hot +
                ", type=" + type +
                ", images='" + images + '\'' +
                '}';
    }

    @Override
    public String getID() {
        return id;
    }


    public double getPrice() {
        return price;
    }

    public long getCapacity() {
        return capacity;
    }


    public Type getType() {
        return type;
    }
}
enum Type{
    vip("V.I.P"),normal("Normal");
    private String value;
    Type(String value){ this.value = value;}

    public String getValue() {
        return value;
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

// Interface Identifier to get the ID of Objects
interface Identifier{
    String getID();
}

// This finder class will help us find the Objects which implement the Identifier (Helper in OOAD)
class Finder{

    // This func will travels the ArrayList(Collection) to find the Object exist or not through ID(getID in Identifier)
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

class Service{
    private String id,name,description;

    public Service(String id,String name){
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
