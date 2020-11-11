package Main.GUIControllers;

import Main.Models.Navigation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.w3c.dom.Node;

public class SliderController  {
    @FXML
    private VBox mainPane;

    @FXML
    void initialize(){

    }

    @FXML
    void hide(MouseEvent mouseEvent){
        Navigation.getEmployeeController().slideTransition();
    }

    @FXML
    void booking(ActionEvent actionEvent){
        Navigation.getEmployeeController().slideTransition();
        Navigation.getEmployeeController().showBooking();
    }

    @FXML
    void checkin(ActionEvent actionEvent){
        Navigation.getEmployeeController().slideTransition();
        Navigation.getEmployeeController().showCheckIn();
    }
    @FXML
    void checkout(ActionEvent actionEvent){
        Navigation.getEmployeeController().slideTransition();
        Navigation.getEmployeeController().showCheckOut();
    }
    @FXML
    void restaurant(ActionEvent actionEvent){
        Navigation.getEmployeeController().slideTransition();
        Navigation.getEmployeeController().showRestaurant();
    }

}
