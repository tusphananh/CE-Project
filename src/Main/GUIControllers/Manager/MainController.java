package Main.GUIControllers.Manager;

import Main.Models.ManagerManagement;
import Main.Models.Room;
import Main.Models.Use;
import Main.Models.User;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainController {
    private ArrayList<Room> pendingRooms = new ArrayList<>();
    private ArrayList<Room> validRooms = new ArrayList<>();
    private ArrayList<Room> usingRooms = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    @FXML
    private FlowPane roomStack,employeeFlowPane;

    @FXML
    private VBox serviceStack;

    @FXML
    void initialize() throws SQLException {
        loadRoomStack();
        loadServiceStack();
        loadEmployeeStack();
    }

    private void loadUsingRooms() throws SQLException {
        usingRooms.clear();
        usingRooms = ManagerManagement.getUsingRooms();
    }

    private void loadPendingRooms() throws SQLException {
        pendingRooms.clear();
        pendingRooms = ManagerManagement.getPendingRooms();
        pendingRooms.removeAll(usingRooms);
    }

    private void loadValidRooms() throws SQLException {
        validRooms.clear();
        validRooms = ManagerManagement.getRooms();
        validRooms.removeAll(pendingRooms);
        validRooms.removeAll(usingRooms);
    }

    private void loadOnlineUser() throws SQLException {
        users.clear();
        users.addAll(ManagerManagement.getOnlineUsers());
    }

    private void loadRooms() throws SQLException {
        loadUsingRooms();
        loadPendingRooms();
        loadValidRooms();
    }

    private void loadRoomStack() throws SQLException {
        loadRooms();
        roomStack.getChildren().clear();
        for (Room room: validRooms
             ) {
            Label label = new Label(room.getName());
            label.setTextFill(Paint.valueOf("white"));
            label.setFont(Font.font("Arial", FontWeight.BOLD,15 ));
            label.setStyle("-fx-background-radius: 10;-fx-background-color: #8AFF70;-fx-padding: 10,10,10,10");
            roomStack.getChildren().add(label);
        }

        for (Room room: pendingRooms
        ) {
            Label label = new Label(room.getName());
            label.setTextFill(Paint.valueOf("white"));
            label.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            label.setStyle("-fx-background-radius: 10;-fx-background-color: #72F2FF;-fx-padding: 10,10,10,10");
            roomStack.getChildren().add(label);
        }

        for (Room room: usingRooms
        ) {
            Label label = new Label(room.getName());
            label.setTextFill(Paint.valueOf("white"));
            label.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            label.setStyle("-fx-background-radius: 10;-fx-background-color: #FF7070;-fx-padding: 10,10,10,10");
            roomStack.getChildren().add(label);
        }
    }

    private void loadServiceStack() throws SQLException {
        serviceStack.getChildren().clear();
        for (Use use: ManagerManagement.getPreparingUses()
             ) {
            Label label = new Label(  use.getService().getPermission() + " - " + use.getService().getName() + " : " + use.getAmount() );
            label.setStyle("-fx-padding: 10,10,10,10");
            serviceStack.getChildren().add(label);
        }
    }

    private void loadEmployeeStack() throws SQLException {
        loadOnlineUser();
        employeeFlowPane.getChildren().clear();
        for (User user : users
             ) {
            Label label = new Label(  user.getRole() + "\n" + user.getName());
            label.setTextFill(Paint.valueOf("white"));
            label.setFont(Font.font("Arial", FontWeight.BOLD, 15));
            label.setTextAlignment(TextAlignment.CENTER);
            label.setStyle("-fx-background-radius: 10;-fx-background-color: #8AFF70;-fx-padding: 10,10,10,10");
            employeeFlowPane.getChildren().add(label);
        }
    }
}
