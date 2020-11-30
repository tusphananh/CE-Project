package Main.Models;

import javafx.scene.control.Alert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class HotelManagement {
    private static String from,to;
    private static Owner owner;
    public static ArrayList<Room> rooms = new ArrayList<>();
    private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH");
    public static ArrayList<Reservation> reservations = new ArrayList<>();
    public static ArrayList<Reservation> pendingReservations = new ArrayList<>();
    public static ArrayList<Reservation> usingReservations = new ArrayList<>();
    public static ArrayList<Service> services = new ArrayList<Service>();
    public static ArrayList<Room> selectedRoom = new ArrayList<>();
    public static ArrayList<Use> selectedUse = new ArrayList<>();
    public static ArrayList<Room> availableRooms = new ArrayList<>();
    public static double totalPrice;

    public static void checkIn(Reservation reservation){
        if (reservation.getPaymentStatus().equals("success")){
            reservation.setStatus("using");
        }
    }

    public static double getTotalPrice() throws ParseException {
        totalPrice = 0;
        for (Room room : selectedRoom){
            totalPrice = totalPrice + room.getPrice()*getDuration();
        }
        return totalPrice;
    }

    // This func will check and add the reservation

    public static String moneyFormat(String money){
        return money + " $";
    }

    public static void addSelectedRoom(Room room) throws Exception {
        if (!selectedRoom.isEmpty()) {
            for (Room r : selectedRoom
            ) {
                if (r.getID().equals(room.getID())) {
                    selectedRoom.remove(r);
                    break;
                }
            }
        }
        HotelManagement.selectedRoom.add(room);
    }

    public static void dropSelectedRoom(Room room) throws Exception {
        for (Room r : selectedRoom
        ) {
            if (r.getID().equals(room.getID())) {
                selectedRoom.remove(r);
                break;
            }
        }
    }

    public static void addSelectedUse(Service service){
        if (!selectedUse.isEmpty()){
            for (Use use: selectedUse
                 ) {
                if (use.getService().equals(service)){
                    use.setAmount(use.getAmount() + 1);
                    return;
                }
            }
        }
        selectedUse.add(new Use(service,1));
    }

    public static void dropSelectedUse(Service service){
        if (!selectedUse.isEmpty()){
            for (Use use: selectedUse
            ) {
                if (use.getService().equals(service)){
                    long amount = use.getAmount() - 1;
                    if (amount > 0){
                        use.setAmount(use.getAmount() - 1);
                        return;
                    }
                    selectedUse.remove(use);
                    break;
                }
            }
        }
    }

    public static void addReservation() throws Exception {
        int id = 1;
        Reservation reservation = new Reservation(id,from,to,owner,selectedRoom,selectedUse);
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


    public static boolean checkAvailableDate(Reservation reservation) throws Exception {
        // Cuz most of hotel now will be reserved from 14PM reserved day to 12AM the day after
        Date f1 = format.parse(from);
        Date t1 = format.parse(to);
        Date f2 = format.parse(reservation.getFrom());
        Date t2 = format.parse(reservation.getTo());
        //  If from1 in other reservation's duration ( from1 >= from2 or from1 < to2) or ( to1 > from2 or to1 <= to2)
        // We will throw fail
        return ( t1.before(f2) || f1.after(t2) );
    }

    private static boolean checkValidDate() throws Exception {
        Date f1 = format.parse(from);
        Date t1 = format.parse(to);
        if (f1.after(t1) || f1.equals(t1)){
            System.out.println("Why start day after end day");
            showAlertInformation("Something wrong","Why start day after end day");
            return false;
        }

        return true;
    }

    public static Boolean updateAvailableRooms() throws Exception {
        availableRooms.clear();
        if (checkValidDate()){
            availableRooms.addAll(rooms);
            for (Reservation reservation: reservations
                 ) {
                if (!checkAvailableDate(reservation)){
                    for (Room room: reservation.getRooms()
                    ) {
                        try {
                        availableRooms.remove(room);
                      } catch (Exception e){ }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static void showAlertInformation(String title, String body){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(body);
        alert.show();
    }

    private static long getDuration() throws ParseException {
        Date date1 = HotelManagement.getFormat().parse(from);
        Date date2 = HotelManagement.getFormat().parse(to);
        // Different between 2 days in MiliSec
        long diff = Math.abs(date2.getTime() - date1.getTime());
        // Cuz the duration from 14PM to 12AM so it's not fully 24 hours so we need +1 after this
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
    }

    public static void setFrom(String from) {
        HotelManagement.from = from + " 14";
    }

    public static void setTo(String to) {
        HotelManagement.to = to + " 12";
    }

    public static String getFrom() {
        return from;
    }

    public static String getTo() {
        return to;
    }

    public static void setOwner(Owner owner) {
        HotelManagement.owner = owner;
    }
}


