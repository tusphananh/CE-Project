package Main.Models;

import Main.Database.Data;
import javafx.scene.control.Alert;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class HotelManagement {
    private static Data data = new Data();
    private static String from,to,note;
    private static Owner owner;
    private static User user;
    public static ArrayList<Room> rooms = new ArrayList<>();
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static ArrayList<Reservation> reservations = new ArrayList<>();
    public static ArrayList<Service> services = new ArrayList<Service>();
    public static ArrayList<Room> selectedRoom = new ArrayList<>();
    public static ArrayList<Use> selectedUse = new ArrayList<>();
    public static ArrayList<Room> availableRooms = new ArrayList<>();
    public static double totalPrice;
    private static RoomBooking roomBooking;

    private static void resetData(){
        roomBooking = null;
        from = "";
        to = "";
        note="";
        owner = null;
        selectedRoom.clear();
        selectedUse.clear();
    }

    public static void updateStatus(String status,String paymentStatus,int id) throws SQLException {
        data.updateStatus(status,paymentStatus,"RB",id);
    }

    public static void addOwner() throws SQLException, ParseException {
        Owner existOwner = data.getCustomerByPhone(owner.getPhone());
        if(existOwner == null){
            data.insertNewCustomer(owner);
        }
        owner = data.getCustomerByPhone(owner.getPhone());
    }

    public static void addReservation() throws Exception {
        int id = data.getSizeRoomBooking() + 1;
        roomBooking = new RoomBooking(id,owner,user,"pending","pending",getTotalPrice(),from,to,selectedRoom,selectedUse,note);
        data.insertReservation("RB", roomBooking);
        data.insertRoomBooking(roomBooking);
        data.insertUse(roomBooking);
        data.insertContain(roomBooking);
        resetData();
    }

    public static ArrayList<RoomBooking> getPendingReservation() throws Exception {
        Date date = new Date();
        String curr = format.format(date);
        return data.getPendingReservationByStartDate(curr);
    }

    public static ArrayList<RoomBooking> getUsingReservation() throws Exception {
        return data.getUsingReservation();
    }

    public static void loadRoom() throws SQLException {
        rooms.addAll(data.getRooms());
    }
    public static void loadService() throws SQLException {
        services.addAll(data.getServices("RB"));
    }

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

    public static SimpleDateFormat getFormat() {
        return format;
    }

    // We will use Finder to find room by room ID
    public static Room findRoom(String id){
        return Finder.search(rooms,id);
    }
    // We will use Finder to find reservation by ID
    public Reservation findReservations(String id){
        return Finder.search(reservations,id);
    }
    public static Service findService(String id){
        return Finder.search(services,id);
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
            for (Room room: data.getAvailableRooms(from,to)
                 ) {
                availableRooms.remove(room);
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
        long diffInMillies = Math.abs(date1.getTime() - date2.getTime());
        return TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    }

    public static void setFrom(String from) {
        HotelManagement.from = from;
    }

    public static void setTo(String to) {
        HotelManagement.to = to;
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

    public static void setUser(User u) {
        user = u;
    }

    public static User getUser() {
        return user;
    }

    public static void setNote(String note) {
        HotelManagement.note = note;
    }
}


