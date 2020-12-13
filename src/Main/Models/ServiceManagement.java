package Main.Models;

import Main.Database.Data;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ServiceManagement {
    private static Data data = new Data();

    public static void updateUses(RoomBooking roomBooking,ArrayList<Use> selectedUse) throws SQLException {
        for (Use use: selectedUse
             ) {
            try{
                data.insertUse(roomBooking.getFirst(),roomBooking.getId(),use);
            }
            catch (Exception e){
                use.setAmount(use.getAmount() + use.getAmount());
                data.updateUse(roomBooking.getFirst(),roomBooking.getId(),use);
            }
        }
    }

    public static ArrayList<RoomBooking> getUsingReservation() throws Exception {
        return data.getUsingReservation();
    }

    public static ArrayList<Service> getServices() throws SQLException {
        return data.getServices("RB");
    }

    public static void addSelectedUse(Service service,ArrayList<Use> selectedUse){
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

    public static void dropSelectedUse(Service service,ArrayList<Use> selectedUse){
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
}
