package Main.Models;

import Main.Database.Data;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ManagerManagement {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static Data data = new Data();
    private static ArrayList<Service> services;
    private static User user;

    public static ArrayList<RoomBooking> getUnpaidReservation() throws Exception {
        return data.getUnpaidReservation();
    }

    public static void updateLogOutStatus() throws SQLException {
        data.updateLogOutStatus(user);
    }

    public static ArrayList<User> getOnlineUsers() throws SQLException {
        return data.getOnlineUser();
    }

    public static ArrayList<Use> getPreparingUses() throws SQLException {
        services = getServices();
        return data.getPreparingUses();
    }

    public static Service findService(String id)  {
        return Finder.search(services,id);
    }

    public static ArrayList<Room> getPendingRooms() throws SQLException {
        Date date = new Date();
        String curr = format.format(date);
        return data.getPendingRooms(curr);
    }

    public static ArrayList<Room> getUsingRooms() throws SQLException {
        Date date = new Date();
        String curr = format.format(date);
        return data.getUsingRooms(curr);
    }

    public static Owner getOwnerByPhone(String phone) throws SQLException {
        return data.getCustomerByPhone(phone);
    }

    public static void addService(String name,double price,String permission) throws SQLException {
        data.addService(name,price,permission);
    }

    public static void updateService(int id,String name,double price,String permission) throws SQLException {
        data.updateService(id,name,price,permission);
    }

    public static ArrayList<Service> getServices() throws SQLException {
        return data.getServices();
    }

    public static void addRoom(String name,String status,String type,long capacity,double price,double sale,String image) throws SQLException {
        data.addRoom(name,status,type,capacity,price,sale,image);
    }

    public static void updateRoom(int id,String name,String status,String type,long capacity,double price,double sale,String image) throws SQLException {
        data.updateRoom(id,name,status,type,capacity,price,sale,image);
    }

    public static void deleteRoom(int id) throws SQLException {
        data.deleteRoom(id);
    }

    public static void deleteService(int id) throws SQLException {
        data.deleteService(id);
    }

    public static ArrayList<Room> getRooms() throws SQLException {
        return data.getRooms();
    }
    public static void deleteUser(int id) throws SQLException {
        data.deleteUser(id);
    }

    public static void updateUser(int id,String name,String phone,String identifier,String username,String dob,String password,String role,String image,String status) throws SQLException {
        data.updateUser(id,name,phone,identifier,username,dob,password,role,image,status);
    }
    public static void addUser(String name,String phone,String identifier,String username,String dob,String password,String role,String image,String status) throws SQLException {
        data.addUser(name,phone,identifier,username,dob,password,role,image,status);
    }

    public static ArrayList<User> getUsers() throws SQLException {
        return data.getUsers();
    }

    public static void editReservation(String first,int last,String status,String paymentStatus,String note) throws SQLException {
        data.editReservation(first,last,status,paymentStatus,note);
    }

    public static ArrayList<Reservation> getReservationByPhone(String phone) throws Exception {
        return data.getReservationByPhone(phone);
    }

    public static Reservation getReservationByID(String first,int id) throws Exception {
        return data.getReservationByID(first,id);
    }

    public static RoomBooking getRBbyRes(Reservation reservation) throws Exception {
        return data.getRBbyRes(reservation);
    }

    public static BanquetBooking getBBbyRes(Reservation reservation) throws Exception {
        return data.getBBbyRes(reservation);
    }

    public static void setUser(User user) {
        ManagerManagement.user = user;
    }
}
