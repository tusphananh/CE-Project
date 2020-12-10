package Main;

import Main.Database.JDBC;
import Main.Models.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.SQLException;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene( FXMLLoader.load(getClass().getResource("fxml/Login.fxml"))));
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();

    }

    public static void main(String[] args) throws SQLException {
        if (JDBC.checkJDBC()){
            setUp();
            launch(args);
        }
    }
    private static void setUp() throws SQLException {
        RoomBookingManagement.loadRoom();
        RoomBookingManagement.loadService();
    }
}
