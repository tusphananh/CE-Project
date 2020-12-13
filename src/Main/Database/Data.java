package Main.Database;

import Main.Models.*;

import java.sql.*;
import java.util.ArrayList;

public class Data {
    public ArrayList<Room> getRooms() throws SQLException {
        ArrayList<Room> rooms = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM room";
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            Room room = new Room(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getLong(5),rs.getDouble(6),rs.getLong(7),rs.getString(8));
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

    public ArrayList<Service> getServices() throws SQLException {
        ArrayList<Service> services = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "SELECT * FROM service";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            Service service = new Service(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getString(4));
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
        String sql = "update customer set coin = ? ,type = ? where Cus_ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,owner.getCoins());
        stmt.setString(2,owner.getType());
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
            user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
        }
        rs.close();
        conn.close();
        return user;
    }

    public ArrayList<Room> getPendingRooms(String from, String to) throws SQLException {
        ArrayList<Room> rooms = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "SELECT contain.room_ID FROM contain\n" +
                "where contain.last_id in (select roombooking.last_id from roombooking join reservation\n" +
                "on roombooking.first_id like reservation.first_id \n" +
                "and roombooking.last_id = reservation.last_id \n" +
                "where ( status like 'pending' or status like 'using') \n" +
                "and (( roombooking.start_date <= ? and roombooking.start_date > ? ) \n " +
                "or ( roombooking.end_date > ? and roombooking.end_date <= ? ))) \n" +
                "group by contain.room_ID ;";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,from);
        preparedStatement.setString(2,to);
        preparedStatement.setString(3,from);
        preparedStatement.setString(4,to);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            rooms.add(RoomBookingManagement.findRoom(String.valueOf(rs.getInt(1))));
        }
        rs.close();
        conn.close();
        return rooms;
    }

    public ArrayList<Room> getPendingRooms(String current) throws SQLException {
        ArrayList<Room> rooms = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "SELECT contain.room_ID FROM contain\n" +
                "                where contain.last_id in (select roombooking.last_id from roombooking join reservation\n" +
                "                on roombooking.first_id like reservation.first_id\n" +
                "                and roombooking.last_id = reservation.last_id \n" +
                "                where reservation.status like 'pending'\n" +
                "                and ( ? between roombooking.start_date and roombooking.end_date))\n" +
                "                group by contain.room_ID";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,current);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            rooms.add(RoomBookingManagement.findRoom(String.valueOf(rs.getInt(1))));
        }
        rs.close();
        conn.close();
        return rooms;
    }

    public ArrayList<Room> getUsingRooms(String current) throws SQLException {
        ArrayList<Room> rooms = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "SELECT contain.room_ID FROM contain\n" +
                "                where contain.last_id in (select roombooking.last_id from roombooking join reservation\n" +
                "                on roombooking.first_id like reservation.first_id\n" +
                "                and roombooking.last_id = reservation.last_id \n" +
                "                where reservation.status like 'using'\n" +
                "                and ( ? between roombooking.start_date and roombooking.end_date))\n" +
                "                group by contain.room_ID";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1,current);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()){
            rooms.add(RoomBookingManagement.findRoom(String.valueOf(rs.getInt(1))));
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
            rooms.add(RoomBookingManagement.findRoom(String.valueOf(rs.getInt(1))));
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
                uses.add(new Use(RoomBookingManagement.findService(String.valueOf(rs.getInt(1))),rs.getLong(2)));
            }
            else {
                uses.add(new Use(BanquetBookingManagement.findService(String.valueOf(rs.getInt(1))),rs.getLong(2)));
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
            user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
        }
        conn.close();

        return user;
    }



    public ArrayList<RoomBooking> getPendingReservationByStartDate(String from) throws Exception {
        ArrayList<RoomBooking> reservations = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "SELECT reservation.first_id,reservation.last_id,reservation.cus_ID,reservation.user_ID,reservation.status,reservation.payment_status,reservation.total_price,roombooking.start_date,roombooking.end_date,reservation.note \n" +
                "FROM hotel.reservation join hotel.roombooking \n" +
                "on ( reservation.first_id like 'RB' and reservation.last_id = roombooking.last_id and reservation.status like 'pending' )\n" +
                "where start_date like ? ;";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,from);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            int id = rs.getInt(2);
            RoomBooking reservation = new RoomBooking(rs.getString(1),id,getCustomerByID(rs.getInt(3))
                    ,getUserByID(rs.getInt(4)),rs.getString(5),
                    rs.getString(6),rs.getDouble(7),rs.getString(8),
                    rs.getString(9),getContainByID(id),getUsesByID("RB",id),rs.getString(10));
            reservations.add(reservation);
        }
        conn.close();

        return reservations;
    }


    public ArrayList<RoomBooking> getUsingReservation() throws Exception {
        ArrayList<RoomBooking> reservations = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "SELECT reservation.first_id,reservation.last_id,reservation.cus_ID,reservation.user_ID,reservation.status,reservation.payment_status,reservation.total_price,roombooking.start_date,roombooking.end_date,reservation.note \n" +
                "FROM hotel.reservation join hotel.roombooking \n" +
                "on ( reservation.first_id like 'RB' and reservation.last_id = roombooking.last_id and reservation.status like 'using' )";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            int id = rs.getInt(2);
            RoomBooking reservation = new RoomBooking(rs.getString(1),id,getCustomerByID(rs.getInt(3))
                    ,getUserByID(rs.getInt(4)),rs.getString(5),
                    rs.getString(6),rs.getDouble(7),rs.getString(8),
                    rs.getString(9),getContainByID(id),getUsesByID("RB",id),rs.getString(10));
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
        String sql = "SELECT reservation.first_id,reservation.last_id,reservation.cus_ID,reservation.user_ID,reservation.status,reservation.payment_status,reservation.total_price,banquetbooking.start_date,banquetbooking.start_hour,reservation.note\n" +
                "                FROM hotel.reservation join hotel.banquetbooking \n" +
                "                on ( reservation.first_id like 'BB' and reservation.last_id = banquetbooking.last_id and reservation.status like 'pending' )\n" +
                "                where start_date like ? ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,from);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            int id = rs.getInt(2);
            BanquetBooking reservation = new BanquetBooking(rs.getString(1),id,getCustomerByID(rs.getInt(3))
                    ,getUserByID(rs.getInt(4)),rs.getString(5),
                    rs.getString(6),rs.getDouble(7),rs.getString(8),
                    rs.getString(9),getUsesByID("BB",id),rs.getString(10));
            reservations.add(reservation);
        }
        conn.close();

        return reservations;
    }

    public ArrayList<Reservation> getReservationByPhone(String phone) throws Exception {
        Owner owner = getCustomerByPhone(phone);
        ArrayList<Reservation> reservations = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "select * from reservation where cus_ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,owner.getId());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Reservation reservation = new Reservation(rs.getString(1),rs.getInt(2),owner,getUserByID(rs.getInt(4)),rs.getString(5),rs.getString(6),rs.getDouble(7),rs.getString(8));
            reservations.add(reservation);
        }

        return reservations;
    }

    public Reservation getReservationByID(String first,int last) throws Exception {
        Reservation reservation = null;
        Connection conn = JDBC.getConnection();
        String sql = "select * from reservation where first_id like ? and last_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,first);
        stmt.setInt(2,last);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            reservation = new Reservation(rs.getString(1),rs.getInt(2),getCustomerByID(rs.getInt(3)),getUserByID(rs.getInt(4)),rs.getString(5),rs.getString(6),rs.getDouble(7),rs.getString(8));
        }

        return reservation;
    }


    public RoomBooking getRBbyRes(Reservation reservation) throws Exception {
        RoomBooking roomBooking = null;
        Connection conn = JDBC.getConnection();
        String sql ="SELECT reservation.first_id,reservation.last_id,reservation.cus_ID,reservation.user_ID,reservation.status,reservation.payment_status,reservation.total_price,roombooking.start_date,roombooking.end_date,reservation.note\n" +
                "                FROM hotel.reservation inner join hotel.roombooking\n" +
                "                on ( reservation.first_id like roombooking.first_id and reservation.last_id = roombooking.last_id) where roombooking.last_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,reservation.getId());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            roomBooking = new RoomBooking(rs.getString(1),rs.getInt(2),reservation.getOwner()
                    ,reservation.getUser(),rs.getString(5),
                    rs.getString(6),rs.getDouble(7),rs.getString(8),
                    rs.getString(9),getContainByID(reservation.getId()),getUsesByID("RB",reservation.getId()),rs.getString(10));
        }
        conn.close();

        return roomBooking;
    }

    public BanquetBooking getBBbyRes(Reservation reservation) throws Exception {
        BanquetBooking banquetBooking = null;
        Connection conn = JDBC.getConnection();
        String sql = "SELECT reservation.first_id,reservation.last_id,reservation.cus_ID,reservation.user_ID,reservation.status,reservation.payment_status,reservation.total_price,banquetbooking.start_date,banquetbooking.start_hour,reservation.note\n" +
                "                FROM hotel.reservation join hotel.banquetbooking \n" +
                "                on ( reservation.first_id like banquetbooking.first_id and reservation.last_id = banquetbooking.last_id ) \n" +
                "                where banquetbooking.last_id = ? ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,reservation.getId());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            banquetBooking = new BanquetBooking(rs.getString(1),reservation.getId(),reservation.getOwner()
                    ,reservation.getUser(),rs.getString(5),
                    rs.getString(6),rs.getDouble(7),rs.getString(8),
                    rs.getString(9),getUsesByID("BB",reservation.getId()),rs.getString(10));
        }
        conn.close();

        return banquetBooking;
    }

    public void editReservation(String firstID,int id,String status,String paymentStatus,String note) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "update reservation " +
                "set status = ? , payment_status = ? ,note = ? where first_id like ? and last_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,status);
        stmt.setString(2,paymentStatus);
        stmt.setString(3,note);
        stmt.setString(4,firstID);
        stmt.setInt(5,id);
        stmt.executeUpdate();

        conn.close();
    }

    public ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "select  * from user";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
           User user = new User(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10));
            users.add(user);
        }
        conn.close();
        return users;
    }

    public void updateUser(int id,String name,String phone,String identifier,String username,String dob,String password,String role,String image,String status) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "update user " +
                "set name = ? , phone = ? ,identifier = ? , username = ?,dob = ?,password = ?,role = ?,image = ? ,status = ? " +
                "where user_ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setString(2,phone);
        stmt.setString(3,identifier);
        stmt.setString(4,username);
        stmt.setString(5,dob);
        stmt.setString(6,password);
        stmt.setString(7,role);
        stmt.setString(8,image);
        stmt.setString(9,status);
        stmt.setInt(10,id);

        stmt.executeUpdate();

        conn.close();
    }

    public void addUser(String name,String phone,String identifier,String username,String dob,String password,String role,String image,String status) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "insert into user(name,phone,identifier,username,dob,password,role,status,image) " +
                "values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setString(2,phone);
        stmt.setString(3,identifier);
        stmt.setString(4,username);
        stmt.setString(5,dob);
        stmt.setString(6,password);
        stmt.setString(7,role);
        stmt.setString(8,image);
        stmt.setString(9,status);

        stmt.executeUpdate();

        conn.close();
    }

    public void deleteUser(int id) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "delete from user where user_ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,id);
        stmt.executeUpdate();
        conn.close();
    }

    public void deleteRoom(int id) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "delete from room where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,id);
        stmt.executeUpdate();
        conn.close();
    }

    public void deleteService(int id) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "delete from service where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,id);
        stmt.executeUpdate();
        conn.close();
    }

    public void updateRoom(int id,String name,String status,String type,long capacity,double price,double sale,String image) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "update room " +
                "set name = ? , status = ? ,type = ? , capacity = ?,price = ?,sale = ?,image = ? " +
                "where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setString(2,status);
        stmt.setString(3,type);
        stmt.setLong(4,capacity);
        stmt.setDouble(5,price);
        stmt.setDouble(6,sale);
        stmt.setString(7,image);
        stmt.setInt(8,id);

        stmt.executeUpdate();

        conn.close();
    }

    public void addRoom(String name,String status,String type,long capacity,double price,double sale,String image) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "insert into room(name,status,type,capacity,price,sale,image) " +
                "values (?,?,?,?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setString(2,status);
        stmt.setString(3,type);
        stmt.setLong(4,capacity);
        stmt.setDouble(5,price);
        stmt.setDouble(6,sale);
        stmt.setString(7,image);

        stmt.executeUpdate();

        conn.close();
    }

    public void updateService(int id,String name,double price,String permission) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "update service " +
                "set name = ? ,price = ?,permission = ? " +
                "where id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setDouble(2,price);
        stmt.setString(3,permission);
        stmt.setInt(4,id);

        stmt.executeUpdate();

        conn.close();
    }

    public void addService(String name,double price,String permission) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "insert into service(name,price,permission) " +
                "values (?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,name);
        stmt.setDouble(2,price);
        stmt.setString(3,permission);

        stmt.executeUpdate();

        conn.close();
    }

    public ArrayList<Use> getPreparingUses() throws SQLException {
        ArrayList<Use> uses = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "select uses.ser_id,SUM(uses.amount) as amount \n" +
                "from uses \n" +
                "join reservation \n" +
                "on (uses.first_id like reservation.first_id and uses.last_id = reservation.last_id ) \n" +
                "where reservation.status like 'pending' or reservation.status like 'using' \n" +
                "group by uses.ser_id ";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            Use use = new Use(ManagerManagement.findService(String.valueOf(rs.getInt(1))),rs.getLong(2));
            uses.add(use);
        }

        return uses;
    }

    public ArrayList<User> getOnlineUser() throws SQLException {
        ArrayList<User> users = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "select * from user where status like ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,"online");
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            User user = new User(rs.getInt(1),rs.getString(2),rs.getString(3)
            ,rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)
            ,rs.getString(8),rs.getString(9),rs.getString(10));

            users.add(user);
        }

        return users;
    }

    public void updateLogInStatus(User user) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "update user set status = 'online' where user_ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,user.getId());

        stmt.executeUpdate();
    }

    public void updateLogOutStatus(User user) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "update user set status = 'offline' where user_ID = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1,user.getId());

        stmt.executeUpdate();
    }

    public void updateUse(String first,int last,Use use) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "update uses set amount = ? where first_id like ? and last_id = ? and ser_id = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setLong(1,use.getAmount());
        stmt.setString(2,first);
        stmt.setInt(3,last);
        stmt.setInt(4,use.getService().getId());
        stmt.executeUpdate();
    }

    public void insertUse(String first,int last,Use use) throws SQLException {
        Connection conn = JDBC.getConnection();
        String sql = "insert into uses(first_id,last_id,ser_id,amount) values (?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1,first);
        stmt.setInt(2,last);
        stmt.setInt(3,use.getService().getId());
        stmt.setLong(4,use.getAmount());

        stmt.executeUpdate();
    }

    public ArrayList<RoomBooking> getUnpaidReservation() throws Exception {
        ArrayList<RoomBooking> reservations = new ArrayList<>();
        Connection conn = JDBC.getConnection();
        String sql = "SELECT reservation.first_id,reservation.last_id,reservation.cus_ID,reservation.user_ID,reservation.status,reservation.payment_status,reservation.total_price,roombooking.start_date,roombooking.end_date,reservation.note \n" +
                "FROM hotel.reservation join hotel.roombooking \n" +
                "on ( reservation.first_id like 'RB' and reservation.last_id = roombooking.last_id and reservation.payment_status like 'pending' )";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()){
            int id = rs.getInt(2);
            RoomBooking reservation = new RoomBooking(rs.getString(1),id,getCustomerByID(rs.getInt(3))
                    ,getUserByID(rs.getInt(4)),rs.getString(5),
                    rs.getString(6),rs.getDouble(7),rs.getString(8),
                    rs.getString(9),getContainByID(id),getUsesByID("RB",id),rs.getString(10));
            reservations.add(reservation);
        }
        conn.close();

        return reservations;
    }
}

