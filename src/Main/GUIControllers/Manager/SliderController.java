package Main.GUIControllers.Manager;

import Main.Models.Navigation;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

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
    void logout(ActionEvent actionEvent) throws IOException, InterruptedException {
        Navigation.logout(actionEvent);
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
