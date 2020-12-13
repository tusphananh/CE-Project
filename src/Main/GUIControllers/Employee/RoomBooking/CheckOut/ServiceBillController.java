package Main.GUIControllers.Employee.RoomBooking.CheckOut;

import Main.Models.Navigation;
import Main.Models.Owner;
import Main.Models.RoomBooking;
import Main.Models.RoomBookingManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ServiceBillController {
    private RoomBooking reservation;

    @FXML
    private Label total;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox vstack;

    @FXML
    void close(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.close();
    }

    @FXML
    void confirm(ActionEvent actionEvent) throws Exception {
        RoomBookingManagement.updateStatus("success","success",reservation.getId());
        Owner owner = reservation.getOwner();
        owner.setCoins(owner.getCoins() + (int) reservation.getTotalPrice());
        RoomBookingManagement.updateCoin(owner);
        Navigation.getCheckOutController().loadStack();
        close(actionEvent);
    }
    public void setTotal(String total) {
        this.total.setText(total + " $");
    }

    public void addStack(Parent parent){
        vstack.getChildren().add(parent);
    }

    public void setReservation(RoomBooking reservation) {
        this.reservation = reservation;
    }
}
