package Main;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    HotelManagement hotelManagement = new HotelManagement();

    @Override
    public void start(Stage primaryStage) throws IOException {
        hotelManagement.rooms.add(new Room("1", 1, 200000,Room.Type.vip));
        hotelManagement.rooms.add(new Room("2", 2, 300000,Room.Type.normal));
        hotelManagement.rooms.add(new Room("3", 3, 400000,Room.Type.normal));
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
