package Main.Models;

import javafx.scene.control.Alert;

import java.text.SimpleDateFormat;
import java.util.*;

public class HotelManagement {
    public static ArrayList<Room> rooms = new ArrayList<>();
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
    public static ArrayList<Reservation> reservations = new ArrayList<>();
    public static ArrayList<Service> services = new ArrayList<Service>();
    public static ArrayList<Room> selectedRoom = new ArrayList<>();

    // This func will check and add the reservation

    public static void addSelectedRoom(Room room,String from,String to) throws Exception {
        if (checkingRoom(room,from,to) && checkSelectedRoom(room)){
            HotelManagement.selectedRoom.add(room);
            System.out.println("Number of services: " + room.services.size());
        }
    }

    public static void addReservation(ArrayList<Room> rooms, String from, String to, Owner owner) throws Exception {
        for (Room room : rooms
             ) {
            if (!HotelManagement.checkingRoom(room,from,to)){
               return;
            }
        }
        reservations.add(new Reservation(String.valueOf(Reservation.getTransID()),from,to,owner,rooms));
    }
    public static Boolean checkSelectedRoom(Room room){
        if (selectedRoom.isEmpty()){
            System.out.println("Add selected Room : True");
            return true;
        }
        else {
            for (Room r : selectedRoom
                 ) {
                if (r.equals(room)){
                    System.out.println("Add selected Room : False");
                    return false;
                }
            }
            System.out.println("Add selected Room : True");
            return true;

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

    public static boolean checkingRoom(Room room, String from1, String to1) throws Exception {
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
            System.out.println("Checking Room : True");
            return true;
        } else {
            for (Reservation r : room.reservations
            ) {
                Date f2 = format.parse(r.from);
                Date t2 = format.parse(r.to);
                //  If from1 in other reservation's duration ( from1 >= from2 or from1 < to2) or ( to1 > from2 or to1 <= to2)
                // We will throw fail
                if ((t1.after(f2) && (t1.before(t2) || t1.equals(t2))) || (f1.before(t2) && (f1.after(f2) || f1.equals(f2)))){
                    System.out.println("Checking Room : False");
                    return false;
                }
            }
        }
        System.out.println("Checking Room : True");
        return true;
    }

    public static void showAlertInformation(String title, String body){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(body);
        alert.show();
    }

}


