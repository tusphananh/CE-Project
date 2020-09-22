package Main;

import java.util.Scanner;


public class TestCase {
    static HotelManagement hotelManagement ;
    public static void main(String[] args) throws Exception {
        hotelManagement = new HotelManagement();
        hotelManagement.rooms.add(new Room("1", 1, 70000, 200000));
        hotelManagement.rooms.add(new Room("2", 2, 100000, 300000));
        hotelManagement.rooms.add(new Room("3", 3, 150000, 400000));
        for (Identifier r : hotelManagement.rooms
        ) {
            System.out.println(r);
        }
        String dateStart,dateStop,name,phone,userID,roomID ;
        Room room;
        Owner owner;

        System.out.println("Reserve : r");
        System.out.println("Checkout: co");
        System.out.println("Exit: exit");
        System.out.println("List Reservations: l");

        Scanner scanner = new Scanner(System.in);
        while (true){
            String message = scanner.nextLine();
            switch (message){
                case "r" :
                    System.out.println("From:");
                    dateStart = scanner.nextLine();
                    System.out.println("To:");
                    dateStop = scanner.nextLine();
                    System.out.println("Name:");
                    name = scanner.nextLine();
                    System.out.println("Phone:");
                    phone = scanner.nextLine();
                    System.out.println("UserID:");
                    userID = scanner.nextLine();
                    System.out.println("RoomID:");
                    roomID = scanner.nextLine();
                    room = hotelManagement.findRoom(roomID);
                    owner = new Owner(name,phone,userID);
                    hotelManagement.addReservation(room,dateStart,dateStop,owner);
                    break;
                case "l" :
                    for (Identifier r: hotelManagement.reservations
                         ) {
                        System.out.println(r.toString());
                    }
                    break;
                case "exit":
                    System.exit(-1);
                    break;
                case "co":
                    break;
                default:
                    System.out.println("None syntax");
                    break;
            }
        }
    }
}
