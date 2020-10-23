package Main;


import Main.Enums.Type;
import Main.Models.HotelManagement;
import Main.Models.Navigation;
import Main.Models.Room;
import Main.Models.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        HotelManagement.rooms.add(new Room("1", 1, 30, Type.vip,"Room1.png",0.2,5));
        HotelManagement.rooms.add(new Room("2", 2, 60,Type.normal,"Room2.png",0.3,4));
        HotelManagement.rooms.add(new Room("3", 3, 80,Type.normal,"Room3.png",0.5,3));
        HotelManagement.rooms.add(new Room("4", 4, 120,Type.vip,"Room4.png",0.3,2));
        HotelManagement.rooms.add(new Room("5", 5, 130,Type.normal,"Room3.png",0.2,1));
        HotelManagement.rooms.add(new Room("6", 6, 140,Type.vip,"Room2.png",0,0));
        HotelManagement.services.add(new Service("1","Swimming"));
        HotelManagement.services.add(new Service("2","Message"));
        HotelManagement.services.add(new Service("3","Buffet"));
        HotelManagement.services.add(new Service("4","Bus"));
        HotelManagement.services.add(new Service("5","SkyDiving"));
        HotelManagement.services.add(new Service("6","ScubaDiving"));
        HotelManagement.services.add(new Service("7","Tour"));

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene( FXMLLoader.load(getClass().getResource("fxml/Login.fxml"))));
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
