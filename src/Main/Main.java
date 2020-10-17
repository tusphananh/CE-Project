package Main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        HotelManagement.rooms.add(new Room("1", 1, 30,Type.vip,"Room1.png"));
        HotelManagement.rooms.add(new Room("2", 2, 60,Type.normal,"Room2.png"));
        HotelManagement.rooms.add(new Room("3", 3, 80,Type.normal,"Room3.png"));
        HotelManagement.rooms.add(new Room("4", 4, 120,Type.vip,"Room4.png"));
        HotelManagement.rooms.add(new Room("5", 5, 130,Type.normal,"Room3.png"));
        HotelManagement.rooms.add(new Room("6", 6, 140,Type.vip,"Room2.png"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene( FXMLLoader.load(Navigation.class.getResource("fxml/Login.fxml"))));
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
