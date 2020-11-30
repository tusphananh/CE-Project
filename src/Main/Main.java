package Main;

import Main.Models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        HotelManagement.rooms.add(new Room("1", 1, 30, "vip","Room1.png",0.2,5));
        HotelManagement.rooms.add(new Room("2", 2, 60,"normal","Room2.png",0.3,4));
        HotelManagement.rooms.add(new Room("3", 3, 80,"normal","Room3.png",0.5,3));
        HotelManagement.rooms.add(new Room("4", 4, 120,"vip","Room4.png",0.3,2));
        HotelManagement.rooms.add(new Room("5", 5, 130,"normal","Room3.png",0.2,1));
        HotelManagement.rooms.add(new Room("6", 6, 140,"vip","Room2.png",0,0));
        HotelManagement.services.add(new Service("1","Swimming",60.5));
        HotelManagement.services.add(new Service("2","Message",60));
        HotelManagement.services.add(new Service("3","Buffet",119.99));
        HotelManagement.services.add(new Service("4","Bus",20));
        HotelManagement.services.add(new Service("5","SkyDiving",1000));
        HotelManagement.services.add(new Service("6","ScubaDiving",500));
        HotelManagement.services.add(new Service("7","Tour",499.99));
        HotelManagement.pendingReservations.add(new Reservation(1,"11-11-2020 14","12-11-2020 12",new Owner("anh tu","123"),HotelManagement.rooms,null));
        
        primaryStage.setScene(new Scene( FXMLLoader.load(getClass().getResource("fxml/Login.fxml"))));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
