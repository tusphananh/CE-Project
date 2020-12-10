package Main.GUIControllers.Employee;

import Main.Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;

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
    void logout(ActionEvent actionEvent) throws IOException {
        Navigation.logout(actionEvent);
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

}
