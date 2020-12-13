package Main.GUIControllers.Employee;

import Main.Models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class SliderController  {
    @FXML
    private VBox mainPane;

    @FXML
    void initialize(){

    }

    @FXML
    void hide(MouseEvent mouseEvent){
        Navigation.getDayPickController().slideTransition();
    }

    @FXML
    void logout(ActionEvent actionEvent) throws IOException, SQLException {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.initOwner(primaryStage);
        alert.setTitle("Logout");
        alert.setHeaderText("Confirm");
        alert.setContentText("Do you want to LOGOUT");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK){
            RoomBookingManagement.updateLogOutStatus();
            Navigation.logout(actionEvent);
        }
    }

    @FXML
    void banquetCheckIn(ActionEvent actionEvent) throws IOException {
        Navigation.getDayPickController().slideTransition();
        Navigation.getDayPickController().showBanquetCheckIn();
    }

    @FXML
    void booking(ActionEvent actionEvent) throws IOException {
        Navigation.getDayPickController().slideTransition();
        Navigation.getDayPickController().showBooking();
    }

    @FXML
    void checkin(ActionEvent actionEvent) throws IOException {
        Navigation.getDayPickController().slideTransition();
        Navigation.getDayPickController().showCheckIn();
    }
    @FXML
    void checkout(ActionEvent actionEvent) throws IOException {
        Navigation.getDayPickController().slideTransition();
        Navigation.getDayPickController().showCheckOut();
    }
    @FXML
    void restaurant(ActionEvent actionEvent) throws IOException {
        Navigation.getDayPickController().slideTransition();
        Navigation.getDayPickController().showRestaurant();
    }

    @FXML
    void service(ActionEvent actionEvent) throws IOException {
        Navigation.getDayPickController().slideTransition();
        Navigation.getDayPickController().showServicePane();
    }

}
