package Main.GUIControllers.Manager;

import Main.Models.ManagerManagement;
import Main.Models.Navigation;
import Main.Models.RoomBookingManagement;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class SliderController {
    @FXML
    private VBox mainPane;

    @FXML
    void initialize(){

    }

    @FXML
    void hide(MouseEvent mouseEvent){
        Navigation.getManagerController().slideTransition();
    }

    @FXML
    void logout(ActionEvent actionEvent) throws IOException, SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("Confirm");
        alert.setContentText("Do you want to LOGOUT");
        Optional<ButtonType> option = alert.showAndWait();
        if (option.get() == ButtonType.OK){
            ManagerManagement.updateLogOutStatus();
            Navigation.logout(actionEvent);
        }
    }

    @FXML
    void editEmployee(ActionEvent event) throws IOException {
        Navigation.getManagerController().showEditEmployee();
        Navigation.getManagerController().slideTransition();
    }

    @FXML
    void editReservation(ActionEvent event)throws IOException {
        Navigation.getManagerController().showEditReservation();
        Navigation.getManagerController().slideTransition();
    }

    @FXML
    void editRoom(ActionEvent event)throws IOException {
        Navigation.getManagerController().showEditRoom();
        Navigation.getManagerController().slideTransition();
    }

    @FXML
    void editService(ActionEvent event)throws IOException {
        Navigation.getManagerController().showEditService();
        Navigation.getManagerController().slideTransition();
    }

    @FXML
    void editOwner(ActionEvent event)throws IOException {
        Navigation.getManagerController().showEditOwner();
        Navigation.getManagerController().slideTransition();
    }

    @FXML
    void goMain(ActionEvent event) throws IOException{
        Navigation.getManagerController().showMain();
        Navigation.getManagerController().slideTransition();
    }

}
