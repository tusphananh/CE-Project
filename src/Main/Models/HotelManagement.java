package Main.Models;

import javafx.scene.control.Alert;

import java.text.SimpleDateFormat;
import java.util.*;

public class HotelManagement {
    public static ArrayList<Room> rooms = new ArrayList<>();
    private static SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH");
    public static ArrayList<RoomBooking> reservations = new ArrayList<>();
    public static ArrayList<Service> services = new ArrayList<Service>();
    public static ArrayList<Room> selectedRoom = new ArrayList<>();
    public static ArrayList<Use> selectedUse = new ArrayList<>();
    public static ArrayList<Room> availableRooms = new ArrayList<>();
    public static double totalPrice;

    public static double getTotalPrice() {
        totalPrice = 0;
        for (Room room : selectedRoom){
            totalPrice = totalPrice + room.getPrice();
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

//    public static void addReservation(ArrayList<Room> rooms, String from, String to, Owner owner) throws Exception {
//        for (Room room : rooms
//             ) {
//            if (!HotelManagement.checkingRoom(room,from,to)){
//               return;
//            }
//        }
//        reservations.add(new Reservation(String.valueOf(RoomBooking.getTransID()),from,to,owner,rooms));
//    }

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


    public static boolean checkingRoom(String from, String to, RoomBooking roomBooking) throws Exception {
        // Cuz most of hotel now will be reserved from 14PM reserved day to 12AM the day after
        String fromDate = from + " 14";
        String toDate = to + " 12";
        Date f1 = format.parse(fromDate);
        Date t1 = format.parse(toDate);
        if (checkValidDate(from,to)){
            Date f2 = format.parse(roomBooking.from);
            Date t2 = format.parse(roomBooking.to);
            //  If from1 in other reservation's duration ( from1 >= from2 or from1 < to2) or ( to1 > from2 or to1 <= to2)
            // We will throw fail
            return (t1.after(f2) && (t1.before(t2) || t1.equals(t2))) || (f1.before(t2) && (f1.after(f2) || f1.equals(f2)));
        }
        throw new Exception("Invalid Room");
    }

    private static boolean checkValidDate(String from,String to) throws Exception {
        Date f1 = format.parse(from + " 14");
        Date t1 = format.parse(to+ " 12");
        if (f1.after(t1) || f1.equals(t1)){
            System.out.println("Why start day after end day");
            showAlertInformation("Something wrong","Why start day after end day");
            return false;
        }

        return true;
    }

    public static Boolean updateAvailableRooms(String from, String to) throws Exception {
        if (checkValidDate(from,to)){
            availableRooms = rooms;
            for (RoomBooking r: reservations
            ) {
                if (!checkingRoom(from,to,r)){
                    for (Room room: r.rooms
                    ) {
                        availableRooms.remove(room);
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

}


