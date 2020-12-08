package Main.Database;

import Main.Models.*;

import java.net.ServerSocket;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class Data {
    public ArrayList<Room> getRooms() throws SQLException {
        ArrayList<Room> rooms = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM room";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            Room room = new Room(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getLong(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8));
            rooms.add(room);
        }
        rs.close();
        return rooms;
    }

    public ArrayList<Service> getServices(String permission) throws SQLException {
        ArrayList<Service> services = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "SELECT * FROM service where permission like ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,permission);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            Service service = new Service(rs.getInt(1),rs.getString(2),rs.getDouble(3));
            services.add(service);
        }
        rs.close();
        return services;
    }


    public void insertNewCustomer(Owner owner) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "insert into customer(name,phone,coin,type) values (?,?,0,'bronze')";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,owner.getName());
        stmt.setString(2,owner.getPhone());
        stmt.executeUpdate();
        conn.close();
    }

    public void updateCoin(Owner owner) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "insert into customer(coin,type) values (?,?) where Cus_ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,owner.getName());
        stmt.setString(2,owner.getPhone());
        stmt.setInt(3,owner.getId());
        stmt.executeUpdate();
        conn.close();
    }

    public Owner getCustomerByPhone(String phone) throws SQLException {
        Owner owner = null;
        Connection conn = JDBC.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM customer where phone like " + "\"" + phone + "\"";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            owner = new Owner(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
        }
        rs.close();

        return owner;
    }
    public Owner getCustomerByID(int id) throws SQLException {
        Owner owner = null;
        Connection conn = JDBC.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM customer where cus_ID like " + "\"" + id + "\"";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            owner = new Owner(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5));
        }
        rs.close();

        return owner;
    }

    public void insertUse(RoomBooking reservation) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "insert into uses(first_id,last_id,ser_id,amount) values ('RB',?,?,?) ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (Use use: reservation.getUses()
        ) {
            stmt.clearParameters();
            stmt.setInt(1,reservation.getId());
            stmt.setInt(2,use.getService().getId());
            stmt.setLong(3,use.getAmount());
            stmt.addBatch();
        }
        stmt.executeBatch();
        stmt.close();
        conn.close();
    }
    public void insertUse(BanquetBooking reservation) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "insert into uses(first_id,last_id,ser_id,amount) values ('BB',?,?,?) ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (Use use: reservation.getUses()
        ) {
            stmt.clearParameters();
            stmt.setInt(1,reservation.getId());
            stmt.setInt(2,use.getService().getId());
            stmt.setLong(3,use.getAmount());
            stmt.addBatch();
        }
        stmt.executeBatch();
        stmt.close();
        conn.close();
    }

    public void insertContain(RoomBooking reservation) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "insert into contain(first_id,last_id,room_ID) values ('RB',?,?) ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (Room room: reservation.getRooms()
             ) {
            stmt.clearParameters();
            stmt.setInt(1,reservation.getId());
            stmt.setInt(2,room.getId());
            stmt.addBatch();
        }
        stmt.executeBatch();
        stmt.close();
        conn.close();
    }

    public void insertReservation(String firstID,Reservation reservation) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "insert into reservation(first_id,last_id,cus_ID,user_ID,status,payment_status,total_price,note) values (?,?,?,?,?,?,?,?) ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,firstID);
        stmt.setInt(2,reservation.getId());
        stmt.setInt(3,reservation.getOwner().getId());
        stmt.setInt(4, reservation.getUser().getId());
        stmt.setString(5,reservation.getStatus());
        stmt.setString(6,reservation.getPaymentStatus());
        stmt.setDouble(7,reservation.getTotalPrice());
        stmt.setString(8,reservation.getNote());
        stmt.executeUpdate();
        conn.close();
    }

    public void insertRoomBooking(RoomBooking reservation) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "insert into roombooking(first_id,last_id,start_date,end_date) values ('RB',?,?,?) ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,reservation.getId());
        stmt.setString(2,reservation.getFrom());
        stmt.setString(3,reservation.getTo());
        stmt.executeUpdate();
        conn.close();
    }
    public void insertBanquetBooking(BanquetBooking reservation) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "insert into banquetbooking(first_id,last_id,start_date,start_hour) values ('BB',?,?,?) ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,reservation.getId());
        stmt.setString(2,reservation.getFrom());
        stmt.setString(3,reservation.getHour());
        stmt.executeUpdate();
        conn.close();
    }

    public int getSizeRoomBooking() throws SQLException {
        int size = 0;
        Connection conn = JDBC.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT max(last_id) FROM reservation where first_id like 'RB'  ";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            size = rs.getInt(1);
        }
        rs.close();
        conn.close();
        return size;
    }
    public int getSizeBanquetBooking() throws SQLException {
        int size = 0;
        Connection conn = JDBC.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT max(last_id) FROM reservation where first_id like 'BB' ";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            size = rs.getInt(1);
        }
        rs.close();
        conn.close();
        return size;
    }

    public User getUserByPassword(String username,String password) throws SQLException {
        User user = null;
        Connection conn = JDBC.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM user where username like " + "\"" + username + "\" and password like" + "\"" + password + "\"";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
        }
        rs.close();
        conn.close();
        return user;
    }

    public ArrayList<Room> getAvailableRooms(String from,String to) throws SQLException {
        ArrayList<Room> rooms = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "SELECT contain.room_ID FROM contain\n" +
                "where contain.last_id in (select roombooking.last_id from roombooking join reservation\n" +
                "on roombooking.first_id like reservation.first_id \n" +
                "and roombooking.last_id = reservation.last_id \n" +
                "where ( status like 'pending' or status like 'using') \n" +
                "and (( roombooking.start_date between ? and ? ) \n " +
                "or ( roombooking.end_date between ? and ? ))) \n" +
                "group by contain.room_ID ;";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,from);
        preparedStatement.setString(2,to);
        preparedStatement.setString(3,from);
        preparedStatement.setString(4,to);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            rooms.add(HotelManagement.findRoom(String.valueOf(rs.getInt(1))));
        }
        rs.close();
        conn.close();
        return rooms;
    }
    public ArrayList<Room> getContainByID(int id) throws SQLException {
        ArrayList<Room> rooms = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "select  room_ID from contain where first_id like 'RB' and last_id = ? ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            rooms.add(HotelManagement.findRoom(String.valueOf(rs.getInt(1))));
        }
        conn.close();
        return rooms;
    }

    public ArrayList<Use> getUsesByID(String firstID,int id) throws SQLException {
        ArrayList<Use> uses = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "select ser_ID,amount from uses where first_id like ? and last_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,firstID);
        stmt.setInt(2,id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            if (firstID.equals("RB")){
                uses.add(new Use(HotelManagement.findService(String.valueOf(rs.getInt(1))),rs.getLong(2)));
            }
            else {
                uses.add(new Use(BanquetManagement.findService(String.valueOf(rs.getInt(1))),rs.getLong(2)));
            }
        }
        conn.close();
        return uses;
    }

    public User getUserByID(int id) throws SQLException {
        User user = null;
        Connection conn = JDBC.getConnection();
        String sql = "select  * from user where user_ID like ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8));
        }
        conn.close();

        return user;
    }



    public ArrayList<RoomBooking> getPendingReservationByStartDate(String from) throws Exception {
        ArrayList<RoomBooking> reservations = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "SELECT reservation.last_id,reservation.cus_ID,reservation.user_ID,reservation.status,reservation.payment_status,reservation.total_price,roombooking.start_date,roombooking.end_date,reservation.note \n" +
                "FROM hotel.reservation join hotel.roombooking \n" +
                "on ( reservation.first_id like 'RB' and reservation.last_id = roombooking.last_id and reservation.status like 'pending' )\n" +
                "where start_date like ? ;";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,from);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            int id = rs.getInt(1);
            RoomBooking reservation = new RoomBooking(id,getCustomerByID(rs.getInt(2))
                    ,getUserByID(rs.getInt(3)),rs.getString(4),
                    rs.getString(5),rs.getDouble(6),rs.getString(7),
                    rs.getString(8),getContainByID(id),getUsesByID("RB",id),rs.getString(9));
            reservations.add(reservation);
        }
        conn.close();

        return reservations;
    }


    public ArrayList<RoomBooking> getUsingReservation() throws Exception {
        ArrayList<RoomBooking> reservations = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "SELECT reservation.last_id,reservation.cus_ID,reservation.user_ID,reservation.status,reservation.payment_status,reservation.total_price,roombooking.start_date,roombooking.end_date,reservation.note \n" +
                "FROM hotel.reservation join hotel.roombooking \n" +
                "on ( reservation.first_id like 'RB' and reservation.last_id = roombooking.last_id and reservation.status like 'using' )";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            int id = rs.getInt(1);
            RoomBooking reservation = new RoomBooking(id,getCustomerByID(rs.getInt(2))
                    ,getUserByID(rs.getInt(3)),rs.getString(4),
                    rs.getString(5),rs.getDouble(6),rs.getString(7),
                    rs.getString(8),getContainByID(id),getUsesByID("RB",id),rs.getString(9));
            reservations.add(reservation);
        }
        conn.close();

        return reservations;
    }

    public void updateStatus(String status,String paymentStatus,String firstID,int id) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "update reservation " +
                "set status = ? , payment_status = ? where first_id like ? and last_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,status);
        stmt.setString(2,paymentStatus);
        stmt.setString(3,firstID);
        stmt.setInt(4,id);
        stmt.executeUpdate();

        conn.close();
    }


    public ArrayList<BanquetBooking> getPendingBanquetReservationByStartDate(String from) throws Exception {
        ArrayList<BanquetBooking> reservations = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "SELECT reservation.last_id,reservation.cus_ID,reservation.user_ID,reservation.status,reservation.payment_status,reservation.total_price,banquetbooking.start_date,banquetbooking.start_hour,reservation.note\n" +
                "                FROM hotel.reservation join hotel.banquetbooking \n" +
                "                on ( reservation.first_id like 'BB' and reservation.last_id = banquetbooking.last_id and reservation.status like 'pending' )\n" +
                "                where start_date like ? ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,from);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            int id = rs.getInt(1);
            BanquetBooking reservation = new BanquetBooking(id,getCustomerByID(rs.getInt(2))
                    ,getUserByID(rs.getInt(3)),rs.getString(4),
                    rs.getString(5),rs.getDouble(6),rs.getString(7),
                    rs.getString(8),getUsesByID("BB",id),rs.getString(9));
            reservations.add(reservation);
        }
        conn.close();

        return reservations;
    }

}
