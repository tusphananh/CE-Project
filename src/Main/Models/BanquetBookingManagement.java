package Main.Models;

import Main.Database.Data;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BanquetBookingManagement {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private static Data data = new Data();
    private static String from,note,hour;
    private static Owner owner;
    private static User user;
    private static ArrayList<Service> services;

    static {
        try {
            services = data.getServices("BB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Use> selectedUse = new ArrayList<>();
    public static double totalPrice;
    private static BanquetBooking reservation;


    public static ArrayList<BanquetBooking> getPendingReservation() throws Exception {
        Date date = new Date();
        String curr = format.format(date);
        return data.getPendingBanquetReservationByStartDate(curr);
    }
    public static void updateStatus(String status,String paymentStatus,int id) throws SQLException {
        data.updateStatus(status,paymentStatus,"BB",id);
    }

    public static Service findService(String id){
        return Finder.search(services,id);
    }

    public static double getTotalPrice() throws ParseException {
        totalPrice = 0;
        for (Use use : selectedUse){
            totalPrice = totalPrice + use.getService().getPrice()*use.getAmount();
        }
        return totalPrice;
    }

    public static void addOwner() throws SQLException {
        Owner existOwner = data.getCustomerByPhone(owner.getPhone());
        if(existOwner == null){
            data.insertNewCustomer(owner);
        }
        owner = data.getCustomerByPhone(owner.getPhone());
    }

    public static void addReservation() throws Exception {
        int id = data.getSizeBanquetBooking() + 1;
        reservation = new BanquetBooking("BB",id,owner,user,"pending","pending",getTotalPrice(),from,hour,selectedUse,note);
        data.insertReservation("BB",reservation);
        data.insertBanquetBooking(reservation);
        data.insertUse(reservation);
        resetData();
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

    private static void resetData(){
        reservation = null;
        from = "";
        note="";
        owner = null;
    }

    public static void setNote(String note) {
        BanquetBookingManagement.note = note;
    }

    public static void setUser(User user) {
        BanquetBookingManagement.user = user;
    }

    public static void setFrom(String from) {
        BanquetBookingManagement.from = from;
    }

    public static void setOwner(Owner owner) {
        BanquetBookingManagement.owner = owner;
    }

    public static void setHour(String hour) {
        BanquetBookingManagement.hour = hour;
    }

    public static ArrayList<Service> getServices() {
        return services;
    }
}
