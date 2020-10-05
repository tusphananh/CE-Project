package Main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        HotelManagement.rooms.add(new Room("1", 1, 200000,Type.vip));
        HotelManagement.rooms.add(new Room("2", 2, 300000,Type.normal));
        HotelManagement.rooms.add(new Room("3", 3, 400000,Type.normal));
        HotelManagement.rooms.add(new Room("4", 4, 500000,Type.vip));
        HotelManagement.rooms.add(new Room("5", 5, 600000,Type.normal));
        HotelManagement.rooms.add(new Room("6", 6, 700000,Type.vip));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(new Scene( FXMLLoader.load(Navigation.class.getResource("Login.fxml"))));
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
