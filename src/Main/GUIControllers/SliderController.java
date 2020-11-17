package Main.GUIControllers;

import Main.Models.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

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
    void booking(ActionEvent actionEvent){
        Navigation.getDayPickController().slideTransition();
        Navigation.getDayPickController().showBooking();
    }

    @FXML
    void checkin(ActionEvent actionEvent){
        Navigation.getDayPickController().slideTransition();
        Navigation.getDayPickController().showCheckIn();
    }
    @FXML
    void checkout(ActionEvent actionEvent){
        Navigation.getDayPickController().slideTransition();
        Navigation.getDayPickController().showCheckOut();
    }
    @FXML
    void restaurant(ActionEvent actionEvent){
        Navigation.getDayPickController().slideTransition();
        Navigation.getDayPickController().showRestaurant();
    }

}
